/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.quests;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

/**
 *
 * @author mcory01
 */
public class QuestLoader {
    private static final Json json = new Json();
    
    public static QuestDetails fromJson(final FileHandle file) {
        final QuestDetails res = json.fromJson(QuestDetails.class, file);
        return res;
    }
}
