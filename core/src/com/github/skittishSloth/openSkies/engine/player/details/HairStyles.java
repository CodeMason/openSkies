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
public enum HairStyles {
    BALD,
    BANGS,
    BANGSLONG,
    BANGSLONG2,
    BANGSSHORT,
    BEDHEAD,
    BUNCHES,
    JEWFRO,
    LONG,
    LONGHAWK,
    LONGKNOT,
    LOOSE,
    MESSY1,
    MESSY2,
    MOHAWK,
    PAGE,
    PAGE2,
    PARTED,
    PIXIE,
    PLAIN,
    PONYTAIL,
    PONYTAIL2,
    PRINCESS,
    SHORTHAWK,
    SHORTKNOT,
    SHOULDERL,
    SHOULDERR,
    SWOOP,
    UNKEMPT,
    XLONG,
    XLONGKNOT;
    
    private final String displayName;
    
    private HairStyles(final String displayName) {
        this.displayName = displayName;
    }
    
    private HairStyles() {
        this.displayName = StringUtils.capitalize(name().toLowerCase());
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public static HairStyles fromDisplayName(final String displayName) {
        for (final HairStyles v : values()) {
            if (v.getDisplayName().equalsIgnoreCase(displayName)) {
                return v;
            }
        }
        
        return null;
    }
}
