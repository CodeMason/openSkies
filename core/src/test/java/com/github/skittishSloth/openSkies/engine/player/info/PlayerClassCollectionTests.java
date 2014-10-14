/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.player.info;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import static org.junit.Assert.*;

import org.junit.Test;
/**
 *
 * @author mcory01
 */
public class PlayerClassCollectionTests {
    
    @Test
    public void ensureConstructorDoesNotInitializeAnything() {
        final PlayerClassCollection col = new PlayerClassCollection();
        
        assertNull(col.getClasses());
    }
    
    @Test
    public void ensureSizeReturnsZeroIfUnderlyingCollectionIsNull() {
        final PlayerClassCollection col = new PlayerClassCollection();
        
        assertNull(col.getClasses());
        assertEquals(0, col.size());
    }
    
    @Test
    public void ensureCollectionSetterInitializesCollectionIfPassedNull() {
        final PlayerClassCollection col = new PlayerClassCollection();
        
        assertNull(col.getClasses());
        
        final Collection<PlayerClass> classes = null;
        col.setClasses(classes);
        assertNotNull(col.getClasses());
    }
    
    @Test
    public void ensureCollectionSetterInitializesCollectionIfPassedValidCollection() {
        final PlayerClassCollection col = new PlayerClassCollection();
        
        assertNull(col.getClasses());
        
        final List<PlayerClass> classes = new ArrayList<>();
        
        final int size = 5;
        for (int i = 0; i < size; ++i) {
            final PlayerClass pc = new PlayerClass();
            classes.add(pc);
        }
        col.setClasses(classes);
        assertNotNull(col.getClasses());
        assertEquals(size, col.size());
        assertEquals(size, col.getClasses().size());
        
        for (int i = 0; i < size; ++i) {
            assertEquals(classes.get(i), col.getClasses().get(i));
        }
    }
    
    @Test
    public void ensureCollectionSetterClearsExistingCollection() {
        final PlayerClassCollection col = new PlayerClassCollection();
        
        assertNull(col.getClasses());
        
        final List<PlayerClass> classes = new ArrayList<>();
        
        final int size = 5;
        for (int i = 0; i < size; ++i) {
            final PlayerClass pc = new PlayerClass();
            classes.add(pc);
        }
        
        col.setClasses(classes);
        assertNotNull(col.getClasses());
        assertEquals(size, col.size());
        assertEquals(size, col.getClasses().size());
        
        for (int i = 0; i < size; ++i) {
            assertEquals(classes.get(i), col.getClasses().get(i));
        }
        
        final List<PlayerClass> nullClasses = null;
        col.setClasses(nullClasses);
        assertNotNull(col.getClasses());
        assertEquals(0, col.size());
        assertEquals(0, col.getClasses().size());
    }
}
