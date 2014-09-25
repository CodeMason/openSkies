/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.skittishSloth.openSkies.engine.ui.characterBuilder.appearance;

import com.github.skittishSloth.openSkies.engine.player.details.CharacterAppearanceData;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.github.skittishSloth.openSkies.engine.common.GdxUtils;
import com.github.skittishSloth.openSkies.engine.player.details.BaseDetails;
import com.github.skittishSloth.openSkies.engine.player.details.ColoredDetails;
import com.github.skittishSloth.openSkies.engine.player.details.Gender;
import com.github.skittishSloth.openSkies.engine.player.details.SkinColorDetails;
import com.github.skittishSloth.openSkies.engine.ui.characterBuilder.CharacterBuilderAssets;
import java.util.Collection;

/**
 *
 * @author mcory01
 */
public final class CharacterAppearanceTable extends Table implements Disposable {
    
    public CharacterAppearanceTable(final Skin skin, final CharacterAppearanceScreen parentScreen, final CharacterBuilderAssets assets) {
        super(skin);
        
        this.parentScreen = parentScreen;
        
        clearChildren();
        
        final int width = Gdx.graphics.getWidth();
        
        final Label label = new Label("Appearance", skin);
        add(label).top().left().padLeft(5f);
        row();
        
        settings = new CharacterAppearanceSettings(skin, this);
        view = new CharacterView(skin, this, assets);
        controls = new CharacterAppearanceControls(skin, this);
        add(view).center().width(width * 0.4f).expandY();
        add(settings).top().width(width * 0.6f).expandY();
        row();
        add(controls).bottom().right().colspan(2).pad(5f);
        
        settingsValidator = new CharacterAppearanceSettingsValidator(settings);
        updateSettings();
    }
    
    public Collection<BaseDetails> getAvailableRaces() {
        return view.getAvailableRaces();
    }
    
    public Collection<SkinColorDetails> getAvailableColors() {
        return view.getAvailableColors();
    }
    
    public void setCharacterSkinColor(final SkinColorDetails color) {
        view.setActiveColor(color);
    }
    
    public void setCharacterGender(final Gender gender) {
        view.setGender(gender);
    }

    public void setCharacterRace(final BaseDetails race) {
        view.setRace(race);
    }
    
    public void updateSettings() {
        final CharacterAppearanceData buildData = view.getCharacter();
        settings.update(buildData);
    }
    
    public Collection<ColoredDetails> getAvailableEyeDetails() {
        return view.getAvailableEyeDetails();
    }
    
    public Collection<BaseDetails> getAvailableEarDetails() {
        return view.getAvailableEarDetails();
    }
    
    public Collection<BaseDetails> getAvailableNoses() {
        return view.getAvailableNoses();
    }
    
    public Collection<BaseDetails> getAvailableHairStyles() {
        return view.getAvailableHairStyles();
    }
    
    public Collection<BaseDetails> getAvailableHairColors() {
        return view.getAvailableHairColors();
    }
    
    public Collection<ColoredDetails> getAvailableShirtColors() {
        return view.getAvailableShirtColors();
    }
    
    public Collection<ColoredDetails> getAvailablePantsColors() {
        return view.getAvailablePantsColors();
    }
    
    public Collection<ColoredDetails> getAvailableShoeColors() {
        return view.getAvailableShoeColors();
    }
    
    public void setCharacterName(final String name) {
        view.setCharacterName(name);
    }

    public void setCharacterEyeDetails(final ColoredDetails eye) {
        view.setEye(eye);
    }
    
    public void setCharacterEarDetails(final BaseDetails ears) {
        view.setEars(ears);
    }
    
    public void setCharacterNose(final BaseDetails nose) {
        view.setNose(nose);
    }
    
    public void setCharacterHairStyle(final BaseDetails style) {
        view.setHairStyle(style);
    }
    
    public void setCharacterHairColor(final BaseDetails color) {
        view.setHairColor(color);
    }
    
    public void setCharacterShirtColor(final ColoredDetails color) {
        view.setShirtColor(color);
    }
    
    public void setCharacterPantsColor(final ColoredDetails color) {
        view.setPantsColor(color);
    }
    
    public void setCharacterShoeColor(final ColoredDetails color) {
        view.setShoeColor(color);
    }
    
    public CharacterAppearanceData getCurrentState() {
        return view.getCharacter();
    }
    
    public BaseDetails getHairColorByDisplayName(final String displayName) {
        return view.getHairColorByDisplayName(displayName);
    }
    
    public BaseDetails getHairStyleByDisplayName(final String displayName) {
        return view.getHairStyleByDisplayName(displayName);
    }
    
    public boolean validateSettings() {
        final CharacterAppearanceData data = view.getCharacter();
        return settingsValidator.validate(data);
    }
    
    public void handleNext() {
        if (validateSettings()) {
            parentScreen.appearanceScreenNext();
        }
    }
    
    public void handleCancel() {
        parentScreen.appearanceScreenCancel();
    }
    
    @Override
    public void dispose() {
        Gdx.app.log(getClass().getSimpleName(), "Disposing");
        GdxUtils.safeDispose(view);
        GdxUtils.safeDispose(settings);
    }
    
    private final CharacterAppearanceSettings settings;
    private final CharacterView view;
    private final CharacterAppearanceControls controls;
    private final CharacterAppearanceScreen parentScreen;
    private final CharacterAppearanceSettingsValidator settingsValidator;
}
