/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.quests;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author mcory01
 */
public class QuestDetailsCollection {

    public QuestDetailsCollection() {
    }

    public List<QuestDetails> getQuests() {
        return quests;
    }

    public void setQuests(final Collection<QuestDetails> quests) {
        if (this.quests == null) {
            this.quests = new ArrayList<QuestDetails>();
        } else {
            this.quests.clear();
        }
        
        if (quests != null) {
            this.quests.addAll(quests);
        }
    }
    
    public int size() {
        if (quests == null) {
            return 0;
        } else {
            return quests.size();
        }
    }
    
    private ArrayList<QuestDetails> quests;
}
