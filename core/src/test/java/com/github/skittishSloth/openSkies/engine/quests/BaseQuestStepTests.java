/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.quests;


import com.github.skittishSloth.openSkies.testUtils.SimpleBeanTests;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 *
 * @author mcory01
 */
public class BaseQuestStepTests extends SimpleBeanTests {
    
    @Test
    public void ensureHasParentStepWorksCorrectly() {
        final BaseQuestStep bqs = new BaseQuestStep();
        
        assertNull(bqs.getParentStep());
        assertFalse(bqs.hasParentStep());
        
        final BaseQuestStep parent = new BaseQuestStep();
        bqs.setParentStep(parent);
        assertNotNull(bqs.getParentStep());
        assertTrue(bqs.hasParentStep());
    }

    @Override
    protected Class<?> getClassForTest() {
        return BaseQuestStep.class;
    }
}
