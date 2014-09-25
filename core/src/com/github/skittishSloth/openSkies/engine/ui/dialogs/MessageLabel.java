/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.ui.dialogs;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 *
 * @author mcory01
 */
public class MessageLabel extends Label {

    public MessageLabel(CharSequence text, Skin skin) {
        super(text, skin);
        setColor(Color.CLEAR);
    }
    
}
