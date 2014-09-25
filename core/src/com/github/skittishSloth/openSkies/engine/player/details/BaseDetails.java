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

    public final boolean isValidForGender(final Gender gender) {
        if (StringUtils.isNotBlank(texturePathPattern)) {
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

    public final String getTexturePath(final CharacterAppearanceData appearanceData) {
        if (!hasTexture()) {
            return null;
        }

        final Gender gender = appearanceData.getGender();
        if (StringUtils.isNotBlank(texturePathPattern)) {

            final BaseDetails race = appearanceData.getRace();
            final BaseDetails skin = appearanceData.getSkinColor();
            final BaseDetails shirt = appearanceData.getShirt();
            final BaseDetails hairStyle = appearanceData.getHairStyle();

            final String withGender = texturePathPattern.replace("${gender}", gender.name().toLowerCase());
            final String withRace = withGender.replace("${race}", race.getName().toLowerCase());
            final String withSkin = withRace.replace("${skinColor}", skin.getName().toLowerCase());
            final String withShirt = withSkin.replace("${shirt}", shirt.getName().toLowerCase());

            final String withHairStyle;
            if (hairStyle != null) {
                withHairStyle = withShirt.replace("${hair}", hairStyle.getName().toLowerCase());
            } else {
                withHairStyle = withShirt;
            }

            return withHairStyle;
        } else {
            if (gender == Gender.MALE) {
                return maleTexturePath;
            } else if (gender == Gender.FEMALE) {
                return femaleTexturePath;
            }
        }

        return null;
    }

    public final boolean hasTexture() {
        return (StringUtils.isNotBlank(texturePathPattern) || StringUtils.isNotBlank(maleTexturePath) || StringUtils.isNotBlank(femaleTexturePath));
    }

    private String name;
    private String displayName;
    private boolean defaultDetails;
    private String texturePathPattern;
    private String maleTexturePath;
    private String femaleTexturePath;
}
