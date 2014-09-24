/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.ui.characterBuilder.appearance;

import com.github.skittishSloth.openSkies.engine.player.details.CharacterAppearanceData;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author mcory01
 */
public class CharacterAppearanceSettingsValidator {
    
    public CharacterAppearanceSettingsValidator(final CharacterAppearanceSettings settings) {
        this.settings = settings;
    }
    
    public boolean validate(final CharacterAppearanceData buildData) {
        final String name = buildData.getName();
        final boolean validName = StringUtils.isNotBlank(name);
        settings.setValidName(validName);
        
        return validName;
    }
    
    private final CharacterAppearanceSettings settings;
}
