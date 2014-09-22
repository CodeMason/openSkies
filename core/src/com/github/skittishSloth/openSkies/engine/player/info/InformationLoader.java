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
    
    public static PlayerClassCollection playerClassesFromJsonFile(final FileHandle file) {
        json.addClassTag("classes", PlayerClass.class);
        final PlayerClassCollection res = json.fromJson(PlayerClassCollection.class, file);
        
        System.err.println("Res found? " + (res != null));
        System.err.println("Count? " + res.size());
        return res;
    }
    
    public static BackStoryCollection backStoriesFromJsonFile(final FileHandle file) {
        json.addClassTag("backStories", BackStory.class);
        
        final BackStoryCollection res = json.fromJson(BackStoryCollection.class, file);
        System.err.println("Res found? " + (res != null));
        System.err.println("Count? " + res.size());
        return res;
    }
}
