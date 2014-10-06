/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.maps.npcs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author mcory01
 */
public class NPCDetailsCollection {

    public NPCDetailsCollection() {
    }

    public List<NPCDetails> getNpcs() {
        return npcs;
    }

    public void setNpcs(final Collection<NPCDetails> npcs) {
        if (this.npcs == null) {
            this.npcs = new ArrayList<NPCDetails>();
        } else {
            this.npcs.clear();
        }
        
        if (npcs != null) {
            this.npcs.addAll(npcs);
        }
    }
    
    public int size() {
        if (npcs == null) {
            return 0;
        }
        
        return npcs.size();
    }

    private ArrayList<NPCDetails> npcs;
}
