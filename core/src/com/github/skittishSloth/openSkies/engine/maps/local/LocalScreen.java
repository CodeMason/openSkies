/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.skittishSloth.openSkies.engine.maps.local;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.github.skittishSloth.openSkies.OpenSkies;
import com.github.skittishSloth.openSkies.engine.player.Player;
import com.github.skittishSloth.openSkies.engine.player.PositionInformation;
import com.github.skittishSloth.openSkies.engine.ui.AbstractScreen;

/**
 *
 * @author mcory01
 */
public class LocalScreen extends AbstractScreen {

    public LocalScreen(OpenSkies game) {
        super(game);
        
        mapManager.addMap("first_world", "gfx/maps/first_world.tmx");
        currentMap = mapManager.getMap("first_world");
        camera = OrthographicCamera.class.cast(getStage().getCamera());
        

        final float w = Gdx.graphics.getWidth();
        final float h = Gdx.graphics.getHeight();
        camera.setToOrtho(false, w, h);
        
        player = new Player();
        
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
        final float width = player.getWidth();
        final float collisionHeight = player.getHeight() / 2;
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

        // calc min/max camera points inside the map
        final float minCameraX = camera.zoom * (camera.viewportWidth / 2);
        final float maxCameraX = worldSizeWidth - minCameraX;
        final float minCameraY = camera.zoom * (camera.viewportHeight / 2);
        final float maxCameraY = worldSizeHeight - minCameraY;

        final PositionInformation playerPos = player.getPositionInformation();

        float charX = playerPos.getX();
        float charY = playerPos.getY();

        camera.position.set(
                Math.min(maxCameraX, Math.max(charX, minCameraX)),
                Math.min(maxCameraY, Math.max(charY, minCameraY)),
                0
        );

        camera.update();
    }
    
    private void handleCollisions() {
        final float width = player.getWidth();
        final float collisionHeight = player.getHeight() / 2;

        final PositionInformation playerPos = player.getPositionInformation();
        float charX = playerPos.getX();
        float charY = playerPos.getY();
        final PositionInformation previousPosition = player.getPreviousPosition();
        float prevX = previousPosition.getX();
        float prevY = previousPosition.getY();
        if (currentMap.isCollision(charX, charY, width, collisionHeight) || currentMap.isOutOfBounds(charX, charY, width, collisionHeight)) {
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

//        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
//            performAction();
//        }

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

        if (player.isAllAnimationFinished()) {
            player.setMoving(false);
        }
    }

    private final OrthogonalTiledMapRendererWithSprites mapRenderer;
    private final TiledMapManager mapManager = new TiledMapManager();
    
    private ManagedMap currentMap = null;

    private boolean inTransition;
    
    private final Player player;

    private final OrthographicCamera camera;

}
