/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;
import com.github.skittishSloth.openSkies.engine.sprites.AnimationState;
import com.github.skittishSloth.openSkies.engine.sprites.UniversalDirectionalSprite;

/**
 *
 * @author mcory01
 */
public class PlayerDescriptor implements Disposable {

    public PlayerDescriptor() {

    }
    
    public void setBody(final Texture body) {
        setBody(body, true);
    }

    public void setBody(final Texture body, final boolean mergeNow) {
        this.body = body;
        this.updateTextureRequired = true;
        if (mergeNow) {
            mergeSprites();
        }
    }
    
    public void setHair(final Texture hair) {
        setHair(hair, true);
    }

    public void setHair(final Texture hair, final boolean mergeNow) {
        this.hair = hair;
        this.updateTextureRequired = true;
        if (mergeNow) {
            mergeSprites();
        }
    }
    
    public void setEars(final Texture ears, final boolean mergeNow) {
        this.ears = ears;
        this.updateTextureRequired = true;
        if (mergeNow) {
            mergeSprites();
        }
    }
    
    public void setEyes(final Texture eyes) {
        setEyes(eyes, true);
    }

    public void setEyes(final Texture eyes, final boolean mergeNow) {
        this.eyes = eyes;
        this.updateTextureRequired = true;
        if (mergeNow) {
            mergeSprites();
        }
    }

    public void setNose(final Texture nose, final boolean mergeNow) {
        this.nose = nose;
        this.updateTextureRequired = true;
        if (mergeNow) {
            mergeSprites();
        }
    }

    public void setFacial(final Texture facial, final boolean mergeNow) {
        this.facial = facial;
        this.updateTextureRequired = true;
        if (mergeNow) {
            mergeSprites();
        }
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(final Gender gender) {
        this.gender = gender;
    }

    public UniversalDirectionalSprite getMergedSprite() {
        if ((mergedSprite == null) || (updateTextureRequired)) {
            mergeSprites();
        }

        return mergedSprite;
    }

    @Override
    public void dispose() {
        safeDispose(mergedSprite);
        safeDispose(body);
        safeDispose(eyes);
        safeDispose(facial);
        safeDispose(hair);
        safeDispose(nose);
    }

    public void mergeSprites() {
        if (mergedSprite != null) {
            mergedSprite.dispose();
        }
        
        final float frameRate = 1 / 15f;

        final Texture[] textures = new Texture[]{
            body,
            ears,
            hair,
            eyes,
            nose,
            facial
        };

        mergedSprite = UniversalDirectionalSprite.createdMergedSprite(frameRate, AnimationState.values(), textures);
        updateTextureRequired = false;
    }
    
    private static void safeDispose(final Disposable d) {
        if (d != null) {
            d.dispose();
        }
    }

    private Texture body;
    private Texture hair;
    private Texture ears;
    private Texture eyes;
    private Texture nose;
    private Texture facial;
    private UniversalDirectionalSprite mergedSprite;
    private boolean updateTextureRequired;
    private Gender gender;
}
