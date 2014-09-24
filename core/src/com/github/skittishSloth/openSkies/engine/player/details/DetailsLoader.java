/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.player.details;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

/**
 *
 * @author mcory01
 */
public class DetailsLoader {
    private static final Json json = new Json();
    
    static {
        json.addClassTag("eyeDetails", EyeDetails.class);
        json.addClassTag("earDetails", EarDetails.class);
        json.addClassTag("raceDetails", RaceDetails.class);
        json.addClassTag("skinColorDetails", SkinColorDetails.class);
    }
    
    public static final <T> DetailsCollection<T> fromJson(final Class<T> clazz, final FileHandle fh) {
        
        final DetailsCollection<T> res = json.fromJson(DetailsCollection.class, clazz, fh);
        return res;
    }
}
