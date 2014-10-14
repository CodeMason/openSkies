/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.quests;

import com.github.skittishSloth.openSkies.engine.common.CollectionUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author mcory01
 */
public class RetrievalStep extends MapSpecificStep {

    public RetrievalStep() {
        
    }

    public List<QuestItem> getItems() {
        return items;
    }

    public void setItems(final Collection<QuestItem> items) {
        if (this.items == null) {
            this.items = new ArrayList<QuestItem>();
        } else {
            this.items.clear();
        }
        
        if (items != null) {
            this.items.addAll(items);
            for (final QuestItem item : this.items) {
                item.setStep(this);
            }
        }
    }
    
    public boolean hasItems() {
        return CollectionUtils.isNotEmpty(items);
    }
    
    private ArrayList<QuestItem> items;
}
