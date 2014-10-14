/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.inventory;

import com.github.skittishSloth.openSkies.engine.inventory.items.ItemDetails;
import com.github.skittishSloth.openSkies.engine.common.CollectionUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author mcory01
 */
public class Inventory {

    public Inventory() {
    }

    public List<ItemDetails> getItems() {
        return items;
    }

    public void setItems(final Collection<ItemDetails> items) {
        if (this.items == null) {
            this.items = new ArrayList<ItemDetails>();
        } else {
            this.items.clear();
        }
        
        if (items != null) {
            this.items.addAll(items);
        }
    }
    
    public void addItem(final ItemDetails item) {
        if (items == null) {
            items = new ArrayList<ItemDetails>();
        }
        
        if (!items.contains(item)) {
            items.add(item);
            fireAfterItemAddedListeners(item);
        }
    }
    
    public boolean containsItem(final ItemDetails item) {
        return containsItem(items, item);
    }

    public List<ItemDetails> getQuestItems() {
        return questItems;
    }

    public void setQuestItems(final Collection<ItemDetails> questItems) {
        if (this.questItems == null) {
            this.questItems = new ArrayList<ItemDetails>();
        } else {
            this.questItems.clear();
        }
        
        if (questItems != null) {
            this.questItems.addAll(questItems);
        }
    }
    
    public void addQuestItem(final ItemDetails questItem) {
        if (questItems == null) {
            questItems = new ArrayList<ItemDetails>();
        }
        
        if (!questItems.contains(questItem)) {
            questItems.add(questItem);
            fireAfterItemAddedListeners(questItem);
        }
    }
    
    public boolean containsQuestItem(final ItemDetails questItem) {
        return containsItem(questItems, questItem);
    }

    public List<ItemDetails> getSpecialItems() {
        return specialItems;
    }

    public void setSpecialItems(final Collection<ItemDetails> specialItems) {
        if (this.specialItems == null) {
            this.specialItems = new ArrayList<ItemDetails>();
        } else {
            this.specialItems.clear();
        }
        
        if (specialItems != null) {
            this.specialItems.addAll(specialItems);
        }
    }
    
    public void addSpecialItem(final ItemDetails item) {
        if (specialItems == null) {
            specialItems = new ArrayList<ItemDetails>();
        }
        
        if (!specialItems.contains(item)) {
            specialItems.add(item);
            fireAfterItemAddedListeners(item);
        }
    }
    
    public boolean containsSpecialItem(final ItemDetails item) {
        return containsItem(specialItems, item);
    }
    
    public void addInventoryListener(final AddItemListener listener) {
        if (listener == null) {
            return;
        }
        
        if (!(listeners.contains(listener))) {
            listeners.add(listener);
        }
    }
    
    public void removeListener(final AddItemListener listener) {
        if (listener == null) {
            return;
        }
        
        listeners.remove(listener);
    }
    
    private void fireAfterItemAddedListeners(final ItemDetails item) {
        for (final AddItemListener listener : listeners) {
            listener.afterItemAdded(item);
        }
    }
    
    private static boolean containsItem(final Collection<ItemDetails> collection, final ItemDetails item) {
        if (CollectionUtils.isEmpty(collection)) {
            return false;
        } 
        
        return collection.contains(item);
    }

    private ArrayList<ItemDetails> items;
    private ArrayList<ItemDetails> questItems;
    private ArrayList<ItemDetails> specialItems;
    
    private final List<AddItemListener> listeners = new ArrayList<AddItemListener>();
}
