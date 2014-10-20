/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.maps.areas;

import com.badlogic.gdx.math.Vector2;
import com.github.skittishSloth.openSkies.testUtils.SimpleBeanTests;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;

/**
 *
 * @author mcory01
 */
public class MapDetailsTests extends SimpleBeanTests {
    
    @Test
    public void ensureNpcsSetterProperlyInitializesCollectionEvenWithNullInput() {
        final MapDetails md = new MapDetails();
        
        assertNull(md.getNpcs());
        
        final Collection<MapDetailNPCEntry> npcs = null;
        md.setNpcs(npcs);
        
        assertNotNull(md.getNpcs());
        assertEquals(0, md.getNpcs().size());
    }
    
    @Test
    public void ensureNpcsSetterProperlySetsCollection() {
        final MapDetails md = new MapDetails();
        
        assertNull(md.getNpcs());
        
        final List<MapDetailNPCEntry> npcs = new ArrayList<>();
        
        final int size = 5;
        for (int i = 0; i < size; ++i) {
            final MapDetailNPCEntry npc = new MapDetailNPCEntry();
            npc.setId(Integer.toString(i));
            npc.setLocation(new Vector2(i, i));
            npcs.add(npc);
        }
        
        md.setNpcs(npcs);
        
        final List<MapDetailNPCEntry> resNpcs = md.getNpcs();
        assertNotNull(resNpcs);
        assertEquals(size, resNpcs.size());
        
        for (int i = 0; i < size; ++i) {
            assertEquals(npcs.get(i), resNpcs.get(i));
        }
    }
    
    @Test
    public void ensureNpcsSetterProperlyClearsExistingCollection() {
        final MapDetails md = new MapDetails();
        
        assertNull(md.getNpcs());
        
        final List<MapDetailNPCEntry> npcs = new ArrayList<>();
        
        final int size = 5;
        for (int i = 0; i < size; ++i) {
            final MapDetailNPCEntry npc = new MapDetailNPCEntry();
            npc.setId(Integer.toString(i));
            npc.setLocation(new Vector2(i, i));
            npcs.add(npc);
        }
        
        md.setNpcs(npcs);
        
        final List<MapDetailNPCEntry> resNpcs = md.getNpcs();
        assertNotNull(resNpcs);
        assertEquals(size, resNpcs.size());
        
        for (int i = 0; i < size; ++i) {
            assertEquals(npcs.get(i), resNpcs.get(i));
        }
        
        final List<MapDetailNPCEntry> nullNpcs = null;
        md.setNpcs(nullNpcs);
        
        final List<MapDetailNPCEntry> nullResNpcs = md.getNpcs();
        assertNotNull(nullResNpcs);
        assertEquals(0, nullResNpcs.size());
    }

    @Override
    protected Class<?> getClassForTest() {
        return MapDetails.class;
    }
}
