/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.maps.areas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(final Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    private String name;
    private String relativePath;
    private ArrayList<MapDetailNPCEntry> npcs;
}
