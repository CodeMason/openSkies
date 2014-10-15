/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;
import com.github.skittishSloth.openSkies.engine.common.Direction;

/**
 *
 * @author mcory01
 */
public interface DirectionalSprite extends Disposable {

    AnimationState getAnimationState();

    Direction getCurrentDirection();

    int getHeight();

    TextureRegion getTextureRegion(final float deltaTime);

    int getWidth();

    boolean isAnimationFinished();

    boolean isMoveable();

    boolean isMoving();

    void setAnimationState(final AnimationState animationState);

    void setCurrentDirection(final Direction currentDirection);

    void setMoving(final boolean moving);
    
}
