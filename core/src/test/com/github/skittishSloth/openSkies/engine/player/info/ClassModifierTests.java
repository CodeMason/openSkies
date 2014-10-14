/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.player.info;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 *
 * @author mcory01
 */
public class ClassModifierTests {
    
    @Test
    public void ensureDefaultConstructorDoesNothing() {
        final ClassModifier mod = new ClassModifier();
        
        assertNull(mod.getAmount());
        assertNull(mod.getArea());
        assertNull(mod.getType());
    }
    
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
    
    @Test
    public void ensureSettersWorkCorrectly() {
        final ClassModifier mod = new ClassModifier();
        
        assertNull(mod.getAmount());
        assertNull(mod.getArea());
        assertNull(mod.getType());
        
        final ClassModifier.Type type = ClassModifier.Type.BONUS;
        final String amount = "The Amount";
        final String area = "The Area";
        
        mod.setType(type);
        mod.setArea(area);
        mod.setAmount(amount);
        
        assertEquals(type, mod.getType());
        assertEquals(amount, mod.getAmount());
        assertEquals(area, mod.getArea());
    }
}
