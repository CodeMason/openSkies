/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.player.details;

import com.badlogic.gdx.graphics.Color;

/**
 *
 * @author mcory01
 */
public enum ShirtColors {
    BROWN("744b30"),
    MAROON("641f1f"),
    TEAL("28b2c6"),
    WHITE("e5e6c7");
    
    private final Color sampleColor;
    
    private ShirtColors(final String sampleColorString) {
        this.sampleColor = Color.valueOf(sampleColorString);
    }
    
    public Color getSampleColor() {
        return sampleColor;
    }
}
