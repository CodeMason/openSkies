/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.skittishSloth.openSkies.engine.ui.characterBuilder;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Disposable;

/**
 *
 * @author mcory01
 */
public class CharacterBuildControls extends Table implements Disposable {

    public CharacterBuildControls(final Skin skin) {
        super();
        
        cancelButton = new TextButton("Cancel", skin);
        createButton = new TextButton("Create!", skin);
        
        add(cancelButton).padRight(10f);
        add(createButton);
    }
    
    @Override
    public void dispose() {
        
    }
    
    private final TextButton cancelButton, createButton;
}
