/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.ui.characterBuilder.information;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.github.skittishSloth.openSkies.engine.player.info.BackStory;
import java.util.Collection;

/**
 *
 * @author mcory01
 */
public class CharacterBackStorySelector extends Table {

    public CharacterBackStorySelector(final Skin skin, final CharacterInformationTable parent) {
        super(skin);

        final Label title = new Label("Select a backstory:", skin);
        add(title).top().left();
        row();

        final Table classTable = new Table(skin);
        final Collection<BackStory> availableBackStories = parent.getAvailableBackStories();

        final float cellWidth = 200f;
        for (final BackStory bs : availableBackStories) {
            final Button backStoryBtn = new Button(skin, "toggle");

            backStoryBtn.setWidth(cellWidth);

            backStoryBtn.padRight(5f);

            final Label nameLabel = new Label(bs.getName(), skin);
            backStoryBtn.add(nameLabel).center();
            backStoryBtn.row();

            final Label descLabel = new Label(bs.getDescription(), skin);
            descLabel.setWrap(true);
            backStoryBtn.add(descLabel).left().width(cellWidth);
            backStoryBtn.addListener(new ChangeListener() {

                @Override
                public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                    if (backStoryBtn.isChecked()) {
                        System.err.println("Selected: " + bs.getName());
                        selectedBackStory = bs;
                    }
                }
            });
            classTable.add(backStoryBtn).top().pad(5f).spaceRight(10f).padBottom(30f);
            group.add(backStoryBtn);
        }

        final ScrollPane scrollPane = new ScrollPane(classTable, skin);
        scrollPane.setScrollingDisabled(false, true);
        add(scrollPane).left().expandX();
    }

    public BackStory getSelectedBackStory() {
        return selectedBackStory;
    }

    private final ButtonGroup group = new ButtonGroup();
    private BackStory selectedBackStory = null;
}
