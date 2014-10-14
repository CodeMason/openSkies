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
public class ShirtDetails extends BaseDetails {

    public ShirtDetails() {

    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(final Gender gender) {
        this.gender = gender;
    }
    
    public boolean isDefaultDetails(final Gender forGender) {
        if ((gender == null) || (gender == Gender.NEUTRAL)) {
            return isDefaultDetails();
        }
        
        return (isDefaultDetails() && (forGender == gender));
    }

    private Gender gender;
}
