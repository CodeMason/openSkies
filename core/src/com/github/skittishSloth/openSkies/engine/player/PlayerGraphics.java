/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.github.skittishSloth.openSkies.engine.common.Direction;
import com.github.skittishSloth.openSkies.engine.player.details.BaseDetails;
import com.github.skittishSloth.openSkies.engine.player.details.CharacterAppearanceData;
import com.github.skittishSloth.openSkies.engine.player.details.CharacterClothingData;
import com.github.skittishSloth.openSkies.engine.player.details.CharacterData;
import com.github.skittishSloth.openSkies.engine.sprites.AnimationState;
import com.github.skittishSloth.openSkies.engine.sprites.UniversalDirectionalSprite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author mcory01
 */
public class PlayerGraphics {
    
    private static final Logger log = LoggerFactory.getLogger(PlayerGraphics.class);
    
    public PlayerGraphics(final AssetManager assets) {
        this.assets = assets;
    }
    
    public void setCharacterData(final CharacterData characterData) {
        this.characterData = characterData;
        this.appearance = characterData.getAppearanceData();
        this.clothing = characterData.getClothingData();
    }
    
    public TextureRegion getTextureRegion(final float deltaTime) {
        return getMergedSprite().getTextureRegion(deltaTime);
    }

    public float getWidth() {
        return getMergedSprite().getWidth();
    }

    public int getHeight() {
        return getMergedSprite().getHeight();
    }

    public void setCurrentDirection(final Direction direction) {
        getMergedSprite().setCurrentDirection(direction);
    }

    public void setMoving(final boolean moving) {
        getMergedSprite().setMoving(moving);
    }

    public void setAnimationState(final AnimationState animationState) {
        currentAnimation = animationState;
        getMergedSprite().setAnimationState(animationState);
    }

    public boolean isAllAnimationFinished() {
        return getMergedSprite().isAnimationFinished();
    }

    public boolean needsUpdate() {
        return (!(isAllAnimationFinished()));
    }

    public UniversalDirectionalSprite getMergedSprite() {
        if ((mergedSprite == null) || (updateTextureRequired)) {
            mergeSprites();
        }

        return mergedSprite;
    }

    public boolean canMove() {
        final boolean bodySpriteMoveable = getMergedSprite().isMoveable();
        return (currentAnimation.isMoveable() || bodySpriteMoveable);
    }

    private void mergeSprites() {
        if (mergedSprite != null) {
            mergedSprite.dispose();
            mergedSprite = null;
        }
        
        if (appearance == null) {
            log.warn("Appearance data is null; nothing to merge.");
            return;
        }
        
        final Texture body = getAppearanceTexture(appearance.getSkinColor());
        final Texture ears = getAppearanceTexture(appearance.getEarDetails());
        final Texture hair = getAppearanceTexture(appearance.getHairColor());
        final Texture eyes = getAppearanceTexture(appearance.getEyeDetails());
        final Texture nose = getAppearanceTexture(appearance.getNose());
        
        final Texture torso = getAppearanceTexture(clothing.getShirtColor());
        final Texture legs = getAppearanceTexture(clothing.getPantsColor());
        final Texture feet = getAppearanceTexture(clothing.getShoeColor());
        
        final Texture[] textures = new Texture[]{
            body,
            ears,
            hair,
            eyes,
            nose,
            torso,
            legs,
            feet
        };

        mergedSprite = UniversalDirectionalSprite.createdMergedSprite(textures);
        updateTextureRequired = false;
    }
    
    private Texture getAppearanceTexture(final BaseDetails details) {
        if (details == null) {
            return null;
        }
        
        final String texturePath = details.getTexturePath(characterData);
        if (!assets.isLoaded(texturePath, Texture.class)) {
            assets.load(texturePath, Texture.class);
            assets.finishLoading();
            if (!assets.isLoaded(texturePath, Texture.class)) {
                return null;
            }
        }
        
        return assets.get(texturePath, Texture.class);
    }
    
    private UniversalDirectionalSprite mergedSprite;
    private boolean updateTextureRequired;
    private AnimationState currentAnimation = AnimationState.IDLE;
    
    private CharacterData characterData;
    private CharacterAppearanceData appearance;
    private CharacterClothingData clothing;
    
    private final AssetManager assets;
}
