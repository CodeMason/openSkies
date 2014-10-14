/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.player.details;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author mcory01
 */
public class BaseDetails {

    public BaseDetails() {
        
    }

    public final String getName() {
        return name;
    }

    public final void setName(final String name) {
        this.name = name;
    }

    public final String getDisplayName() {
        if (StringUtils.isBlank(displayName)) {
            return name;
        }

        return displayName;
    }

    public final void setDisplayName(final String displayName) {
        this.displayName = displayName;
    }

    public final boolean isDefaultDetails() {
        return defaultDetails;
    }

    public final void setDefaultDetails(final boolean defaultDetails) {
        this.defaultDetails = defaultDetails;
    }

    public final String getTexturePathPattern() {
        return texturePathPattern;
    }

    public final void setTexturePathPattern(final String texturePathPattern) {
        this.texturePathPattern = texturePathPattern;
    }

    public final String getMaleTexturePath() {
        return maleTexturePath;
    }

    public final void setMaleTexturePath(final String maleTexturePath) {
        this.maleTexturePath = maleTexturePath;
    }

    public final String getFemaleTexturePath() {
        return femaleTexturePath;
    }

    public final void setFemaleTexturePath(final String femaleTexturePath) {
        this.femaleTexturePath = femaleTexturePath;
    }

    public boolean isNullTexture() {
        return nullTexture;
    }

    public void setNullTexture(final boolean nullTexture) {
        this.nullTexture = nullTexture;
    }
    
    public final boolean isValidForGender(final Gender gender) {
        if (StringUtils.isNotBlank(texturePathPattern) || nullTexture) {
            return true;
        }

        boolean res;
        switch (gender) {
            case MALE:
                res = StringUtils.isNotBlank(maleTexturePath);
                break;
            case FEMALE:
                res = StringUtils.isNotBlank(femaleTexturePath);
                break;
            default:
                res = false;
                break;
        }

        return res;
    }
    
    public final String getTexturePath(final Gender gender, final Map<String, String> patternVariables) {
        if (!hasTexture()) {
            return null;
        }
        
        if (StringUtils.isNotBlank(texturePathPattern)) {
            String res = texturePathPattern;
            for (final String patternVar : patternVariables.keySet()) {
                res = res.replace(patternVar, patternVariables.get(patternVar));
            }
            
            return res;
        } else {
            if (gender == Gender.MALE) {
                return maleTexturePath;
            } else if (gender == Gender.FEMALE) {
                return femaleTexturePath;
            }
        }
        
        return null;
    }

    public final String getTexturePath(final CharacterData characterData) {
        if (!hasTexture()) {
            return null;
        }

        final CharacterAppearanceData appearanceData = characterData.getAppearanceData();
        final CharacterClothingData clothingData = characterData.getClothingData();
        final Gender gender = appearanceData.getGender();
        
        final Map<String, String> appearanceVars = appearanceData.getPatternVariables();
        final Map<String, String> clothingVars = clothingData.getPatternVariables();
        final Map<String, String> patternVars = new HashMap<>(appearanceVars.size() + clothingVars.size());
        patternVars.putAll(appearanceVars);
        patternVars.putAll(clothingVars);
        
        return getTexturePath(gender, patternVars);
    }

    public final boolean hasTexture() {
        if (StringUtils.isNotBlank(texturePathPattern)) {
            return true;
        }
        
        if (StringUtils.isNotBlank(maleTexturePath)) {
            return true;
        }
        
        if (StringUtils.isNotBlank(femaleTexturePath)) {
            return true;
        }
        
        return !(nullTexture);
    }

    private String name;
    private String displayName;
    private boolean defaultDetails;
    private String texturePathPattern;
    private String maleTexturePath;
    private String femaleTexturePath;
    private boolean nullTexture;
}
