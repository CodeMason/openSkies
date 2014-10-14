/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.inventory.items;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.github.skittishSloth.openSkies.engine.common.DataCollection;

/**
 *
 * @author mcory01
 */
public final class ItemDetailsLoader {
    private static final Json json = new Json();
    
    private ItemDetailsLoader() {
        
    }
    
    public static DataCollection<ItemDetails> fromJson(final FileHandle file) {
        final ItemDetailsCollection res = json.fromJson(ItemDetailsCollection.class, file);
        return res;
    }
}
