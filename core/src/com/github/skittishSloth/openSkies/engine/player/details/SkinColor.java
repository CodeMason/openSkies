/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.skittishSloth.openSkies.engine.player.details;

import com.badlogic.gdx.graphics.Color;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mcory01
 */
public enum SkinColor {
    LIGHT(Race.HUMAN, Color.valueOf("fdd5b7")),
    TANNED(Race.HUMAN, Color.valueOf("fdd082")),
    TANNED2(Race.HUMAN, Color.valueOf("ecc479")),
    DARK(Race.HUMAN, Color.valueOf("ba8454")),
    DARK2(Race.HUMAN, Color.valueOf("9c663e")),
    DARKELF(Race.ELF, Color.valueOf("aeb3ca")),
    DARKELF2(Race.ELF, Color.valueOf("c9d0ee"));
    
    private final Race race;
    private final Color sampleColor;
    
    private SkinColor(final Race race, final Color sampleColor) {
        this.race = race;
        this.sampleColor = sampleColor;
    }
    
    public Race getRace() {
        return race;
    }
    
    public Color getSampleColor() {
        return sampleColor;
    }
    
    public static SkinColor[] getAvailableByRace(final Race race) {
        final List<SkinColor> resList = new ArrayList<SkinColor>();
        
        for (final SkinColor sc : values()) {
            if (sc.getRace() == race) {
                resList.add(sc);
            }
        }
        
        return resList.toArray(new SkinColor[resList.size()]);
    }
}
