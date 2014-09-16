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
public enum Gender {
    MALE,
    FEMALE,
    NEUTRAL(false);
    
    private final boolean forCharacterBuilding;
    
    private Gender() {
        this.forCharacterBuilding = true;
    }
    
    private Gender(final boolean forCharacterBuilding) {
        this.forCharacterBuilding = forCharacterBuilding;
    }
    
    public boolean isForCharacterBuilding() {
        return forCharacterBuilding;
    }
}
