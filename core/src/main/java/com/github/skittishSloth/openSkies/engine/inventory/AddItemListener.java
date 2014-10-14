/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.inventory;

import com.github.skittishSloth.openSkies.engine.inventory.items.ItemDetails;

/**
 *
 * @author mcory01
 */
public interface AddItemListener {
    void afterItemAdded(ItemDetails item);
}
