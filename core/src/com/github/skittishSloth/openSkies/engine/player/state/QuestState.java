/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.player.state;

import com.github.skittishSloth.openSkies.engine.quests.QuestDetails;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author mcory01
 */
public class QuestState {
    
    private static final Logger log = LoggerFactory.getLogger(QuestState.class);
    
    public QuestState() {
        
    }

    public List<QuestDetails> getCurrentQuests() {
        return currentQuests;
    }

    public void setCurrentQuests(final Collection<QuestDetails> currentQuests) {
        if (this.currentQuests == null) {
            this.currentQuests = new ArrayList<QuestDetails>();
        } else {
            this.currentQuests.clear();
        }
        
        if (currentQuests != null) {
            this.currentQuests.addAll(currentQuests);
        }
    }
    
    public void addQuest(final QuestDetails quest) {
        if (currentQuests == null) {
            currentQuests = new ArrayList<QuestDetails>();
        }
        
        if (currentQuests.contains(quest)) {
            log.debug("Quest is already present.");
            return;
        }
        
        currentQuests.add(quest);
        
        final QuestProgress progress = new QuestProgress();
        progress.setQuestId(quest.getId());
        addProgress(progress);
    }

    public List<QuestProgress> getQuestProgresses() {
        return questProgresses;
    }

    public void setQuestProgresses(final Collection<QuestProgress> questProgresses) {
        if (this.questProgresses == null) {
            this.questProgresses = new ArrayList<QuestProgress>();
        } else {
            this.questProgresses.clear();
        }
        
        if (questProgresses != null) {
            this.questProgresses.addAll(questProgresses);
        }
    }
    
    public void addProgress(final QuestProgress progress) {
        if (questProgresses == null) {
            questProgresses = new ArrayList<QuestProgress>();
        }
        
        if (questProgresses.contains(progress)) {
            return;
        }
        
        questProgresses.add(progress);
    }
    
    public QuestProgress getProgress(final QuestDetails quest) {
        return getProgress(quest.getId());
    }
    
    public QuestProgress getProgress(final int questId) {
        if (questProgresses == null) {
            return null;
        }
        
        QuestProgress res = null;
        for (final QuestProgress progress : questProgresses) {
            if (progress.getQuestId() == questId) {
                res = progress;
                break;
            }
        }
        
        return null;
    }

    public QuestDetails getActiveQuest() {
        return activeQuest;
    }

    public void setActiveQuest(final QuestDetails activeQuest) {
        this.activeQuest = activeQuest;
        addQuest(activeQuest);
    }
    
    public boolean hasActiveQuest() {
        return (activeQuest != null);
    }
    
    public boolean hasAlreadyAccepted(final QuestDetails quest) {
        if (currentQuests == null) {
            return false;
        }
        
        return currentQuests.contains(quest);
    }
    
    private ArrayList<QuestDetails> currentQuests;
    private ArrayList<QuestProgress> questProgresses;
    private QuestDetails activeQuest;
}
