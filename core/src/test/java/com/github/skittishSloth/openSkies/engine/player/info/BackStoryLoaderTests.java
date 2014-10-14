/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.player.info;

import com.badlogic.gdx.files.FileHandle;
import com.github.skittishSloth.openSkies.engine.common.DataCollection;
import com.github.skittishSloth.openSkies.testUtils.DataCollectionLoaderTests;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mcory01
 */
public class BackStoryLoaderTests extends DataCollectionLoaderTests<BackStory> {

    @Override
    protected String getDataPath() {
        return "/json/backstories.json";
    }

    @Override
    protected DataCollection<BackStory> loadData(FileHandle fh) {
        return BackStoryLoader.backStoriesFromJsonFile(fh);
    }

    @Override
    protected List<BackStory> buildExpectedData() {
        final List<BackStory> res = new ArrayList<>();
        
        final String name1 = "Vendetta";
        final String description1 = "They killed your family.  They killed your dog.  They killed their only hope of survival.";

        final BackStory bs1 = new BackStory(name1, description1);
        res.add(bs1);

        final String name2 = "Glory";
        final String description2 = "You want your name in the books, and that's all that matters.";

        final BackStory bs2 = new BackStory(name2, description2);
        res.add(bs2);
        
        return res;
    }
    
}
