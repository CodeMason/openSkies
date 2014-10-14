/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.quests;

import com.github.skittishSloth.openSkies.engine.common.DataCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author mcory01
 */
class QuestDetailsCollection implements DataCollection<QuestDetails> {

    public QuestDetailsCollection() {
    }

    @Override
    public List<QuestDetails> getData() {
        return quests;
    }

    @Override
    public void setData(final Collection<QuestDetails> data) {
        if (quests == null) {
            quests = new ArrayList<>();
        } else {
            quests.clear();
        }
        
        if (data != null) {
            quests.addAll(data);
        }
    }
    
    @Override
    public int size() {
        if (quests == null) {
            return 0;
        } else {
            return quests.size();
        }
    }
    
    private ArrayList<QuestDetails> quests;
}
