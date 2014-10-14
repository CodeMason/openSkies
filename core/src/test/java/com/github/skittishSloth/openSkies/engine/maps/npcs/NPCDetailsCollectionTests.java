/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.maps.npcs;

import com.github.skittishSloth.openSkies.engine.common.DataCollection;
import com.github.skittishSloth.openSkies.testUtils.DataCollectionTests;

/**
 *
 * @author mcory01
 */
public class NPCDetailsCollectionTests extends DataCollectionTests<NPCDetails> {

    @Override
    protected DataCollection<NPCDetails> buildWithDefaultConstructor() {
        return new NPCDetailsCollection();
    }

    @Override
    protected NPCDetails buildItemInstance(final int index) {
        return new NPCDetails();
    }
    
}
