/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.lighting;

import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author mcory01
 */
public class LightTile {

    public LightTile(final String name, final Rectangle rectangle) {
        this.name = name;
        this.rectangle = rectangle;
    }

    public String getName() {
        return name;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    private final String name;
    private final Rectangle rectangle;
}
