/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.player.state;

import com.github.skittishSloth.openSkies.engine.inventory.Inventory;

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

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(final Inventory inventory) {
        this.inventory = inventory;
    }

    private QuestState questState;
    private Inventory inventory;
}
