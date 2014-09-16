/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.ui.characterBuilder.partDetails;

import com.github.skittishSloth.openSkies.engine.common.GdxUtils;
import com.github.skittishSloth.openSkies.engine.player.details.Gender;
import com.github.skittishSloth.openSkies.engine.player.details.Nose;
import com.github.skittishSloth.openSkies.engine.player.details.Race;
import com.github.skittishSloth.openSkies.engine.player.details.SkinColor;
import com.github.skittishSloth.openSkies.engine.sprites.UniversalDirectionalSprite;

/**
 *
 * @author mcory01
 */
public class NoseListDetails extends AbstractListDetails {

    public NoseListDetails(int order, Gender gender, Race race, SkinColor color, Nose nose, UniversalDirectionalSprite sprite) {
        super(order);
        this.gender = gender;
        this.race = race;
        this.color = color;
        this.nose = nose;
        this.sprite = sprite;
    }

    public Gender getGender() {
        return gender;
    }

    public Race getRace() {
        return race;
    }

    public SkinColor getColor() {
        return color;
    }

    public Nose getNose() {
        return nose;
    }

    public UniversalDirectionalSprite getSprite() {
        return sprite;
    }

    @Override
    public void dispose() {
        GdxUtils.safeDispose(sprite);
    }

    private final Gender gender;
    private final Race race;
    private final SkinColor color;
    private final Nose nose;
    private final UniversalDirectionalSprite sprite;
}
