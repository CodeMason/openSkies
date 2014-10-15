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
        assertNull(bd.getFemaleTextureAtlasPath());
        assertNull(bd.getMaleTextureAtlasPath());
        assertNull(bd.getName());
        assertNull(bd.getTextureAtlasPathPattern());
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
        bd.setFemaleTextureAtlasPath(femaleTexturePath);
        bd.setMaleTextureAtlasPath(maleTexturePath);
        bd.setName(name);
        bd.setTextureAtlasPathPattern(texturePathPattern);
        
        assertEquals(displayName, bd.getDisplayName());
        assertEquals(defaultDetails, bd.isDefaultDetails());
        assertEquals(nullTexture, bd.isNullTexture());
        assertEquals(femaleTexturePath, bd.getFemaleTextureAtlasPath());
        assertEquals(maleTexturePath, bd.getMaleTextureAtlasPath());
        assertEquals(name, bd.getName());
        assertEquals(texturePathPattern, bd.getTextureAtlasPathPattern());
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
        
        bd.setFemaleTextureAtlasPath(femaleTexturePath);
        assertTrue(bd.hasTexture());
        
        bd.setFemaleTextureAtlasPath(null);
        assertFalse(bd.hasTexture());
        
        bd.setMaleTextureAtlasPath(maleTexturePath);
        assertTrue(bd.hasTexture());
        
        bd.setMaleTextureAtlasPath(null);
        assertFalse(bd.hasTexture());
        
        bd.setTextureAtlasPathPattern(texturePathPattern);
        assertTrue(bd.hasTexture());
        
        bd.setTextureAtlasPathPattern(null);
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
        
        bd.setTextureAtlasPathPattern(texturePathPattern);
        assertTrue(bd.isValidForGender(Gender.MALE));
        assertTrue(bd.isValidForGender(Gender.FEMALE));
        
        bd.setTextureAtlasPathPattern(null);
        assertFalse(bd.isValidForGender(Gender.MALE));
        assertFalse(bd.isValidForGender(Gender.FEMALE));
        assertFalse(bd.isValidForGender(Gender.NEUTRAL));
        
        bd.setFemaleTextureAtlasPath(femaleTexturePath);
        assertFalse(bd.isValidForGender(Gender.MALE));
        assertTrue(bd.isValidForGender(Gender.FEMALE));
        assertFalse(bd.isValidForGender(Gender.NEUTRAL));
        
        bd.setFemaleTextureAtlasPath(null);
        assertFalse(bd.isValidForGender(Gender.MALE));
        assertFalse(bd.isValidForGender(Gender.FEMALE));
        assertFalse(bd.isValidForGender(Gender.NEUTRAL));
        
        bd.setMaleTextureAtlasPath(maleTexturePath);
        assertTrue(bd.isValidForGender(Gender.MALE));
        assertFalse(bd.isValidForGender(Gender.FEMALE));
        assertFalse(bd.isValidForGender(Gender.NEUTRAL));
        
        bd.setMaleTextureAtlasPath(null);
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
        assertNull(bd.getTextureAtlasPath(Gender.MALE, params));
        
        bd.setNullTexture(false);
        bd.setMaleTextureAtlasPath(maleTexturePath);
        bd.setFemaleTextureAtlasPath(femaleTexturePath);
        
        assertEquals(femaleTexturePath, bd.getTextureAtlasPath(Gender.FEMALE, params));
        assertEquals(maleTexturePath, bd.getTextureAtlasPath(Gender.MALE, params));
        
        assertNull(bd.getTextureAtlasPath(Gender.NEUTRAL, params));
        
        bd.setTextureAtlasPathPattern(texturePathPattern);
        
        params.put("${replace}", "target");
        final String expTexturePathPattern = "Texture Path Pattern target";
        assertEquals(expTexturePathPattern, bd.getTextureAtlasPath(Gender.MALE, params));
    }
}
