/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.lighting;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 *
 * @author mcory01
 */
public class PulsingLightTileTests {
    
    @Test
    public void ensureConstructorInitializesEverythingCorrectly() {
        final String name = "Name";
        final Rectangle rectangle = new Rectangle(1, 2, 3, 4);
        final float pulseCycleTime = 0.25f;
        final float maxDistance = 30f;
        final Color color = Color.RED;
        final PulsingLightTile tile = new PulsingLightTile(name, rectangle, pulseCycleTime, maxDistance, color);
        
        assertEquals(name, tile.getName());
        assertEquals(rectangle, tile.getRectangle());
        assertEquals(pulseCycleTime, tile.getPulseCycleTime(), 0.0f);
        assertEquals(maxDistance, tile.getMaxDistance(), 0.0f);
        assertEquals(color, tile.getLightColor());
    }
}
