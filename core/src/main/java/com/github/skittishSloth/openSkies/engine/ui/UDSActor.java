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
import com.github.skittishSloth.openSkies.engine.sprites.DirectionalSprite;

/**
 *
 * @author mcory01
 */
public class UDSActor extends Actor {
    
    public UDSActor() {
        super();
    }
    
    public UDSActor(final DirectionalSprite sprite) {
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
    
    public void setSprite(final DirectionalSprite sprite) {
        this.sprite = sprite;
    }
    
    public void setEyeSprite(final DirectionalSprite eyeSprite) {
        this.eyeSprite = eyeSprite;
    }
    
    public void setEarSprite(final DirectionalSprite earSprite) {
        this.earSprite = earSprite;
    }
    
    public void setNoseSprite(final DirectionalSprite noseSprite) {
        this.noseSprite = noseSprite;
    }
    
    public void setHairSprite(final DirectionalSprite hairSprite) {
        this.hairSprite = hairSprite;
    }
    
    public void setFacialHairSprite(final DirectionalSprite hairSprite) {
        this.facialHairSprite = hairSprite;
    }
    
    public void setShirtSprite(final DirectionalSprite shirtSprite) {
        this.shirtSprite = shirtSprite;
    }
    
    public void setPantsSprite(final DirectionalSprite pantsSprite) {
        this.pantsSprite = pantsSprite;
    }
    
    public void setShoeSprite(final DirectionalSprite shoeSprite) {
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
        safeDraw(batch, facialHairSprite);
        safeDraw(batch, hairSprite);
    }
    
    private void safeDraw(final Batch batch, final DirectionalSprite spriteToDraw) {
        if (spriteToDraw != null) {
            final TextureRegion region = spriteToDraw.getTextureRegion(deltaTime);
            batch.draw(region, getX(), getY());
        }
    }
    
    private float deltaTime = 0f;
    private DirectionalSprite sprite, eyeSprite, earSprite, noseSprite, hairSprite, facialHairSprite, shirtSprite, pantsSprite, shoeSprite;
}
