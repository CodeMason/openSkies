/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.lighting;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author mcory01
 */
public class FlickerLightTile extends LightTile {

    public FlickerLightTile(final String name, final Rectangle rectangle, final float maxFlickerTime, final float maxFlickerDistance, final Color lightColor) {
        super(name, rectangle);

        this.maxFlickerTime = maxFlickerTime;
        this.maxFlickerDistance = maxFlickerDistance;
        this.lightColor = lightColor;
    }

    public float getMaxFlickerTime() {
        return maxFlickerTime;
    }

    public float getMaxFlickerDistance() {
        return maxFlickerDistance;
    }

    public Color getLightColor() {
        return lightColor;
    }

    private final float maxFlickerTime;
    private final float maxFlickerDistance;
    private final Color lightColor;
}
