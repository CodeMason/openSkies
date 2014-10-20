/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.player.details;

import com.badlogic.gdx.files.FileHandle;
import com.github.skittishSloth.openSkies.testUtils.SimpleBeanTests;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author mcory01
 */
public class BaseDetailsTests extends SimpleBeanTests {
    
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
    
    @Test
    public void ensureGetTextureAtlasPathReturnsNullIfHasTextureIsFalse() {
        final BaseDetails bd = new BaseDetails();
        bd.setNullTexture(true);
        assertFalse(bd.hasTexture());
        
        final CharacterData cd = new CharacterData();
        
        assertNull(bd.getTextureAtlasPath(cd));
    }
    
    @Test
    public void ensureGetTextureAtlasPathWorkCorrectlyIfPassedAppropriateCharacterData() throws URISyntaxException {
        final BaseDetails bd = new BaseDetails();
        
        final String femaleTexturePath = "Female Texture Path";
        final String maleTexturePath = "Male Texture Path";
        
        final boolean nullTexture = true;
        
        bd.setNullTexture(nullTexture);
        assertFalse(bd.hasTexture());
        
        bd.setNullTexture(false);
        bd.setMaleTextureAtlasPath(maleTexturePath);
        bd.setFemaleTextureAtlasPath(femaleTexturePath);
        
        final FileHandle ffh = getFileHandle("/json/female-character-data.json");
        final CharacterData femaleChar = DetailsLoader.loadCharacterData(ffh);
        
        assertEquals(Gender.FEMALE, femaleChar.getAppearanceData().getGender());
        final String actFemaleTextureAtlasPath = bd.getTextureAtlasPath(femaleChar);
        assertNotNull(actFemaleTextureAtlasPath);
        assertEquals(femaleTexturePath, actFemaleTextureAtlasPath);
        
        final FileHandle mfh = getFileHandle("/json/male-character-data.json");
        final CharacterData maleChar = DetailsLoader.loadCharacterData(mfh);
        assertEquals(Gender.MALE, maleChar.getAppearanceData().getGender());
        final String actMaleTextureAtlasPath = bd.getTextureAtlasPath(maleChar);
        assertNotNull(actMaleTextureAtlasPath);
        assertEquals(maleTexturePath, actMaleTextureAtlasPath);
    }

    protected final FileHandle getFileHandle(final String path) throws URISyntaxException {
        final URL fileUrl = getClass().getResource(path);
        final File file = new File(fileUrl.toURI());
        final FileHandle fh = new FileHandle(file);

        return fh;
    }

    @Override
    protected Class<?> getClassForTest() {
        return BaseDetails.class;
    }
}
