/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.maps.areas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

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

    public void setMaps(final Collection<MapDetails> maps) {
        if (this.maps == null) {
            this.maps = new ArrayList<>();
        } else {
            this.maps.clear();
        }

        if (maps != null) {
            this.maps.addAll(maps);
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.name);
        hash = 67 * hash + Objects.hashCode(this.startingMap);
        hash = 67 * hash + Objects.hashCode(this.maps);
        return hash;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AreaDetails other = (AreaDetails) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.startingMap, other.startingMap)) {
            return false;
        }
        
        return Objects.equals(this.maps, other.maps);
    }

    private String name;
    private String startingMap;
    private ArrayList<MapDetails> maps;
}
