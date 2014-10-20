/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.player.info;

import com.github.skittishSloth.openSkies.testUtils.SimpleBeanTests;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 *
 * @author mcory01
 */
public class BackStoryTests extends SimpleBeanTests {
    
    @Test
    public void ensureParameterizedConstructorProperlyInitializesFields() {
        final String name = "Name";
        final String desc = "Description";
        
        final BackStory bs = new BackStory(name, desc);
        
        assertEquals(name, bs.getName());
        assertEquals(desc, bs.getDescription());
    }
    
    @Override
    protected Class<?> getClassForTest() {
        return BackStory.class;
    }
}
