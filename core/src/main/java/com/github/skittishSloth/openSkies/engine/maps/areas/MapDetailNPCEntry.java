/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.maps.areas;

import com.badlogic.gdx.math.Vector2;
import java.util.Objects;

/**
 *
 * @author mcory01
 */
public class MapDetailNPCEntry {

    public MapDetailNPCEntry() {

    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public Vector2 getLocation() {
        return location;
    }

    public void setLocation(final Vector2 location) {
        this.location = location;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
        hash = 23 * hash + Objects.hashCode(this.location);
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
        final MapDetailNPCEntry other = (MapDetailNPCEntry) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        
        return Objects.equals(this.location, other.location);
    }

    private String id;
    private Vector2 location;
}
