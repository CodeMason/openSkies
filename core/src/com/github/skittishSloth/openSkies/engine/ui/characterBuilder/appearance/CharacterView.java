/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.ui.characterBuilder.appearance;

import com.github.skittishSloth.openSkies.engine.player.details.CharacterAppearanceData;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.utils.Disposable;
import com.github.skittishSloth.openSkies.engine.player.details.BaseDetails;
import com.github.skittishSloth.openSkies.engine.player.details.ColoredDetails;
import com.github.skittishSloth.openSkies.engine.player.details.Gender;
import com.github.skittishSloth.openSkies.engine.player.details.SkinColorDetails;
import com.github.skittishSloth.openSkies.engine.sprites.AnimationState;
import com.github.skittishSloth.openSkies.engine.sprites.UniversalDirectionalSprite;
import com.github.skittishSloth.openSkies.engine.ui.UDSActor;
import com.github.skittishSloth.openSkies.engine.ui.characterBuilder.CharacterBuilderAssets;
import java.util.Collection;

/**
 *
 * @author mcory01
 */
public final class CharacterView extends Table implements Disposable {

    public CharacterView(final Skin skin, final CharacterAppearanceTable parent, final CharacterBuilderAssets assets) {
        super(skin);
        this.parent = parent;
        this.assets = assets;

        defaults().align(Align.center);
        buildData = new CharacterAppearanceData();

        spriteActor = new UDSActor(null);
        add(spriteActor).center();
        buildData.setGender(Gender.MALE);
        buildData.setShirt(assets.getDefaultShirt(buildData));
        buildData.setShirtColor(assets.getDefaultShirtColor());
        buildData.setRace(assets.getDefaultRace());
        buildData.setSkinColor(assets.getDefaultSkinColor());
        buildData.setNose(assets.getDefaultNose());
        buildData.setPantsColor(assets.getDefaultPantsColor());
        buildData.setShoeColor(assets.getDefaultShoeColor());

        updateCurrentSprite();
    }

    public Collection<BaseDetails> getAvailableRaces() {
        return assets.getRaceDetails();
    }

    public Collection<SkinColorDetails> getAvailableColors() {
        if (buildData.getRace() == null) {
            return null;
        }

        return assets.getAvailableSkinColors(buildData.getRace());
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

    public CharacterAppearanceData getCharacter() {
        return buildData;
    }

    public void setCharacterName(final String name) {
        buildData.setName(name);
    }

    public void setActiveColor(final SkinColorDetails color) {
        buildData.setSkinColor(color);
        doUpdate();
    }

    public void setGender(final Gender gender) {
        buildData.setGender(gender);
        buildData.setShirt(assets.getDefaultShirt(buildData));
        doUpdate();
    }

    public void setRace(final BaseDetails race) {
        buildData.setRace(race);
        final SkinColorDetails raceSkinColor = assets.normalizeColorForRace(buildData.getSkinColor(), race);
        buildData.setSkinColor(raceSkinColor);
        doUpdate();
    }

    public void setEye(final ColoredDetails eye) {
        buildData.setEyeDetails(eye);
        doUpdate();
    }

    public void setEars(final BaseDetails ears) {
        buildData.setEarDetails(ears);
        doUpdate();
    }

    public void setNose(final BaseDetails nose) {
        buildData.setNose(nose);
        doUpdate();
    }

    public void setHairStyle(final BaseDetails style) {
        buildData.setHairStyle(style);
        doUpdate();
    }

    public void setHairColor(final BaseDetails color) {
        buildData.setHairColor(color);
        doUpdate();
    }

    public void setShirtColor(final ColoredDetails color) {
        buildData.setShirtColor(color);
        doUpdate();
    }

    public void setPantsColor(final ColoredDetails color) {
        buildData.setPantsColor(color);
        doUpdate();
    }

    public void setShoeColor(final ColoredDetails color) {
        buildData.setShoeColor(color);
        doUpdate();
    }

    public SkinColorDetails getActiveColor() {
        return buildData.getSkinColor();
    }

    public ColoredDetails getActiveEye() {
        return buildData.getEyeDetails();
    }

    public BaseDetails getActiveNose() {
        return buildData.getNose();
    }

    public BaseDetails getActiveHairStyle() {
        return buildData.getHairStyle();
    }

    public BaseDetails getActiveHairColor() {
        return buildData.getHairColor();
    }

    public ColoredDetails getActiveShirtColor() {
        return buildData.getShirtColor();
    }

    public ColoredDetails getActivePantsColor() {
        return buildData.getPantsColor();
    }

    @Override
    public void dispose() {
        Gdx.app.log(getClass().getSimpleName(), "Disposing.");
    }

    private void updateCurrentSprite() {
        final UniversalDirectionalSprite sprite = assets.getBodySprite(buildData);
        initSpriteState(sprite);

        final UniversalDirectionalSprite eyes = assets.getEyeDetailsSprite(buildData);
        initSpriteState(eyes);

        final UniversalDirectionalSprite nose = assets.getNoseSprite(buildData);
        initSpriteState(nose);

        final UniversalDirectionalSprite ears = assets.getEarDetailsSprite(buildData);
        initSpriteState(ears);

        final UniversalDirectionalSprite hair = assets.getHairSprite(buildData);
        initSpriteState(hair);

        final UniversalDirectionalSprite shirt = assets.getShirtSprite(buildData);
        initSpriteState(shirt);

        final UniversalDirectionalSprite pants = assets.getPantsSprite(buildData);
        initSpriteState(pants);

        final UniversalDirectionalSprite shoes = assets.getShoeSprite(buildData);
        initSpriteState(shoes);

        spriteActor.setSprite(sprite);
        spriteActor.setEyeSprite(eyes);
        spriteActor.setNoseSprite(nose);
        spriteActor.setEarSprite(ears);
        spriteActor.setHairSprite(hair);
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

    private final CharacterAppearanceData buildData;
}
