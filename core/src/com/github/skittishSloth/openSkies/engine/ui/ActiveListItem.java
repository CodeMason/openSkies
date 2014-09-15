/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.skittishSloth.openSkies.engine.ui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 *
 * @author mcory01
 */
public class ActiveListItem extends Actor {
    
    public ActiveListItem(final List<ActiveListItem> parent, final Skin skin, final String text) {
        super();
        
        table = new Table(skin);
        button = new TextButton("X", skin);
        label = new Label(text, skin);
        table.add(button).left();
        table.add(label).right();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        table.draw(batch, parentAlpha);
    }
    
    private Table table;
    private TextButton button;
    private Label label;
}
