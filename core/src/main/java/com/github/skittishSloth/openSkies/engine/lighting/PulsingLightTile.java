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
public class PulsingLightTile extends LightTile {

    public PulsingLightTile(final String name, final Rectangle rectangle, final float pulseCycleTime, final float maxDistance, final Color lightColor) {
        super(name, rectangle);

        this.pulseCycleTime = pulseCycleTime;
        this.maxDistance = maxDistance;
        this.lightColor = lightColor;
    }

    public float getPulseCycleTime() {
        return pulseCycleTime;
    }

    public float getMaxDistance() {
        return maxDistance;
    }

    public Color getLightColor() {
        return lightColor;
    }
    
    private final float pulseCycleTime;
    private final float maxDistance;
    private final Color lightColor;
}
