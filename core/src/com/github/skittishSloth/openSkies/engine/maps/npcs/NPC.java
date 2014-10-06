/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.maps.npcs;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Disposable;
import com.github.skittishSloth.openSkies.engine.sprites.UniversalDirectionalSprite;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author mcory01
 */
public class NPC {

    public NPC(final String npcName, final UniversalDirectionalSprite sprite, final Rectangle rectangle) {
        this.npcName = npcName;
        this.sprite = sprite;
        this.rectangle = rectangle;
    }

    public String getNpcName() {
        return npcName;
    }

    public UniversalDirectionalSprite getSprite() {
        return sprite;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    private final String npcName;
    private final UniversalDirectionalSprite sprite;
    private final Rectangle rectangle;
}
