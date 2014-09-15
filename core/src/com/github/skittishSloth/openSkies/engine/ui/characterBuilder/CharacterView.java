/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.ui.characterBuilder;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.github.skittishSloth.openSkies.engine.player.Gender;
import com.github.skittishSloth.openSkies.engine.player.Race;
import com.github.skittishSloth.openSkies.engine.sprites.AnimationState;
import com.github.skittishSloth.openSkies.engine.sprites.UniversalDirectionalSprite;
import com.github.skittishSloth.openSkies.engine.ui.UDSActor;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author mcory01
 */
public final class CharacterView extends Table {

    public CharacterView(final Skin skin, final CharacterBuildTable parent) {
        super(skin);
        this.parent = parent;

        defaults().align(Align.center);
        sprites = buildSpriteMaps();
        final Collection<Color> availableColors = getAvailableColors();
        for (final Color c : availableColors) {
            activeColor = c;
            break;
        }

        spriteActor = new UDSActor(null);
        add(spriteActor).center();
    }

    public Collection<Color> getAvailableColors() {
        if ((activeGender == null) || (activeRace == null)) {
            return null;
        }
        
        final Collection<SpriteListDetails> detailsCol = sprites.get(activeGender).get(activeRace).values();
        final List<SpriteListDetails> details = new ArrayList<SpriteListDetails>(detailsCol);
        details.sort(new Comparator<SpriteListDetails>() {

            @Override
            public int compare(SpriteListDetails o1, SpriteListDetails o2) {
                return Integer.compare(o1.getOrder(), o2.getOrder());
            }
        });
        
        final Collection<Color> colors = new ArrayList<Color>(details.size());
        for (final SpriteListDetails sld : details) {
            colors.add(sld.getColor());
        }
        return colors;
    }

    public void setActiveColor(final Color color) {
        this.activeColor = color;
        updateCurrentSprite();
        parent.updateSettings();
    }

    public void setGender(final Gender gender) {
        this.activeGender = gender;
        updateCurrentSprite();
        parent.updateSettings();
    }

    public void setRace(final Race race) {
        this.activeRace = race;
        updateCurrentSprite();
        parent.updateSettings();
    }
    
    public Color getActiveColor() {
        return activeColor;
    }

    private void updateCurrentSprite() {
        final UniversalDirectionalSprite sprite;
        if ((activeGender != null) && (activeRace != null) && (activeColor != null)) {
            final Map<Color, SpriteListDetails> colorMap = sprites.get(activeGender).get(activeRace);
            if (!colorMap.containsKey(activeColor)) {
                for (final Color color : colorMap.keySet()) {
                    activeColor = color;
                    break;
                }
            }
            final SpriteListDetails details = colorMap.get(activeColor);
            if (details == null) {
                sprite = null;
            } else {
                sprite = details.getSprite();
                sprite.setMoving(true);
                sprite.setAnimationState(AnimationState.WALKING);
            }
        } else {
            sprite = null;
        }

        spriteActor.setSprite(sprite);
    }

    private static Map<Color, UniversalDirectionalSprite> loadMaleSprites() {
        final Map<Color, UniversalDirectionalSprite> res = new HashMap<Color, UniversalDirectionalSprite>();
        final FileHandle maleBodyFiles = Gdx.files.internal("gfx/char-building/body/male/human");
        final FileHandle[] files = maleBodyFiles.list(new FileFilter() {

            @Override
            public boolean accept(final File pathname) {
                final String name = pathname.getName();

                return ((!StringUtils.contains(name, "human-grayscale.png"))
                        && (StringUtils.contains(name, "-human-")));
            }
        });

        Arrays.sort(files, new Comparator<FileHandle>() {

            @Override
            public int compare(FileHandle o1, FileHandle o2) {
                return o1.name().compareTo(o2.name());
            }
        });

        for (final FileHandle fh : files) {
            final String fileName = fh.nameWithoutExtension();
            final String colorString = fileName.substring(1).replace("-human-", "");
            final Color c = Color.valueOf(colorString);

            final UniversalDirectionalSprite theSprite = new UniversalDirectionalSprite(fh.path(), 1 / 15f, AnimationState.values());
            res.put(c, theSprite);
        }
        return res;
    }

    private static Map<Gender, Map<Race, Map<Color, SpriteListDetails>>> buildSpriteMaps() {
        final Map<Gender, Map<Race, Map<Color, SpriteListDetails>>> res = new EnumMap<Gender, Map<Race, Map<Color, SpriteListDetails>>>(Gender.class);
        final String baseDir = "gfx/char-building/body";

        for (final Gender gender : Gender.values()) {
            if (!gender.isForCharacterBuilding()) {
                continue;
            }

            final Map<Race, Map<Color, SpriteListDetails>> raceMap = new EnumMap<Race, Map<Color, SpriteListDetails>>(Race.class);

            final String genderDir = baseDir + "/" + gender.name().toLowerCase();
            for (final Race race : Race.values()) {
                final String raceName = race.name().toLowerCase();

                final String raceDir = genderDir + "/" + raceName;
                final Map<Color, SpriteListDetails> colorMap = new HashMap<Color, SpriteListDetails>();

                final FileHandle bodyFiles = Gdx.files.internal(raceDir);
                final FileHandle[] files = bodyFiles.list(new FileFilter() {

                    @Override
                    public boolean accept(final File pathname) {
                        final String name = pathname.getName();

                        return ((!StringUtils.contains(name, raceName + "-grayscale.png"))
                                && (StringUtils.contains(name, "-" + raceName + "-")));
                    }
                });

                Arrays.sort(files, new Comparator<FileHandle>() {

                    @Override
                    public int compare(FileHandle o1, FileHandle o2) {
                        return o1.name().compareTo(o2.name());
                    }
                });

                for (final FileHandle fh : files) {
                    final String fileName = fh.nameWithoutExtension();
                    final char orderChar = fileName.charAt(0);
                    final int order = (int) orderChar;
                    final String colorString = fileName.substring(1).replace("-" + raceName + "-", "");
                    final Color c = Color.valueOf(colorString);

                    final UniversalDirectionalSprite theSprite = new UniversalDirectionalSprite(fh.path(), 1 / 15f, AnimationState.values());
                    final SpriteListDetails details = new SpriteListDetails(order, gender, race, c, theSprite);
                    colorMap.put(c, details);
                }
                raceMap.put(race, colorMap);
            }
            res.put(gender, raceMap);
        }
        return res;
    }

    private final Map<Gender, Map<Race, Map<Color, SpriteListDetails>>> sprites;

    private final UDSActor spriteActor;
    private final CharacterBuildTable parent;

    private Color activeColor;
    private Gender activeGender = Gender.MALE;
    private Race activeRace = Race.HUMAN;
}
