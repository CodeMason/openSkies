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
public class EyeDetails {

    public EyeDetails() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSampleColorRgb() {
        return sampleColorRgb;
    }

    public void setSampleColorRgb(String sampleColorRgb) {
        this.sampleColorRgb = sampleColorRgb;
    }

    public String getMaleTexturePath() {
        return maleTexturePath;
    }

    public void setMaleTexturePath(String maleTexturePath) {
        this.maleTexturePath = maleTexturePath;
    }

    public String getFemaleTexturePath() {
        return femaleTexturePath;
    }

    public void setFemaleTexturePath(String femaleTexturePath) {
        this.femaleTexturePath = femaleTexturePath;
    }
    
    public boolean isValidForGender(final Gender gender) {
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

    private String name;
    private String sampleColorRgb;
    private String maleTexturePath;
    private String femaleTexturePath;
}
