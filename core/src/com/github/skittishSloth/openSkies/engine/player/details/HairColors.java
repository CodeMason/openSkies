/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.player.details;

import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author mcory01
 */
public enum HairColors {
    BLACK,
    BLONDE,
    BLONDE2,
    BLUE,
    BLUE2,
    BROWN,
    BROWN2,
    BRUNETTE,
    BRUNETTE2,
    DARK_BLONDE,
    GOLD,
    GRAY,
    GRAY2,
    GREEN,
    GREEN2,
    LIGHT_BLONDE,
    LIGHT_BLONDE2,
    PINK,
    PINK2,
    PURPLE,
    RAVEN,
    RAVEN2,
    REDHEAD,
    REDHEAD2,
    RUBY_RED,
    WHITE_BLONDE,
    WHITE_BLONDE2,
    WHITE_CYAN,
    WHITE;

    public String getDisplayName() {
        final String tempName = name().replace("2", "_2");
        final String[] parts = tempName.split("_");
        final StringBuilder res = new StringBuilder(tempName.length());
        for (final String part : parts) {
            res.append(StringUtils.capitalize(part.toLowerCase())).append(" ");
        }
        return res.toString().trim();
    }

    public static HairColors fromDisplayName(final String displayName) {
        final String fixedNums = displayName.replace(" 2", "2");
        final String fixedSpaces = fixedNums.replace(" ", "_");
        return valueOf(fixedSpaces.toUpperCase());
    }
    
    public static HairColors fromStringIgnoreCase(final String name) {
        for (final HairColors hc : values()) {
            final String hcName = hc.name();
            if (StringUtils.equalsIgnoreCase(hcName, name)) {
                return hc;
            }
            
            if (StringUtils.equalsIgnoreCase(hcName.replace("_", "-"), name)) {
                return hc;
            }
        }
        
        return null;
    }
    
    public String toFileNameString() {
        return name().replace("_", "-").toLowerCase();
    }
}
