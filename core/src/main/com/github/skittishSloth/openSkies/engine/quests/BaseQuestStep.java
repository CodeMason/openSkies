/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.quests;

/**
 *
 * @author mcory01
 */
public class BaseQuestStep {

    public BaseQuestStep() {

    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public QuestDetails getParentQuest() {
        return parentQuest;
    }

    public void setParentQuest(final QuestDetails parentQuest) {
        this.parentQuest = parentQuest;
    }

    public BaseQuestStep getParentStep() {
        return parentStep;
    }

    public void setParentStep(final BaseQuestStep parentStep) {
        this.parentStep = parentStep;
    }
    
    public boolean hasParentStep() {
        return (parentStep != null);
    }

    private int id;
    private String description;

    private transient QuestDetails parentQuest;
    private transient BaseQuestStep parentStep;
}
