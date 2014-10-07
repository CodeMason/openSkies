/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.maps.local;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author mcory01
 */
public class Transition {
    
    private static final Logger log = LoggerFactory.getLogger(Transition.class);

    public Transition(final String mapName, final Integer index) {
        this.mapName = mapName;
        this.index = index;
        
        log.debug("New transition: n: '{}', index: '{}'", mapName, index);
    }

    public String getMapName() {
        return mapName;
    }

    public Integer getIndex() {
        return index;
    }

    private final String mapName;
    private final Integer index;
}
