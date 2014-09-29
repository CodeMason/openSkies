/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.player.details;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author mcory01
 */
public class CharacterClothingData {

    public ShirtDetails getShirt() {
        return shirt;
    }

    public void setShirt(final ShirtDetails shirt) {
        this.shirt = shirt;
    }

    public ColoredDetails getShirtColor() {
        return shirtColor;
    }

    public void setShirtColor(final ColoredDetails shirtColor) {
        this.shirtColor = shirtColor;
    }

    public ColoredDetails getPantsColor() {
        return pantsColor;
    }

    public void setPantsColor(final ColoredDetails pantsColor) {
        this.pantsColor = pantsColor;
    }

    public ColoredDetails getShoeColor() {
        return shoeColor;
    }

    public void setShoeColor(final ColoredDetails shoeColor) {
        this.shoeColor = shoeColor;
    }
    
    public Map<String, String> getPatternVariables() {
        final Map<String, String> res = new HashMap<String, String>();
        
        res.put("${shirt}", shirt.getName().toLowerCase());
        
        return res;
    }
    
    private ShirtDetails shirt;
    private ColoredDetails shirtColor;
    private ColoredDetails pantsColor;
    private ColoredDetails shoeColor;
}
