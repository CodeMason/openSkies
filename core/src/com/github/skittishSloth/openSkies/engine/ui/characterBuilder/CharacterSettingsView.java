/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.ui.characterBuilder;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Tree;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.github.skittishSloth.openSkies.engine.player.Gender;
import com.github.skittishSloth.openSkies.engine.player.Race;
import java.util.Collection;

/**
 *
 * @author mcory01
 */
public class CharacterSettingsView extends Table {

    private static final int PRESET_BTNS_PER_ROW = 3;

    public CharacterSettingsView(final Skin skin, final CharacterBuildTable parent) {
        super(skin);
        this.skin = skin;

        actionsTree = new Tree(skin);
        scrollPane = new ScrollPane(actionsTree, skin, "no-bg");
        this.parent = parent;

        genderButtonGroup = new ButtonGroup();
        raceButtonGroup = new ButtonGroup();
        presetColorGroup = new ButtonGroup();

        Tree.Node genderNode = buildGenderNode();

        actionsTree.add(genderNode);

        actionsTree.add(buildRaceNode());

        presetColorsNode = buildPresetColorNode(null, null);

        actionsTree.add(presetColorsNode);
        add(scrollPane);
        scrollPane.setFillParent(true);

    }

    @Override
    public void layout() {
        super.layout(); //To change body of generated methods, choose Tools | Templates.
        scrollPane.setFillParent(true);
        scrollPane.setHeight(getHeight());
    }

    public void setAvailableColors(final Collection<Color> colors, final Color activeColor) {
        buildPresetColorNode(colors, activeColor);
    }

    public void update(final Color activeColor) {
        final Collection<Color> colors = parent.getAvailableColors();
        boolean differentColors = false;
        if (availableColors == null) {
            availableColors = colors;
            differentColors = true;
        } else {
            for (final Color c : colors) {
                if (!availableColors.contains(c)) {
                    differentColors = true;
                    break;
                }
            }
        }
        
        if (differentColors) {
            Gdx.app.log(getClass().getSimpleName(), "Colors was different :/");
            availableColors = colors;
            setAvailableColors(availableColors, activeColor);
        }
    }

    private Tree.Node buildGenderNode() {
        final Label genderLabel = new Label("Gender", skin);
        final Tree.Node genderNode = new Tree.Node(genderLabel);
        genderNode.setSelectable(false);

        final CheckBox maleBox = new CheckBox("Male", skin);
        maleBox.setChecked(true);
        maleBox.addListener(new ClickListener() {
            @Override
            public void clicked(final InputEvent event, final float x, final float y) {
                parent.setCharacterGender(Gender.MALE);
            }
        });

        final CheckBox femaleBox = new CheckBox("Female", skin);
        femaleBox.addListener(new ClickListener() {
            @Override
            public void clicked(final InputEvent event, final float x, final float y) {
                parent.setCharacterGender(Gender.FEMALE);
            }
        });

        final Array<Button> genderButtons = genderButtonGroup.getButtons();
        if (genderButtons.size > 0) {
            for (final Button btn : genderButtons) {
                genderButtonGroup.remove(btn);
            }
        }
        genderButtonGroup.add(maleBox, femaleBox);

        final Table genderTable = new Table();
        genderTable.defaults().left();
        genderTable.add(maleBox);
        genderTable.row();
        genderTable.add(femaleBox);

        final Tree.Node genderGroupNode = new Tree.Node(genderTable);
        genderGroupNode.setSelectable(false);
        genderNode.add(genderGroupNode);
        return genderNode;
    }

    private Tree.Node buildPresetColorNode(final Collection<Color> availableColors, final Color activeColor) {
        if (presetColorsNode == null) {
            final Label colorsLabel = new Label("Preset Colors", skin);
            presetColorsNode = new Tree.Node(colorsLabel);
            presetColorsNode.setSelectable(false);
        } else {
            presetColorsNode.removeAll();
        }

        if (availableColors == null) {
            return presetColorsNode;
        }

        final Table btnTable = new Table();
        int count = 1;
        final Array<Button> presetGroupButtons = presetColorGroup.getButtons();
        if (presetGroupButtons.size > 0) {
            for (final Button btn : presetGroupButtons) {
                presetColorGroup.remove(btn);
            }
        }
        
        for (final Color c : availableColors) {
            final Button btn = new Button(skin, "colored");
            final CheckBox cb = new CheckBox("", skin);
            presetColorGroup.add(cb);
            btn.setColor(c);
            btn.setSize(32f, 32f);
            if (c == activeColor) {
                cb.setChecked(true);
            }
            cb.addListener(new ChangeListener() {

                @Override
                public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                    if (cb.isChecked()) {
                        parent.setCharacterColor(c);
                    }
                }
            });
            
            btn.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    parent.setCharacterColor(c);
                    cb.setChecked(true);
                }
            });

            btnTable.add(cb);
            btnTable.add(btn).pad(5f);
            if (count < PRESET_BTNS_PER_ROW) {
                count++;
            } else {
                btnTable.row();
                count = 1;
            }
        }
        final Tree.Node btnTableNode = new Tree.Node(btnTable);
        btnTableNode.setSelectable(false);
        presetColorsNode.add(btnTableNode);

        return presetColorsNode;
    }

    private Tree.Node buildRaceNode() {
        if (raceNode == null) {
            final Label raceLbl = new Label("Race", skin);
            raceNode = new Tree.Node(raceLbl);
            raceNode.setSelectable(false);
        } else {
            raceNode.removeAll();
        }

        final Table raceTbl = new Table(skin);
        raceTbl.defaults().left();

        final CheckBox humanBtn = new CheckBox("Human", skin);
        humanBtn.addListener(new RaceButtonClickListener(parent, Race.HUMAN));

        final CheckBox elfBtn = new CheckBox("Elf", skin);
        elfBtn.addListener(new RaceButtonClickListener(parent, Race.ELF));

        final CheckBox orcBtn = new CheckBox("Orc", skin);
        orcBtn.addListener(new RaceButtonClickListener(parent, Race.ORC));

        final Array<Button> raceBtns = raceButtonGroup.getButtons();
        if (raceBtns.size > 0) {
            for (final Button btn : raceBtns) {
                raceButtonGroup.remove(btn);
            }
        }

        raceButtonGroup.add(humanBtn, elfBtn, orcBtn);

        raceTbl.add(humanBtn);
        raceTbl.row();
        raceTbl.add(elfBtn);
        raceTbl.row();
        raceTbl.add(orcBtn);

        final Tree.Node raceTblNode = new Tree.Node(raceTbl);
        raceTblNode.setSelectable(false);
        raceNode.add(raceTblNode);

        return raceNode;
    }

    private static final class RaceButtonClickListener extends ClickListener {

        public RaceButtonClickListener(final CharacterBuildTable parent, final Race race) {
            this.parent = parent;
            this.race = race;
        }

        @Override
        public void clicked(InputEvent event, float x, float y) {
            parent.setCharacterRace(race);
        }

        private final CharacterBuildTable parent;
        private final Race race;
    }

    private Tree.Node presetColorsNode;
    private Tree.Node raceNode;
    private final Skin skin;
    private final CharacterBuildTable parent;
    private final Tree actionsTree;
    private final ButtonGroup genderButtonGroup, raceButtonGroup, presetColorGroup;
    private final ScrollPane scrollPane;
    
    private Collection<Color> availableColors = null;
}
