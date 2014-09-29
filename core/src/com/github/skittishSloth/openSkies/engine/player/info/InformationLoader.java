/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.player.info;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

/**
 *
 * @author mcory01
 */
public class InformationLoader {
    private static final Json json = new Json();
    
    static {
        json.addClassTag("backStories", BackStory.class);
        json.addClassTag("classes", PlayerClass.class);
    }
    
    public static PlayerClassCollection playerClassesFromJsonFile(final FileHandle file) {
        final PlayerClassCollection res = json.fromJson(PlayerClassCollection.class, file);
        return res;
    }
    
    public static BackStoryCollection backStoriesFromJsonFile(final FileHandle file) {
        final BackStoryCollection res = json.fromJson(BackStoryCollection.class, file);
        return res;
    }
}
