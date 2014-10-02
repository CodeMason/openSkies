/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.maps.areas;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

/**
 *
 * @author mcory01
 */
public final class AreaDetailsLoader {
    private static final Json json = new Json();
    
    private AreaDetailsLoader() {
        
    }
    
    public static AreaDetails fromJson(final FileHandle file) {
        final AreaDetails res = json.fromJson(AreaDetails.class, file);
        return res;
    }
}
