/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.player.details;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author mcory01
 */
public class DetailsCollection<T> {
    
    public Collection<T> getItems() {
        return items;
    }

    public void setItems(Collection<T> items) {
        if (this.items == null) {
            this.items = new ArrayList<T>();
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
        }

        return items.size();
    }
    
    private ArrayList<T> items;
}
