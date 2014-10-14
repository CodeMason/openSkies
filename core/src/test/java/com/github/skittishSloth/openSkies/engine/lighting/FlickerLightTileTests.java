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
public class FlickerLightTileTests {
    
    @Test
    public void ensureConstructorInitializesEverythingCorrectly() {
        final String name = "Name";
        final Rectangle rect = new Rectangle(1, 2, 3, 4);
        final float maxFlickerTime = 0.25f;
        final float maxFlickerDistance = 30f;
        final Color color = Color.RED;
        
        final FlickerLightTile tile = new FlickerLightTile(name, rect, maxFlickerTime, maxFlickerDistance, color);
        assertEquals(name, tile.getName());
        assertEquals(rect, tile.getRectangle());
        assertEquals(maxFlickerTime, tile.getMaxFlickerTime(), 0.0f);
        assertEquals(maxFlickerDistance, tile.getMaxFlickerDistance(), 0.0f);
        assertEquals(color, tile.getLightColor());
    }
}
