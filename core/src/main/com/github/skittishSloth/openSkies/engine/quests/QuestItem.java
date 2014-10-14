/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.quests;

import com.badlogic.gdx.math.Vector2;
import com.github.skittishSloth.openSkies.engine.inventory.items.ItemDetails;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author mcory01
 */
public class QuestItem {

    public QuestItem() {

    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(final int itemId) {
        this.itemId = itemId;
    }

    public Vector2 getLocation() {
        return location;
    }

    public void setLocation(final Vector2 location) {
        this.location = location;
    }

    public final String getSpritePath() {
        return spritePath;
    }

    public final void setSpritePath(final String spritePath) {
        this.spritePath = spritePath;
    }

    public final boolean hasSprite() {
        return (StringUtils.isNotBlank(spritePath));
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(final int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(final int height) {
        this.height = height;
    }

    public BaseQuestStep getStep() {
        return step;
    }

    public void setStep(final RetrievalStep step) {
        this.step = step;
    }

    public ItemDetails getItem() {
        return item;
    }

    public void setItem(final ItemDetails item) {
        this.item = item;
    }

    private int itemId;
    private String spritePath;
    private Vector2 location;
    private int width, height;

    private transient BaseQuestStep step;
    private transient ItemDetails item;
}
