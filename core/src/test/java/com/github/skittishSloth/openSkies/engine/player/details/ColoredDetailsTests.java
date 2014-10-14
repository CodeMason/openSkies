/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.player.details;

import com.badlogic.gdx.graphics.Color;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 *
 * @author mcory01
 */
public class ColoredDetailsTests {
    
    @Test
    public void ensureConstructorInitializesNothing() {
        final ColoredDetails cd = new ColoredDetails();
        
        assertNull(cd.getSampleColorRgb());
    };
    
    @Test
    public void ensureSetterWorksProperly() {
        final ColoredDetails cd = new ColoredDetails();
        
        assertNull(cd.getSampleColorRgb());
        
        final String sampleColorRgb = "FF00FF";
        cd.setSampleColorRgb(sampleColorRgb);
        
        assertEquals(sampleColorRgb, cd.getSampleColorRgb());
    }
    
    @Test
    public void ensureGetSampleColorReturnsAppropriateResult() {
        final ColoredDetails cd = new ColoredDetails();
        
        assertNull(cd.getSampleColorRgb());
        
        assertNull(cd.getSampleColor());
        
        cd.setSampleColorRgb("");
        assertNull(cd.getSampleColor());
        
        final String sampleColorRgb = "FF00FF";
        final float expRed = 1.0f;
        final float expGreen = 0.0f;
        final float expBlue = 1.0f;
        final float expAlpha = 1.0f;
        
        cd.setSampleColorRgb(sampleColorRgb);
        
        final Color sampleColor = cd.getSampleColor();
        assertNotNull(sampleColor);
        
        assertEquals(expRed, sampleColor.r, 0.0f);
        assertEquals(expGreen, sampleColor.g, 0.0f);
        assertEquals(expBlue, sampleColor.b, 0.0f);
        assertEquals(expAlpha, sampleColor.a, 0.0f);
    }
}
