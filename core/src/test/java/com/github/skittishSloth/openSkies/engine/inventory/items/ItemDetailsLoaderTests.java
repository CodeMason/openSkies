/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.inventory.items;

import com.badlogic.gdx.files.FileHandle;
import com.github.skittishSloth.openSkies.engine.common.DataCollection;
import com.github.skittishSloth.openSkies.testUtils.DataCollectionLoaderTests;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mcory01
 */
public class ItemDetailsLoaderTests extends DataCollectionLoaderTests<ItemDetails> {

    @Override
    protected String getDataPath() {
        return "/json/items.json";
    }

    @Override
    protected DataCollection loadData(FileHandle fh) {
        return ItemDetailsLoader.listFromJson(fh);
    }

    @Override
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
