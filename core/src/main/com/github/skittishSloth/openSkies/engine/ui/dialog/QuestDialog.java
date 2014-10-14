/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.ui.dialog;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Tree;
import com.github.skittishSloth.openSkies.engine.quests.QuestDetails;
import com.github.skittishSloth.openSkies.engine.quests.Reward;
import java.util.List;

/**
 *
 * @author mcory01
 */
public class QuestDialog extends BaseDialog {

    public QuestDialog(final Skin skin) {
        super("Uninitialized", skin);
        this.skin = skin;
        
        rewardsTree = new Tree(skin);
        rewardsLabel = new Label("Rewards:", skin);
        rewardsTable = new Table(skin);
        
        rewardsTable.add(rewardsLabel).top().left();
        rewardsTable.row();
        rewardsTable.add(rewardsTree).top().left();
        getTextTable().row();
        getTextTable().add(rewardsTable);
    }
    
    public void setQuest(final QuestDetails quest) {
        this.quest = quest;
        final String description = quest.getDescription();
        final String title = quest.getName();
        
        setText(description);
        setTitle(title);
        buildRewardsNode();
    }
    
    private void buildRewardsNode() {
        if ((quest == null) || !quest.hasRewards()) {
            return;
        }
        
        rewardsTree.clear();
        final List<Reward> rewards = quest.getRewards();
        for (final Reward reward : rewards) {
            final Label desc = new Label(reward.getDescription(), skin);
            final Tree.Node node = new Tree.Node(desc);
            rewardsTree.add(node);
        }
    }
    
    private final Skin skin;
    private final Tree rewardsTree;
    private final Table rewardsTable;
    private final Label rewardsLabel;
    private QuestDetails quest;
}
