/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.ui.characterBuilder.partDetails;

import com.badlogic.gdx.graphics.Color;
import com.github.skittishSloth.openSkies.engine.common.GdxUtils;
import com.github.skittishSloth.openSkies.engine.player.Gender;
import com.github.skittishSloth.openSkies.engine.player.Nose;
import com.github.skittishSloth.openSkies.engine.player.Race;
import com.github.skittishSloth.openSkies.engine.sprites.UniversalDirectionalSprite;

/**
 *
 * @author mcory01
 */
public class NoseListDetails extends AbstractListDetails {

    public NoseListDetails(int order, Gender gender, Race race, Color color, Nose nose, UniversalDirectionalSprite sprite) {
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

    public Color getColor() {
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
    private final Color color;
    private final Nose nose;
    private final UniversalDirectionalSprite sprite;
}
