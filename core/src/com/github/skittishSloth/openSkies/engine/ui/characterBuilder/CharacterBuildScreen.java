/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.skittishSloth.openSkies.engine.ui.characterBuilder;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.github.skittishSloth.openSkies.OpenSkies;
import com.github.skittishSloth.openSkies.engine.ui.AbstractScreen;

/**
 *
 * @author mcory01
 */
public class CharacterBuildScreen extends AbstractScreen {
    public CharacterBuildScreen(final OpenSkies game) {
        super(game);
        
        final Skin skin = getSkin();
        window = new Window("Character Builder", skin);
        window.setFillParent(true);
        window.setTitleAlignment(Align.left);
        window.defaults().bottom().left();
        final CharacterBuildTable cbt = new CharacterBuildTable(skin);
        cbt.setFillParent(true);
        getStage().addActor(cbt);
        
        Gdx.input.setInputProcessor(getStage());
    }
    
    private final Window window;
}
