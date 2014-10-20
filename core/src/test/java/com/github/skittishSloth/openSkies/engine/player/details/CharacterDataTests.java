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
public class CharacterDataTests {
    
    @Test
    public void ensureDefaultConstructorInitializesNothing() {
        final CharacterData cd = new CharacterData();
        
        assertNull(cd.getAppearanceData());
        assertNull(cd.getClothingData());
        assertNull(cd.getInformationData());
    }
    
    @Test
    public void ensureParameterizedConstructorProperlyInitializesFields() {
        final CharacterAppearanceData cad = new CharacterAppearanceData();
        final CharacterClothingData ccd = new CharacterClothingData();
        final CharacterInformationData cid = new CharacterInformationData();
        
        final CharacterData cd = new CharacterData(cad, ccd, cid);
        
        assertEquals(cad, cd.getAppearanceData());
        assertEquals(ccd, cd.getClothingData());
        assertEquals(cid, cd.getInformationData());
    }
    
    @Test
    public void ensureSettersWorkProperly() {
        final CharacterData cd = new CharacterData();
        
        assertNull(cd.getAppearanceData());
        assertNull(cd.getClothingData());
        assertNull(cd.getInformationData());
        
        final CharacterAppearanceData cad = new CharacterAppearanceData();
        final CharacterClothingData ccd = new CharacterClothingData();
        final CharacterInformationData cid = new CharacterInformationData();
        
        cd.setAppearanceData(cad);
        cd.setClothingData(ccd);
        cd.setInformationData(cid);
        
        assertEquals(cad, cd.getAppearanceData());
        assertEquals(ccd, cd.getClothingData());
        assertEquals(cid, cd.getInformationData());
    }
}
