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
import com.github.skittishSloth.openSkies.engine.player.Gender;
import com.github.skittishSloth.openSkies.engine.player.Race;
import java.util.Collection;

/**
 *
 * @author mcory01
 */
public class CharacterBuildTable extends Table {
    
    public CharacterBuildTable(final Skin skin) {
        super(skin);
        
        clearChildren();
        
        final int width = Gdx.graphics.getWidth();
        final int height = Gdx.graphics.getHeight();
        
        settings = new CharacterSettingsView(skin, this);
        view = new CharacterView(skin, this);
        controls = new CharacterBuildControls(skin);
        debug();
        add(view).center().width(width * 0.4f).expandY();
        add(settings).top().width(width * 0.6f).expandY();
        row();
        add(controls).bottom().right().colspan(2);
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
        settings.update(activeColor);
    }
    
    private final CharacterSettingsView settings;
    private final CharacterView view;
    private final CharacterBuildControls controls;
}
