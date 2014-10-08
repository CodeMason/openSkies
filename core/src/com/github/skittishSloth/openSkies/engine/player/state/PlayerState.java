/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.player.state;

/**
 *
 * @author mcory01
 */
public class PlayerState {

    public PlayerState() {

    }

    public QuestState getQuestState() {
        return questState;
    }

    public void setQuestState(final QuestState questState) {
        this.questState = questState;
    }

    private QuestState questState;
}
