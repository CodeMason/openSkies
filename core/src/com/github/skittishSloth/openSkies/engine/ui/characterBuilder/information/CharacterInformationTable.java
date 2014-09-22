/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.ui.characterBuilder.information;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.github.skittishSloth.openSkies.engine.player.info.BackStory;
import com.github.skittishSloth.openSkies.engine.player.info.PlayerClass;
import java.util.Collection;

/**
 *
 * @author mcory01
 */
public class CharacterInformationTable extends Table {

    public CharacterInformationTable(final Skin skin, final CharacterInformationScreen parentScreen) {
        super(skin);
        clearChildren();
        
        this.parentScreen = parentScreen;
        final int width = Gdx.graphics.getWidth();
        classSelector = new CharacterClassSelector(skin, this);
        add(classSelector).width(width).expand().top().left();
        row();
        
        backStorySelector = new CharacterBackStorySelector(skin, this);
        add(backStorySelector).top().left().width(width).expand();
        row();
        
        controls = new CharacterInformationControls(skin, this);
        add(controls).expandX().bottom().right().pad(5f);
        
        data = new CharacterInformationData();
    }
    
    public CharacterInformationData getInformationData() {
        return data;
    }
    
    public void handleCancel() {
        parentScreen.informationScreenCancel();
    }
    
    public void handleNext() {
        data.setSelectedBackstory(backStorySelector.getSelectedBackStory());
        data.setSelectedClass(classSelector.getSelectedClass());
        parentScreen.informationScreenNext();
    }
    
    public Collection<PlayerClass> getAvailableClasses() {
        return parentScreen.getAvailablePlayerClasses();
    }
    
    public Collection<BackStory> getAvailableBackStories() {
        return parentScreen.getAvailableBackStories();
    }
    
    private final CharacterInformationData data;
    private final CharacterInformationScreen parentScreen;
    private final CharacterInformationControls controls;
    private final CharacterClassSelector classSelector;
    private final CharacterBackStorySelector backStorySelector;
}
