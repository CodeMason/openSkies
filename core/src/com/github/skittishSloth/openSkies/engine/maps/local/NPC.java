/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.maps.local;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Disposable;
import com.github.skittishSloth.openSkies.engine.sprites.UniversalDirectionalSprite;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author mcory01
 */
public class NPC implements Disposable {

    public NPC(final UniversalDirectionalSprite sprite, final Rectangle rectangle) {
        this.sprite = sprite;
        this.rectangle = rectangle;
        
        this.dialog = "Hey there!  I'm an NPC! :)";
    }

    public UniversalDirectionalSprite getSprite() {
        return sprite;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
    
    public boolean hasDialog() {
        return (StringUtils.isNotBlank(dialog));
    }
    
    public String getDialog() {
        return dialog;
    }

    @Override
    public void dispose() {
        
    }
    
    private final UniversalDirectionalSprite sprite;
    private final Rectangle rectangle;
    
    private final String dialog;
}
