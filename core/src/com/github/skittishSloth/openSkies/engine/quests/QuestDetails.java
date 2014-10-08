/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.quests;

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

    public List<BaseQuestAction> getActions() {
        return actions;
    }

    public void setActions(final Collection<BaseQuestAction> actions) {
        if (this.actions == null) {
            this.actions = new ArrayList<BaseQuestAction>();
        } else {
            this.actions.clear();
        }

        if (actions != null) {
            this.actions.addAll(actions);
        }
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

    public List<MapSpecificAction> getMapSpecificActions(final String mapName) {
        final List<MapSpecificAction> res = new ArrayList<MapSpecificAction>();

        for (final BaseQuestAction bqa : actions) {
            if (bqa instanceof MapSpecificAction) {
                final MapSpecificAction msa = MapSpecificAction.class.cast(bqa);
                if (StringUtils.equalsIgnoreCase(msa.getMap(), mapName)) {
                    res.add(msa);
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
    private ArrayList<BaseQuestAction> actions;
    private ArrayList<Reward> rewards;
}
