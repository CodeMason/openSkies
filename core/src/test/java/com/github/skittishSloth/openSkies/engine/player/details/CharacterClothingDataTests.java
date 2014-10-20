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
public class CharacterClothingDataTests {
    
    @Test
    public void ensureDefaultConstructorInitializesNothing() {
        final CharacterClothingData ccd = new CharacterClothingData();
        
        assertNull(ccd.getPantsColor());
        assertNull(ccd.getShirt());
        assertNull(ccd.getShirtColor());
        assertNull(ccd.getShoeColor());
        
        assertEquals(0, ccd.getPatternVariables().size());
    }
    
    @Test
    public void ensureSettersWorkProperly() {
        final CharacterClothingData ccd = new CharacterClothingData();
        
        assertNull(ccd.getPantsColor());
        assertNull(ccd.getShirt());
        assertNull(ccd.getShirtColor());
        assertNull(ccd.getShoeColor());
        
        assertEquals(0, ccd.getPatternVariables().size());
        
        final ColoredDetails pantsColor = new ColoredDetails();
        final ShirtDetails shirt = new ShirtDetails();
        final ColoredDetails shirtColor = new ColoredDetails();
        final ColoredDetails shoeColor = new ColoredDetails();
        
        ccd.setPantsColor(pantsColor);
        ccd.setShirt(shirt);
        ccd.setShirtColor(shirtColor);
        ccd.setShoeColor(shoeColor);
        
        assertEquals(pantsColor, ccd.getPantsColor());
        assertEquals(shirt, ccd.getShirt());
        assertEquals(shirtColor, ccd.getShirtColor());
        assertEquals(shoeColor, ccd.getShoeColor());
    }
    
    @Test
    public void ensurePatternVariablesAreGeneratedCorrectly() {
        final CharacterClothingData ccd = new CharacterClothingData();
        
        assertNull(ccd.getPantsColor());
        assertNull(ccd.getShirt());
        assertNull(ccd.getShirtColor());
        assertNull(ccd.getShoeColor());
        
        assertEquals(0, ccd.getPatternVariables().size());
        
        final ShirtDetails shirt = new ShirtDetails();
        final String name = "the shirt";
        
        shirt.setName(name);
        
        ccd.setShirt(shirt);
        
        final int expSize = 1;
        
        final Map<String, String> patternVars = ccd.getPatternVariables();
        assertNotNull(patternVars);
        assertEquals(expSize, patternVars.size());
        
        boolean shirtFound = false;
        for (final String var : patternVars.values()) {
            if (var.equals(name)) {
                shirtFound = true;
                break;
            }
        }
        
        assertTrue(shirtFound);
    }
}
