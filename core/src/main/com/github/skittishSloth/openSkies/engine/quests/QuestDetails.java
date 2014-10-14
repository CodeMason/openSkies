/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.quests;

import com.github.skittishSloth.openSkies.engine.inventory.items.ItemDetails;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author mcory01
 */
public class QuestDetails {

    public enum Type {

        STORY,
        OPTIONAL
    }

    public QuestDetails() {

    }

    public Type getType() {
        return type;
    }

    public void setType(final Type type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
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

    public void setPrerequisites(final Collection<QuestDetails> prerequisites) {
        if (this.prerequisites == null) {
            this.prerequisites = new ArrayList<QuestDetails>();
        } else {
            this.prerequisites.clear();
        }

        if (prerequisites != null) {
            this.prerequisites.addAll(prerequisites);
        }
    }

    public List<BaseQuestStep> getSteps() {
        return steps;
    }

    public void setSteps(final Collection<BaseQuestStep> steps) {
        if (this.steps == null) {
            this.steps = new ArrayList<BaseQuestStep>();
        } else {
            this.steps.clear();
        }

        if (steps != null) {
            this.steps.addAll(steps);
            for (final BaseQuestStep step : this.steps) {
                step.setParentQuest(this);
            }
        }
    }

    public boolean hasRewards() {
        return ((rewards != null) && (!rewards.isEmpty()));
    }

    public List<Reward> getRewards() {
        return rewards;
    }

    public void setRewards(final Collection<Reward> rewards) {
        if (this.rewards == null) {
            this.rewards = new ArrayList<Reward>();
        } else {
            this.rewards.clear();
        }

        if (rewards != null) {
            this.rewards.addAll(rewards);
        }
    }

    public List<MapSpecificStep> getMapSpecificSteps(final String mapName) {
        final List<MapSpecificStep> res = new ArrayList<MapSpecificStep>();

        for (final BaseQuestStep bqs : steps) {
            if (bqs instanceof MapSpecificStep) {
                final MapSpecificStep mss = MapSpecificStep.class.cast(bqs);
                if (StringUtils.equalsIgnoreCase(mss.getMap(), mapName)) {
                    res.add(mss);
                }
            } else if (bqs instanceof SequenceStep) {
                final SequenceStep ss = SequenceStep.class.cast(bqs);
                final List<MapSpecificStep> substeps = ss.getMapSpecificSteps(mapName);
                res.addAll(substeps);
            }
        }

        return res;
    }

    public QuestItem findItem(final ItemDetails item) {
        QuestItem res = null;
        
        for (final BaseQuestStep bqs : steps) {
            res = findItem(bqs, item);
            if (res != null) {
                break;
            }
        }
        
        return res;
    }
    
    private QuestItem findItem(final BaseQuestStep step, final ItemDetails item) {
        QuestItem res = null;
        
        if (step instanceof RetrievalStep) {
            final RetrievalStep rs = RetrievalStep.class.cast(step);
            final List<QuestItem> items = rs.getItems();
            for (final QuestItem questItem : items) {
                if ((questItem.getItem() == item) || (questItem.getItemId() == item.getId())) {
                    res = questItem;
                    break;
                }
            }
        } else if (step instanceof SequenceStep) {
            final SequenceStep ss = SequenceStep.class.cast(step);
            for (final BaseQuestStep childStep : ss.getSteps()) {
                res = findItem(childStep, item);
                if (res != null) {
                    break;
                }
            }
        }
        
        return res;
    }

    private Type type;
    private int id;
    private String name;
    private QuestGiver giver;
    private String description;
    private ArrayList<QuestDetails> prerequisites;
    private ArrayList<BaseQuestStep> steps;
    private ArrayList<Reward> rewards;
}
