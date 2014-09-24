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
public class CharacterAppearanceData {

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
    
    public EyeDetails getEyeDetails() {
        return eyeDetails;
    }
    
    public void setEyeDetails(final EyeDetails eyeDetails) {
        this.eyeDetails = eyeDetails;
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

    public Shirts getShirt() {
        return shirt;
    }

    public void setShirt(Shirts shirt) {
        this.shirt = shirt;
    }

    public ShirtColors getShirtColor() {
        return shirtColor;
    }

    public void setShirtColor(ShirtColors shirtColor) {
        this.shirtColor = shirtColor;
    }

    public PantsColors getPantsColor() {
        return pantsColor;
    }

    public void setPantsColor(PantsColors pantsColor) {
        this.pantsColor = pantsColor;
    }

    public ShoeColors getShoeColor() {
        return shoeColor;
    }

    public void setShoeColor(ShoeColors shoeColor) {
        this.shoeColor = shoeColor;
    }

    private String name;
    private Gender gender;
    private Race race;
    private SkinColor skinColor;
    private EyeDetails eyeDetails;
    private Ears ears;
    private Nose nose;
    private HairStyles hairStyle;
    private HairColors hairColor;
    private Shirts shirt;
    private ShirtColors shirtColor;
    private PantsColors pantsColor;
    private ShoeColors shoeColor;
}
