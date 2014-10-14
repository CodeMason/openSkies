/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.player.info;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 *
 * @author mcory01
 */
public class PlayerClassTests {
    
    @Test
    public void ensureDefaultConstructorDoesNothing() {
        final PlayerClass playerClass = new PlayerClass();
        
        assertNull(playerClass.getModifiers());
        assertNull(playerClass.getName());
        assertNull(playerClass.getDescription());
    }
    
    @Test
    public void ensureConstructorWithStringsAndNullCollectionProperlyInitializesFields() {
        final String name = "The name";
        final String desc = "Description";
        final Collection<ClassModifier> mods = null;
        
        final PlayerClass playerClass = new PlayerClass(name, desc, mods);
        assertEquals(name, playerClass.getName());
        assertEquals(desc, playerClass.getDescription());
        assertNotNull(playerClass.getModifiers());
        assertEquals(0, playerClass.getModifiers().size());
    }
    
    @Test
    public void ensureConstructorWithStringsAndEmptyCollectionProperlyInitializesFields() {
        final String name = "The name";
        final String desc = "Description";
        final Collection<ClassModifier> mods = new ArrayList<>();
        
        final PlayerClass playerClass = new PlayerClass(name, desc, mods);
        assertEquals(name, playerClass.getName());
        assertEquals(desc, playerClass.getDescription());
        assertNotNull(playerClass.getModifiers());
        assertEquals(0, playerClass.getModifiers().size());
    }
    
    @Test
    public void ensureConstructorWithStringsAndClassModifierCollectionProperlyInitializesFields() {
        final String name = "The name";
        final String desc = "Description";
        final List<ClassModifier> mods = new ArrayList<>();
        
        final int size = 5;
        for (int i = 0; i < size; ++i) {
            final ClassModifier mod = new ClassModifier(ClassModifier.Type.BONUS, "Area " + i, "Amount " + i);
            mods.add(mod);
        }
        
        final PlayerClass playerClass = new PlayerClass(name, desc, mods);
        assertEquals(name, playerClass.getName());
        assertEquals(desc, playerClass.getDescription());
        
        final List<ClassModifier> resMods = new ArrayList<>(playerClass.getModifiers());
        assertNotNull(resMods);
        assertEquals(size, resMods.size());
        
        for (int i = 0; i < size; ++i) {
            assertEquals(mods.get(i), resMods.get(i));
        }
    }
    
    @Test
    public void ensureNameAndDescriptionSettersWorkAppropriately() {
        final PlayerClass playerClass = new PlayerClass();
        
        assertNull(playerClass.getName());
        assertNull(playerClass.getDescription());
        
        final String name = "The name";
        final String desc = "Description";
        
        playerClass.setName(name);
        playerClass.setDescription(desc);
        
        assertEquals(name, playerClass.getName());
        assertEquals(desc, playerClass.getDescription());
    }
    
    @Test
    public void ensureModifierSetterInitializesUnderlyingCollectionEvenIfPassedNull() {
        final PlayerClass playerClass = new PlayerClass();
        
        assertNull(playerClass.getModifiers());
        
        final Collection<ClassModifier> mods = null;
        playerClass.setModifiers(mods);
        assertNotNull(playerClass.getModifiers());
        assertEquals(0, playerClass.getModifiers().size());
    }
    
    @Test
    public void ensureModifierSetterProperlyFillsCollection() {
        final PlayerClass playerClass = new PlayerClass();
        
        assertNull(playerClass.getModifiers());
        
        final List<ClassModifier> mods = new ArrayList<>();
        
        final int size = 5;
        for (int i = 0; i < size; ++i) {
            final ClassModifier mod = new ClassModifier(ClassModifier.Type.BONUS, "Area " + i, "Amount " + i);
            mods.add(mod);
        }
        
        playerClass.setModifiers(mods);
        
        final List<ClassModifier> resMods = new ArrayList<>(playerClass.getModifiers());
        assertNotNull(resMods);
        assertEquals(size, resMods.size());
        
        for (int i = 0; i < size; ++i) {
            assertEquals(mods.get(i), resMods.get(i));
        }
    }
    
    @Test
    public void ensureModifierSetterProperlyClearsAnExistingCollection() {
        final PlayerClass playerClass = new PlayerClass();
        
        assertNull(playerClass.getModifiers());
        
        final List<ClassModifier> mods = new ArrayList<>();
        
        final int size = 5;
        for (int i = 0; i < size; ++i) {
            final ClassModifier mod = new ClassModifier(ClassModifier.Type.BONUS, "Area " + i, "Amount " + i);
            mods.add(mod);
        }
        
        playerClass.setModifiers(mods);
        
        final List<ClassModifier> resMods = new ArrayList<>(playerClass.getModifiers());
        assertNotNull(resMods);
        assertEquals(size, resMods.size());
        
        for (int i = 0; i < size; ++i) {
            assertEquals(mods.get(i), resMods.get(i));
        }
        
        final List<ClassModifier> nullMods = null;
        playerClass.setModifiers(nullMods);
        
        final List<ClassModifier> resModsFromNull = new ArrayList<>(playerClass.getModifiers());
        assertNotNull(resModsFromNull);
        assertEquals(0, resModsFromNull.size());
    }
}
