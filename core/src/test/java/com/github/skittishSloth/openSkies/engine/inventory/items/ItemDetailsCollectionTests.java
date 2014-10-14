/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.inventory.items;

import com.github.skittishSloth.openSkies.engine.common.DataCollection;
import com.github.skittishSloth.openSkies.testUtils.DataCollectionTests;

/**
 *
 * @author mcory01
 */
public class ItemDetailsCollectionTests extends DataCollectionTests<ItemDetails> {

    @Override
    protected DataCollection<ItemDetails> buildWithDefaultConstructor() {
        return new ItemDetailsCollection();
    }

    @Override
    protected ItemDetails buildItemInstance(final int index) {
        return new ItemDetails();
    }
}
