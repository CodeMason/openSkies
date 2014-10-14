/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.player.info;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

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
        this.modifiers = new ArrayList<>();
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
            this.modifiers = new ArrayList<>();
        } else {
            this.modifiers.clear();
        }

        if (modifiers != null) {
            this.modifiers.addAll(modifiers);
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + Objects.hashCode(this.name);
        hash = 31 * hash + Objects.hashCode(this.description);
        hash = 31 * hash + Objects.hashCode(this.modifiers);
        return hash;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PlayerClass other = (PlayerClass) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        
        return Objects.equals(this.modifiers, other.modifiers);
    }

    private String name;
    private String description;
    private ArrayList<ClassModifier> modifiers;
}
