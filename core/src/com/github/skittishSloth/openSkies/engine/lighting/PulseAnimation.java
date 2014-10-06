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
public class PulseAnimation extends LightingAnimation<PulsingLightTile, Light> {

    public PulseAnimation(final PulsingLightTile tile, final Light light) {
        super(tile, light);
        light.setColor(tile.getLightColor());
    }
    
    @Override
    public void update(final float delta) {
        lightPulseTime += delta;
        final PulsingLightTile tile = getTile();
        final Light light = getLight();
        
        if (lightPulseTime > tile.getPulseCycleTime()) {
            lightPulseUp = !(lightPulseUp);
            lightPulseTime = 0f;
        }

        if (!light.isActive()) {
            return;
        }
        
        final float startDistance;
        final float endDistance;
        if (lightPulseUp) {
            startDistance = 0f;
            endDistance = tile.getMaxDistance();
        } else {
            startDistance = tile.getMaxDistance();
            endDistance = 0f;
        }
        
        final float distance = MathUtils.lerp(startDistance, endDistance, lightPulseTime);
        light.setDistance(distance);
        
    }

    private float lightPulseTime;
    private boolean lightPulseUp;
}
