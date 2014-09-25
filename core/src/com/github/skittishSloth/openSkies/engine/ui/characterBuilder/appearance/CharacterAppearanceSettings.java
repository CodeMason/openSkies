/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.ui.characterBuilder.appearance;

import com.github.skittishSloth.openSkies.engine.player.details.CharacterAppearanceData;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Tree;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.github.skittishSloth.openSkies.engine.player.details.BaseDetails;
import com.github.skittishSloth.openSkies.engine.player.details.ColoredDetails;
import com.github.skittishSloth.openSkies.engine.player.details.Gender;
import com.github.skittishSloth.openSkies.engine.player.details.SkinColorDetails;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author mcory01
 */
public class CharacterAppearanceSettings extends Table implements Disposable {

    private static final int PRESET_BTNS_PER_ROW = 3;

    public CharacterAppearanceSettings(final Skin skin, final CharacterAppearanceTable parent) {
        super(skin);
        this.skin = skin;

        actionsTree = new Tree(skin);
        scrollPane = new ScrollPane(actionsTree, skin, "no-bg");
        this.parent = parent;

        genderButtonGroup = new ButtonGroup();
        raceButtonGroup = new ButtonGroup();
        skinColorGroup = new ButtonGroup();
        eyesGroup = new ButtonGroup();
        earsGroup = new ButtonGroup();
        noseGroup = new ButtonGroup();
        shirtColorGroup = new ButtonGroup();
        pantsColorGroup = new ButtonGroup();
        shoeColorGroup = new ButtonGroup();
        
        invalidNameLbl = new Label("You must add a valid name.", skin);
        invalidNameLbl.setColor(Color.RED);
        invalidNameLbl.setVisible(false);
        nameNode = buildNameNode();
        actionsTree.add(nameNode);

        genderNode = buildGenderNode();
        actionsTree.add(genderNode);

        raceNode = buildRaceNode();
        actionsTree.add(raceNode);

        skinColorsNode = buildSkinColorNode(null);
        actionsTree.add(skinColorsNode);

        eyesNode = buildEyesNode(null);
        actionsTree.add(eyesNode);

        earsNode = buildEarsNode(null);
        actionsTree.add(earsNode);

        noseNode = buildNoseNode(null);
        actionsTree.add(noseNode);

        hairNode = buildHairNode(null, null);
        actionsTree.add(hairNode);

        shirtColorNode = buildShirtColorNode(null);
        actionsTree.add(shirtColorNode);

        pantsColorNode = buildPantsColorNode(null);
        actionsTree.add(pantsColorNode);

        shoeColorNode = buildShoeColorNode(null);
        actionsTree.add(shoeColorNode);

        add(scrollPane);
        scrollPane.setFillParent(true);
    }

    public void setValidName(final boolean validName) {
        this.validName = validName;
        invalidNameLbl.setVisible(!this.validName);
    }

    @Override
    public void layout() {
        super.layout(); //To change body of generated methods, choose Tools | Templates.
        scrollPane.setFillParent(true);
        scrollPane.setHeight(getHeight());
    }

    public void update(final CharacterAppearanceData buildData) {

        final Collection<BaseDetails> races = parent.getAvailableRaces();
        if (availableRaces == null) {
            availableRaces = races;
            buildRaceNode();
        }
        
        final Collection<SkinColorDetails> skinColors = parent.getAvailableColors();
        final boolean differentSkinColors = differentCollections(availableSkinColors, skinColors);

        if (differentSkinColors) {
            Gdx.app.log(getClass().getSimpleName(), "Different skin colors!");
            availableSkinColors = skinColors;
            buildSkinColorNode(buildData.getSkinColor());
        } else {
            Gdx.app.log(getClass().getSimpleName(), "Skin colors were the same :/");
        }

        if (availableEyes == null) {
            availableEyes = parent.getAvailableEyeDetails();
            buildEyesNode(buildData.getEyeDetails());
        }

        final Collection<BaseDetails> ears = parent.getAvailableEarDetails();
        final boolean differentEars = differentCollections(availableEars, ears);

        if (differentEars) {
            availableEars = ears;
            buildEarsNode(buildData.getEarDetails());
        }
        
        if (availableNoses == null) {
            availableNoses = parent.getAvailableNoses();
            buildNoseNode(buildData.getNose());
        }
        final Collection<BaseDetails> noses = parent.getAvailableNoses();
        final boolean differentNoses = differentCollections(availableNoses, noses);

        if (differentNoses) {
            availableNoses = noses;
            buildNoseNode(buildData.getNose());
        }

        final Collection<BaseDetails> hairStyles = parent.getAvailableHairStyles();
        final boolean differentStyles = differentCollections(availableHairStyles, hairStyles);

        if (differentStyles) {
            availableHairStyles = hairStyles;
        }

        final Collection<BaseDetails> hairColors = parent.getAvailableHairColors();
        final boolean differentHairColors = differentCollections(availableHairColors, hairColors);

        if (differentHairColors) {
            availableHairColors = hairColors;
        }

        if (differentStyles || differentHairColors) {
            buildHairNode(buildData.getHairStyle(), buildData.getHairColor());
        }

        final Collection<ColoredDetails> shirtColors = parent.getAvailableShirtColors();
        final boolean differentShirtColors = differentCollections(availableShirtColors, shirtColors);

        if (differentShirtColors) {
            availableShirtColors = shirtColors;
            buildShirtColorNode(buildData.getShirtColor());
        }

        final Collection<ColoredDetails> pantsColors = parent.getAvailablePantsColors();
        final boolean differentPantsColors = differentCollections(availablePantsColors, pantsColors);

        if (differentPantsColors) {
            availablePantsColors = pantsColors;
            buildPantsColorNode(buildData.getPantsColor());
        }

        if (availableShoeColors == null) {
            availableShoeColors = parent.getAvailableShoeColors();
            buildShoeColorNode(buildData.getShoeColor());
        }
    }

    @Override
    public void dispose() {

    }

    private Tree.Node buildNameNode() {
        if (nameNode == null) {
            nameNode = buildLabeledNode("Character Name", skin);
        } else {
            nameNode.removeAll();
        }

        if (nameField == null) {
            nameField = new TextField("", skin);
            nameField.setTextFieldListener(new TextField.TextFieldListener() {
                @Override
                public void keyTyped(TextField textField, char c) {
                    Gdx.app.log(getClass().getSimpleName(), "Textfield changed - " + nameField.getText());
                    parent.setCharacterName(nameField.getText());
                    parent.validateSettings();
                }
            });
        } else {
            nameField.setText("");
        }

        final Table nameTbl = new Table(skin);
        final Label nameLbl = new Label("Name:", skin);
        nameTbl.add(nameLbl).right().pad(2f);
        nameTbl.add(nameField).left();

        
        nameTbl.row();
        nameTbl.add(invalidNameLbl).colspan(2);

        final Tree.Node nameTblNode = new Tree.Node(nameTbl);
        nameTblNode.setSelectable(false);
        nameNode.add(nameTblNode);
        nameNode.setExpanded(true);

        return nameNode;
    }

    private Tree.Node buildGenderNode() {
        if (genderNode == null) {
            genderNode = buildLabeledNode("Gender", skin);
        } else {
            genderNode.removeAll();
        }

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

    private Tree.Node buildSkinColorNode(final SkinColorDetails activeColor) {
        if (skinColorsNode == null) {
            skinColorsNode = buildLabeledNode("Skin Colors", skin);
        } else {
            skinColorsNode.removeAll();
        }

        if (availableSkinColors == null) {
            return skinColorsNode;
        }

        final Table btnTable = new Table();
        int count = 1;
        final Array<Button> presetGroupButtons = skinColorGroup.getButtons();
        if (presetGroupButtons.size > 0) {
            for (final Button btn : presetGroupButtons) {
                skinColorGroup.remove(btn);
            }
        }

        for (final SkinColorDetails c : availableSkinColors) {
            final Button btn = new Button(skin, "colored");
            final CheckBox cb = new CheckBox("", skin);
            skinColorGroup.add(cb);
            btn.setColor(c.getSampleColor());
            btn.setSize(32f, 32f);
            if (c == activeColor) {
                cb.setChecked(true);
            }
            cb.addListener(new ChangeListener() {

                @Override
                public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                    if (cb.isChecked()) {
                        parent.setCharacterSkinColor(c);
                    }
                }
            });

            btn.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    parent.setCharacterSkinColor(c);
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
        skinColorsNode.add(btnTableNode);

        return skinColorsNode;
    }

    private Tree.Node buildRaceNode() {
        if (raceNode == null) {
            raceNode = buildLabeledNode("Race", skin);
        } else {
            raceNode.removeAll();
        }

        clearButtonGroup(raceButtonGroup);
        
        if (availableRaces == null) {
            return raceNode;
        }
        
        final Table raceTbl = new Table(skin);
        raceTbl.defaults().left();

        for (final BaseDetails race : availableRaces) {
            final CheckBox btn = new CheckBox(race.getName(), skin);
            btn.addListener(new ClickListener() {

                @Override
                public void clicked(InputEvent event, float x, float y) {
                    parent.setCharacterRace(race);
                }
            });
            
            raceButtonGroup.add(btn);
            raceTbl.add(btn);
            raceTbl.row();
        }

        final Tree.Node raceTblNode = new Tree.Node(raceTbl);
        raceTblNode.setSelectable(false);
        raceNode.add(raceTblNode);

        return raceNode;
    }
    
    private Tree.Node buildEyesNode(final ColoredDetails activeEyes) {
        Gdx.app.log(getClass().getSimpleName(), "Building Eyes Node.");
        if (eyesNode == null) {
            eyesNode = buildLabeledNode("Eyes", skin);
        } else {
            eyesNode.removeAll();
        }

        clearButtonGroup(eyesGroup);
        if (availableEyes == null) {
            Gdx.app.log(getClass().getSimpleName(), "--Available eyes were null; returning.");
            return eyesNode;
        }

        final Table btnTable = new Table(skin);
        int count = 1;
        for (final ColoredDetails eye : availableEyes) {
            final Button btn = new Button(skin, "colored");
            final CheckBox cb = new CheckBox("", skin);
            eyesGroup.add(cb);
            final Color eyeColor = Color.valueOf(eye.getSampleColorRgb());
            btn.setColor(eyeColor);
            btn.setSize(32f, 32f);
            if (eye == activeEyes) {
                cb.setChecked(true);
            }
            cb.addListener(new ChangeListener() {

                @Override
                public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                    if (cb.isChecked()) {
                        parent.setCharacterEyeDetails(eye);
                    }
                }
            });

            btn.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    parent.setCharacterEyeDetails(eye);
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

        final Tree.Node tableNode = new Tree.Node(btnTable);
        tableNode.setSelectable(false);
        eyesNode.add(tableNode);
        return eyesNode;
    }

    private Tree.Node buildEarsNode(final BaseDetails activeEars) {
        if (earsNode == null) {
            earsNode = buildLabeledNode("Ears", skin);
        } else {
            earsNode.removeAll();
        }

        clearButtonGroup(earsGroup);
        if (availableEars == null) {
            return earsNode;
        }

        final Table btnTable = new Table(skin);
        for (final BaseDetails ear : availableEars) {
            
            final String earName = StringUtils.capitalize(ear.getName().toLowerCase());
            final CheckBox cb = new CheckBox(earName, skin);
            earsGroup.add(cb);
            if (ear == activeEars) {
                cb.setChecked(true);
            }
            cb.addListener(new ChangeListener() {

                @Override
                public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                    if (cb.isChecked()) {
                        parent.setCharacterEarDetails(ear);
                    }
                }
            });

            btnTable.add(cb).left();
            btnTable.row();
        }

        final Tree.Node earsTableNode = new Tree.Node(btnTable);
        earsTableNode.setSelectable(false);
        earsNode.add(earsTableNode);

        return earsNode;
    }

    private Tree.Node buildNoseNode(final BaseDetails activeNose) {
        if (noseNode == null) {
            noseNode = buildLabeledNode("Nose", skin);
        } else {
            noseNode.removeAll();
        }

        clearButtonGroup(noseGroup);
        if (availableNoses == null) {
            return noseNode;
        }

        final Table btnTable = new Table(skin);
        for (final BaseDetails nose : availableNoses) {
            final String earName = StringUtils.capitalize(nose.getName().toLowerCase());
            final CheckBox cb = new CheckBox(earName, skin);
            noseGroup.add(cb);
            if (nose == activeNose) {
                cb.setChecked(true);
            }
            cb.addListener(new ChangeListener() {

                @Override
                public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                    if (cb.isChecked()) {
                        parent.setCharacterNose(nose);
                    }
                }
            });

            btnTable.add(cb).left();
            btnTable.row();
        }

        final Tree.Node noseTableNode = new Tree.Node(btnTable);
        noseTableNode.setSelectable(false);
        noseNode.add(noseTableNode);

        return noseNode;
    }

    private Tree.Node buildHairNode(final BaseDetails activeHairStyle, final BaseDetails activeHairColor) {
        if (hairNode == null) {
            hairNode = buildLabeledNode("Hair", skin);
        } else {
            hairNode.removeAll();
        }

        if (availableHairStyles == null) {
            return hairNode;
        }

        final Label styleLabel = new Label("Style:", skin);
        final SelectBox<String> stylesSelection = new SelectBox<String>(skin);
        final List<String> hairStylesList = new ArrayList<String>(availableHairStyles.size());
        for (final BaseDetails style : availableHairStyles) {
            final String dispName = style.getDisplayName();
            hairStylesList.add(dispName);
        }
        stylesSelection.setItems(hairStylesList.toArray(new String[hairStylesList.size()]));
        stylesSelection.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                final String selected = stylesSelection.getSelected();
                final BaseDetails style = parent.getHairStyleByDisplayName(selected);
                parent.setCharacterHairStyle(style);
            }
        });
        if (activeHairStyle != null) {
            stylesSelection.setSelected(activeHairStyle.getDisplayName());
        } else {
            stylesSelection.setSelectedIndex(0);
        }

        final Table styleTable = new Table(skin);
        styleTable.add(styleLabel).right().pad(5f);
        styleTable.add(stylesSelection).left();
        styleTable.row();

        final Label colorLabel = new Label("Color:", skin);
        final SelectBox<String> colorsSelection = new SelectBox<String>(skin);
        if (availableHairColors == null) {
            colorsSelection.getItems().add("N/A");
            colorsSelection.setDisabled(true);
        } else {
            colorsSelection.setDisabled(false);
            final List<String> colorsList = new ArrayList<String>(availableHairColors.size());
            for (final BaseDetails color : availableHairColors) {
                final String dispName = color.getDisplayName();
                colorsList.add(dispName);
            }
            colorsSelection.setItems(colorsList.toArray(new String[colorsList.size()]));
            colorsSelection.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                    final String selected = colorsSelection.getSelected();
                    final BaseDetails color = parent.getHairColorByDisplayName(selected);
                    parent.setCharacterHairColor(color);
                }
            });
            if (activeHairColor != null) {
                colorsSelection.setSelected(activeHairColor.getDisplayName());
            } else {
                colorsSelection.setSelectedIndex(0);
            }
        }

        styleTable.add(colorLabel).right().pad(5f);
        styleTable.add(colorsSelection).left();

        final Tree.Node stylesTableNode = new Tree.Node(styleTable);
        stylesTableNode.setSelectable(false);
        hairNode.add(stylesTableNode);

        return hairNode;
    }

    private Tree.Node buildShirtColorNode(final ColoredDetails activeColor) {
        if (shirtColorNode == null) {
            shirtColorNode = buildLabeledNode("Shirt Color", skin);
        } else {
            shirtColorNode.removeAll();
        }

        clearButtonGroup(shirtColorGroup);

        if (availableShirtColors == null) {
            return shirtColorNode;
        }

        final Table btnTable = new Table(skin);
        int count = 1;
        for (final ColoredDetails color : availableShirtColors) {
            final Button btn = new Button(skin, "colored");
            final CheckBox cb = new CheckBox("", skin);
            shirtColorGroup.add(cb);
            btn.setColor(color.getSampleColor());
            btn.setSize(32f, 32f);
            if (color == activeColor) {
                cb.setChecked(true);
            }
            cb.addListener(new ChangeListener() {

                @Override
                public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                    if (cb.isChecked()) {
                        parent.setCharacterShirtColor(color);
                    }
                }
            });

            btn.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    parent.setCharacterShirtColor(color);
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

        final Tree.Node tableNode = new Tree.Node(btnTable);
        tableNode.setSelectable(false);
        shirtColorNode.add(tableNode);

        return shirtColorNode;
    }

    private Tree.Node buildPantsColorNode(final ColoredDetails activeColor) {
        if (pantsColorNode == null) {
            pantsColorNode = buildLabeledNode("Pants Color", skin);
        } else {
            pantsColorNode.removeAll();
        }

        clearButtonGroup(pantsColorGroup);

        if (availablePantsColors == null) {
            return pantsColorNode;
        }

        final Table btnTable = new Table(skin);
        int count = 1;
        for (final ColoredDetails color : availablePantsColors) {
            final Button btn = new Button(skin, "colored");
            final CheckBox cb = new CheckBox("", skin);
            pantsColorGroup.add(cb);
            btn.setColor(color.getSampleColor());
            btn.setSize(32f, 32f);
            if (color == activeColor) {
                cb.setChecked(true);
            }
            cb.addListener(new ChangeListener() {

                @Override
                public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                    if (cb.isChecked()) {
                        parent.setCharacterPantsColor(color);
                    }
                }
            });

            btn.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    parent.setCharacterPantsColor(color);
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

        final Tree.Node tableNode = new Tree.Node(btnTable);
        tableNode.setSelectable(false);
        pantsColorNode.add(tableNode);

        return pantsColorNode;
    }

    private Tree.Node buildShoeColorNode(final ColoredDetails activeColor) {
        if (shoeColorNode == null) {
            shoeColorNode = buildLabeledNode("Shoe Color", skin);
        } else {
            shoeColorNode.removeAll();
        }

        clearButtonGroup(shoeColorGroup);

        if (availableShoeColors == null) {
            return shoeColorNode;
        }

        final Table btnTable = new Table(skin);
        int count = 1;
        for (final ColoredDetails color : availableShoeColors) {
            final Button btn = new Button(skin, "colored");
            final CheckBox cb = new CheckBox("", skin);
            shoeColorGroup.add(cb);
            btn.setColor(color.getSampleColor());
            btn.setSize(32f, 32f);
            if (color == activeColor) {
                cb.setChecked(true);
            }
            cb.addListener(new ChangeListener() {

                @Override
                public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                    if (cb.isChecked()) {
                        parent.setCharacterShoeColor(color);
                    }
                }
            });

            btn.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    parent.setCharacterShoeColor(color);
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

        final Tree.Node tableNode = new Tree.Node(btnTable);
        tableNode.setSelectable(false);
        shoeColorNode.add(tableNode);

        return shoeColorNode;
    }

    private static Tree.Node buildLabeledNode(final String text, final Skin skin) {
        final Label label = new Label(text, skin);
        final Tree.Node res = new Tree.Node(label);
        res.setSelectable(false);
        return res;
    }

    private static void clearButtonGroup(final ButtonGroup group) {
        final Array<Button> btns = group.getButtons();
        if (btns.size <= 0) {
            return;
        }

        for (final Button btn : btns) {
            group.remove(btn);
        }
    }

    private static <T> boolean differentCollections(final Collection<T> a, final Collection<T> b) {
        if (a == null) {
            return true;
        }

        boolean res = false;
        if (a.size() != b.size()) {
            res = true;
        } else {
            for (final T item : b) {
                if (!a.contains(item)) {
                    res = true;
                    break;
                }
            }
        }

        return res;
    }

    private Tree.Node nameNode;
    private TextField nameField;
    private Tree.Node genderNode;
    private Tree.Node skinColorsNode;
    private Tree.Node raceNode;
    private Tree.Node eyesNode;
    private Tree.Node earsNode;
    private Tree.Node noseNode;
    private Tree.Node hairNode;
    private Tree.Node shirtColorNode;
    private Tree.Node pantsColorNode;
    private Tree.Node shoeColorNode;
    private final Skin skin;
    private final CharacterAppearanceTable parent;
    private final Tree actionsTree;
    private final ButtonGroup genderButtonGroup, raceButtonGroup, skinColorGroup, eyesGroup, earsGroup, noseGroup, shirtColorGroup, pantsColorGroup, shoeColorGroup;
    private final ScrollPane scrollPane;

    private boolean validName = true;
    private final Label invalidNameLbl;

    private Collection<BaseDetails> availableRaces = null;
    private Collection<SkinColorDetails> availableSkinColors = null;
    private Collection<ColoredDetails> availableEyes = null;
    private Collection<BaseDetails> availableEars = null;
    private Collection<BaseDetails> availableNoses = null;
    private Collection<BaseDetails> availableHairStyles = null;
    private Collection<BaseDetails> availableHairColors = null;
    private Collection<ColoredDetails> availableShirtColors = null;
    private Collection<ColoredDetails> availablePantsColors = null;
    private Collection<ColoredDetails> availableShoeColors = null;
}
