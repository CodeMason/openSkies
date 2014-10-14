/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.player.info;

import com.github.skittishSloth.openSkies.engine.common.DataCollection;
import com.github.skittishSloth.openSkies.testUtils.DataCollectionTests;
/**
 *
 * @author mcory01
 */
public class BackStoryCollectionTests extends DataCollectionTests<BackStory> {

    @Override
    protected DataCollection<BackStory> buildWithDefaultConstructor() {
        return new BackStoryCollection();
    }

    @Override
    protected BackStory buildItemInstance(int index) {
        return new BackStory();
    }
}
