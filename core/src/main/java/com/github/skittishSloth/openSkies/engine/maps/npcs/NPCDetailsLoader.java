/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.maps.npcs;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.github.skittishSloth.openSkies.engine.common.DataCollection;

/**
 *
 * @author mcory01
 */
public class NPCDetailsLoader {
    private static final Json json = new Json();
    
    static {
        json.addClassTag("npcs", NPCDetails.class);
    }
    
    public static DataCollection<NPCDetails> fromJson(final FileHandle file) {
        final NPCDetailsCollection res = json.fromJson(NPCDetailsCollection.class, file);
        return res;
    }
}
