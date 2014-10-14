/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.maps.areas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author mcory01
 */
public class AreaDetails {
    
    public AreaDetails() {
        
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getStartingMap() {
        return startingMap;
    }

    public void setStartingMap(final String startingMap) {
        this.startingMap = startingMap;
    }

    public List<MapDetails> getMaps() {
        return maps;
    }

    public void setMaps(Collection<MapDetails> maps) {
        if (this.maps == null) {
            this.maps = new ArrayList<MapDetails>();
        } else {
            this.maps.clear();
        }
        
        if (maps != null) {
            this.maps.addAll(maps);
        }
    }
    
    private String name;
    private String startingMap;
    private ArrayList<MapDetails> maps;
}
