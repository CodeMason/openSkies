/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.player.info;

import com.github.skittishSloth.openSkies.engine.common.DataCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author mcory01
 */
public class BackStoryCollection implements DataCollection<BackStory> {
    public BackStoryCollection() {
        
    }
    
    @Override
    public List<BackStory> getData() {
        return backStories;
    }

    @Override
    public void setData(final Collection<BackStory> backstories) {
        if (this.backStories == null) {
            this.backStories = new ArrayList<>();
        } else {
            this.backStories.clear();
        }
        
        if (backstories != null) {
            this.backStories.addAll(backstories);
        }
    }
    
    @Override
    public int size() {
        if (backStories == null) {
            return 0;
        } else {
            return backStories.size();
        }
    }

    private ArrayList<BackStory> backStories;
}
