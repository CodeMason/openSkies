/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.player.details;

import com.github.skittishSloth.openSkies.engine.player.info.BackStory;
import com.github.skittishSloth.openSkies.engine.player.info.PlayerClass;

/**
 *
 * @author mcory01
 */
public class CharacterInformationData {
    
    public CharacterInformationData() {
        
    }

    public PlayerClass getSelectedClass() {
        return selectedClass;
    }

    public void setSelectedClass(final PlayerClass selectedClass) {
        this.selectedClass = selectedClass;
    }

    public BackStory getSelectedBackstory() {
        return selectedBackstory;
    }

    public void setSelectedBackstory(final BackStory selectedBackstory) {
        this.selectedBackstory = selectedBackstory;
    }
    
    
    
    private PlayerClass selectedClass;
    private BackStory selectedBackstory;
}
