/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.player.state;

import com.github.skittishSloth.openSkies.engine.quests.BaseQuestStep;
import com.github.skittishSloth.openSkies.engine.quests.QuestDetails;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    public void setQuestId(final int questId) {
        this.questId = questId;
    }

    public QuestDetails getQuest() {
        return quest;
    }

    public void setQuest(final QuestDetails quest) {
        this.quest = quest;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(final boolean completed) {
        this.completed = completed;
    }

    public float getCompletionPercent() {
        return completionPercent;
    }

    public void setCompletionPercent(final float completionPercent) {
        this.completionPercent = completionPercent;
    }

    public List<StepProgress> getStepProgress() {
        return stepProgress;
    }

    public void setStepProgress(final Collection<StepProgress> stepProgress) {
        if (this.stepProgress == null) {
            this.stepProgress = new ArrayList<StepProgress>();
        } else {
            this.stepProgress.clear();
        }

        if (stepProgress != null) {
            this.stepProgress.addAll(stepProgress);
        }
    }

    public StepProgress findStepProgress(final BaseQuestStep step) {
        if (step.getParentQuest().getId() != questId) {
            return null;
        }
        
        if (stepProgressMap.isEmpty()) {
            buildStepProgressMap();
        }

        final StepProgress res = stepProgressMap.get(step);
        
        if (res == null) {
            log.debug("No progress found :/");
        }

        return res;
    }
    
    private void buildStepProgressMap() {
        if (quest == null) {
            return;
        }
        
        if (stepProgress == null) {
            return;
        }
        
        for (final StepProgress sp : stepProgress) {
            addStepProgress(sp);
        }
    }
    
    private void addStepProgress(final StepProgress prog) {
        final BaseQuestStep step = prog.getStep();
        stepProgressMap.put(step, prog);
        final List<StepProgress> childStepProgress = prog.getChildStepProgress();
        if (childStepProgress != null) {
            for (final StepProgress childProg : childStepProgress) {
                addStepProgress(childProg);
            }
        }
    }

    private int questId;
    private boolean completed;
    private float completionPercent;
    private ArrayList<StepProgress> stepProgress;

    private transient QuestDetails quest;
    private final Map<BaseQuestStep, StepProgress> stepProgressMap = new HashMap<BaseQuestStep, StepProgress>();
    
    protected final Logger log = LoggerFactory.getLogger(getClass());
}
