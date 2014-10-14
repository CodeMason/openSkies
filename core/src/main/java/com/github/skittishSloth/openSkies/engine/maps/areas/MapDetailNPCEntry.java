/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.maps.areas;

import com.badlogic.gdx.math.Vector2;

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

    private String id;
    private Vector2 location;
}
