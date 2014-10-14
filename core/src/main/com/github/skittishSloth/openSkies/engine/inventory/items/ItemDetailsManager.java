/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.inventory.items;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author mcory01
 */
public class ItemDetailsManager {
    
    public ItemDetailsManager(final ItemDetailsCollection idc) {
        final List<ItemDetails> items = idc.getItems();
        
        for (final ItemDetails item : items) {
            itemsById.put(item.getId(), item);
            
            final ItemDetails.Type itemType = item.getType();
            final List<ItemDetails> typeItems;
            if (itemsByType.containsKey(itemType)) {
                typeItems = itemsByType.get(itemType);
            } else {
                typeItems = new ArrayList<ItemDetails>();
                itemsByType.put(itemType, typeItems);
            }
            
            typeItems.add(item);
        }
    }
    
    public ItemDetails getItemById(final int id) {
        return itemsById.get(id);
    }
    
    public List<ItemDetails> getItemsByType(final ItemDetails.Type type) {
        return itemsByType.get(type);
    }

    private final Map<ItemDetails.Type, List<ItemDetails>> itemsByType = new EnumMap<ItemDetails.Type, List<ItemDetails>>(ItemDetails.Type.class);
    private final Map<Integer, ItemDetails> itemsById = new HashMap<Integer, ItemDetails>();
}
