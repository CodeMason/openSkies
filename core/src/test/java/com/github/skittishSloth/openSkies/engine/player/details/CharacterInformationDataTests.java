/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.player.details;

import com.github.skittishSloth.openSkies.engine.player.info.BackStory;
import com.github.skittishSloth.openSkies.engine.player.info.PlayerClass;
import com.github.skittishSloth.openSkies.testUtils.SimpleBeanTests;

/**
 *
 * @author mcory01
 */
public class CharacterInformationDataTests extends SimpleBeanTests {

    @Override
    protected Object createObjectInstance(final Class<?> type) {
        final Object res;
        if (type == BackStory.class) {
            res = new BackStory();
        } else if (type == PlayerClass.class) {
            res = new PlayerClass();
        } else {
            res = super.createObjectInstance(type);
        }
        
        return res;
    }

    @Override
    protected Class<?> getClassForTest() {
        return CharacterInformationData.class;
    }
    
}
