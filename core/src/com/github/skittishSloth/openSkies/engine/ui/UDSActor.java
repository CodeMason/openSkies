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
        
        if (eyeSprite != null) {
            eyeSprite.setAnimationState(state);
        }
    }
    
    public void changeDirection(final Direction direction) {
        if (sprite == null) {
            throw new IllegalStateException("Sprite has not been initialized yet.");
        }
        sprite.setCurrentDirection(direction);
        
        if (eyeSprite != null) {
            eyeSprite.setCurrentDirection(direction);
        }
    }
    
    public void setSprite(final UniversalDirectionalSprite sprite) {
        this.sprite = sprite;
    }
    
    public void setEyeSprite(final UniversalDirectionalSprite eyeSprite) {
        this.eyeSprite = eyeSprite;
    }
    
    public void setEarSprite(final UniversalDirectionalSprite earSprite) {
        this.earSprite = earSprite;
    }
    
    public void setNoseSprite(final UniversalDirectionalSprite noseSprite) {
        this.noseSprite = noseSprite;
    }
    
    public void setHairSprite(final UniversalDirectionalSprite hairSprite) {
        this.hairSprite = hairSprite;
    }
    
    public void setShirtSprite(final UniversalDirectionalSprite shirtSprite) {
        this.shirtSprite = shirtSprite;
    }
    
    public void setPantsSprite(final UniversalDirectionalSprite pantsSprite) {
        this.pantsSprite = pantsSprite;
    }
    
    public void setShoeSprite(final UniversalDirectionalSprite shoeSprite) {
        this.shoeSprite = shoeSprite;
    }

    @Override
    public void act(final float delta) {
        deltaTime = delta;
    }

    @Override
    public void draw(final Batch batch, final float parentAlpha) {
        safeDraw(batch, sprite);
        safeDraw(batch, eyeSprite);
        safeDraw(batch, earSprite);
        safeDraw(batch, noseSprite);
        safeDraw(batch, shirtSprite);
        safeDraw(batch, shoeSprite);
        safeDraw(batch, pantsSprite);
        safeDraw(batch, hairSprite);
    }
    
    private void safeDraw(final Batch batch, final UniversalDirectionalSprite spriteToDraw) {
        if (spriteToDraw != null) {
            final TextureRegion region = spriteToDraw.getTextureRegion(deltaTime);
            batch.draw(region, getX(), getY());
        }
    }
    
    private float deltaTime = 0f;
    private UniversalDirectionalSprite sprite, eyeSprite, earSprite, noseSprite, hairSprite, shirtSprite, pantsSprite, shoeSprite;
}
