/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.github.skittishSloth.openSkies.OpenSkies;
import com.github.skittishSloth.openSkies.engine.common.GdxUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author mcory01
 */
public abstract class AbstractScreen implements Screen {

    protected AbstractScreen(final OpenSkies game) {
        this.game = game;
        this.stage = new Stage();
        
        final int width = Gdx.graphics.getWidth();
        final int height = Gdx.graphics.getHeight();
        final Viewport viewport = new ExtendViewport(width, height, getStage().getCamera());
        getStage().setViewport(viewport);
        Gdx.input.setInputProcessor(getStage());
        
        log = LoggerFactory.getLogger(getClass());
    }

    protected final String getName() {
        return getClass().getSimpleName();
    }

    public SpriteBatch getBatch() {
        if (batch == null) {
            batch = new SpriteBatch();
        }
        return batch;
    }

    // Screen implementation
    @Override
    public void show() {
        log.debug("Showing screen: {}", getName());

        // set the stage as the input processor
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void resize(final int width, final int height) {
        log.debug("Resizing screen {} to {} x {}.", getName(), width, height);
        getStage().getViewport().update(width, height);
    }

    @Override
    public void render(final float delta) {
        // (1) process the game logic

        // update the actors
        stage.act(delta);

        // (2) draw the result
        // clear the screen with the given RGB color (black)
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // draw the actors
        stage.draw();
    }

    @Override
    public void hide() {
        log.debug("Hiding screen {}", getName());
    }

    @Override
    public void pause() {
        log.debug("Pausing screen {}", getName());
    }

    @Override
    public void resume() {
        log.debug("Resuming screen {}", getName());
    }

    @Override
    public void dispose() {
        log.debug("Disposing screen {}", getName());
        GdxUtils.safeDispose(stage);
        GdxUtils.safeDispose(font);
        GdxUtils.safeDispose(batch);
        GdxUtils.safeDispose(skin);
    }

    protected Skin getSkin() {
        if (skin == null) {
            final FileHandle skinFile = Gdx.files.internal("gfx/ui/skins/uiskin.json");
            skin = new Skin(skinFile);
        }
        return skin;
    }

    protected final Stage getStage() {
        return stage;
    }

    protected final OpenSkies getGame() {
        return game;
    }

    private final OpenSkies game;
    private final Stage stage;

    private Skin skin;
    private BitmapFont font;
    private SpriteBatch batch;
    
    protected final Logger log;
}
