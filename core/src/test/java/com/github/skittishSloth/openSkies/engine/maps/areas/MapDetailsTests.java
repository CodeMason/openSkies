/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.maps.areas;

import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 *
 * @author mcory01
 */
public class MapDetailsTests {
    
    @Test
    public void ensureDefaultConstructorDoesNothing() {
        final MapDetails md = new MapDetails();
        
        assertNull(md.getName());
        assertNull(md.getRelativePath());
        assertNull(md.getNpcs());
    }
    
    @Test
    public void ensureNameAndRelativePathSettersWorkProperly() {
        final MapDetails md = new MapDetails();
        
        assertNull(md.getName());
        assertNull(md.getRelativePath());
        
        final String name = "Name";
        final String relativePath = "/relative/path";
        
        md.setName(name);
        md.setRelativePath(relativePath);
        
        assertEquals(name, md.getName());
        assertEquals(relativePath, md.getRelativePath());
    }
    
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
}
