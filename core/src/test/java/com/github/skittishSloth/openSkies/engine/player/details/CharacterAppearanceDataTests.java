/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.player.details;

import java.util.Map;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 *
 * @author mcory01
 */
public class CharacterAppearanceDataTests {
    @Test
    public void ensureDefaultConstructorInitializesNothing() {
        final CharacterAppearanceData cad = new CharacterAppearanceData();
        
        assertNull(cad.getEarDetails());
        assertNull(cad.getEyeDetails());
        assertNull(cad.getFacialHairColor());
        assertNull(cad.getFacialHairStyle());
        assertNull(cad.getGender());
        assertNull(cad.getHairColor());
        assertNull(cad.getHairStyle());
        assertNull(cad.getName());
        assertNull(cad.getNose());
        assertNull(cad.getRace());
        assertNull(cad.getSkinColor());
    }
    
    @Test
    public void ensureSettersWorkProperly() {
        final CharacterAppearanceData cad = new CharacterAppearanceData();
        
        assertNull(cad.getEarDetails());
        assertNull(cad.getEyeDetails());
        assertNull(cad.getFacialHairColor());
        assertNull(cad.getFacialHairStyle());
        assertNull(cad.getGender());
        assertNull(cad.getHairColor());
        assertNull(cad.getHairStyle());
        assertNull(cad.getName());
        assertNull(cad.getNose());
        assertNull(cad.getRace());
        assertNull(cad.getSkinColor());
        
        final BaseDetails earDetails = new BaseDetails();
        final ColoredDetails eyeDetails = new ColoredDetails();
        final BaseDetails facialHairColor = new BaseDetails();
        final BaseDetails facialHairStyle = new BaseDetails();
        final Gender gender = Gender.MALE;
        final BaseDetails hairColor = new BaseDetails();
        final BaseDetails hairStyle = new BaseDetails();
        final String name = "Name";
        final BaseDetails nose = new BaseDetails();
        final BaseDetails race = new BaseDetails();
        final SkinColorDetails skinColor = new SkinColorDetails();
        
        cad.setEarDetails(earDetails);
        cad.setEyeDetails(eyeDetails);
        cad.setFacialHairColor(facialHairColor);
        cad.setFacialHairStyle(facialHairStyle);
        cad.setGender(gender);
        cad.setHairColor(hairColor);
        cad.setHairStyle(hairStyle);
        cad.setName(name);
        cad.setNose(nose);
        cad.setRace(race);
        cad.setSkinColor(skinColor);
        
        assertEquals(earDetails, cad.getEarDetails());
        assertEquals(eyeDetails, cad.getEyeDetails());
        assertEquals(facialHairColor, cad.getFacialHairColor());
        assertEquals(facialHairStyle, cad.getFacialHairStyle());
        assertEquals(gender, cad.getGender());
        assertEquals(hairColor, cad.getHairColor());
        assertEquals(hairStyle, cad.getHairStyle());
        assertEquals(name, cad.getName());
        assertEquals(nose, cad.getNose());
        assertEquals(race, cad.getRace());
        assertEquals(skinColor, cad.getSkinColor());
    }
    
    @Test
    public void ensureGetPatternVariablesReturnsAppropriateResult() {
        final CharacterAppearanceData cad = new CharacterAppearanceData();
        
        final BaseDetails facialHairStyle = new BaseDetails();
        facialHairStyle.setName("Facial Hair Style");
        
        final Gender gender = Gender.MALE;
        final BaseDetails hairStyle = new BaseDetails();
        hairStyle.setName("Hair Style");
        
        final BaseDetails race = new BaseDetails();
        race.setName("Race");
        
        final SkinColorDetails skinColor = new SkinColorDetails();
        skinColor.setName("Skin Color");
        cad.setGender(gender);
        cad.setRace(race);
        cad.setSkinColor(skinColor);
        
        final Map<String, String> varsNoHair = cad.getPatternVariables();
        assertNotNull(varsNoHair);
        assertTrue(varsNoHair.size() > 0);
        
        assertTrue(varsNoHair.containsKey("${gender}"));
        assertEquals(gender.name().toLowerCase(), varsNoHair.get("${gender}"));
        
        assertTrue(varsNoHair.containsKey("${race}"));
        assertEquals(race.getName().toLowerCase(), varsNoHair.get("${race}"));
        
        assertTrue(varsNoHair.containsKey("${skinColor}"));
        assertEquals(skinColor.getName().toLowerCase(), varsNoHair.get("${skinColor}"));
        
        assertFalse(varsNoHair.containsKey("${hair}"));
        assertFalse(varsNoHair.containsKey("${facialHair}"));
        
        cad.setHairStyle(hairStyle);
        final Map<String, String> varsWithOnlyHair = cad.getPatternVariables();
        assertNotNull(varsWithOnlyHair);
        assertTrue(varsWithOnlyHair.size() > 0);
        
        assertTrue(varsWithOnlyHair.containsKey("${gender}"));
        assertEquals(gender.name().toLowerCase(), varsWithOnlyHair.get("${gender}"));
        
        assertTrue(varsWithOnlyHair.containsKey("${race}"));
        assertEquals(race.getName().toLowerCase(), varsWithOnlyHair.get("${race}"));
        
        assertTrue(varsWithOnlyHair.containsKey("${skinColor}"));
        assertEquals(skinColor.getName().toLowerCase(), varsWithOnlyHair.get("${skinColor}"));
        
        assertTrue(varsWithOnlyHair.containsKey("${hair}"));
        assertEquals(hairStyle.getName().toLowerCase(), varsWithOnlyHair.get("${hair}"));
        
        assertFalse(varsWithOnlyHair.containsKey("${facialHair}"));
        
        cad.setFacialHairStyle(facialHairStyle);
        final Map<String, String> varsWithAllHair = cad.getPatternVariables();
        assertNotNull(varsWithAllHair);
        assertTrue(varsWithAllHair.size() > 0);
        
        assertTrue(varsWithAllHair.containsKey("${gender}"));
        assertEquals(gender.name().toLowerCase(), varsWithAllHair.get("${gender}"));
        
        assertTrue(varsWithAllHair.containsKey("${race}"));
        assertEquals(race.getName().toLowerCase(), varsWithAllHair.get("${race}"));
        
        assertTrue(varsWithAllHair.containsKey("${skinColor}"));
        assertEquals(skinColor.getName().toLowerCase(), varsWithAllHair.get("${skinColor}"));
        
        assertTrue(varsWithAllHair.containsKey("${hair}"));
        assertEquals(hairStyle.getName().toLowerCase(), varsWithAllHair.get("${hair}"));
        
        assertTrue(varsWithAllHair.containsKey("${facialHair}"));
        assertEquals(facialHairStyle.getName().toLowerCase(), varsWithAllHair.get("${facialHair}"));
    }
}
