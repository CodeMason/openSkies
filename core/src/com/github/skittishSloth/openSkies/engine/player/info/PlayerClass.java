/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.player.info;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author mcory01
 */
public class PlayerClass {
    
    public PlayerClass() {
        
    }

    public PlayerClass(final String name, final String description, final Collection<ClassModifier> modifiers) {
        this.name = name;
        this.description = description;
        this.modifiers = new ArrayList<ClassModifier>();
        if (modifiers != null) {
            for (final ClassModifier cm : modifiers) {
                this.modifiers.add(cm);
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public Collection<ClassModifier> getModifiers() {
        return modifiers;
    }

    public void setModifiers(final Collection<ClassModifier> modifiers) {
        if (this.modifiers == null) {
            this.modifiers = new ArrayList<ClassModifier>();
        } else {
            this.modifiers.clear();
        }
        
        if (modifiers != null) {
            this.modifiers.addAll(modifiers);
        }
    }

    private String name;
    private String description;
    private ArrayList<ClassModifier> modifiers;
}
