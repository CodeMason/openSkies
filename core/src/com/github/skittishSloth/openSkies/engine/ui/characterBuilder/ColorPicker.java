/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.ui.characterBuilder;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 *
 * @author mcory01
 */
public class ColorPicker extends Table {

    public ColorPicker(final Skin skin) {
        super(skin);
        this.skin = skin;

        add(new Label("Hello World!", skin));
    }

    private final Skin skin;
}
