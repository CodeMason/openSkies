/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.skittishSloth.openSkies.engine.ui.characterBuilder;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.github.skittishSloth.openSkies.engine.player.Ears;
import com.github.skittishSloth.openSkies.engine.player.Eye;
import com.github.skittishSloth.openSkies.engine.player.Gender;
import com.github.skittishSloth.openSkies.engine.player.Nose;
import com.github.skittishSloth.openSkies.engine.player.Race;
import com.github.skittishSloth.openSkies.engine.ui.characterBuilder.partDetails.EarListDetails;
import com.github.skittishSloth.openSkies.engine.ui.characterBuilder.partDetails.EyeListDetails;
import com.github.skittishSloth.openSkies.engine.ui.characterBuilder.partDetails.NoseListDetails;
import java.util.Collection;

/**
 *
 * @author mcory01
 */
public final class CharacterBuildTable extends Table {
    
    public CharacterBuildTable(final Skin skin) {
        super(skin);
        
        clearChildren();
        
        final int width = Gdx.graphics.getWidth();
        final int height = Gdx.graphics.getHeight();
        
        settings = new CharacterSettingsView(skin, this);
        view = new CharacterView(skin, this);
        controls = new CharacterBuildControls(skin);
        add(view).center().width(width * 0.4f).expandY();
        add(settings).top().width(width * 0.6f).expandY();
        row();
        add(controls).bottom().right().colspan(2);
        
        updateSettings();
    }
    
    public void setAvailableColors(final Collection<Color> colors) {
        settings.setAvailableColors(colors, null);
    } 
    
    public Collection<Color> getAvailableColors() {
        return view.getAvailableColors();
    }
    
    public void setCharacterColor(final Color color) {
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
        final Color activeColor = view.getActiveColor();
        final Eye activeEye = view.getActiveEye();
        final Ears activeEars = view.getActiveEars();
        final Nose activeNose = view.getActiveNose();
        settings.update(activeColor, activeEye, activeEars, activeNose);
    }

    public Collection<EyeListDetails> getAvailableEyes() {
        return view.getAvailableEyes();
    }
    
    public Collection<EarListDetails> getAvailableEars() {
        return view.getAvailableEars();
    }
    
    public Collection<NoseListDetails> getAvailableNoses() {
        return view.getAvailableNoses();
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
    
    private final CharacterSettingsView settings;
    private final CharacterView view;
    private final CharacterBuildControls controls;
}
