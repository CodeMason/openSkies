/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.player.details;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author mcory01
 */
public class CharacterAppearanceData {

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(final Gender gender) {
        this.gender = gender;
    }

    public BaseDetails getRace() {
        return race;
    }

    public void setRace(final BaseDetails race) {
        this.race = race;
    }

    public SkinColorDetails getSkinColor() {
        return skinColor;
    }

    public void setSkinColor(final SkinColorDetails skinColor) {
        this.skinColor = skinColor;
    }

    public ColoredDetails getEyeDetails() {
        return eyeDetails;
    }

    public void setEyeDetails(final ColoredDetails eyeDetails) {
        this.eyeDetails = eyeDetails;
    }

    public BaseDetails getEarDetails() {
        return earDetails;
    }

    public void setEarDetails(final BaseDetails earDetails) {
        this.earDetails = earDetails;
    }

    public BaseDetails getNose() {
        return nose;
    }

    public void setNose(final BaseDetails nose) {
        this.nose = nose;
    }

    public BaseDetails getHairStyle() {
        return hairStyle;
    }

    public void setHairStyle(final BaseDetails hairStyle) {
        this.hairStyle = hairStyle;
    }

    public BaseDetails getHairColor() {
        return hairColor;
    }

    public void setHairColor(final BaseDetails hairColor) {
        this.hairColor = hairColor;
    }

    public BaseDetails getFacialHairStyle() {
        return facialHairStyle;
    }

    public void setFacialHairStyle(final BaseDetails facialHairStyle) {
        this.facialHairStyle = facialHairStyle;
    }

    public BaseDetails getFacialHairColor() {
        return facialHairColor;
    }

    public void setFacialHairColor(final BaseDetails facialHairColor) {
        this.facialHairColor = facialHairColor;
    }
    
    public Map<String, String> getPatternVariables() {
        if (isInvalidForPatternVariables()) {
            return null;
        }
        
        final Map<String, String> res = new HashMap<>();
        
        res.put("${gender}", gender.name().toLowerCase());
        res.put("${race}", race.getName().toLowerCase());
        res.put("${skinColor}", skinColor.getName().toLowerCase());
        
        if (hasTexture(hairStyle)) {
            res.put("${hair}", hairStyle.getName().toLowerCase());
        }
        
        if (hasTexture(facialHairStyle)) {
            res.put("${facialHair}", facialHairStyle.getName().toLowerCase());
        }

        return res;
    }
    
    private static boolean hasTexture(final BaseDetails details) {
        final boolean res;
        if (details == null) {
            res = false;
        } else {
            res = details.hasTexture();
        }
        
        return res;
    }
    
    private boolean isInvalidForPatternVariables() {
        boolean res = false;
        
        if (gender == null) {
            res = true;
        }
        
        if (race == null) {
            res = true;
        }
        
        if (skinColor == null) {
            res = true;
        }
        
        return res;
    }

    private String name;
    private Gender gender;
    private BaseDetails race;
    private SkinColorDetails skinColor;
    private ColoredDetails eyeDetails;
    private BaseDetails earDetails;
    private BaseDetails nose;
    private BaseDetails hairStyle;
    private BaseDetails hairColor;
    private BaseDetails facialHairStyle;
    private BaseDetails facialHairColor;
}
