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

    public void setNpcs(Collection<MapDetailNPCEntry> npcs) {
        if (this.npcs == null) {
            this.npcs = new ArrayList<MapDetailNPCEntry>();
        } else {
            this.npcs.clear();
        }
        
        if (npcs != null) {
            this.npcs.addAll(npcs);
        }
    }

    private String name;
    private String relativePath;
    private ArrayList<MapDetailNPCEntry> npcs;
}
