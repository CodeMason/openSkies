/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.ui.hud;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Tree;
import com.github.skittishSloth.openSkies.engine.player.state.QuestProgress;
import com.github.skittishSloth.openSkies.engine.player.state.QuestState;
import com.github.skittishSloth.openSkies.engine.player.state.StepProgress;
import com.github.skittishSloth.openSkies.engine.quests.BaseQuestStep;
import com.github.skittishSloth.openSkies.engine.quests.QuestDetails;
import com.github.skittishSloth.openSkies.engine.quests.SequenceStep;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author mcory01
 */
public class QuestTracker extends Table {

    private static final Logger log = LoggerFactory.getLogger(QuestTracker.class);

    public QuestTracker(final Skin skin, final QuestState questState) {
        super(skin);

        this.skin = skin;
        questTitle = new Label("No Active Quests", skin);
        steps = new Tree(skin);
        add(questTitle);
        row();
        add(steps);
        this.questState = questState;
    }

    public QuestDetails getCurrentQuest() {
        return currentQuest;
    }

    public void setCurrentQuest(final QuestDetails currentQuest) {
        this.currentQuest = currentQuest;
        steps.clear();
        if (currentQuest == null) {
            questTitle.setText("No Active Quests");
        } else {
            questTitle.setText(currentQuest.getName());
            buildStepsTree();
        }
    }

    public void updateSteps() {
        steps.clear();
        buildStepsTree();
    }

    private void buildStepsTree() {
        final List<BaseQuestStep> questSteps = currentQuest.getSteps();
        for (final BaseQuestStep step : questSteps) {
            final Label stepLabel = new Label("", skin);
            final Tree.Node stepNode = new Tree.Node(stepLabel);
            final String description;
            if (step instanceof SequenceStep) {
                final SequenceStep sequenceStep = SequenceStep.class.cast(step);
                final BaseQuestStep activeStep = getActiveStep(sequenceStep);
                if (activeStep == null) {
                    description = "<No Active Step>";
                    log.error("Active step was null.");
                } else {
                    description = activeStep.getDescription();
                }
            } else {
                description = step.getDescription();
            }

            stepLabel.setText(description);

            steps.add(stepNode);
        }
    }

    private BaseQuestStep getActiveStep(final SequenceStep step) {
        final int id = step.getId();

        final QuestProgress questProgress = questState.getProgress(currentQuest);
        final List<StepProgress> questStepProgress = questProgress.getStepProgress();
        StepProgress sequenceStepProgress = null;
        for (final StepProgress qsp : questStepProgress) {
            if (id == qsp.getStepId()) {
                sequenceStepProgress = qsp;
                break;
            }
        }

        if (sequenceStepProgress == null) {
            return null;
        }

        final List<StepProgress> childStepProgress = sequenceStepProgress.getChildStepProgress();
        final int numChildSteps = childStepProgress.size();

        int activeStepIdx = 0;
        for (int i = 0; i < numChildSteps; ++i) {
            final StepProgress sp = childStepProgress.get(i);
            if (sp.isCompleted()) {
                continue;
            }

            activeStepIdx = i;
            break;
        }

        final List<BaseQuestStep> childSteps = step.getSteps();
        final BaseQuestStep activeStep = childSteps.get(activeStepIdx);
        return activeStep;
    }

    private final Label questTitle;
    private final Tree steps;
    private final QuestState questState;
    private final Skin skin;

    private QuestDetails currentQuest;
}
