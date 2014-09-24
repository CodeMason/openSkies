/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.player.details;

/**
 *
 * @author mcory01
 */
public class SkinColorDetails {

    public SkinColorDetails() {
    }

    public SkinColorDetails(String name, String sampleColor, String race, String maleTexturePath, String femaleTexturePath, boolean defaultColor) {
        this.name = name;
        this.sampleColor = sampleColor;
        this.race = race;
        this.maleTexturePath = maleTexturePath;
        this.femaleTexturePath = femaleTexturePath;
        this.defaultColor = defaultColor;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getSampleColor() {
        return sampleColor;
    }

    public void setSampleColor(final String sampleColor) {
        this.sampleColor = sampleColor;
    }

    public String getRace() {
        return race;
    }

    public void setRace(final String race) {
        this.race = race;
    }

    public String getMaleTexturePath() {
        return maleTexturePath;
    }

    public void setMaleTexturePath(final String maleTexturePath) {
        this.maleTexturePath = maleTexturePath;
    }

    public String getFemaleTexturePath() {
        return femaleTexturePath;
    }

    public void setFemaleTexturePath(final String femaleTexturePath) {
        this.femaleTexturePath = femaleTexturePath;
    }

    public boolean isDefaultColor() {
        return defaultColor;
    }

    public void setDefaultColor(final boolean defaultColor) {
        this.defaultColor = defaultColor;
    }

    private String name;
    private String sampleColor;
    private String race;
    private String maleTexturePath;
    private String femaleTexturePath;
    private boolean defaultColor;
}
