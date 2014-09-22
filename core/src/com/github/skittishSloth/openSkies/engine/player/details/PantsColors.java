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
public enum PantsColors {
    MAGENTA("ae687f"),
    RED("b91f20"),
    TEAL("79979d"),
    WHITE("fafafa");
    
    private final Color sampleColor;
    
    private PantsColors(final String sampleColorString) {
        this.sampleColor = Color.valueOf(sampleColorString);
    }
    
    public Color getSampleColor() {
        return sampleColor;
    }
}
