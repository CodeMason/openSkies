/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.player.state;

import com.github.skittishSloth.openSkies.engine.quests.BaseQuestStep;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author mcory01
 */
public class StepProgress {

    public StepProgress() {
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(final boolean completed) {
        this.completed = completed;
    }

    public int getStepId() {
        return stepId;
    }

    public void setStepId(final int stepId) {
        this.stepId = stepId;
    }

    public List<StepProgress> getChildStepProgress() {
        return childStepProgress;
    }

    public void setChildStepProgress(final Collection<StepProgress> childStepProgress) {
        if (this.childStepProgress == null) {
            this.childStepProgress = new ArrayList<>();
        } else {
            this.childStepProgress.clear();
        }

        if (childStepProgress != null) {
            this.childStepProgress.addAll(childStepProgress);
        }
    }

    public float getCompletionPercent() {
        return completionPercent;
    }

    public void setCompletionPercent(float completionPercent) {
        this.completionPercent = completionPercent;
    }

    public BaseQuestStep getStep() {
        return step;
    }

    public void setStep(final BaseQuestStep step) {
        log.debug("Setting step! {}, {}", step.getDescription(), step.getClass().getSimpleName());
        this.step = step;
    }

    private boolean completed;
    private int stepId;
    private ArrayList<StepProgress> childStepProgress;
    private float completionPercent = 0.0f;

    private transient BaseQuestStep step;
    
    protected final Logger log = LoggerFactory.getLogger(getClass());
}
