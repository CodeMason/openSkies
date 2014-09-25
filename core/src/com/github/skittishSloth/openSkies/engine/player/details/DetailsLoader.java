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
        json.addClassTag("eyeDetails", ColoredDetails.class);
        json.addClassTag("earDetails", BaseDetails.class);
        json.addClassTag("raceDetails", BaseDetails.class);
        json.addClassTag("skinColorDetails", SkinColorDetails.class);
        json.addClassTag("noseDetails", BaseDetails.class);
        json.addClassTag("shoeColorDetails", ColoredDetails.class);
        json.addClassTag("pantsColors", ColoredDetails.class);
        json.addClassTag("shirtDetails", ShirtDetails.class);
        json.addClassTag("shirtColorDetails", ColoredDetails.class);
        json.addClassTag("hairStyleDetails", BaseDetails.class);
        json.addClassTag("hairColorDetails", BaseDetails.class);
    }
    
    public static final <T extends BaseDetails> DetailsCollection<T> fromJson(final Class<T> clazz, final FileHandle fh) {
        final DetailsCollection<T> res = json.fromJson(DetailsCollection.class, clazz, fh);
        return res;
    }
}
