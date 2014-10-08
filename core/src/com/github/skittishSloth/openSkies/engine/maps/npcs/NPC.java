/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.maps.npcs;

import com.badlogic.gdx.math.Rectangle;
import com.github.skittishSloth.openSkies.engine.quests.QuestDetails;
import com.github.skittishSloth.openSkies.engine.sprites.UniversalDirectionalSprite;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author mcory01
 */
public class NPC {
    
    public NPC(final String npcName, final UniversalDirectionalSprite sprite, final Rectangle rectangle) {
        this.npcName = npcName;
        this.sprite = sprite;
        
        this.rectangle = new Rectangle(rectangle);
        
        this.collisionRectangle = new Rectangle(this.rectangle);
        collisionRectangle.width /= 2;
        collisionRectangle.height /= 2;
        collisionRectangle.x += collisionRectangle.width / 2;
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
    
    public Rectangle getCollisionRectangle() {
        return collisionRectangle;
    }
    
    public boolean hasAvailableQuests() {
        return (!availableQuests.isEmpty());
    }
    
    public QuestDetails getActiveQuest() {
        if (!hasAvailableQuests()) {
            return null;
        }
        
        return availableQuests.get(0);
    }
    
    public List<QuestDetails> getAvailableQuests() {
        return availableQuests;
    }
    
    public void setAvailableQuests(final Collection<QuestDetails> quests) {
        this.availableQuests.clear();
        if (quests != null) {
            this.availableQuests.addAll(quests);
        }
    }

    private final String npcName;
    private final UniversalDirectionalSprite sprite;
    private final Rectangle rectangle, collisionRectangle;
    private final List<QuestDetails> availableQuests = new ArrayList<QuestDetails>();
}
