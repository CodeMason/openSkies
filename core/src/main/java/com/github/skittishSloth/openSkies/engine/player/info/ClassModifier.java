/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.player.info;

import java.util.Objects;

/**
 *
 * @author mcory01
 */
public class ClassModifier {

    public static enum Type {

        BONUS,
        PENALTY;
    }

    public ClassModifier() {

    }

    public ClassModifier(final Type type, final String area, final String amount) {
        this.type = type;
        this.area = area;
        this.amount = amount;
    }

    public Type getType() {
        return type;
    }

    public void setType(final Type type) {
        this.type = type;
    }

    public String getArea() {
        return area;
    }

    public void setArea(final String area) {
        this.area = area;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(final String amount) {
        this.amount = amount;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.type);
        hash = 89 * hash + Objects.hashCode(this.area);
        hash = 89 * hash + Objects.hashCode(this.amount);
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
        final ClassModifier other = (ClassModifier) obj;
        if (this.type != other.type) {
            return false;
        }
        if (!Objects.equals(this.area, other.area)) {
            return false;
        }
        
        return  Objects.equals(this.amount, other.amount);
    }

    private Type type;
    private String area;
    private String amount;
}
