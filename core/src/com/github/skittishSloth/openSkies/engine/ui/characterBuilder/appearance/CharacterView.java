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
import com.github.skittishSloth.openSkies.engine.player.details.EarDetails;
import com.github.skittishSloth.openSkies.engine.player.details.EyeDetails;
import com.github.skittishSloth.openSkies.engine.player.details.Gender;
import com.github.skittishSloth.openSkies.engine.player.details.HairColors;
import com.github.skittishSloth.openSkies.engine.player.details.HairStyles;
import com.github.skittishSloth.openSkies.engine.player.details.Nose;
import com.github.skittishSloth.openSkies.engine.player.details.PantsColors;
import com.github.skittishSloth.openSkies.engine.player.details.RaceDetails;
import com.github.skittishSloth.openSkies.engine.player.details.ShirtColors;
import com.github.skittishSloth.openSkies.engine.player.details.Shirts;
import com.github.skittishSloth.openSkies.engine.player.details.ShoeColors;
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
        buildData.setShirt(Shirts.LONGSLEEVE);
        buildData.setShirtColor(ShirtColors.values()[0]);
        buildData.setRace(assets.getDefaultRace());
        buildData.setSkinColor(assets.getDefaultSkinColor());
        buildData.setNose(Nose.STRAIGHT);
        buildData.setPantsColor(PantsColors.WHITE);
        buildData.setShoeColor(ShoeColors.BLACK);

        updateCurrentSprite();
    }
    
    public Collection<RaceDetails> getAvailableRaces() {
        return assets.getRaceDetails();
    }

    public Collection<SkinColorDetails> getAvailableColors() {
        if (buildData.getRace() == null) {
            return null;
        }

        return assets.getAvailableSkinColors(buildData.getRace());
    }
    
    public Collection<EarDetails> getAvailableEarDetails() {
        return assets.getEarDetails();
    }

    public Collection<Nose> getAvailableNoses() {
        return assets.getAvailableNoses();
    }
    
    public Collection<EyeDetails> getAvailableEyeDetails() {
        return assets.getEyeDetails();
    }
    
    public Collection<HairStyles> getAvailableHairStyles() {
        return assets.getAvailableHairStyles();
    }
    
    public Collection<HairColors> getAvailableHairColors() {
        return assets.getAvailableHairColors(buildData);
    }
    
    public Collection<ShirtColors> getAvailableShirtColors() {
        return assets.getAvailableShirtColors();
    }
    
    public Collection<PantsColors> getAvailablePantsColors() {
        return assets.getAvailablePantsColors();
    }
    
    public Collection<ShoeColors> getAvailableShoeColors() {
        return assets.getAvailableShoeColors();
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
        if (gender == Gender.MALE) {
            buildData.setShirt(Shirts.LONGSLEEVE);
        } else {
            buildData.setShirt(Shirts.SLEEVELESS);
        }
        
        doUpdate();
    }

    public void setRace(final RaceDetails race) {
        buildData.setRace(race);
        doUpdate();
    }
    
    public void setEye(final EyeDetails eye) {
        buildData.setEyeDetails(eye);
        doUpdate();
    }
    
    public void setEars(final EarDetails ears) {
        buildData.setEarDetails(ears);
        doUpdate();
    }

    public void setNose(final Nose nose) {
        buildData.setNose(nose);
        doUpdate();
    }
    
    public void setHairStyle(final HairStyles style) {
        buildData.setHairStyle(style);
        doUpdate();
    }
    
    public void setHairColor(final HairColors color) {
        buildData.setHairColor(color);
        doUpdate();
    }
    
    public void setShirtColor(final ShirtColors color) {
        buildData.setShirtColor(color);
        doUpdate();
    }
    
    public void setPantsColor(final PantsColors color) {
        buildData.setPantsColor(color);
        doUpdate();
    }
    
    public void setShoeColor(final ShoeColors color) {
        buildData.setShoeColor(color);
        doUpdate();
    }

    public SkinColorDetails getActiveColor() {
        return buildData.getSkinColor();
    }

    public EyeDetails getActiveEye() {
        return buildData.getEyeDetails();
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
    
    public ShirtColors getActiveShirtColor() {
        return buildData.getShirtColor();
    }
    
    public PantsColors getActivePantsColor() {
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
