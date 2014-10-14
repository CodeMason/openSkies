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
public class QuestGiver {
    public enum Type {
        NPC,
        EVENT;
    }
    
    public QuestGiver() {
        
    }

    public Type getType() {
        return type;
    }

    public void setType(final Type type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getMap() {
        return map;
    }

    public void setMap(final String map) {
        this.map = map;
    }
    
    private Type type;
    private String id;
    private String map;
}
