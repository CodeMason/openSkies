/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.ui.characterBuilder;

import com.github.skittishSloth.openSkies.engine.player.details.Ears;
import com.github.skittishSloth.openSkies.engine.player.details.Eye;
import com.github.skittishSloth.openSkies.engine.player.details.Gender;
import com.github.skittishSloth.openSkies.engine.player.details.HairColors;
import com.github.skittishSloth.openSkies.engine.player.details.HairStyles;
import com.github.skittishSloth.openSkies.engine.player.details.Nose;
import com.github.skittishSloth.openSkies.engine.player.details.Race;
import com.github.skittishSloth.openSkies.engine.player.details.SkinColor;

/**
 *
 * @author mcory01
 */
public class CharacterBuildData {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public SkinColor getSkinColor() {
        return skinColor;
    }

    public void setSkinColor(SkinColor skinColor) {
        this.skinColor = skinColor;
    }

    public Eye getEyes() {
        return eyes;
    }

    public void setEyes(Eye eyes) {
        this.eyes = eyes;
    }

    public Ears getEars() {
        return ears;
    }

    public void setEars(Ears ears) {
        this.ears = ears;
    }

    public Nose getNose() {
        return nose;
    }

    public void setNose(Nose nose) {
        this.nose = nose;
    }

    public HairStyles getHairStyle() {
        return hairStyle;
    }

    public void setHairStyle(HairStyles hairStyle) {
        this.hairStyle = hairStyle;
    }

    public HairColors getHairColor() {
        return hairColor;
    }

    public void setHairColor(HairColors hairColor) {
        this.hairColor = hairColor;
    }

    private String name;
    private Gender gender;
    private Race race;
    private SkinColor skinColor;
    private Eye eyes;
    private Ears ears;
    private Nose nose;
    private HairStyles hairStyle;
    private HairColors hairColor;
}
