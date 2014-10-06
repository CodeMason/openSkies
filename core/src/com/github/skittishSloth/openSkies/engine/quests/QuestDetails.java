/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.quests;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mcory01
 */
public class QuestDetails {
    
    public QuestDetails() {
        
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public QuestGiver getGiver() {
        return giver;
    }

    public void setGiver(final QuestGiver giver) {
        this.giver = giver;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public List<QuestDetails> getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(ArrayList<QuestDetails> prerequisites) {
        if (this.prerequisites == null) {
            this.prerequisites = new ArrayList<QuestDetails>();
        } else {
            this.prerequisites.clear();
        }
        
        if (prerequisites != null) {
            this.prerequisites.addAll(prerequisites);
        }
    }
    
    private String name;
    private QuestGiver giver;
    private String description;
    private ArrayList<QuestDetails> prerequisites;
}
