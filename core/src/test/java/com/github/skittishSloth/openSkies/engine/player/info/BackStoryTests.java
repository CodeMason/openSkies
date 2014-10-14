/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.player.info;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 *
 * @author mcory01
 */
public class BackStoryTests {
    
    @Test
    public void ensureDefaultConstructorDoesNotInitializeAnything() {
        final BackStory bs = new BackStory();
        
        assertNull(bs.getDescription());
        assertNull(bs.getName());
    }
    
    @Test
    public void ensureParameterizedConstructorProperlyInitializesFields() {
        final String name = "Name";
        final String desc = "Description";
        
        final BackStory bs = new BackStory(name, desc);
        
        assertEquals(name, bs.getName());
        assertEquals(desc, bs.getDescription());
    }
    
    @Test
    public void ensureSettersWorkAppropriately() {
        final BackStory bs = new BackStory();
        
        assertNull(bs.getDescription());
        assertNull(bs.getName());
        
        final String name = "Name";
        final String desc = "Description";
        
        bs.setName(name);
        bs.setDescription(desc);
        
        assertEquals(name, bs.getName());
        assertEquals(desc, bs.getDescription());
    }
}
