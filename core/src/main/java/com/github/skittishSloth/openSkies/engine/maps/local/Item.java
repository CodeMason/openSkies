/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.skittishSloth.openSkies.engine.maps.local;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Disposable;
import com.github.skittishSloth.openSkies.engine.common.GdxUtils;

/**
 *
 * @author mcory01
 */
public class Item implements Disposable {

    public Item(final String id, final Texture initialTexture, final Texture actionTexture, final String contains, final Rectangle rectangle) {
        this.id = id;
        this.initialTexture = initialTexture;
        this.actionTexture = actionTexture;
        this.contains = contains;
        this.rectangle = rectangle;
    }
    
    public String getId() {
        return id;
    }
    
    public boolean isActionable() {
        return true;
    }

    public boolean isActionPerformed() {
        return actionPerformed;
    }

    public void setActionPerformed(final boolean actionPerformed) {
        this.actionPerformed = actionPerformed;
    }
    
    public Rectangle getRectangle() {
        return rectangle;
    }
    
    public TextureRegion getTextureRegion() {
        final TextureRegion res;
        
        if (actionPerformed && isActionable() && (actionTexture != null)) {
            res = new TextureRegion(actionTexture);
        } else if (initialTexture != null) {
            res = new TextureRegion(initialTexture);
        } else {
            res = null;
        }
        
        return res;
    }

    public String getContains() {
        return contains;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(final boolean alive) {
        this.alive = alive;
    }
    
    @Override
    public void dispose() {
        GdxUtils.safeDispose(initialTexture);
        GdxUtils.safeDispose(actionTexture);
        if (initialTexture != null) {
            initialTexture.dispose();
        }
        
        if (actionTexture != null) {
            actionTexture.dispose();
        }
    }
    
    private final String id;
    private final Texture initialTexture;
    private final Texture actionTexture;
    private final String contains;
    private final Rectangle rectangle;
    
    private boolean actionPerformed = false;
    private boolean alive = true;
}
