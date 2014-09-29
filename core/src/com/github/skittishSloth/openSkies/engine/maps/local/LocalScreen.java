/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.skittishSloth.openSkies.engine.maps.local;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.github.skittishSloth.openSkies.OpenSkies;
import com.github.skittishSloth.openSkies.engine.player.Player;
import com.github.skittishSloth.openSkies.engine.player.PlayerGraphics;
import com.github.skittishSloth.openSkies.engine.player.PositionInformation;
import com.github.skittishSloth.openSkies.engine.player.details.CharacterData;
import com.github.skittishSloth.openSkies.engine.ui.AbstractScreen;

/**
 *
 * @author mcory01
 */
public class LocalScreen extends AbstractScreen {

    public LocalScreen(OpenSkies game) {
        super(game);
        
        mapManager.addMap("island", "gfx/maps/prologue/island.tmx");
        currentMap = mapManager.getMap("island");
        camera = OrthographicCamera.class.cast(getStage().getCamera());
        

        final float w = Gdx.graphics.getWidth();
        final float h = Gdx.graphics.getHeight();
        camera.setToOrtho(false, w, h);
        camera.update();
        
        
        playerGraphicsAssets = new AssetManager();
        
        final CharacterData currentCharacterData = game.getCurrentCharacter();
        player = new Player(currentCharacterData);
        
        playerGraphics = new PlayerGraphics(playerGraphicsAssets);
        player.setPlayerGraphics(playerGraphics);
        
        currentMap.initializePlayer(null, null, 0, player);
        mapRenderer = new OrthogonalTiledMapRendererWithSprites(currentMap, getStage().getBatch());
        
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
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
    }
    
    private void updateMap(final float deltaTime) {
        currentMap.updatePlayer(player, deltaTime);
        currentMap.updateItems();
    }

    private void handleTransition(final float deltaTime) {
        final PositionInformation playerPos = player.getPositionInformation();
        
        float charX = playerPos.getX();
        float charY = playerPos.getY();
        final float width = playerGraphics.getWidth();
        final float collisionHeight = playerGraphics.getHeight() / 2;
        final Transition nextMap = currentMap.getTransition(charX, charY, width, collisionHeight);
        if (nextMap != null) {
            inTransition = true;

            final FadeInOutEffect fade = new FadeInOutEffect(getStage());
            final Runnable afterFadeOut = new Runnable() {
                @Override
                public void run() {
                    final String prevMap = currentMap.getName();
                    currentMap.removePlayer();
                    currentMap = mapManager.getMap(nextMap.getMapName());
                    currentMap.initializePlayer(prevMap, nextMap.getIndex(), deltaTime, player);
                    mapRenderer.setMap(currentMap);
                }
            };

            final Runnable afterFadeIn = new Runnable() {
                @Override
                public void run() {
                    inTransition = false;
                }
            };

            fade.runEffect(afterFadeOut, afterFadeIn);
        }
    }
    
    private void updateCamera() {
        // calc total map size
        final int worldSizeWidth = currentMap.getWorldWidth();
        final int worldSizeHeight = currentMap.getWorldHeight();
        final float viewportWidth = camera.viewportWidth;
        final float viewportHeight = camera.viewportHeight;
        
        if ((worldSizeWidth < viewportWidth) && (worldSizeHeight < viewportHeight)) {
            camera.position.set(worldSizeWidth / 2, worldSizeHeight / 2, 0);
        } else {
            // calc min/max camera points inside the map
            final float minCameraX = camera.zoom * (viewportWidth / 2);
            final float maxCameraX = worldSizeWidth - minCameraX;
            final float minCameraY = camera.zoom * (viewportHeight / 2);
            final float maxCameraY = worldSizeHeight - minCameraY;

            final PositionInformation playerPos = player.getPositionInformation();

            float charX = playerPos.getX();
            float charY = playerPos.getY();

            camera.position.set(
                    Math.min(maxCameraX, Math.max(charX, minCameraX)),
                    Math.min(maxCameraY, Math.max(charY, minCameraY)),
                    0
            );
        }

        camera.update();
    }
    
    private void handleCollisions() {
        final float width = 32;
        final float collisionHeight = playerGraphics.getHeight() / 2;

        final PositionInformation playerPos = player.getPositionInformation();
        float charX = playerPos.getX();
        float charY = playerPos.getY();
        final PositionInformation previousPosition = player.getPreviousPosition();
        float prevX = previousPosition.getX();
        float prevY = previousPosition.getY();
        if (currentMap.isCollision(charX + (width / 2), charY, width, collisionHeight) || currentMap.isOutOfBounds(charX, charY, width, collisionHeight)) {
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

        if (Gdx.input.isKeyJustPressed(Input.Keys.A)) {
            player.attack();
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
        if (item == null) {
            return;
        }

        if (item.isActionPerformed()) {
            return;
        }

        if (!item.isAlive()) {
            return;
        }

        displayItemContents(item);
        item.setActionPerformed(true);
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
        System.err.println("You just got " + contains);
    }

    private final OrthogonalTiledMapRendererWithSprites mapRenderer;
    private final TiledMapManager mapManager = new TiledMapManager();
    
    private ManagedMap currentMap = null;

    private boolean inTransition;
    
    private final Player player;

    private final OrthographicCamera camera;

    private final AssetManager playerGraphicsAssets;
    private final PlayerGraphics playerGraphics;
}
