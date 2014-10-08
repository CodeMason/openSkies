/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.quests;

import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author mcory01
 */
public class RetrievalAction extends MapSpecificAction {

    public RetrievalAction() {

    }

    public Vector2 getLocation() {
        return location;
    }

    public void setLocation(final Vector2 location) {
        this.location = location;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(final String itemName) {
        this.itemName = itemName;
    }

    private Vector2 location;
    private String itemName;
}
