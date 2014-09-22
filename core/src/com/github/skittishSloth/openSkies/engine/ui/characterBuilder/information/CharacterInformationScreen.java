/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.skittishSloth.openSkies.engine.ui.characterBuilder.information;

import com.github.skittishSloth.openSkies.OpenSkies;
import com.github.skittishSloth.openSkies.engine.player.info.BackStory;
import com.github.skittishSloth.openSkies.engine.player.info.PlayerClass;
import com.github.skittishSloth.openSkies.engine.ui.AbstractScreen;
import com.github.skittishSloth.openSkies.engine.ui.characterBuilder.CharacterBuilderScreenManager;
import java.util.Collection;

/**
 *
 * @author mcory01
 */
public class CharacterInformationScreen extends AbstractScreen {

    public CharacterInformationScreen(final OpenSkies game, final CharacterBuilderScreenManager manager) {
        super(game);
        
        this.manager = manager;
        this.infoTable = new CharacterInformationTable(getSkin(), this);
        infoTable.setFillParent(true);
        
        getStage().addActor(infoTable);
    }
    
    public void informationScreenCancel() {
        manager.informationScreenCancel();
    }
    
    public void informationScreenNext() {
        manager.informationScreenNext();
    }
    
    public Collection<PlayerClass> getAvailablePlayerClasses() {
        return manager.getAvailablePlayerClasses();
    }
    
    public Collection<BackStory> getAvailableBackStories() {
        return manager.getAvailableBackStories();
    }
    
    public CharacterInformationData getCharacterInformationData() {
        return infoTable.getInformationData();
    }
    private final CharacterBuilderScreenManager manager;
    private final CharacterInformationTable infoTable;
}
