/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.player.details;

import com.badlogic.gdx.graphics.Color;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author mcory01
 */
public abstract class ColoredDetails extends BaseDetails {

    public ColoredDetails() {
    }

    public final String getSampleColorRgb() {
        return sampleColorRgb;
    }

    public final void setSampleColorRgb(final String sampleColorRgb) {
        this.sampleColorRgb = sampleColorRgb;
    }
    
    public final Color getSampleColor() {
        if (StringUtils.isBlank(sampleColorRgb)) {
            return null;
        }
        
        return Color.valueOf(sampleColorRgb);
    }
    
    private String sampleColorRgb;
}
