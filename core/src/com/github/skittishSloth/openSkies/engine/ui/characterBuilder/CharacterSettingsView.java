/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.ui.characterBuilder;

import com.github.skittishSloth.openSkies.engine.ui.characterBuilder.partDetails.EyeListDetails;
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
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Tree;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.github.skittishSloth.openSkies.engine.player.Ears;
import com.github.skittishSloth.openSkies.engine.player.Eye;
import com.github.skittishSloth.openSkies.engine.player.Gender;
import com.github.skittishSloth.openSkies.engine.player.Nose;
import com.github.skittishSloth.openSkies.engine.player.Race;
import com.github.skittishSloth.openSkies.engine.ui.characterBuilder.partDetails.EarListDetails;
import com.github.skittishSloth.openSkies.engine.ui.characterBuilder.partDetails.NoseListDetails;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

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
        eyesGroup = new ButtonGroup();
        earsGroup = new ButtonGroup();
        noseGroup = new ButtonGroup();

        nameNode = buildNameNode();
        actionsTree.add(nameNode);

        genderNode = buildGenderNode();
        actionsTree.add(genderNode);

        raceNode = buildRaceNode();
        actionsTree.add(raceNode);

        presetColorsNode = buildPresetColorNode(null, null);
        actionsTree.add(presetColorsNode);

        eyesNode = buildEyesNode(null, null);
        actionsTree.add(eyesNode);
        
        earsNode = buildEarsNode(null, null);
        actionsTree.add(earsNode);
        
        noseNode = buildNoseNode(null, null);
        actionsTree.add(noseNode);
        
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
    
    public void setAvailableEyes(final Collection<EyeListDetails> availableEyes, final Eye activeEye) {
        buildEyesNode(availableEyes, activeEye);
    }
    
    public void setAvailableEars(final Collection<EarListDetails> availableEars, final Ears activeEars) {
        buildEarsNode(availableEars, activeEars);
    }
    
    public void setAvailableNoses(final Collection<NoseListDetails> availableNoses, final Nose activeNose) {
        buildNoseNode(availableNoses, activeNose);
    }

    public void update(final Color activeColor, final Eye activeEye, final Ears activeEars, final Nose activeNose) {
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
            availableColors = colors;
            setAvailableColors(availableColors, activeColor);
        }
        
        if (availableEyes == null) {
            availableEyes = parent.getAvailableEyes();
            setAvailableEyes(availableEyes, activeEye);
        }
        
        boolean differentEars = false;
        final Collection<EarListDetails> ears = parent.getAvailableEars();
        if (availableEars == null) {
            availableEars = ears;
            differentEars = true;
        } else {
            for (final EarListDetails eld : ears) {
                if (!availableEars.contains(eld)) {
                    differentEars = true;
                    break;
                }
            }
        }
        
        if (differentEars) {
            availableEars = ears;
            setAvailableEars(availableEars, activeEars);
        }
        
        boolean differentNoses = false;
        final Collection<NoseListDetails> noses = parent.getAvailableNoses();
        if (availableNoses == null) {
            availableNoses = noses;
            differentNoses = true;
        } else {
            for (final NoseListDetails nld : noses) {
                if (!availableNoses.contains(nld)) {
                    differentNoses = true;
                    break;
                }
            }
        }
        
        if (differentNoses) {
            availableNoses = noses;
            setAvailableNoses(availableNoses, activeNose);
        }
    }

    private Tree.Node buildNameNode() {
        if (nameNode == null) {
            nameNode = buildLabeledNode("Character Name", skin);
        } else {
            nameNode.removeAll();
        }

        if (nameField == null) {
            nameField = new TextField("", skin);
        } else {
            nameField.setText("");
        }

        final Table nameTbl = new Table(skin);
        final Label nameLbl = new Label("Name:", skin);
        nameTbl.add(nameLbl).right().pad(2f);
        nameTbl.add(nameField).left();

        final Tree.Node nameTblNode = new Tree.Node(nameTbl);
        nameTblNode.setSelectable(false);
        nameNode.add(nameTblNode);
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

    private Tree.Node buildPresetColorNode(final Collection<Color> availableColors, final Color activeColor) {
        if (presetColorsNode == null) {
            presetColorsNode = buildLabeledNode("Preset Colors", skin);
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
            raceNode = buildLabeledNode("Race", skin);
        } else {
            raceNode.removeAll();
        }

        final Table raceTbl = new Table(skin);
        raceTbl.defaults().left();

        final CheckBox humanBtn = new CheckBox("Human", skin);
        humanBtn.addListener(new RaceButtonClickListener(parent, Race.HUMAN));

        final CheckBox elfBtn = new CheckBox("Elf", skin);
        elfBtn.addListener(new RaceButtonClickListener(parent, Race.ELF));

        final Array<Button> raceBtns = raceButtonGroup.getButtons();
        if (raceBtns.size > 0) {
            for (final Button btn : raceBtns) {
                raceButtonGroup.remove(btn);
            }
        }

        raceButtonGroup.add(humanBtn, elfBtn);

        raceTbl.add(humanBtn);
        raceTbl.row();
        raceTbl.add(elfBtn);

        final Tree.Node raceTblNode = new Tree.Node(raceTbl);
        raceTblNode.setSelectable(false);
        raceNode.add(raceTblNode);

        return raceNode;
    }

    private Tree.Node buildEyesNode(final Collection<EyeListDetails> availableEyes, final Eye activeEye) {
        if (eyesNode == null) {
            eyesNode = buildLabeledNode("Eyes", skin);
        } else {
            eyesNode.removeAll();
        }

        clearButtonGroup(eyesGroup);
        if (availableEyes == null) {
            return eyesNode;
        }
        
        final List<EyeListDetails> eyeDetails = new ArrayList<EyeListDetails>(availableEyes);

        eyeDetails.sort(new Comparator<EyeListDetails>() {
            @Override
            public int compare(EyeListDetails o1, EyeListDetails o2) {
                return o1.compareTo(o2);
            }
        });

        final Table btnTable = new Table(skin);
        int count = 1;
        for (final EyeListDetails eye : eyeDetails) {
            final Button btn = new Button(skin, "colored");
            final CheckBox cb = new CheckBox("", skin);
            eyesGroup.add(cb);
            btn.setColor(eye.getColor());
            btn.setSize(32f, 32f);
            if (eye.getEye() == activeEye) {
                cb.setChecked(true);
            }
            cb.addListener(new ChangeListener() {

                @Override
                public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                    if (cb.isChecked()) {
                        parent.setCharacterEye(eye.getEye());
                    }
                }
            });

            btn.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    parent.setCharacterEye(eye.getEye());
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
    
    private Tree.Node buildEarsNode(final Collection<EarListDetails> availableEars, final Ears activeEars) {
        if (earsNode == null) {
            earsNode = buildLabeledNode("Ears", skin);
        } else {
            earsNode.removeAll();
        }
        
        clearButtonGroup(earsGroup);
        if (availableEars == null) {
            return earsNode;
        }
        
        final List<EarListDetails> earDetails = new ArrayList<EarListDetails>(availableEars);

        earDetails.sort(new Comparator<EarListDetails>() {
            @Override
            public int compare(EarListDetails o1, EarListDetails o2) {
                return o1.compareTo(o2);
            }
        });

        final Table btnTable = new Table(skin);
        for (final EarListDetails ear : earDetails) {
            final String earName = StringUtils.capitalize(ear.getEars().name().toLowerCase());
            final CheckBox cb = new CheckBox(earName, skin);
            earsGroup.add(cb);
            if (ear.getEars() == activeEars) {
                cb.setChecked(true);
            }
            cb.addListener(new ChangeListener() {

                @Override
                public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                    if (cb.isChecked()) {
                        parent.setCharacterEars(ear.getEars());
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
    
    private Tree.Node buildNoseNode(final Collection<NoseListDetails> availableNoses, final Nose activeNose) {
        if (noseNode == null) {
            noseNode = buildLabeledNode("Nose", skin);
        } else {
            noseNode.removeAll();
        }
        
        clearButtonGroup(noseGroup);
        if (availableNoses == null) {
            return noseNode;
        }
        
        final List<NoseListDetails> noseDetails = new ArrayList<NoseListDetails>(availableNoses);

        noseDetails.sort(new Comparator<NoseListDetails>() {
            @Override
            public int compare(NoseListDetails o1, NoseListDetails o2) {
                return o1.compareTo(o2);
            }
        });

        final Table btnTable = new Table(skin);
        for (final NoseListDetails nose : noseDetails) {
            final String earName = StringUtils.capitalize(nose.getNose().name().toLowerCase());
            final CheckBox cb = new CheckBox(earName, skin);
            earsGroup.add(cb);
            if (nose.getNose() == activeNose) {
                cb.setChecked(true);
            }
            cb.addListener(new ChangeListener() {

                @Override
                public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                    if (cb.isChecked()) {
                        parent.setCharacterNose(nose.getNose());
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

    private Tree.Node nameNode;
    private TextField nameField;
    private Tree.Node genderNode;
    private Tree.Node presetColorsNode;
    private Tree.Node raceNode;
    private Tree.Node eyesNode;
    private Tree.Node earsNode;
    private Tree.Node noseNode;
    private final Skin skin;
    private final CharacterBuildTable parent;
    private final Tree actionsTree;
    private final ButtonGroup genderButtonGroup, raceButtonGroup, presetColorGroup, eyesGroup, earsGroup, noseGroup;
    private final ScrollPane scrollPane;

    private Collection<Color> availableColors = null;
    private Collection<EyeListDetails> availableEyes = null;
    private Collection<EarListDetails> availableEars = null;
    private Collection<NoseListDetails> availableNoses = null;
}
