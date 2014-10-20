/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.player.info;

import com.github.skittishSloth.openSkies.testUtils.SimpleBeanTests;
import org.apache.commons.lang3.RandomUtils;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 *
 * @author mcory01
 */
public class ClassModifierTests extends SimpleBeanTests {
    
    @Test
    public void ensureParameterizedConstructorProperlyInitializesFields() {
        final ClassModifier.Type type = ClassModifier.Type.BONUS;
        final String amount = "The Amount";
        final String area = "The Area";
        
        final ClassModifier mod = new ClassModifier(type, area, amount);
        
        assertEquals(type, mod.getType());
        assertEquals(amount, mod.getAmount());
        assertEquals(area, mod.getArea());
    }

    @Override
    protected Object createObjectInstance(Class<?> type) {
        if (type == ClassModifier.Type.class) {
            final ClassModifier.Type[] vals = ClassModifier.Type.values();
            final int numVals = vals.length;
            final int idx = RandomUtils.nextInt(0, numVals - 1);
            return vals[idx];
        }
        return super.createObjectInstance(type); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    protected Class<?> getClassForTest() {
        return ClassModifier.class;
    }
}
