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

    public final String getTextureAtlasPathPattern() {
        return textureAtlasPathPattern;
    }

    public final void setTextureAtlasPathPattern(final String textureAtlasPathPattern) {
        this.textureAtlasPathPattern = textureAtlasPathPattern;
    }

    public final String getMaleTextureAtlasPath() {
        return maleTextureAtlasPath;
    }

    public final void setMaleTextureAtlasPath(final String maleTextureAtlasPath) {
        this.maleTextureAtlasPath = maleTextureAtlasPath;
    }

    public final String getFemaleTextureAtlasPath() {
        return femaleTextureAtlasPath;
    }

    public final void setFemaleTextureAtlasPath(final String femaleTextureAtlasPath) {
        this.femaleTextureAtlasPath = femaleTextureAtlasPath;
    }

    public boolean isNullTexture() {
        return nullTexture;
    }

    public void setNullTexture(final boolean nullTexture) {
        this.nullTexture = nullTexture;
    }
    
    public final boolean isValidForGender(final Gender gender) {
        if (StringUtils.isNotBlank(textureAtlasPathPattern) || nullTexture) {
            return true;
        }

        boolean res;
        switch (gender) {
            case MALE:
                res = StringUtils.isNotBlank(maleTextureAtlasPath);
                break;
            case FEMALE:
                res = StringUtils.isNotBlank(femaleTextureAtlasPath);
                break;
            default:
                res = false;
                break;
        }

        return res;
    }
    
    public final String getTextureAtlasPath(final Gender gender, final Map<String, String> patternVariables) {
        if (!hasTexture()) {
            return null;
        }
        
        if (StringUtils.isNotBlank(textureAtlasPathPattern)) {
            String res = textureAtlasPathPattern;
            for (final String patternVar : patternVariables.keySet()) {
                res = res.replace(patternVar, patternVariables.get(patternVar));
            }
            
            return res;
        } else {
            if (gender == Gender.MALE) {
                return maleTextureAtlasPath;
            } else if (gender == Gender.FEMALE) {
                return femaleTextureAtlasPath;
            }
        }
        
        return null;
    }

    public final String getTextureAtlasPath(final CharacterData characterData) {
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
        
        return getTextureAtlasPath(gender, patternVars);
    }

    public final boolean hasTexture() {
        if (StringUtils.isNotBlank(textureAtlasPathPattern)) {
            return true;
        }
        
        if (StringUtils.isNotBlank(maleTextureAtlasPath)) {
            return true;
        }
        
        if (StringUtils.isNotBlank(femaleTextureAtlasPath)) {
            return true;
        }
        
        return !(nullTexture);
    }

    private String name;
    private String displayName;
    private boolean defaultDetails;
    private String textureAtlasPathPattern;
    private String maleTextureAtlasPath;
    private String femaleTextureAtlasPath;
    private boolean nullTexture;
}
