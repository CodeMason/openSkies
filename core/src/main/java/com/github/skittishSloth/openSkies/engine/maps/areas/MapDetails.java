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
public class MapDetails {

    public MapDetails() {
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getRelativePath() {
        return relativePath;
    }

    public void setRelativePath(final String relativePath) {
        this.relativePath = relativePath;
    }

    public List<MapDetailNPCEntry> getNpcs() {
        return npcs;
    }

    public void setNpcs(final Collection<MapDetailNPCEntry> npcs) {
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
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.name);
        hash = 89 * hash + Objects.hashCode(this.relativePath);
        hash = 89 * hash + Objects.hashCode(this.npcs);
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
        final MapDetails other = (MapDetails) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.relativePath, other.relativePath)) {
            return false;
        }
        
        return Objects.equals(this.npcs, other.npcs);
    }

    private String name;
    private String relativePath;
    private ArrayList<MapDetailNPCEntry> npcs;
}
