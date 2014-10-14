/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.player.info;

import com.github.skittishSloth.openSkies.engine.common.DataCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author mcory01
 */
public class PlayerClassCollection implements DataCollection<PlayerClass> { 
    
    public PlayerClassCollection() {
        
    }

    @Override
    public List<PlayerClass> getData() {
        return classes;
    }

    @Override
    public void setData(final Collection<PlayerClass> classes) {
        if (this.classes == null) {
            this.classes = new ArrayList<>();
        } else {
            this.classes.clear();
        }
        
        if (classes != null) {
            this.classes.addAll(classes);
        }
    }
    
    @Override
    public int size() {
        if (classes == null) {
            return 0;
        } else {
            return classes.size();
        }
    }

    private ArrayList<PlayerClass> classes;
}
