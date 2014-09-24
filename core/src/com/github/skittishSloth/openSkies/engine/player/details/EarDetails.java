/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.player.details;

import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author mcory01
 */
public class EarDetails {

    public EarDetails() {
        
    }
    
    public EarDetails(final String name, final String texturePathPattern) {
        this.name = name;
        this.texturePathPattern = texturePathPattern;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getTexturePathPattern() {
        return texturePathPattern;
    }

    public void setTexturePathPattern(final String texturePathPattern) {
        this.texturePathPattern = texturePathPattern;
    }
    
    public boolean hasTexture() {
        return StringUtils.isNotBlank(texturePathPattern);
    }
    
    public String getTexturePath(final CharacterAppearanceData appearanceData) {
        if (!hasTexture()) {
            return null;
        }
        
        final Gender gender = appearanceData.getGender();
        final RaceDetails race = appearanceData.getRace();
        final SkinColorDetails skin = appearanceData.getSkinColor();
        
        final String withGender = texturePathPattern.replace("${gender}", gender.name().toLowerCase());
        final String withRace = withGender.replace("${race}", race.getName().toLowerCase());
        final String withSkin = withRace.replace("${skin}", skin.getName().toLowerCase());
        
        return withSkin;
    }
    
    private String name;
    private String texturePathPattern;
}
