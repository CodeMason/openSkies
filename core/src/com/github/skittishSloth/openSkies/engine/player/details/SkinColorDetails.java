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
public class SkinColorDetails extends ColoredDetails {

    public SkinColorDetails() {
    }
    
    public String getRace() {
        return race;
    }

    public void setRace(final String race) {
        this.race = race;
    }

    private String race;
}
