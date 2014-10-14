/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.maps.npcs;

import com.github.skittishSloth.openSkies.engine.common.DataCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author mcory01
 */
class NPCDetailsCollection implements DataCollection<NPCDetails> {

    public NPCDetailsCollection() {
    }

    @Override
    public List<NPCDetails> getData() {
        return npcs;
    }

    @Override
    public void setData(final Collection<NPCDetails> npcs) {
        if (this.npcs == null) {
            this.npcs = new ArrayList<>();
        } else {
            this.npcs.clear();
        }
        
        if (npcs != null) {
            this.npcs.addAll(npcs);
        }
    }
    
    @Override
    public int size() {
        if (npcs == null) {
            return 0;
        }
        
        return npcs.size();
    }

    private ArrayList<NPCDetails> npcs;
}
