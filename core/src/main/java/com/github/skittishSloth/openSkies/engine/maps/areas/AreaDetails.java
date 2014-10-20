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
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(final Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    private String name;
    private String startingMap;
    private ArrayList<MapDetails> maps;
}
