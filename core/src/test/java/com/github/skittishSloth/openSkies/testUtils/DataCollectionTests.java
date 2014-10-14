/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.testUtils;

import com.github.skittishSloth.openSkies.engine.common.DataCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;

import org.junit.Test;

/**
 *
 * @author mcory01
 * @param <T>
 */
public abstract class DataCollectionTests<T> {
    
    @Before
    public void setup() {
        col = buildWithDefaultConstructor();
    }
    
    @Test
    public void ensureConstructorDoesNotInitializeAnything() {
        assertNull(col.getData());
    }
    
    @Test
    public void ensureSizeReturnsZeroIfUnderlyingCollectionIsNull() {
        assertNull(col.getData());
        assertEquals(0, col.size());
    }
    
    @Test
    public void ensureCollectionSetterInitializesCollectionIfPassedNull() {
        assertNull(col.getData());
        
        final Collection<T> backStories = null;
        col.setData(backStories);
        assertNotNull(col.getData());
    }
    
    @Test
    public void ensureCollectionSetterInitializesCollectionIfPassedValidCollection() {
        assertNull(col.getData());
        
        final List<T> data = new ArrayList<>();
        
        final int size = 5;
        for (int i = 0; i < size; ++i) {
            final T item = buildItemInstance(i);
            data.add(item);
        }
        col.setData(data);
        assertNotNull(col.getData());
        assertEquals(size, col.size());
        assertEquals(size, col.getData().size());
        
        for (int i = 0; i < size; ++i) {
            assertEquals(data.get(i), col.getData().get(i));
        }
    }
    
    @Test
    public void ensureCollectionSetterClearsExistingCollection() {
        assertNull(col.getData());
        
        final List<T> data = new ArrayList<>();
        
        final int size = 5;
        for (int i = 0; i < size; ++i) {
            final T item = buildItemInstance(i);
            data.add(item);
        }
        
        col.setData(data);
        assertNotNull(col.getData());
        assertEquals(size, col.size());
        assertEquals(size, col.getData().size());
        
        for (int i = 0; i < size; ++i) {
            assertEquals(data.get(i), col.getData().get(i));
        }
        
        final List<T> nullBackStories = null;
        col.setData(nullBackStories);
        assertNotNull(col.getData());
        assertEquals(0, col.size());
        assertEquals(0, col.getData().size());
    }
    
    protected abstract DataCollection<T> buildWithDefaultConstructor();

    protected abstract T buildItemInstance(int index);
    
    private DataCollection<T> col;
}
