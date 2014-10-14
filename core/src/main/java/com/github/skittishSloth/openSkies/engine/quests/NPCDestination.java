/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.quests;

/**
 *
 * @author mcory01
 */
public class NPCDestination extends Destination {

    public NPCDestination() {
    }

    public String getNpcId() {
        return npcId;
    }

    public void setNpcId(final String npcId) {
        this.npcId = npcId;
    }

    private String npcId;
}
