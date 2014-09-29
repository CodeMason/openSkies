/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.ui.tools.characterBuilder.information;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.github.skittishSloth.openSkies.engine.player.info.PlayerClass;
import java.util.Collection;

/**
 *
 * @author mcory01
 */
public class CharacterClassSelector extends Table {

    public CharacterClassSelector(final Skin skin, final CharacterInformationTable parent) {
        super(skin);

        
        final Label title = new Label("Select a class:", skin);
        add(title).top().left();
        row();
        
        final Table classTable = new Table(skin);
        final Collection<PlayerClass> availableClasses = parent.getAvailableClasses();

        final float cellWidth = 200f;
        for (final PlayerClass pc : availableClasses) {
            final Button playerClassBtn = new Button(skin, "toggle");

            playerClassBtn.setWidth(cellWidth);

            playerClassBtn.padRight(5f);

            final Label nameLabel = new Label(pc.getName(), skin);
            playerClassBtn.add(nameLabel).center();
            playerClassBtn.row();

            final Label descLabel = new Label(pc.getDescription(), skin);
            descLabel.setWrap(true);
            playerClassBtn.add(descLabel).left().width(cellWidth);
            playerClassBtn.addListener(new ChangeListener() {

                @Override
                public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                    if (playerClassBtn.isChecked()) {
                        selectedClass = pc;
                        System.err.println("Selected: " + pc.getName());
                    }
                }
            });
            classTable.add(playerClassBtn).top().pad(5f).spaceRight(10f).padBottom(30f);
            group.add(playerClassBtn);
        }

        final ScrollPane scrollPane = new ScrollPane(classTable, skin);
        scrollPane.setScrollingDisabled(false, true);
        add(scrollPane).left().expandX();
    }

    public PlayerClass getSelectedClass() {
        return selectedClass;
    }

    private final ButtonGroup group = new ButtonGroup();

    private PlayerClass selectedClass = null;
}
