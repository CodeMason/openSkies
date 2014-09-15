/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.skittishSloth.openSkies.engine.ui.characterBuilder.partDetails;

import com.badlogic.gdx.utils.Disposable;

/**
 *
 * @author mcory01
 */
public abstract class AbstractListDetails implements Comparable<AbstractListDetails>, Disposable {

    public AbstractListDetails(int order) {
        this.order = order;
    }

    public int getOrder() {
        return order;
    }

    @Override
    public int compareTo(AbstractListDetails o) {
        return Integer.compare(order, o.order);
    }
    
    private final int order;
}
