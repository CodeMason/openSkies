/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.inventory.items;

import com.badlogic.gdx.files.FileHandle;
import com.github.skittishSloth.openSkies.engine.common.DataCollection;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 *
 * @author mcory01
 */
public class ItemDetailsManagerTests {
    
    @Test(expected = NullPointerException.class)
    public void ensureConstructorFailsOnNullInput() {
        final DataCollection<ItemDetails> data = null;
        
        final ItemDetailsManager manager = new ItemDetailsManager(data);
    }
    
    @Test
    public void ensureProperlyConstructedManagerCanAccessItemsByTypeOrId() throws URISyntaxException {
        final URL fileUrl = getClass().getResource("/json/items.json");
        final File file = new File(fileUrl.toURI());
        final FileHandle fh = new FileHandle(file);
        
        final DataCollection<ItemDetails> dataCollection = ItemDetailsLoader.fromJson(fh);
        
        final ItemDetailsManager manager = new ItemDetailsManager(dataCollection);
        
        final List<ItemDetails> expected = buildExpectedData();
        
        for (final ItemDetails exp : expected) {
            final ItemDetails byId = manager.getItemById(exp.getId());
            assertEquals(exp, byId);
            
            final List<ItemDetails> listByType = manager.getItemsByType(exp.getType());
            assertTrue(listByType.contains(exp));
        }
    }
    
    protected List<ItemDetails> buildExpectedData() {
        final List<ItemDetails> res = new ArrayList<>();
        
        final ItemDetails scarItem = new ItemDetails();
        scarItem.setId(1);
        scarItem.setName("SCAR Card");
        scarItem.setType(ItemDetails.Type.SPECIAL);
        
        res.add(scarItem);
        
        final ItemDetails otherItem = new ItemDetails();
        otherItem.setId(2);
        otherItem.setName("Something Else");
        otherItem.setType(ItemDetails.Type.QUEST);
        res.add(otherItem);
        
        final ItemDetails anotherSpecial = new ItemDetails();
        anotherSpecial.setId(3);
        anotherSpecial.setName("Another Special");
        anotherSpecial.setType(ItemDetails.Type.SPECIAL);
        
        res.add(anotherSpecial);
                                
        return res;
    }
}
