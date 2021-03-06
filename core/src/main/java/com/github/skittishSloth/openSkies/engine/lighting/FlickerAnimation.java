/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.lighting;

import box2dLight.Light;
import com.badlogic.gdx.math.MathUtils;

/**
 *
 * @author mcory01
 */
public class FlickerAnimation extends LightingAnimation<FlickerLightTile, Light> {

    public FlickerAnimation(final FlickerLightTile tile, final Light light) {
        super(tile, light);
        light.setColor(tile.getLightColor());
    }

    @Override
    public void update(final float delta) {
        final Light light = getLight();
        if (!light.isActive()) {
            return;
        }
        currentFlickerTime += delta;
        final FlickerLightTile tile = getTile();
        
        if (currentFlickerTime > currentDuration) {
            if (!flickerUp) {
                currentDuration = MathUtils.random(tile.getMaxFlickerTime());
                currentDistance = MathUtils.random(tile.getMaxFlickerDistance());
            }
            flickerUp = !(flickerUp);
            currentFlickerTime = 0f;
        }

        if (!light.isActive()) {
            return;
        }
        
        final float startDistance;
        final float endDistance;
        if (flickerUp) {
            startDistance = 0f;
            endDistance = currentDistance;
        } else {
            startDistance = currentDistance;
            endDistance = 0f;
        }
        
        final float distance = MathUtils.lerp(startDistance, endDistance, currentFlickerTime);
        light.setDistance(distance);
    }
    
    private boolean flickerUp = true;
    private float currentDuration = 0f;
    private float currentFlickerTime = 0f;
    private float currentDistance = 0f;
}
