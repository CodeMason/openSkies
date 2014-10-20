/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.quests;

import com.github.skittishSloth.openSkies.testUtils.SimpleBeanTests;
import org.apache.commons.lang3.RandomUtils;

/**
 *
 * @author mcory01
 */
public class QuestDetailsTests extends SimpleBeanTests {

    @Override
    protected Class<?> getClassForTest() {
        return QuestDetails.class;
    }

    @Override
    protected Object createObjectInstance(final Class<?> type) {
        if (type == QuestDetails.Type.class) {
            final QuestDetails.Type[] vals = QuestDetails.Type.values();
            final int randIdx = RandomUtils.nextInt(0, vals.length - 1);
            return vals[randIdx];
        }
        
        return super.createObjectInstance(type); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
