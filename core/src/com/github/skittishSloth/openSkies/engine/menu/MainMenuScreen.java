/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.menu;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.github.skittishSloth.openSkies.OpenSkies;
import com.github.skittishSloth.openSkies.engine.ui.AbstractScreen;
import com.github.skittishSloth.openSkies.engine.ui.tools.characterBuilder.CharacterBuilderScreenManager;

/**
 *
 * @author mcory01
 */
public class MainMenuScreen extends AbstractScreen {

    public MainMenuScreen(final OpenSkies game) {
        super(game);
        
        characterBuilderManager = new CharacterBuilderScreenManager(game);
        final Skin skin = getSkin();
        final Table table = new Table(skin);
        final TextButton newGameBtn = new TextButton("New Game", skin);
        newGameBtn.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                characterBuilderManager.startCharacterBuilder();
            }
            
        });
        table.add(newGameBtn);
        table.row();
        
        final TextButton loadGameBtn = new TextButton("Load Game", skin);
        table.add(loadGameBtn);
        table.row();
        
        final TextButton optionsBtn = new TextButton("Options", skin);
        table.add(optionsBtn);
        table.row();
        table.setFillParent(true);
        getStage().addActor(table);
    }

    private final CharacterBuilderScreenManager characterBuilderManager;
}
