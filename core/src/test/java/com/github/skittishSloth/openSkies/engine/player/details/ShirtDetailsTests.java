/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.player.details;

import static org.junit.Assert.*;

import org.junit.Test;
/**
 *
 * @author mcory01
 */
public class ShirtDetailsTests {
    
    @Test
    public void ensureDefaultConstructorDoesNothing() {
        final ShirtDetails sd = new ShirtDetails();
        
        assertNull(sd.getGender());
    }
    
    @Test
    public void ensureSettersWorkProperly() {
        final ShirtDetails sd = new ShirtDetails();
        
        assertNull(sd.getGender());
        
        final Gender gender = Gender.MALE;
        
        sd.setGender(gender);
        
        assertEquals(gender, sd.getGender());
    }
    
    @Test
    public void ensureIsDefaultDetailsWorksProperlyIfPassedMaleOrFemale() {
        final ShirtDetails sd = new ShirtDetails();
        
        assertFalse(sd.isDefaultDetails());
        
        final Gender male = Gender.MALE;
        final Gender female = Gender.FEMALE;
        final Gender neutral = Gender.NEUTRAL;
        
        assertFalse(sd.isDefaultDetails(male));
        assertFalse(sd.isDefaultDetails(female));
        
        sd.setGender(male);
        
        assertFalse(sd.isDefaultDetails(male));
        assertFalse(sd.isDefaultDetails(female));
        
        sd.setGender(female);
        
        assertFalse(sd.isDefaultDetails(male));
        assertFalse(sd.isDefaultDetails(female));
        
        sd.setGender(neutral);
        
        assertFalse(sd.isDefaultDetails(male));
        assertFalse(sd.isDefaultDetails(female));
        
        sd.setDefaultDetails(true);
        
        sd.setGender(male);
        
        assertTrue(sd.isDefaultDetails(male));
        assertFalse(sd.isDefaultDetails(female));
        
        sd.setGender(female);
        
        assertFalse(sd.isDefaultDetails(male));
        assertTrue(sd.isDefaultDetails(female));
    }
}
