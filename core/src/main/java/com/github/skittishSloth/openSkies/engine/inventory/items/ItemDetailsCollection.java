/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.inventory.items;

import com.github.skittishSloth.openSkies.engine.common.DataCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author mcory01
 */
public class ItemDetailsCollection implements DataCollection<ItemDetails> {

    public ItemDetailsCollection() {
    }

    @Override
    public List<ItemDetails> getData() {
        return items;
    }

    @Override
    public void setData(final Collection<ItemDetails> items) {
        if (this.items == null) {
            this.items = new ArrayList<>();
        } else {
            this.items.clear();
        }

        if (items != null) {
            this.items.addAll(items);
        }
    }

    @Override
    public int size() {
        if (items == null) {
            return 0;
        } else {
            return items.size();
        }
    }

    private ArrayList<ItemDetails> items;
}
