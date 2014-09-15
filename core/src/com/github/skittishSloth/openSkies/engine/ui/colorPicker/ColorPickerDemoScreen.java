/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.skittishSloth.openSkies.engine.ui.colorPicker;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.github.skittishSloth.openSkies.OpenSkies;
import com.github.skittishSloth.openSkies.engine.ui.AbstractScreen;
import com.github.skittishSloth.openSkies.engine.ui.characterBuilder.ColorPicker;

/**
 *
 * @author mcory01
 */
public class ColorPickerDemoScreen extends AbstractScreen {

    public ColorPickerDemoScreen(final OpenSkies game) {
        super(game);
        
        final Skin skin = getSkin();
        window = new Window("Color Picker Demo", skin);
        window.setFillParent(true);
        window.setTitleAlignment(Align.left);
        window.defaults().bottom().left();
        final ColorPicker colorPicker = new ColorPicker(skin);
        colorPicker.setFillParent(true);
        getStage().addActor(colorPicker);
        
        Gdx.input.setInputProcessor(getStage());
    }
    
    private final Window window;
}
