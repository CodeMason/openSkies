/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.player.state;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

/**
 *
 * @author mcory01
 */
public class PlayerStateLoader {
    private static final Json json = new Json();
    
    public static PlayerState fromJson(final FileHandle file) {
        final PlayerState res = json.fromJson(PlayerState.class, file);
        return res;
    }
    
    public static void saveJson(final PlayerState state, final FileHandle file) {
        json.toJson(state, file);
    }
}
