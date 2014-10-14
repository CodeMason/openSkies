/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.inventory.items;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author mcory01
 */
public class ItemDetailsCollection {

    public ItemDetailsCollection() {
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

    public int size() {
        if (items == null) {
            return 0;
        } else {
            return items.size();
        }
    }

    private ArrayList<ItemDetails> items;
}
