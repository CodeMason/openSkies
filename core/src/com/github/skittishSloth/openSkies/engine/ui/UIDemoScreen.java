/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.skittishSloth.openSkies.engine.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Disposable;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author mcory01
 */
public class UIDemoScreen implements Screen, Disposable {
    
    public UIDemoScreen() {
        this.stage = new Stage();
        this.skin = new Skin(Gdx.files.internal("gfx/ui/skins/uiskin.json"));
        
        batch = new SpriteBatch();
        window = new Window("UI Demo Stuff", skin);
//        window.setFillParent(true);
        window.setMovable(true);
        window.setKeepWithinStage(true);
        window.setTitleAlignment(Align.left);
        
        
        addItemLabel = new Label("Add item:", skin);
        window.add(addItemLabel).left();
        
        addItemField = new TextField("", skin);
        window.add(addItemField).right();
        
        addItemButton = new TextButton("Add", skin);
        addItemButton.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                final String item = addItemField.getText();
                if (!StringUtils.isEmpty(item)) {
                    itemsList.getItems().add(item);
                    addItemField.setText("");
                }
            }
            
        });
        window.add(addItemButton).right();
        window.row();
        
        itemsList = new List<String>(skin);
        itemsList.setItems("Hello", "World", "From LibGDX");
        itemsList.setLayoutEnabled(true);
        itemsList.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                System.err.println("Changed! " + itemsList.getSelected());
            }
        });
        window.add(itemsList).right();
        window.row();
        window.pack();
        
        stage.addActor(window);
        
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(final float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(final int width, final int height) {
        
    }

    @Override
    public void show() {
        
    }

    @Override
    public void hide() {
        
    }

    @Override
    public void pause() {
        
    }

    @Override
    public void resume() {
        
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
        batch.dispose();
    }
    
    private final Stage stage;
    private final Skin skin;
    private final SpriteBatch batch;
    private final Window window;
    private final List<String> itemsList;
    private final Label addItemLabel;
    private final TextField addItemField;
    private final Button addItemButton;
}
