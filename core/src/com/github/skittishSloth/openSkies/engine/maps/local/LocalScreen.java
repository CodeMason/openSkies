/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.maps.local;

import com.github.skittishSloth.openSkies.engine.maps.npcs.NPC;
import com.github.skittishSloth.openSkies.engine.lighting.LightTile;
import box2dLight.Light;
import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.github.skittishSloth.openSkies.OpenSkies;
import com.github.skittishSloth.openSkies.engine.lighting.LightingAnimation;
import com.github.skittishSloth.openSkies.engine.lighting.LightingAnimationFactory;
import com.github.skittishSloth.openSkies.engine.maps.areas.AreaDetails;
import com.github.skittishSloth.openSkies.engine.maps.areas.MapDetails;
import com.github.skittishSloth.openSkies.engine.maps.npcs.NPCDetails;
import com.github.skittishSloth.openSkies.engine.player.Player;
import com.github.skittishSloth.openSkies.engine.player.PlayerGraphics;
import com.github.skittishSloth.openSkies.engine.player.PositionInformation;
import com.github.skittishSloth.openSkies.engine.player.details.CharacterData;
import com.github.skittishSloth.openSkies.engine.ui.AbstractScreen;
import com.github.skittishSloth.openSkies.engine.ui.dialog.BaseDialog;
import com.github.skittishSloth.openSkies.engine.ui.dialog.DialogOption;
import com.github.skittishSloth.openSkies.engine.ui.maps.MapAssets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author mcory01
 */
public class LocalScreen extends AbstractScreen {
    
    private static final Color DEFAULT_AMBIENT_COLOR = new Color(0.5f, 0.5f, 0.5f, 1.0f);

    public LocalScreen(final OpenSkies game, final MapAssets mapAssets) {
        super(game);

        this.mapManager = new TiledMapManager(mapAssets);
        currentArea = game.getCurrentArea();

        this.npcDetails = game.getNPCDetails();
        
        camera = OrthographicCamera.class.cast(getStage().getCamera());

        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        camera.setToOrtho(false, width, height);
        camera.update();

        playerGraphicsAssets = new AssetManager();

        final CharacterData currentCharacterData = game.getCurrentCharacter();
        player = new Player(currentCharacterData);

        playerGraphics = new PlayerGraphics(playerGraphicsAssets);
        player.setPlayerGraphics(playerGraphics);

        uiStage = new Stage();
        uiCamera = OrthographicCamera.class.cast(uiStage.getCamera());
        uiCamera.setToOrtho(false, width, height);
        uiCamera.update();
        dialog = new BaseDialog("Dialog", getSkin());
        dialog.setVisible(false);

        world = new World(new Vector2(), false);
        RayHandler.setGammaCorrection(true);
        RayHandler.useDiffuseLight(true);
        rayHandler = new RayHandler(world);
        rayHandler.setAmbientLight(DEFAULT_AMBIENT_COLOR);
        rayHandler.setCulling(true);
        rayHandler.setBlur(true);
        rayHandler.setBlurNum(3);

        fade = new FadeInOutEffect(getStage());
    }

    public void setCurrentArea(final AreaDetails areaDetails) {
        this.currentArea = areaDetails;
    }

    @Override
    public void show() {
        super.show();

        if (currentMap == null) {
            initializeMap();
        }
    }

    @Override
    public void render(float delta) {
        // clear the screen with the given RGB color (black)
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (!inTransition) {
            handleMovement(delta);

            handleCollisions();

            updateMap(delta);

            handleTransition(delta);
        }

        updateCamera();

        mapRenderer.setView(camera);
        mapRenderer.render();

        getStage().act(delta);
        getStage().draw();

        handleLightAnimation(delta);
        rayHandler.setCombinedMatrix(camera.combined);
        rayHandler.updateAndRender();

        if (dialog.isVisible()) {
            uiStage.act(delta);
            uiStage.draw();
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        mapManager.dispose();
        world.dispose();
        rayHandler.dispose();
    }

    private void handleLightAnimation(final float deltaTime) {
        for (final LightingAnimation anim : lightingAnimations.values()) {
            anim.update(deltaTime);
        }
    }

    private void updateMap(final float deltaTime) {
        currentMap.updatePlayer(player, deltaTime);
        currentMap.updateNPCs(deltaTime);
        currentMap.updateItems();
    }

    private void handleTransition(final float deltaTime) {
        final PositionInformation playerPos = player.getPositionInformation();

        float charX = playerPos.getX();
        float charY = playerPos.getY();
        final float playerGraphicsWidth = playerGraphics.getWidth();
        final float collisionHeight = playerGraphics.getHeight() / 2;
        final Transition nextMap = currentMap.getTransition(charX, charY, playerGraphicsWidth, collisionHeight);
        if (nextMap != null) {
            inTransition = true;

            final Runnable afterFadeOut = new Runnable() {
                @Override
                public void run() {
                    final String prevMap = currentMap.getName();
                    currentMap.removePlayer();
                    initializeMap(nextMap.getMapName(), prevMap, nextMap.getIndex(), deltaTime);
                    log.debug("Current map name: {}", currentMap.getName());
                }
            };

            final Runnable afterFadeIn = new Runnable() {
                @Override
                public void run() {
                    inTransition = false;
                    log.debug("Transition finished.");
                }
            };

            fade.runEffect(afterFadeOut, afterFadeIn);
        }
    }

    private void updateCamera() {
        updateCamera(camera);
    }

    private void updateCamera(final OrthographicCamera theCamera) {
        // calc total map size
        final int worldSizeWidth = currentMap.getWorldWidth();
        final int worldSizeHeight = currentMap.getWorldHeight();
        final float viewportWidth = theCamera.viewportWidth;
        final float viewportHeight = theCamera.viewportHeight;

        if ((worldSizeWidth < viewportWidth) && (worldSizeHeight < viewportHeight)) {
            theCamera.position.set(worldSizeWidth / 2, worldSizeHeight / 2, 0);
        } else {
            // calc min/max camera points inside the map
            final float minCameraX = theCamera.zoom * (viewportWidth / 2);
            final float maxCameraX = worldSizeWidth - minCameraX;
            final float minCameraY = theCamera.zoom * (viewportHeight / 2);
            final float maxCameraY = worldSizeHeight - minCameraY;

            final PositionInformation playerPos = player.getPositionInformation();

            float charX = playerPos.getX();
            float charY = playerPos.getY();

            theCamera.position.set(
                    Math.min(maxCameraX, Math.max(charX, minCameraX)),
                    Math.min(maxCameraY, Math.max(charY, minCameraY)),
                    0
            );
        }

        theCamera.update();
    }

    private void handleCollisions() {
        final float collisionWidth = 32;
        final float collisionHeight = playerGraphics.getHeight() / 2;

        final PositionInformation playerPos = player.getPositionInformation();
        float charX = playerPos.getX();
        float charY = playerPos.getY();
        final PositionInformation previousPosition = player.getPreviousPosition();
        float prevX = previousPosition.getX();
        float prevY = previousPosition.getY();
        if (currentMap.isCollision(charX + (collisionWidth / 2), charY, collisionWidth, collisionHeight) || currentMap.isOutOfBounds(charX, charY, collisionWidth, collisionHeight)) {
            charX = prevX;
            charY = prevY;
        }

        playerPos.setX(charX);
        playerPos.setY(charY);
    }

    private void handleMovement(final float deltaTime) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
            player.takeHit(50);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            performAction();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player.moveWest(deltaTime);
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.moveEast(deltaTime);
        } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            player.moveNorth(deltaTime);
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            player.moveSouth(deltaTime);
        } else {
            player.setMoving(false);
        }

        if (playerGraphics.isAllAnimationFinished()) {
            player.setMoving(false);
        }
    }

    private void performAction() {
        // based on the player's position and facing direction,
        // get any items within x tiles from their current position
        // in the direction they're facing.
        final Item item = currentMap.getNearbyItems(player);
        if (item != null) {
            if (item.isActionPerformed()) {
                return;
            }

            if (!item.isAlive()) {
                return;
            }

            displayItemContents(item);
            item.setActionPerformed(true);
        }

        final NPC npc = currentMap.getNearbyNPC(player);
        if (npc != null) {
            log.debug("NPC found...");
        }
    }

    private void displayItemContents(final Item item) {
        final String contains = item.getContains();
        final Rectangle itemRect = item.getRectangle();
        final Label lbl = new Label("You just got " + contains, getSkin());
        lbl.setColor(1, 1, 1, 0);
        lbl.setX(itemRect.x);
        lbl.setY(itemRect.y);
        lbl.addAction(Actions.sequence(Actions.fadeIn(0.5f), Actions.delay(1.0f), Actions.fadeOut(0.5f)));
        getStage().addActor(lbl);
        log.debug("You just got ", contains);
    }

    private void displayNPCDialog(final String dialogText) {
        if (dialog.isVisible()) {
            return;
        }

        dialog.setText(dialogText);
        final DialogOption option = new DialogOption("Okay", getSkin());
        option.addListener(new ClickListener() {
            ;
            @Override
            public void clicked(InputEvent event, float x, float y) {
                log.debug("Clicked!");
                dialog.setVisible(false);
                uiStage.getActors().removeValue(dialog, false);
                Gdx.input.setInputProcessor(getStage());
            }
        });

        dialog.setOptions(option);
        dialog.setVisible(true);
        uiStage.addActor(dialog);
        Gdx.input.setInputProcessor(uiStage);
    }

    private void initializeMap() {
        initializeMap(null, null, null, 0);
    }

    private void initializeMap(final String nextMap, final String prevMap, final Integer mapIndex, final float deltaTime) {
        for (final MapDetails md : currentArea.getMaps()) {
            mapManager.addMap(md.getName(), "gfx/maps/" + md.getRelativePath(), npcDetails, md);
        }

        final String mapName;
        if (StringUtils.isBlank(nextMap)) {
            mapName = currentArea.getStartingMap();
        } else {
            mapName = nextMap;
        }

        currentMap = mapManager.getMap(mapName);
        currentMap.initializePlayer(prevMap, mapIndex, deltaTime, player);

        if (mapRenderer == null) {
            mapRenderer = new OrthogonalTiledMapRendererWithSprites(currentMap, getStage().getBatch());
        } else {
            mapRenderer.setMap(currentMap);
        }

        final Color ambientLight;
        if (currentMap.isAmbientLightDefined()) {
            log.debug("Current map had ambient light.");
            ambientLight = currentMap.getAmbientLight();
        } else {
            log.debug("Current map DID NOT HAVE ambient light.");
            ambientLight = DEFAULT_AMBIENT_COLOR;
        }
        rayHandler.setAmbientLight(ambientLight);
        rayHandler.removeAll();
        currentLights.clear();
        lightingAnimations.clear();
        lightingTilesByName.clear();
        final List<LightTile> lights = currentMap.getLights();
        for (final LightTile light : lights) {
            final Rectangle rect = light.getRectangle();
            final Vector2 origin = new Vector2();
            rect.getCenter(origin);
            final Light pointLight = new PointLight(rayHandler, 128);
            pointLight.setColor(1.0f, 1.0f, 1.0f, 1.0f);

            pointLight.setPosition(origin);
            pointLight.setDistance(26f);
            pointLight.setSoft(false);
            pointLight.setStaticLight(true);
            currentLights.put(origin, pointLight);
            final LightingAnimation anim = LightingAnimationFactory.createAnimation(light, pointLight);
            if (anim != null) {
                lightingAnimations.put(light, anim);
            }
            
            lightingTilesByName.put(light.getName(), light);
        }
    }

    private final Stage uiStage;
    private OrthogonalTiledMapRendererWithSprites mapRenderer;
    private final TiledMapManager mapManager;

    private ManagedMap currentMap = null;

    private boolean inTransition;

    private AreaDetails currentArea;

    private final Player player;

    private final OrthographicCamera camera, uiCamera;

    private final AssetManager playerGraphicsAssets;
    private final PlayerGraphics playerGraphics;

    private final BaseDialog dialog;

    private final float width, height;

    private final World world;
    private final RayHandler rayHandler;

    private final Map<Vector2, Light> currentLights = new HashMap<Vector2, Light>();
    private final Map<LightTile, LightingAnimation> lightingAnimations = new HashMap<LightTile, LightingAnimation>();
    private final Map<String, LightTile> lightingTilesByName = new HashMap<String, LightTile>();

    private final FadeInOutEffect fade;
    
    private final Map<String, NPCDetails> npcDetails;
}
