/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.player.state;

import com.github.skittishSloth.openSkies.engine.quests.BaseQuestStep;
import com.github.skittishSloth.openSkies.engine.quests.QuestDetails;
import com.github.skittishSloth.openSkies.engine.quests.QuestItem;
import com.github.skittishSloth.openSkies.engine.quests.RetrievalStep;
import com.github.skittishSloth.openSkies.engine.quests.SequenceStep;
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
        progress.setQuest(quest);
        final List<BaseQuestStep> steps = quest.getSteps();
        if ((steps != null) && (!steps.isEmpty())) {
            final Collection<StepProgress> stepProgresses = new ArrayList<StepProgress>(steps.size());
            for (final BaseQuestStep step : steps) {
                final StepProgress prog = buildStepProgress(step);
                prog.setStepId(step.getId());
                final Collection<StepProgress> childSteps = buildChildStepProgresses(step);
                prog.setChildStepProgress(childSteps);
                stepProgresses.add(prog);
            }
            progress.setStepProgress(stepProgresses);
        }
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

        return res;
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

    public void registerItemTaken(final QuestItem questItem) {
        log.debug("Registering item : " + questItem.getItemId());
        final BaseQuestStep step = questItem.getStep();
        if (step == null) {
            log.debug("Step was null.");
            return;
        }

        if (!(step instanceof RetrievalStep)) {
            log.debug("Step was not a retrieval step: {}", step.getClass().getSimpleName());
            return;
        }

        final StepProgress sp = findStepProgress(step);
        if (sp == null) {
            log.debug("Step progress was null.");
            return;
        }

        if (!(sp instanceof RetrievalStepProgress)) {
            log.debug("Step progress wasn't a retrieval step progress: {}", sp.getClass().getSimpleName());
            return;
        }

        final RetrievalStepProgress rsp = RetrievalStepProgress.class.cast(sp);
        rsp.registerItemTaken(questItem);
    }

    private Collection<StepProgress> buildChildStepProgresses(final BaseQuestStep step) {
        if (!(step instanceof SequenceStep)) {
            return null;
        }

        final SequenceStep seqStep = SequenceStep.class.cast(step);

        final List<BaseQuestStep> childSteps = seqStep.getSteps();
        final Collection<StepProgress> res;
        if ((childSteps == null) || (childSteps.isEmpty())) {
            res = null;
        } else {
            res = new ArrayList<StepProgress>(childSteps.size());
            for (final BaseQuestStep childStep : childSteps) {
                log.debug("Building child progresses for childStep {}, {}, {}.", childStep.getDescription(), childStep.getClass().getSimpleName(), childStep.getId());
                final StepProgress prog = buildStepProgress(childStep);
                prog.setStepId(childStep.getId());
                final Collection<StepProgress> subChildSteps = buildChildStepProgresses(childStep);
                prog.setChildStepProgress(subChildSteps);
                res.add(prog);
            }
        }

        return res;
    }

    private static StepProgress buildStepProgress(final BaseQuestStep step) {
        final StepProgress res;
        if (step instanceof RetrievalStep) {
            res = new RetrievalStepProgress();
        } else {
            res = new StepProgress();
        }
        res.setStep(step);
        return res;
    }

    private StepProgress findStepProgress(final BaseQuestStep step) {
        final int questId = step.getParentQuest().getId();
        QuestProgress questProgress = null;
        for (final QuestProgress availProgress : questProgresses) {
            if (availProgress.getQuestId() == questId) {
                questProgress = availProgress;
                break;
            }
        }

        if (questProgress == null) {
            log.warn("Couldn't find quest progress for quest {}", questId);
            return null;
        }

        final StepProgress res = questProgress.findStepProgress(step);

        return res;
    }

    private ArrayList<QuestDetails> currentQuests;
    private ArrayList<QuestProgress> questProgresses;
    private QuestDetails activeQuest;
}
