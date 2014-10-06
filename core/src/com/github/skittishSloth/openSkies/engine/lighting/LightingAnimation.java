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
 * @param <T>
 * @param <K>
 */
public abstract class LightingAnimation<T extends LightTile, K extends Light> {
    
    protected LightingAnimation(final T tile, final K light) {
        this.tile = tile;
        this.light = light;
    }
    
    protected final T getTile() {
        return tile;
    }
    
    protected final K getLight() {
        return light;
    }
    
    public boolean isLightActive() {
        return light.isActive();
    }
    
    public void setLightActive(final boolean active) {
        light.setActive(active);
    }
    
    public abstract void update(final float delta);
    
    private final T tile;
    private final K light;
}
