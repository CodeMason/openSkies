/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.maps.areas;

import com.badlogic.gdx.math.Vector2;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 *
 * @author mcory01
 */
public class MapDetailNPCEntryTests {
    @Test
    public void ensureDefaultConstructorDoesNothing() {
        final MapDetailNPCEntry mapDetailNPCEntry = new MapDetailNPCEntry();
        
        assertNull(mapDetailNPCEntry.getId());
        assertNull(mapDetailNPCEntry.getLocation());
    }
    
    @Test
    public void ensureSettersWorkProperly() {
        final MapDetailNPCEntry mapDetailNPCEntry = new MapDetailNPCEntry();
        
        assertNull(mapDetailNPCEntry.getId());
        assertNull(mapDetailNPCEntry.getLocation());
        
        final String id = "test_id";
        final Vector2 location = new Vector2(5.0f, 5.0f);
        
        mapDetailNPCEntry.setId(id);
        mapDetailNPCEntry.setLocation(location);
        
        assertEquals(id, mapDetailNPCEntry.getId());
        assertEquals(location, mapDetailNPCEntry.getLocation());
    }
}
