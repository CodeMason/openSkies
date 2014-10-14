/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.player.info;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author mcory01
 */
public class PlayerClassCollection {
    
    public PlayerClassCollection() {
        
    }

    public List<PlayerClass> getClasses() {
        return classes;
    }

    public void setClasses(final Collection<PlayerClass> classes) {
        if (this.classes == null) {
            this.classes = new ArrayList<>();
        } else {
            this.classes.clear();
        }
        
        if (classes != null) {
            this.classes.addAll(classes);
        }
    }
    
    public int size() {
        if (classes == null) {
            return 0;
        } else {
            return classes.size();
        }
    }

    private ArrayList<PlayerClass> classes;
}
