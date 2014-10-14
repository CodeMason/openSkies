/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.maps.npcs;

import com.badlogic.gdx.files.FileHandle;
import com.github.skittishSloth.openSkies.engine.common.DataCollection;
import com.github.skittishSloth.openSkies.testUtils.DataCollectionLoaderTests;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mcory01
 */
public class NPCDetailsLoaderTests extends DataCollectionLoaderTests<NPCDetails> {

    @Override
    protected String getDataPath() {
        return "/json/npcs.json";
    }

    @Override
    protected DataCollection<NPCDetails> loadData(final FileHandle fh) {
        return NPCDetailsLoader.fromJson(fh);
    }

    @Override
    protected List<NPCDetails> buildExpectedData() {
        final List<NPCDetails> res = new ArrayList<>();
        
        final String id = "arthur";
        final String name = "Arthur";
        final String description = "Researcher in charge of your expedition to the island.";
        final String imageFileName = "researcher.png";
        
        final NPCDetails npc = new NPCDetails();
        npc.setId(id);
        npc.setName(name);
        npc.setDescription(description);
        npc.setImageFileName(imageFileName);
        res.add(npc);
        
        return res;
    }
    
}
