/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.maps.npcs;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 *
 * @author mcory01
 */
public class NPCDetailsTests {
    
    @Test
    public void ensureConstructorInitializesNothing() {
        final NPCDetails npc = new NPCDetails();
        
        assertNull(npc.getId());
        assertNull(npc.getDescription());
        assertNull(npc.getName());
        assertNull(npc.getImageFileName());
    }
    
    @Test
    public void ensureSettersWorkProperly() {
        final NPCDetails npc = new NPCDetails();
        
        assertNull(npc.getId());
        assertNull(npc.getDescription());
        assertNull(npc.getName());
        assertNull(npc.getImageFileName());
        
        final String id = "Id";
        final String desc = "Description";
        final String name = "Name";
        final String imageFileName = "Image File Name";
        
        npc.setId(id);
        npc.setDescription(desc);
        npc.setName(name);
        npc.setImageFileName(imageFileName);
        
        assertEquals(id, npc.getId());
        assertEquals(desc, npc.getDescription());
        assertEquals(name, npc.getName());
        assertEquals(imageFileName, npc.getImageFileName());
    }
}
