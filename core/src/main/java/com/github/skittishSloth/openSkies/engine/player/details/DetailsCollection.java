/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.player.details;

import com.github.skittishSloth.openSkies.engine.common.DataCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author mcory01
 * @param <T>
 */
class DetailsCollection<T extends BaseDetails> implements DataCollection<T> {

    @Override
    public List<T> getData() {
        return items;
    }

    @Override
    public void setData(final Collection<T> data) {
        if (this.items == null) {
            this.items = new ArrayList<>();
        } else {
            this.items.clear();
        }

        if (data != null) {
            this.items.addAll(data);
        }
    }

    @Override
    public final int size() {
        if (items == null) {
            return 0;
        }

        return items.size();
    }
    
    private ArrayList<T> items;
}
