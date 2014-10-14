/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.player.info;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.github.skittishSloth.openSkies.engine.common.DataCollection;

/**
 *
 * @author mcory01
 */
public class BackStoryLoader {
    
    private static final Json json = new Json();
    
    public static DataCollection<BackStory> fromJson(final FileHandle file) {
        final BackStoryCollection res = json.fromJson(BackStoryCollection.class, file);
        return res;
    }
}
