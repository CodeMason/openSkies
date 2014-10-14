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
public class BackStoryCollectionTests {
    
    @Test
    public void ensureConstructorDoesNotInitializeAnything() {
        final BackStoryCollection col = new BackStoryCollection();
        
        assertNull(col.getBackStories());
    }
    
    @Test
    public void ensureSizeReturnsZeroIfUnderlyingCollectionIsNull() {
        final BackStoryCollection col = new BackStoryCollection();
        
        assertNull(col.getBackStories());
        assertEquals(0, col.size());
    }
    
    @Test
    public void ensureCollectionSetterInitializesCollectionIfPassedNull() {
        final BackStoryCollection col = new BackStoryCollection();
        
        assertNull(col.getBackStories());
        
        final Collection<BackStory> backStories = null;
        col.setBackStories(backStories);
        assertNotNull(col.getBackStories());
    }
    
    @Test
    public void ensureCollectionSetterInitializesCollectionIfPassedValidCollection() {
        final BackStoryCollection col = new BackStoryCollection();
        
        assertNull(col.getBackStories());
        
        final List<BackStory> backStories = new ArrayList<>();
        
        final int size = 5;
        for (int i = 0; i < size; ++i) {
            final BackStory bs = new BackStory();
            backStories.add(bs);
        }
        col.setBackStories(backStories);
        assertNotNull(col.getBackStories());
        assertEquals(size, col.size());
        assertEquals(size, col.getBackStories().size());
        
        for (int i = 0; i < size; ++i) {
            assertEquals(backStories.get(i), col.getBackStories().get(i));
        }
    }
    
    @Test
    public void ensureCollectionSetterClearsExistingCollection() {
        final BackStoryCollection col = new BackStoryCollection();
        
        assertNull(col.getBackStories());
        
        final List<BackStory> backStories = new ArrayList<>();
        
        final int size = 5;
        for (int i = 0; i < size; ++i) {
            final BackStory bs = new BackStory();
            backStories.add(bs);
        }
        
        col.setBackStories(backStories);
        assertNotNull(col.getBackStories());
        assertEquals(size, col.size());
        assertEquals(size, col.getBackStories().size());
        
        for (int i = 0; i < size; ++i) {
            assertEquals(backStories.get(i), col.getBackStories().get(i));
        }
        
        final List<BackStory> nullBackStories = null;
        col.setBackStories(nullBackStories);
        assertNotNull(col.getBackStories());
        assertEquals(0, col.size());
        assertEquals(0, col.getBackStories().size());
    }
}
