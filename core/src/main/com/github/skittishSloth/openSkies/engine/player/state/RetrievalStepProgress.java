/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.player.state;

import com.github.skittishSloth.openSkies.engine.inventory.AddItemListener;
import com.github.skittishSloth.openSkies.engine.inventory.items.ItemDetails;
import com.github.skittishSloth.openSkies.engine.quests.BaseQuestStep;
import com.github.skittishSloth.openSkies.engine.quests.QuestItem;
import com.github.skittishSloth.openSkies.engine.quests.RetrievalStep;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author mcory01
 */
public class RetrievalStepProgress extends StepProgress {

    public void registerItemTaken(final QuestItem item) {
        final RetrievalStep step = getRetrievalStep();
        if (step == null) {
            return;
        }

        final List<QuestItem> stepItems = step.getItems();
        boolean found = false;
        for (final QuestItem stepItem : stepItems) {
            if (item == stepItem) {
                found = true;
                break;
            }
        }

        if (!found) {
            return;
        }

        addFoundItem(item);
        updateCompletion(stepItems);
    }

    public final List<QuestItem> getFoundItems() {
        return foundItems;
    }

    public final void setFoundItems(Collection<QuestItem> foundItems) {
        if (this.foundItems == null) {
            this.foundItems = new ArrayList<QuestItem>();
        } else {
            this.foundItems.clear();
        }

        if (foundItems != null) {
            this.foundItems.addAll(foundItems);
        }
    }

    protected final void addFoundItem(final QuestItem item) {
        if (foundItems == null) {
            foundItems = new ArrayList<QuestItem>();
        }

        if (!foundItems.contains(item)) {
            foundItems.add(item);
        }
    }

    protected final void updateCompletion(final List<QuestItem> stepItems) {
        final float percent;
        if (foundItems == null) {
            percent = 0;
        } else {
            final int numFoundItems = foundItems.size();
            final int numStepItems = stepItems.size();

            percent = ((float) numFoundItems / (float) numStepItems);
        }

        setCompletionPercent(percent);
        setCompleted(percent >= 1.0f);
    }

    protected final RetrievalStep getRetrievalStep() {
        final BaseQuestStep step = getStep();
        if (step == null) {
            return null;
        }

        if (!(step instanceof RetrievalStep)) {
            return null;
        }

        return RetrievalStep.class.cast(step);
    }

    private ArrayList<QuestItem> foundItems;
}
