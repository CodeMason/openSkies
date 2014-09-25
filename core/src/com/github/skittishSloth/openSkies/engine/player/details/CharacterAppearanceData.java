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

    public void setName(final String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(final Gender gender) {
        this.gender = gender;
    }

    public BaseDetails getRace() {
        return race;
    }

    public void setRace(final BaseDetails race) {
        this.race = race;
    }

    public SkinColorDetails getSkinColor() {
        return skinColor;
    }

    public void setSkinColor(final SkinColorDetails skinColor) {
        this.skinColor = skinColor;
    }

    public ColoredDetails getEyeDetails() {
        return eyeDetails;
    }

    public void setEyeDetails(final ColoredDetails eyeDetails) {
        this.eyeDetails = eyeDetails;
    }

    public BaseDetails getEarDetails() {
        return earDetails;
    }

    public void setEarDetails(final BaseDetails earDetails) {
        this.earDetails = earDetails;
    }

    public BaseDetails getNose() {
        return nose;
    }

    public void setNose(final BaseDetails nose) {
        this.nose = nose;
    }

    public BaseDetails getHairStyle() {
        return hairStyle;
    }

    public void setHairStyle(final BaseDetails hairStyle) {
        this.hairStyle = hairStyle;
    }

    public BaseDetails getHairColor() {
        return hairColor;
    }

    public void setHairColor(final BaseDetails hairColor) {
        this.hairColor = hairColor;
    }

    public ShirtDetails getShirt() {
        return shirt;
    }

    public void setShirt(final ShirtDetails shirt) {
        this.shirt = shirt;
    }

    public ColoredDetails getShirtColor() {
        return shirtColor;
    }

    public void setShirtColor(final ColoredDetails shirtColor) {
        this.shirtColor = shirtColor;
    }

    public ColoredDetails getPantsColor() {
        return pantsColor;
    }

    public void setPantsColor(final ColoredDetails pantsColor) {
        this.pantsColor = pantsColor;
    }

    public ColoredDetails getShoeColor() {
        return shoeColor;
    }

    public void setShoeColor(final ColoredDetails shoeColor) {
        this.shoeColor = shoeColor;
    }

    private String name;
    private Gender gender;
    private BaseDetails race;
    private SkinColorDetails skinColor;
    private ColoredDetails eyeDetails;
    private BaseDetails earDetails;
    private BaseDetails nose;
    private BaseDetails hairStyle;
    private BaseDetails hairColor;
    private ShirtDetails shirt;
    private ColoredDetails shirtColor;
    private ColoredDetails pantsColor;
    private ColoredDetails shoeColor;
}
