/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.player.details;

import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 *
 * @author mcory01
 */
public class BaseDetailsTests {
    
    @Test
    public void ensureDefaultConstructorInitializesNothing() {
        final BaseDetails bd = new BaseDetails();
        
        assertNull(bd.getDisplayName());
        assertNull(bd.getFemaleTexturePath());
        assertNull(bd.getMaleTexturePath());
        assertNull(bd.getName());
        assertNull(bd.getTexturePathPattern());
        assertFalse(bd.isDefaultDetails());
        assertFalse(bd.isNullTexture());
    }
    
    @Test
    public void ensureSettersWorkProperly() {
        final BaseDetails bd = new BaseDetails();
        
        final String displayName = "Display Name";
        final String femaleTexturePath = "Female Texture Path";
        final String maleTexturePath = "Male Texture Path";
        final String name = "Name";
        final String texturePathPattern = "Texture Path Pattern";
        final boolean defaultDetails = true;
        final boolean nullTexture = true;
        
        bd.setDisplayName(displayName);
        bd.setDefaultDetails(defaultDetails);
        bd.setNullTexture(nullTexture);
        bd.setFemaleTexturePath(femaleTexturePath);
        bd.setMaleTexturePath(maleTexturePath);
        bd.setName(name);
        bd.setTexturePathPattern(texturePathPattern);
        
        assertEquals(displayName, bd.getDisplayName());
        assertEquals(defaultDetails, bd.isDefaultDetails());
        assertEquals(nullTexture, bd.isNullTexture());
        assertEquals(femaleTexturePath, bd.getFemaleTexturePath());
        assertEquals(maleTexturePath, bd.getMaleTexturePath());
        assertEquals(name, bd.getName());
        assertEquals(texturePathPattern, bd.getTexturePathPattern());
    }
    
    @Test
    public void ensureHasTextureReturnsAppropriateResult() {
        final BaseDetails bd = new BaseDetails();
        
        final String femaleTexturePath = "Female Texture Path";
        final String maleTexturePath = "Male Texture Path";
        final String texturePathPattern = "Texture Path Pattern";
        final boolean nullTexture = true;
        
        bd.setNullTexture(nullTexture);
        
        
        // null texture is true, so should return false
        assertFalse(bd.hasTexture());
        
        bd.setFemaleTexturePath(femaleTexturePath);
        assertTrue(bd.hasTexture());
        
        bd.setFemaleTexturePath(null);
        assertFalse(bd.hasTexture());
        
        bd.setMaleTexturePath(maleTexturePath);
        assertTrue(bd.hasTexture());
        
        bd.setMaleTexturePath(null);
        assertFalse(bd.hasTexture());
        
        bd.setTexturePathPattern(texturePathPattern);
        assertTrue(bd.hasTexture());
        
        bd.setTexturePathPattern(null);
        assertFalse(bd.hasTexture());
        
        bd.setNullTexture(false);
        assertTrue(bd.hasTexture());
    }
    
    @Test
    public void ensureIsValidForGenderReturnsAppropriateResult() {
        final BaseDetails bd = new BaseDetails();
        
        final String femaleTexturePath = "Female Texture Path";
        final String maleTexturePath = "Male Texture Path";
        final String texturePathPattern = "Texture Path Pattern";
        final boolean nullTexture = true;
        
        bd.setNullTexture(nullTexture);
        assertTrue(bd.isValidForGender(Gender.MALE));
        assertTrue(bd.isValidForGender(Gender.FEMALE));
        
        bd.setNullTexture(false);
        assertFalse(bd.isValidForGender(Gender.MALE));
        assertFalse(bd.isValidForGender(Gender.FEMALE));
        assertFalse(bd.isValidForGender(Gender.NEUTRAL));
        
        bd.setTexturePathPattern(texturePathPattern);
        assertTrue(bd.isValidForGender(Gender.MALE));
        assertTrue(bd.isValidForGender(Gender.FEMALE));
        
        bd.setTexturePathPattern(null);
        assertFalse(bd.isValidForGender(Gender.MALE));
        assertFalse(bd.isValidForGender(Gender.FEMALE));
        assertFalse(bd.isValidForGender(Gender.NEUTRAL));
        
        bd.setFemaleTexturePath(femaleTexturePath);
        assertFalse(bd.isValidForGender(Gender.MALE));
        assertTrue(bd.isValidForGender(Gender.FEMALE));
        assertFalse(bd.isValidForGender(Gender.NEUTRAL));
        
        bd.setFemaleTexturePath(null);
        assertFalse(bd.isValidForGender(Gender.MALE));
        assertFalse(bd.isValidForGender(Gender.FEMALE));
        assertFalse(bd.isValidForGender(Gender.NEUTRAL));
        
        bd.setMaleTexturePath(maleTexturePath);
        assertTrue(bd.isValidForGender(Gender.MALE));
        assertFalse(bd.isValidForGender(Gender.FEMALE));
        assertFalse(bd.isValidForGender(Gender.NEUTRAL));
        
        bd.setMaleTexturePath(null);
        assertFalse(bd.isValidForGender(Gender.MALE));
        assertFalse(bd.isValidForGender(Gender.FEMALE));
        assertFalse(bd.isValidForGender(Gender.NEUTRAL));
    }
    
    @Test
    public void ensureGetTexturePathWithGenderAndVarsReturnsAppropriateResult() {
        final BaseDetails bd = new BaseDetails();
        
        final String femaleTexturePath = "Female Texture Path";
        final String maleTexturePath = "Male Texture Path";
        final String texturePathPattern = "Texture Path Pattern ${replace}";
        
        final boolean nullTexture = true;
        
        bd.setNullTexture(nullTexture);
        assertFalse(bd.hasTexture());
        
        final Map<String, String> params = new HashMap<>();
        assertNull(bd.getTexturePath(Gender.MALE, params));
        
        bd.setNullTexture(false);
        bd.setMaleTexturePath(maleTexturePath);
        bd.setFemaleTexturePath(femaleTexturePath);
        
        assertEquals(femaleTexturePath, bd.getTexturePath(Gender.FEMALE, params));
        assertEquals(maleTexturePath, bd.getTexturePath(Gender.MALE, params));
        
        assertNull(bd.getTexturePath(Gender.NEUTRAL, params));
        
        bd.setTexturePathPattern(texturePathPattern);
        
        params.put("${replace}", "target");
        final String expTexturePathPattern = "Texture Path Pattern target";
        assertEquals(expTexturePathPattern, bd.getTexturePath(Gender.MALE, params));
    }
}
