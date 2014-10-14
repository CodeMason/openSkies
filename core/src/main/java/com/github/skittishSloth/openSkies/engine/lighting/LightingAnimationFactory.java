/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.lighting;

import box2dLight.Light;

/**
 *
 * @author mcory01
 */
public class LightingAnimationFactory {
    
    public static LightingAnimation createAnimation(final LightTile tile, final Light light) {
        final LightingAnimation res;
        if (tile instanceof PulsingLightTile) {
            final PulsingLightTile plt = PulsingLightTile.class.cast(tile);
            res = new PulseAnimation(plt, light);
        } else if (tile instanceof FlickerLightTile) {
            final FlickerLightTile flt = FlickerLightTile.class.cast(tile);
            res = new FlickerAnimation(flt, light);
        } else {
            res = null;
        }
        
        return res;
    }
}
