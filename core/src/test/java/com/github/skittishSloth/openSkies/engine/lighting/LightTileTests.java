/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.lighting;

import com.badlogic.gdx.math.Rectangle;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 *
 * @author mcory01
 */
public class LightTileTests {
    
    @Test
    public void ensureConstructorInitializesEverythingCorrectly() {
        final String name = "The Light";
        final Rectangle rect = new Rectangle(1, 2, 3, 4);
        
        final LightTile tile = new LightTile(name, rect);
        assertEquals(name, tile.getName());
        assertEquals(rect, tile.getRectangle());
    }
}
