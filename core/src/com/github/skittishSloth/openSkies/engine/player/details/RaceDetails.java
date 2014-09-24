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
public class RaceDetails {

    public RaceDetails() {
    }

    public RaceDetails(String name, boolean defaultRace) {
        this.name = name;
        this.defaultRace = defaultRace;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public boolean isDefaultRace() {
        return defaultRace;
    }

    public void setDefaultRace(boolean defaultRace) {
        this.defaultRace = defaultRace;
    }

    private String name;
    private boolean defaultRace;
}
