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
public enum Shirts {
    LONGSLEEVE(Gender.MALE),
    SLEEVELESS(Gender.FEMALE),
    PIRATE(Gender.FEMALE);
    
    private final Gender availableGender;
    
    private Shirts(final Gender availableGender) {
        this.availableGender = availableGender;
    }
    
    public Gender getAvailableGender() {
        return availableGender;
    }
}
