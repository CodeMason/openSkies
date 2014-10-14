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
public class ReturnStep extends MapSpecificStep {

    public ReturnStep() {
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(final Destination destination) {
        this.destination = destination;
    }

    private Destination destination;
}
