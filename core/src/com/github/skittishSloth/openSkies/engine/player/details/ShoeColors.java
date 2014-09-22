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
public enum ShoeColors {
    BLACK("313131"),
    BROWN("865e40"),
    MAROON("83534e");
    
    private final Color sampleColor;
    
    private ShoeColors(final String sampleColorString) {
        this.sampleColor = Color.valueOf(sampleColorString);
    }
    
    public Color getSampleColor() {
        return sampleColor;
    }
}
