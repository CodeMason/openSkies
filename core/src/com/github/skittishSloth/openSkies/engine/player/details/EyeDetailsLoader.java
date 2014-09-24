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
public class EyeDetailsLoader {
    
    private static final Json json = new Json();
    
    
    static {
        json.addClassTag("eyes", EyeDetailsCollection.class);
    }
    
    public static EyeDetailsCollection eyeDetailsFromJsonFile(final FileHandle file) {
        final EyeDetailsCollection res = json.fromJson(EyeDetailsCollection.class, file);
        return res;
    }
}
