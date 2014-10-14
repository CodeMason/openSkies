/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.skittishSloth.openSkies.engine.sprites;

/**
 *
 * @author mcory01
 */
public enum AnimationState {
    IDLE("idle", true, 1, -1, true),
    WALKING("walk", true, 9, 2, true),
    SLASHING("slash", false, 6, 3, false),
//    SHOOTING("shoot", false, 13, 4, false),
    HURT("hurt", false, 6, 5, false),
    THRUSTING("thrust", false, 8, 1, false),
    CASTING("cast", false, 7, 0, false);
    
    private final String frameName;
    private final boolean loopable;
    private final int numFrames;
    private final int sectionIndex;
    private final boolean moveable;
    private final float frameDuration;
    
    private AnimationState(final String frameName, final boolean loopable, final int numFrames, final int sectionIndex, final boolean moveable) {
        this.frameName = frameName;
        this.loopable = loopable;
        this.numFrames = numFrames;
        this.sectionIndex = sectionIndex;
        this.moveable = moveable;
        this.frameDuration = 1 /15f;
    }
    
    private AnimationState(final String frameName, final boolean loopable, final int numFrames, final int sectionIndex, final boolean moveable, final float frameDuration) {
        this.frameName = frameName;
        this.loopable = loopable;
        this.numFrames = numFrames;
        this.sectionIndex = sectionIndex;
        this.moveable = moveable;
        this.frameDuration = frameDuration;
    }
    
    public String getFrameName() {
        return frameName;
    }
    
    public boolean isLoopable() {
        return loopable;
    }

    public int getNumFrames() {
        return numFrames;
    }

    public int getSectionIndex() {
        return sectionIndex;
    }

    public boolean isMoveable() {
        return moveable;
    }
    
    public float getFrameDuration() {
        return frameDuration;
    }
}
