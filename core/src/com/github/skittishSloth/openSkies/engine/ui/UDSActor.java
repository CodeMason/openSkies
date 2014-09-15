/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.skittishSloth.openSkies.engine.ui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.github.skittishSloth.openSkies.engine.common.Direction;
import com.github.skittishSloth.openSkies.engine.sprites.AnimationState;
import com.github.skittishSloth.openSkies.engine.sprites.UniversalDirectionalSprite;

/**
 *
 * @author mcory01
 */
public class UDSActor extends Actor {
    
    public UDSActor() {
        super();
    }
    
    public UDSActor(final UniversalDirectionalSprite sprite) {
        this();
        this.sprite = sprite;
    }
    
    public void changeState(final AnimationState state) {
        if (sprite == null) {
            throw new IllegalStateException("Sprite has not been initialized yet.");
        }
        sprite.setAnimationState(state);
    }
    
    public void changeDirection(final Direction direction) {
        if (sprite == null) {
            throw new IllegalStateException("Sprite has not been initialized yet.");
        }
        sprite.setCurrentDirection(direction);
    }
    
    public void setSprite(final UniversalDirectionalSprite sprite) {
        this.sprite = sprite;
    }

    @Override
    public void act(final float delta) {
        deltaTime = delta;
    }

    @Override
    public void draw(final Batch batch, final float parentAlpha) {
        if (sprite != null) {
            final TextureRegion region = sprite.getTextureRegion(deltaTime);
            batch.draw(region, getX(), getY());
        }
    }
    
    private float deltaTime = 0f;
    private UniversalDirectionalSprite sprite;
    
}