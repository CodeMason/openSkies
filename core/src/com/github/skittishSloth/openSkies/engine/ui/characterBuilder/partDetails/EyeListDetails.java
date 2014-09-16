/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.ui.characterBuilder.partDetails;

import com.badlogic.gdx.graphics.Color;
import com.github.skittishSloth.openSkies.engine.common.GdxUtils;
import com.github.skittishSloth.openSkies.engine.player.details.Eye;
import com.github.skittishSloth.openSkies.engine.sprites.UniversalDirectionalSprite;

/**
 *
 * @author mcory01
 */
public class EyeListDetails extends AbstractListDetails {

    public EyeListDetails(final int order, final Color color, final Eye eye, final UniversalDirectionalSprite sprite) {
        super(order);
        this.color = color;
        this.eye = eye;
        this.sprite = sprite;
    }
    
    public EyeListDetails(final int order, final Eye eye, final UniversalDirectionalSprite sprite) {
        super(order);
        this.color = eye.getColor();
        this.eye = eye;
        this.sprite = sprite;
    }

    public Color getColor() {
        return color;
    }

    public Eye getEye() {
        return eye;
    }

    public UniversalDirectionalSprite getSprite() {
        return sprite;
    }

    @Override
    public void dispose() {
        GdxUtils.safeDispose(sprite);
    }

    private final Color color;
    private final Eye eye;
    private final UniversalDirectionalSprite sprite;
}
