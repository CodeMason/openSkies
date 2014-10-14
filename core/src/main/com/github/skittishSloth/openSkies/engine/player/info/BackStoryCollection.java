/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.player.info;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author mcory01
 */
public class BackStoryCollection {
    public Collection<BackStory> getBackStories() {
        return backStories;
    }

    public void setBackStories(Collection<BackStory> classes) {
        if (this.backStories == null) {
            this.backStories = new ArrayList<BackStory>();
        } else {
            this.backStories.clear();
        }
        
        if (classes != null) {
            this.backStories.addAll(classes);
        }
    }
    
    public int size() {
        if (backStories == null) {
            return 0;
        } else {
            return backStories.size();
        }
    }

    private ArrayList<BackStory> backStories;
}
