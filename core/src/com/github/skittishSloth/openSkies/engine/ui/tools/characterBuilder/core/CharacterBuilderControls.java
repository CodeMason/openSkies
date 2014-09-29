/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.ui.tools.characterBuilder.core;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 *
 * @author mcory01
 */
public abstract class CharacterBuilderControls extends Table {
    
    protected static final String DEFAULT_CANCEL_TEXT = "Cancel";
    protected static final String DEFAULT_ACCEPT_TEXT = "Accept";
    
    protected CharacterBuilderControls(final Skin skin) {
        super(skin);
        
        cancelButton = new TextButton(DEFAULT_CANCEL_TEXT, skin);
        
        cancelButton.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                handleCancelButtonClicked();
            }
            
        });
        
        acceptButton = new TextButton(DEFAULT_ACCEPT_TEXT, skin);
        acceptButton.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                handleAcceptButtonClicked();
            }
            
        });
        
        updateText();
        
        add(cancelButton).padRight(10f);
        add(acceptButton);
    }
    
    protected String getCancelButtonText() {
        return "Cancel";
    }
    
    protected abstract String getAcceptButtonText();
    
    protected abstract void handleCancelButtonClicked();
    
    protected abstract void handleAcceptButtonClicked();
    
    private void updateText() {
        cancelButton.setText(getCancelButtonText());
        acceptButton.setText(getAcceptButtonText());
    }
    
    private final TextButton cancelButton, acceptButton;
}
