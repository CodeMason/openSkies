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
public class ShoeColorDetails {

    public ShoeColorDetails() {
    }

    public ShoeColorDetails(String name, String sampleColor, String texturePathPattern, boolean defaultShoeColor) {
        this.name = name;
        this.sampleColor = sampleColor;
        this.texturePathPattern = texturePathPattern;
        this.defaultShoeColor = defaultShoeColor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSampleColor() {
        return sampleColor;
    }

    public void setSampleColor(String sampleColor) {
        this.sampleColor = sampleColor;
    }

    public String getTexturePathPattern() {
        return texturePathPattern;
    }

    public void setTexturePathPattern(String texturePathPattern) {
        this.texturePathPattern = texturePathPattern;
    }

    public boolean isDefaultShoeColor() {
        return defaultShoeColor;
    }

    public void setDefaultShoeColor(boolean defaultShoeColor) {
        this.defaultShoeColor = defaultShoeColor;
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
        final String withSkin = withRace.replace("${skinColor}", skin.getName().toLowerCase());
        
        return withSkin;
    }
    
    private String name;
    private String sampleColor;
    private String texturePathPattern;
    private boolean defaultShoeColor;
}
