/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.skittishSloth.openSkies.engine.player;

import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author mcory01
 */
public enum Eye {
    BLUE("5187b3"),
    BROWN("544c2e"),
    GRAY("8b8979"),
    GREEN("53b351"),
    ORANGE("b26237"),
    PURPLE("b90da0"),
    RED("cb4a39"),
    YELLOW("d9bf46");
    
    private final String colorString;
    
    private Eye(final String colorString) {
        this.colorString = colorString;
    }
    
    public String getColorString() {
        return colorString;
    }
    
    public static Eye getByColorString(final String colorString) {
        if (StringUtils.isBlank(colorString)) {
            return null;
        }
        
        for (final Eye eye : values()) {
            if (StringUtils.equalsIgnoreCase(colorString, eye.colorString)) {
                return eye;
            }
        }
        
        return null;
    }
}
