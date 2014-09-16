/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.skittishSloth.openSkies.engine.ui.characterBuilder;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.github.skittishSloth.openSkies.engine.common.GdxUtils;
import com.github.skittishSloth.openSkies.engine.player.details.Ears;
import com.github.skittishSloth.openSkies.engine.player.details.Eye;
import com.github.skittishSloth.openSkies.engine.player.details.Gender;
import com.github.skittishSloth.openSkies.engine.player.details.HairColors;
import com.github.skittishSloth.openSkies.engine.player.details.HairStyles;
import com.github.skittishSloth.openSkies.engine.player.details.Nose;
import com.github.skittishSloth.openSkies.engine.player.details.Race;
import com.github.skittishSloth.openSkies.engine.player.details.SkinColor;
import com.github.skittishSloth.openSkies.engine.ui.characterBuilder.partDetails.EyeListDetails;
import java.util.Collection;

/**
 *
 * @author mcory01
 */
public final class CharacterBuildTable extends Table implements Disposable {
    
    public CharacterBuildTable(final Skin skin, final CharacterBuilderAssets assets) {
        super(skin);
        
        clearChildren();
        
        final int width = Gdx.graphics.getWidth();
        final int height = Gdx.graphics.getHeight();
        
        settings = new CharacterSettingsView(skin, this, assets);
        view = new CharacterView(skin, this, assets);
        controls = new CharacterBuildControls(skin);
        add(view).center().width(width * 0.4f).expandY();
        add(settings).top().width(width * 0.6f).expandY();
        row();
        add(controls).bottom().right().colspan(2);
        
        updateSettings();
    }
    
    public void setAvailableColors(final Collection<SkinColor> colors) {
        settings.setAvailableColors(colors, null);
    } 
    
    public Collection<SkinColor> getAvailableColors() {
        return view.getAvailableColors();
    }
    
    public void setCharacterColor(final SkinColor color) {
        view.setActiveColor(color);
    }
    
    public void setCharacterGender(final Gender gender) {
        Gdx.app.log(getClass().getSimpleName(), "Changing gender to " + gender);
        view.setGender(gender);
    }

    public void setCharacterRace(final Race race) {
        Gdx.app.log(getClass().getSimpleName(), "Changing race to " + race);
        view.setRace(race);
    }
    
    public void updateSettings() {
        Gdx.app.log(getClass().getSimpleName(), "Updating settings view.");
        final SkinColor activeColor = view.getActiveColor();
        final Eye activeEye = view.getActiveEye();
        final Ears activeEars = view.getActiveEars();
        final Nose activeNose = view.getActiveNose();
        final HairStyles activeHairStyle = view.getActiveHairStyle();
        final HairColors activeHairColor = view.getActiveHairColor();
        settings.update(activeColor, activeEye, activeEars, activeNose, activeHairStyle, activeHairColor);
    }

    public Collection<EyeListDetails> getAvailableEyes() {
        return view.getAvailableEyes();
    }
    
    public Collection<Ears> getAvailableEars() {
        return view.getAvailableEars();
    }
    
    public Collection<Nose> getAvailableNoses() {
        return view.getAvailableNoses();
    }
    
    public Collection<HairStyles> getAvailableHairStyles() {
        return view.getAvailableHairStyles();
    }
    
    public Collection<HairColors> getAvailableHairColors() {
        return view.getAvailableHairColors();
    }
    
    public void setCharacterEye(final Eye eye) {
        view.setEye(eye);
    }
    
    public void setCharacterEars(final Ears ears) {
        view.setEars(ears);
    }
    
    public void setCharacterNose(final Nose nose) {
        view.setNose(nose);
    }
    
    public void setCharacterHairStyle(final HairStyles style) {
        view.setHairStyle(style);
    }
    
    public void setCharacterHairColor(final HairColors color) {
        view.setHairColor(color);
    }
    
    @Override
    public void dispose() {
        Gdx.app.log(getClass().getSimpleName(), "Disposing");
        GdxUtils.safeDispose(view);
        GdxUtils.safeDispose(settings);
        GdxUtils.safeDispose(controls);
    }
    
    private final CharacterSettingsView settings;
    private final CharacterView view;
    private final CharacterBuildControls controls;
}
