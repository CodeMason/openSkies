/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.maps.areas;

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
public class AreaDetailsTests extends SimpleBeanTests {
    
    @Test
    public void ensureMapsSetterProperlyInitializesCollectionEvenWithNullInput() {
        final AreaDetails ad = new AreaDetails();
        
        assertNull(ad.getMaps());
        
        final Collection<MapDetails> maps = null;
        ad.setMaps(maps);
        
        assertNotNull(ad.getMaps());
        assertEquals(0, ad.getMaps().size());
    }
    
    @Test
    public void ensureMapsSetterProperlySetsCollection() {
        final AreaDetails ad = new AreaDetails();
        
        assertNull(ad.getMaps());
        
        final List<MapDetails> maps = new ArrayList<>();
        
        final int size = 5;
        for (int i = 0; i < size; ++i) {
            final MapDetails map = new MapDetails();
            maps.add(map);
        }
        
        ad.setMaps(maps);
        
        final List<MapDetails> resMaps = ad.getMaps();
        assertNotNull(resMaps);
        assertEquals(size, resMaps.size());
        
        for (int i = 0; i < size; ++i) {
            assertEquals(maps.get(i), resMaps.get(i));
        }
    }
    
    @Test
    public void ensureMapsSetterProperlyClearsExistingCollection() {
        final AreaDetails ad = new AreaDetails();
        
        assertNull(ad.getMaps());
        
        final List<MapDetails> maps = new ArrayList<>();
        
        final int size = 5;
        for (int i = 0; i < size; ++i) {
            final MapDetails map = new MapDetails();
            maps.add(map);
        }
        
        ad.setMaps(maps);
        
        final List<MapDetails> resMaps = ad.getMaps();
        assertNotNull(resMaps);
        assertEquals(size, resMaps.size());
        
        for (int i = 0; i < size; ++i) {
            assertEquals(maps.get(i), resMaps.get(i));
        }
        
        final List<MapDetails> nullMaps = null;
        ad.setMaps(nullMaps);
        
        final List<MapDetails> nullResMaps = ad.getMaps();
        assertNotNull(nullResMaps);
        assertEquals(0, nullResMaps.size());
    }

    @Override
    protected Class<?> getClassForTest() {
        return AreaDetails.class;
    }
}
