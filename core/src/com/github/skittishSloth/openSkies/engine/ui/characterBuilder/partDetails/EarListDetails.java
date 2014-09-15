/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.ui.characterBuilder.partDetails;

import com.badlogic.gdx.graphics.Color;
import com.github.skittishSloth.openSkies.engine.common.GdxUtils;
import com.github.skittishSloth.openSkies.engine.player.Ears;
import com.github.skittishSloth.openSkies.engine.player.Gender;
import com.github.skittishSloth.openSkies.engine.player.Race;
import com.github.skittishSloth.openSkies.engine.sprites.UniversalDirectionalSprite;

/**
 *
 * @author mcory01
 */
public class EarListDetails extends AbstractListDetails {

    public EarListDetails(final int order, final Gender gender, final Race race, final Color color, final Ears ears, final UniversalDirectionalSprite sprite) {
        super(order);
        this.gender = gender;
        this.color = color;
        this.race = race;
        this.ears = ears;
        this.sprite = sprite;
    }
    
    public Gender getGender() {
        return gender;
    }

    public Color getColor() {
        return color;
    }

    public Race getRace() {
        return race;
    }

    public Ears getEars() {
        return ears;
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
    private final Ears ears;
    private final UniversalDirectionalSprite sprite;
}
