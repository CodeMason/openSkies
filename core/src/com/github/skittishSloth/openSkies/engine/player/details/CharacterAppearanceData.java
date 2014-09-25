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

    public RaceDetails getRace() {
        return race;
    }

    public void setRace(RaceDetails race) {
        this.race = race;
    }

    public SkinColorDetails getSkinColor() {
        return skinColor;
    }

    public void setSkinColor(SkinColorDetails skinColor) {
        this.skinColor = skinColor;
    }

    public EyeDetails getEyeDetails() {
        return eyeDetails;
    }

    public void setEyeDetails(final EyeDetails eyeDetails) {
        this.eyeDetails = eyeDetails;
    }

    public EarDetails getEarDetails() {
        return earDetails;
    }

    public void setEarDetails(final EarDetails earDetails) {
        this.earDetails = earDetails;
    }

    public NoseDetails getNose() {
        return nose;
    }

    public void setNose(final NoseDetails nose) {
        this.nose = nose;
    }

    public HairStyleDetails getHairStyle() {
        return hairStyle;
    }

    public void setHairStyle(final HairStyleDetails hairStyle) {
        this.hairStyle = hairStyle;
    }

    public HairColorDetails getHairColor() {
        return hairColor;
    }

    public void setHairColor(HairColorDetails hairColor) {
        this.hairColor = hairColor;
    }

    public ShirtDetails getShirt() {
        return shirt;
    }

    public void setShirt(ShirtDetails shirt) {
        this.shirt = shirt;
    }

    public ShirtColorDetails getShirtColor() {
        return shirtColor;
    }

    public void setShirtColor(ShirtColorDetails shirtColor) {
        this.shirtColor = shirtColor;
    }

    public PantsColorDetails getPantsColor() {
        return pantsColor;
    }

    public void setPantsColor(PantsColorDetails pantsColor) {
        this.pantsColor = pantsColor;
    }

    public ShoeColorDetails getShoeColor() {
        return shoeColor;
    }

    public void setShoeColor(ShoeColorDetails shoeColor) {
        this.shoeColor = shoeColor;
    }

    private String name;
    private Gender gender;
    private RaceDetails race;
    private SkinColorDetails skinColor;
    private EyeDetails eyeDetails;
    private EarDetails earDetails;
    private NoseDetails nose;
    private HairStyleDetails hairStyle;
    private HairColorDetails hairColor;
    private ShirtDetails shirt;
    private ShirtColorDetails shirtColor;
    private PantsColorDetails pantsColor;
    private ShoeColorDetails shoeColor;
}
