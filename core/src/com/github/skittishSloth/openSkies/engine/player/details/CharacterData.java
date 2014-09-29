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
public class CharacterData {

    public CharacterData() {
    }

    public CharacterData(final CharacterAppearanceData appearanceData, final CharacterClothingData clothingData, final CharacterInformationData informationData) {
        this.appearanceData = appearanceData;
        this.clothingData = clothingData;
        this.informationData = informationData;
    }

    public CharacterAppearanceData getAppearanceData() {
        return appearanceData;
    }

    public void setAppearanceData(final CharacterAppearanceData appearanceData) {
        this.appearanceData = appearanceData;
    }

    public CharacterClothingData getClothingData() {
        return clothingData;
    }

    public void setClothingData(final CharacterClothingData clothingData) {
        this.clothingData = clothingData;
    }

    public CharacterInformationData getInformationData() {
        return informationData;
    }

    public void setInformationData(final CharacterInformationData informationData) {
        this.informationData = informationData;
    }

    private CharacterAppearanceData appearanceData;
    private CharacterClothingData clothingData;
    private CharacterInformationData informationData;
}
