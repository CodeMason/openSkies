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
public class MapSpecificAction extends BaseQuestAction {
    

    public final String getMap() {
        return map;
    }

    public final void setMap(final String map) {
        this.map = map;
    }
    
    private String map;
}
