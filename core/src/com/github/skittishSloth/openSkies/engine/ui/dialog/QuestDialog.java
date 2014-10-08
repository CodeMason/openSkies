/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.ui.dialog;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.github.skittishSloth.openSkies.engine.quests.QuestDetails;

/**
 *
 * @author mcory01
 */
public class QuestDialog extends BaseDialog {

    public QuestDialog(final QuestDetails quest, final Skin skin) {
        super(quest.getName(), skin);
        
        this.quest = quest;
        super.setText(quest.getDescription());
        
    }
    
    private final QuestDetails quest;
}
