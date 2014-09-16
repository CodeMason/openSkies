/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.ui.characterBuilder;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.utils.Disposable;
import com.github.skittishSloth.openSkies.engine.player.details.Ears;
import com.github.skittishSloth.openSkies.engine.player.details.Eye;
import com.github.skittishSloth.openSkies.engine.player.details.Gender;
import com.github.skittishSloth.openSkies.engine.player.details.HairColors;
import com.github.skittishSloth.openSkies.engine.player.details.HairStyles;
import com.github.skittishSloth.openSkies.engine.player.details.Nose;
import com.github.skittishSloth.openSkies.engine.player.details.Race;
import com.github.skittishSloth.openSkies.engine.player.details.SkinColor;
import com.github.skittishSloth.openSkies.engine.sprites.AnimationState;
import com.github.skittishSloth.openSkies.engine.sprites.UniversalDirectionalSprite;
import com.github.skittishSloth.openSkies.engine.ui.UDSActor;
import com.github.skittishSloth.openSkies.engine.ui.characterBuilder.partDetails.EyeListDetails;
import java.util.Collection;

/**
 *
 * @author mcory01
 */
public final class CharacterView extends Table implements Disposable {

    public CharacterView(final Skin skin, final CharacterBuildTable parent, final CharacterBuilderAssets assets) {
        super(skin);
        this.parent = parent;
        this.assets = assets;

        defaults().align(Align.center);
        buildData = new CharacterBuildData();

        spriteActor = new UDSActor(null);
        add(spriteActor).center();
        buildData.setGender(Gender.MALE);
        buildData.setRace(Race.HUMAN);
        buildData.setSkinColor(SkinColor.LIGHT);
        buildData.setNose(Nose.STRAIGHT);

        updateCurrentSprite();
    }

    public Collection<SkinColor> getAvailableColors() {
        if (buildData.getRace() == null) {
            return null;
        }

        return assets.getAvailableColors(buildData.getRace());
    }

    public Collection<Ears> getAvailableEars() {
        return assets.getAvailableEars();
    }

    public Collection<Nose> getAvailableNoses() {
        return assets.getAvailableNoses();
    }

    public Collection<EyeListDetails> getAvailableEyes() {
        if (buildData.getGender() == null) {
            return null;
        }

        return assets.getAvailableEyes(buildData.getGender());
    }
    
    public Collection<HairStyles> getAvailableHairStyles() {
        return assets.getAvailableHairStyles();
    }
    
    public Collection<HairColors> getAvailableHairColors() {
        return assets.getAvailableHairColors(buildData);
    }

    public void setActiveColor(final SkinColor color) {
        buildData.setSkinColor(color);
        updateCurrentSprite();
        parent.updateSettings();
    }

    public void setGender(final Gender gender) {
        buildData.setGender(gender);
        updateCurrentSprite();
        parent.updateSettings();
    }

    public void setRace(final Race race) {
        buildData.setRace(race);
        updateCurrentSprite();
        parent.updateSettings();
    }

    public void setEye(final Eye eye) {
        buildData.setEyes(eye);
        updateCurrentSprite();
        parent.updateSettings();
    }

    public void setEars(final Ears ears) {
        buildData.setEars(ears);
        updateCurrentSprite();
        parent.updateSettings();
    }

    public void setNose(final Nose nose) {
        buildData.setNose(nose);
        updateCurrentSprite();
        parent.updateSettings();
    }
    
    public void setHairStyle(final HairStyles style) {
        buildData.setHairStyle(style);
        updateCurrentSprite();
        parent.updateSettings();
    }
    
    public void setHairColor(final HairColors color) {
        buildData.setHairColor(color);
        updateCurrentSprite();
        parent.updateSettings();
    }

    public SkinColor getActiveColor() {
        return buildData.getSkinColor();
    }

    public Eye getActiveEye() {
        return buildData.getEyes();
    }

    public Ears getActiveEars() {
        return buildData.getEars();
    }

    public Nose getActiveNose() {
        return buildData.getNose();
    }
    
    public HairStyles getActiveHairStyle() {
        return buildData.getHairStyle();
    }
    
    public HairColors getActiveHairColor() {
        return buildData.getHairColor();
    }

    @Override
    public void dispose() {
        Gdx.app.log(getClass().getSimpleName(), "Disposing.");
    }

    private void updateCurrentSprite() {
        final UniversalDirectionalSprite sprite = assets.getBodySprite(buildData);
        initSpriteState(sprite);
        
        final UniversalDirectionalSprite eyes = assets.getEyesSprite(buildData);
        initSpriteState(eyes);
        
        final UniversalDirectionalSprite nose = assets.getNoseSprite(buildData);
        initSpriteState(nose);
        
        final UniversalDirectionalSprite ears = assets.getEarSprite(buildData);
        initSpriteState(ears);
        
        final UniversalDirectionalSprite hair = assets.getHairSprite(buildData);
        initSpriteState(hair);

        spriteActor.setSprite(sprite);
        spriteActor.setEyeSprite(eyes);
        spriteActor.setNoseSprite(nose);
        spriteActor.setEarSprite(ears);
        spriteActor.setHairSprite(hair);
    }
    
    private void initSpriteState(final UniversalDirectionalSprite sprite) {
        if (sprite == null) {
            return;
        }
        
        sprite.setMoving(true);
        sprite.setAnimationState(AnimationState.WALKING);
    }

    private final UDSActor spriteActor;
    private final CharacterBuildTable parent;
    private final CharacterBuilderAssets assets;

    private final CharacterBuildData buildData;
}
