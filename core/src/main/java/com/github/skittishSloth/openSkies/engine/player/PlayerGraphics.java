/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.player;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.github.skittishSloth.openSkies.engine.common.Direction;
import com.github.skittishSloth.openSkies.engine.player.details.BaseDetails;
import com.github.skittishSloth.openSkies.engine.player.details.CharacterAppearanceData;
import com.github.skittishSloth.openSkies.engine.player.details.CharacterClothingData;
import com.github.skittishSloth.openSkies.engine.player.details.CharacterData;
import com.github.skittishSloth.openSkies.engine.sprites.AnimationState;
import com.github.skittishSloth.openSkies.engine.sprites.AtlasSprite;
import com.github.skittishSloth.openSkies.engine.sprites.DirectionalSprite;
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

    public DirectionalSprite getMergedSprite() {
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
        
        final TextureAtlas body = getAppearanceTextureAtlas(appearance.getSkinColor());
        final TextureAtlas ears = getAppearanceTextureAtlas(appearance.getEarDetails());
        final TextureAtlas hair = getAppearanceTextureAtlas(appearance.getHairColor());
        final TextureAtlas eyes = getAppearanceTextureAtlas(appearance.getEyeDetails());
        final TextureAtlas nose = getAppearanceTextureAtlas(appearance.getNose());
        
        final TextureAtlas torso = getAppearanceTextureAtlas(clothing.getShirtColor());
        final TextureAtlas legs = getAppearanceTextureAtlas(clothing.getPantsColor());
        final TextureAtlas feet = getAppearanceTextureAtlas(clothing.getShoeColor());
        
        final TextureAtlas[] atlases = new TextureAtlas[]{
            body,
            ears,
            hair,
            eyes,
            nose,
            torso,
            legs,
            feet
        };

//        mergedSprite = UniversalDirectionalSprite.createdMergedSprite(textures);
        mergedSprite = AtlasSprite.createdMergedSprite(atlases);
        updateTextureRequired = false;
    }
    
    private Texture getAppearanceTexture(final BaseDetails details) {
        if (details == null) {
            return null;
        }
        
        final String texturePath = details.getTextureAtlasPath(characterData);
        if (!assets.isLoaded(texturePath, Texture.class)) {
            assets.load(texturePath, Texture.class);
            assets.finishLoading();
            if (!assets.isLoaded(texturePath, Texture.class)) {
                return null;
            }
        }
        
        return assets.get(texturePath, Texture.class);
    }
    
    private TextureAtlas getAppearanceTextureAtlas(final BaseDetails details) {
        if (details == null) {
            return null;
        }
        
        final String textureAtlasPath = details.getTextureAtlasPath(characterData);
//        final String textureAtlasPath = texturePath.replace(".png", ".pack");
        if (!assets.isLoaded(textureAtlasPath, TextureAtlas.class)) {
            assets.load(textureAtlasPath, TextureAtlas.class);
            assets.finishLoading();
            if (!assets.isLoaded(textureAtlasPath, TextureAtlas.class)) {
                return null;
            }
        }
        
        return assets.get(textureAtlasPath, TextureAtlas.class);
    }
    
    private DirectionalSprite mergedSprite;
    private boolean updateTextureRequired;
    private AnimationState currentAnimation = AnimationState.IDLE;
    
    private CharacterData characterData;
    private CharacterAppearanceData appearance;
    private CharacterClothingData clothing;
    
    private final AssetManager assets;
}
