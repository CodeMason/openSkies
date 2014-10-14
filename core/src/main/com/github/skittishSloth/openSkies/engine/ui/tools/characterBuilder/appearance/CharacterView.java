/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.ui.tools.characterBuilder.appearance;

import com.github.skittishSloth.openSkies.engine.player.details.CharacterAppearanceData;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.utils.Disposable;
import com.github.skittishSloth.openSkies.engine.player.details.BaseDetails;
import com.github.skittishSloth.openSkies.engine.player.details.CharacterClothingData;
import com.github.skittishSloth.openSkies.engine.player.details.ColoredDetails;
import com.github.skittishSloth.openSkies.engine.player.details.Gender;
import com.github.skittishSloth.openSkies.engine.player.details.SkinColorDetails;
import com.github.skittishSloth.openSkies.engine.sprites.AnimationState;
import com.github.skittishSloth.openSkies.engine.sprites.UniversalDirectionalSprite;
import com.github.skittishSloth.openSkies.engine.ui.UDSActor;
import com.github.skittishSloth.openSkies.engine.ui.tools.characterBuilder.CharacterBuilderAssets;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author mcory01
 */
public final class CharacterView extends Table implements Disposable {
    
    private static final Logger log = LoggerFactory.getLogger(CharacterView.class);

    public CharacterView(final Skin skin, final CharacterAppearanceTable parent, final CharacterBuilderAssets assets) {
        super(skin);
        this.parent = parent;
        this.assets = assets;

        defaults().align(Align.center);
        appearanceData = new CharacterAppearanceData();
        clothing = new CharacterClothingData();

        spriteActor = new UDSActor(null);
        add(spriteActor).center();
        appearanceData.setGender(Gender.MALE);
        clothing.setShirt(assets.getDefaultShirt(appearanceData));
        clothing.setShirtColor(assets.getDefaultShirtColor());
        appearanceData.setRace(assets.getDefaultRace());
        appearanceData.setSkinColor(assets.getDefaultSkinColor());
        appearanceData.setNose(assets.getDefaultNose());
        clothing.setPantsColor(assets.getDefaultPantsColor());
        clothing.setShoeColor(assets.getDefaultShoeColor());

        updateCurrentSprite();
    }

    public Collection<BaseDetails> getAvailableRaces() {
        return assets.getRaceDetails();
    }

    public Collection<SkinColorDetails> getAvailableColors() {
        if (appearanceData.getRace() == null) {
            return null;
        }

        return assets.getAvailableSkinColors(appearanceData.getRace());
    }

    public Collection<BaseDetails> getAvailableEarDetails() {
        return assets.getEarDetails();
    }

    public Collection<BaseDetails> getAvailableNoses() {
        return assets.getAvailableNoses();
    }

    public Collection<ColoredDetails> getAvailableEyeDetails() {
        return assets.getEyeDetails();
    }

    public Collection<BaseDetails> getAvailableHairStyles() {
        return assets.getAvailableHairStyles();
    }

    public Collection<BaseDetails> getAvailableHairColors() {
        return assets.getAvailableHairColors();
    }

    public Collection<BaseDetails> getAvailableFacialHairStyles() {
        return assets.getFacialHairStyleDetails();
    }

    public Collection<BaseDetails> getAvailableFacialHairColors() {
        return assets.getFacialHairColorDetails();
    }

    public Collection<ColoredDetails> getAvailableShirtColors() {
        return assets.getAvailableShirtColors();
    }

    public Collection<ColoredDetails> getAvailablePantsColors() {
        return assets.getAvailablePantsColors();
    }

    public Collection<ColoredDetails> getAvailableShoeColors() {
        return assets.getAvailableShoeColors();
    }
    
    public BaseDetails getHairColorByDisplayName(final String displayName) {
        return assets.getHairColorByDisplayName(displayName);
    }
    
    public BaseDetails getHairStyleByDisplayName(final String displayName) {
        return assets.getHairStyleByDisplayName(displayName);
    }
    
    public BaseDetails getFacialHairColorByDisplayName(final String displayName) {
        return assets.getFacialHairColorByDisplayName(displayName);
    }
    
    public BaseDetails getFacialHairStyleByDisplayName(final String displayName) {
        return assets.getFacialHairStyleByDisplayName(displayName);
    }

    public CharacterAppearanceData getCharacterAppearance() {
        return appearanceData;
    }
    
    public CharacterClothingData getCharacterClothing() {
        return clothing;
    }

    public void setCharacterName(final String name) {
        appearanceData.setName(name);
    }

    public void setActiveColor(final SkinColorDetails color) {
        appearanceData.setSkinColor(color);
        doUpdate();
    }

    public void setGender(final Gender gender) {
        appearanceData.setGender(gender);
        clothing.setShirt(assets.getDefaultShirt(appearanceData));
        doUpdate();
    }

    public void setRace(final BaseDetails race) {
        appearanceData.setRace(race);
        final SkinColorDetails raceSkinColor = assets.normalizeColorForRace(appearanceData.getSkinColor(), race);
        appearanceData.setSkinColor(raceSkinColor);
        doUpdate();
    }

    public void setEye(final ColoredDetails eye) {
        appearanceData.setEyeDetails(eye);
        doUpdate();
    }

    public void setEars(final BaseDetails ears) {
        appearanceData.setEarDetails(ears);
        doUpdate();
    }

    public void setNose(final BaseDetails nose) {
        appearanceData.setNose(nose);
        doUpdate();
    }

    public void setHairStyle(final BaseDetails style) {
        appearanceData.setHairStyle(style);
        doUpdate();
    }

    public void setHairColor(final BaseDetails color) {
        appearanceData.setHairColor(color);
        doUpdate();
    }

    public void setFacialHairStyle(final BaseDetails style) {
        appearanceData.setFacialHairStyle(style);
        doUpdate();
    }

    public void setFacialHairColor(final BaseDetails color) {
        appearanceData.setFacialHairColor(color);
        doUpdate();
    }

    public void setShirtColor(final ColoredDetails color) {
        clothing.setShirtColor(color);
        doUpdate();
    }

    public void setPantsColor(final ColoredDetails color) {
        clothing.setPantsColor(color);
        doUpdate();
    }

    public void setShoeColor(final ColoredDetails color) {
        clothing.setShoeColor(color);
        doUpdate();
    }

    public SkinColorDetails getActiveColor() {
        return appearanceData.getSkinColor();
    }

    public ColoredDetails getActiveEye() {
        return appearanceData.getEyeDetails();
    }

    public BaseDetails getActiveNose() {
        return appearanceData.getNose();
    }

    public BaseDetails getActiveHairStyle() {
        return appearanceData.getHairStyle();
    }

    public BaseDetails getActiveHairColor() {
        return appearanceData.getHairColor();
    }

    public ColoredDetails getActiveShirtColor() {
        return clothing.getShirtColor();
    }

    public ColoredDetails getActivePantsColor() {
        return clothing.getPantsColor();
    }

    @Override
    public void dispose() {
        log.debug(getClass().getSimpleName(), "Disposing.");
    }

    private void updateCurrentSprite() {
        final UniversalDirectionalSprite sprite = assets.getBodySprite(appearanceData);
        initSpriteState(sprite);

        final UniversalDirectionalSprite eyes = assets.getEyeDetailsSprite(appearanceData);
        initSpriteState(eyes);

        final UniversalDirectionalSprite nose = assets.getNoseSprite(appearanceData);
        initSpriteState(nose);

        final UniversalDirectionalSprite ears = assets.getEarDetailsSprite(appearanceData);
        initSpriteState(ears);

        final UniversalDirectionalSprite hair = assets.getHairSprite(appearanceData);
        initSpriteState(hair);
        
        final UniversalDirectionalSprite facialHair = assets.getFacialHairSprite(appearanceData);
        initSpriteState(facialHair);

        final UniversalDirectionalSprite shirt = assets.getShirtSprite(appearanceData, clothing);
        initSpriteState(shirt);

        final UniversalDirectionalSprite pants = assets.getPantsSprite(appearanceData, clothing);
        initSpriteState(pants);

        final UniversalDirectionalSprite shoes = assets.getShoeSprite(appearanceData, clothing);
        initSpriteState(shoes);

        spriteActor.setSprite(sprite);
        spriteActor.setEyeSprite(eyes);
        spriteActor.setNoseSprite(nose);
        spriteActor.setEarSprite(ears);
        spriteActor.setHairSprite(hair);
        spriteActor.setFacialHairSprite(facialHair);
        spriteActor.setShirtSprite(shirt);
        spriteActor.setPantsSprite(pants);
        spriteActor.setShoeSprite(shoes);
    }

    private void initSpriteState(final UniversalDirectionalSprite sprite) {
        if (sprite == null) {
            return;
        }

        sprite.setMoving(true);
        sprite.setAnimationState(AnimationState.WALKING);
    }

    private void doUpdate() {
        updateCurrentSprite();
        parent.updateSettings();
    }

    private final UDSActor spriteActor;
    private final CharacterAppearanceTable parent;
    private final CharacterBuilderAssets assets;

    private final CharacterAppearanceData appearanceData;
    private final CharacterClothingData clothing;
}
