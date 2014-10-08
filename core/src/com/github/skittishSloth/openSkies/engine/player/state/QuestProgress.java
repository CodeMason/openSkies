/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.player.state;

/**
 *
 * @author mcory01
 */
public class QuestProgress {
    
    public QuestProgress() {
        
    }

    public int getQuestId() {
        return questId;
    }

    public void setQuestId(int questId) {
        this.questId = questId;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public float getCompletionPercent() {
        return completionPercent;
    }

    public void setCompletionPercent(float completionPercent) {
        this.completionPercent = completionPercent;
    }
    
    
    
    private int questId;
    private boolean completed;
    private float completionPercent;
}
