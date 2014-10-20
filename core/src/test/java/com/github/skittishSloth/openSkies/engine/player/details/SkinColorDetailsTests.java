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
public class SkinColorDetailsTests {
    
    @Test
    public void ensureDefaultConstructorDoesNothing() {
        final SkinColorDetails scd = new SkinColorDetails();
        
        assertNull(scd.getRace());
    }
    
    @Test
    public void ensureSettersWorkProperly() {
        final SkinColorDetails scd = new SkinColorDetails();
        
        assertNull(scd.getRace());
        
        final String race = "Race";
        
        scd.setRace(race);
        
        assertEquals(race, scd.getRace());
    }
}
