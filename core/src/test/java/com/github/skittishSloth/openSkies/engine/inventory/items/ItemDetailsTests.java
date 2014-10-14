/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.inventory.items;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 *
 * @author mcory01
 */
public class ItemDetailsTests {
    
    @Test
    public void ensureDefaultConstructorDoesNothing() {
        final ItemDetails item = new ItemDetails();
        
        assertNull(item.getId());
        assertNull(item.getName());
        assertNull(item.getType());
    }
    
    @Test
    public void ensureSettersWorkProperly() {
        final ItemDetails item = new ItemDetails();
        
        assertNull(item.getId());
        assertNull(item.getName());
        assertNull(item.getType());
        
        final Integer id = 1;
        final String name = "Name";
        final ItemDetails.Type type = ItemDetails.Type.QUEST;
        
        item.setId(id);
        item.setName(name);
        item.setType(type);
        
        assertEquals(id, item.getId());
        assertEquals(name, item.getName());
        assertEquals(type, item.getType());
    }
}
