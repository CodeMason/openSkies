/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.quests;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.github.skittishSloth.openSkies.engine.common.DataCollection;

/**
 *
 * @author mcory01
 */
public final class QuestLoader {
    private static final Json json = new Json();
    
    static {
        json.addClassTag("sequenceStep", SequenceStep.class);
        json.addClassTag("retrievalStep", RetrievalStep.class);
        json.addClassTag("returnStep", ReturnStep.class);
        json.addClassTag("npcDestination", NPCDestination.class);
        json.addClassTag("staticItem", StaticItem.class);
    }
    
    private QuestLoader() {
        
    }
    
    public static QuestDetails fromJson(final FileHandle file) {
        final QuestDetails res = json.fromJson(QuestDetails.class, file);
        return res;
    }
    
    public static DataCollection<QuestDetails> listFromJson(final FileHandle file) {
        final QuestDetailsCollection res = json.fromJson(QuestDetailsCollection.class, file);
        return res;
    }
}
