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

    public CharacterData(CharacterAppearanceData appearanceData, CharacterInformationData informationData) {
        this.appearanceData = appearanceData;
        this.informationData = informationData;
    }

    public CharacterAppearanceData getAppearanceData() {
        return appearanceData;
    }

    public void setAppearanceData(CharacterAppearanceData appearanceData) {
        this.appearanceData = appearanceData;
    }

    public CharacterInformationData getInformationData() {
        return informationData;
    }

    public void setInformationData(CharacterInformationData informationData) {
        this.informationData = informationData;
    }

    private CharacterAppearanceData appearanceData;
    private CharacterInformationData informationData;
}
