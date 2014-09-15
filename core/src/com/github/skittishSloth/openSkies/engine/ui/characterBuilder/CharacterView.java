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
import com.github.skittishSloth.openSkies.engine.player.Ears;
import com.github.skittishSloth.openSkies.engine.player.Eye;
import com.github.skittishSloth.openSkies.engine.player.Gender;
import com.github.skittishSloth.openSkies.engine.player.Nose;
import com.github.skittishSloth.openSkies.engine.player.Race;
import com.github.skittishSloth.openSkies.engine.sprites.AnimationState;
import com.github.skittishSloth.openSkies.engine.sprites.UniversalDirectionalSprite;
import com.github.skittishSloth.openSkies.engine.ui.UDSActor;
import com.github.skittishSloth.openSkies.engine.ui.characterBuilder.partDetails.EarListDetails;
import com.github.skittishSloth.openSkies.engine.ui.characterBuilder.partDetails.EyeListDetails;
import com.github.skittishSloth.openSkies.engine.ui.characterBuilder.partDetails.NoseListDetails;
import com.github.skittishSloth.openSkies.engine.ui.characterBuilder.partDetails.SpriteListDetails;
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

        eyeSprites = buildEyesMap();

        earsSprites = buildEarsMap();

        noseSprites = buildNoseMap();
        
        updateCurrentSprite();
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
                return o1.compareTo(o2);
            }
        });

        final Collection<Color> colors = new ArrayList<Color>(details.size());
        for (final SpriteListDetails sld : details) {
            colors.add(sld.getColor());
        }
        return colors;
    }
    
    public Collection<EarListDetails> getAvailableEars() {
        if ((activeGender == null) || (activeRace == null) || (activeColor == null)) {
            return null;
        }
        
        final Collection<EarListDetails> detailsCol = earsSprites.get(activeGender).get(activeRace).get(activeColor).values();
        final List<EarListDetails> details = new ArrayList<EarListDetails>(detailsCol);
        details.sort(new Comparator<EarListDetails>() {

            @Override
            public int compare(EarListDetails o1, EarListDetails o2) {
                return o1.compareTo(o2);
            }
        });
        
        return details;
    }
    
    public Collection<NoseListDetails> getAvailableNoses() {
        if ((activeGender == null) || (activeRace == null) || (activeColor == null)) {
            return null;
        }
        
        final Collection<NoseListDetails> detailsCol = noseSprites.get(activeGender).get(activeRace).get(activeColor).values();
        final List<NoseListDetails> details = new ArrayList<NoseListDetails>(detailsCol);
        details.sort(new Comparator<NoseListDetails>() {

            @Override
            public int compare(NoseListDetails o1, NoseListDetails o2) {
                return o1.compareTo(o2);
            }
        });
        
        return details;
    }

    public Collection<EyeListDetails> getAvailableEyes() {
        if (activeGender == null) {
            return null;
        }

        return eyeSprites.get(activeGender).values();
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

    public void setEye(final Eye eye) {
        this.activeEye = eye;
        updateCurrentSprite();
        parent.updateSettings();
    }
    
    public void setEars(final Ears ears) {
        this.activeEars = ears;
        updateCurrentSprite();
        parent.updateSettings();
    }
    
    public void setNose(final Nose nose) {
        this.activeNose = nose;
        updateCurrentSprite();
        parent.updateSettings();
    }

    public Color getActiveColor() {
        return activeColor;
    }

    public Eye getActiveEye() {
        return activeEye;
    }
    
    public Ears getActiveEars() {
        return activeEars;
    }
    
    public Nose getActiveNose() {
        return activeNose;
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

        final UniversalDirectionalSprite eyeSprite;
        if ((activeGender != null) && (activeEye != null)) {
            eyeSprite = eyeSprites.get(activeGender).get(activeEye).getSprite();
            eyeSprite.setMoving(true);
            eyeSprite.setAnimationState(AnimationState.WALKING);
        } else {
            eyeSprite = null;
        }

        final UniversalDirectionalSprite earSprite;
        if ((activeGender != null) && (activeRace != null) && (activeColor != null) && (activeEars != null)) {
            if (activeEars == Ears.DEFAULT) {
                earSprite = null;
            } else {
                earSprite = earsSprites.get(activeGender).get(activeRace).get(activeColor).get(activeEars).getSprite();
                earSprite.setMoving(true);
                earSprite.setAnimationState(AnimationState.WALKING);
            }
        } else {
            earSprite = null;
        }
        
        final UniversalDirectionalSprite noseSprite;
        if ((activeGender != null) && (activeRace != null) && (activeColor != null) && (activeNose != null)) {
            if (activeNose == Nose.NONE) {
                noseSprite = null;
            } else {
                noseSprite = noseSprites.get(activeGender).get(activeRace).get(activeColor).get(activeNose).getSprite();
                noseSprite.setMoving(true);
                noseSprite.setAnimationState(AnimationState.WALKING);
            }
        } else {
            noseSprite = null;
        }

        spriteActor.setSprite(sprite);
        spriteActor.setEyeSprite(eyeSprite);
        spriteActor.setEarSprite(earSprite);
        spriteActor.setNoseSprite(noseSprite);
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

                final String raceDir = genderDir + "/" + raceName + "/body";
                final Map<Color, SpriteListDetails> colorMap = new HashMap<Color, SpriteListDetails>();

                final FileHandle bodyFiles = Gdx.files.internal(raceDir);
                final FileHandle[] files = bodyFiles.list(new FileFilter() {

                    @Override
                    public boolean accept(final File pathname) {
                        final String name = pathname.getName();

                        return (StringUtils.contains(name, "-" + raceName + "-"));
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

    private static Map<Gender, Map<Eye, EyeListDetails>> buildEyesMap() {
        final Map<Gender, Map<Eye, EyeListDetails>> res = new EnumMap<Gender, Map<Eye, EyeListDetails>>(Gender.class);
        final String baseDir = "gfx/char-building/body";

        for (final Gender gender : Gender.values()) {
            if (!gender.isForCharacterBuilding()) {
                continue;
            }

            final String genderDir = baseDir + "/" + gender.name().toLowerCase();
            final Map<Eye, EyeListDetails> eyeMap = new EnumMap<Eye, EyeListDetails>(Eye.class);

            final String eyeDir = genderDir + "/eyes/";
            final FileHandle eyeFiles = Gdx.files.internal(eyeDir);
            for (final Eye eye : Eye.values()) {
                final String eyeName = eye.name().toLowerCase();
                final String colorStr = eye.getColorString().toLowerCase();

                final FileHandle[] files = eyeFiles.list(new FileFilter() {

                    @Override
                    public boolean accept(final File pathname) {
                        final String match = eyeName + "-" + colorStr;
                        return (pathname.getName().toLowerCase().contains(match));
                    }
                });

                for (final FileHandle fh : files) {
                    final UniversalDirectionalSprite sprite = new UniversalDirectionalSprite(fh.path(), 1 / 15f, AnimationState.values());
                    final String fileName = fh.nameWithoutExtension();
                    final char orderChar = fileName.charAt(0);
                    final int order = (int) orderChar;
                    final String colorString = fileName.substring(1).replace("-" + eyeName + "-", "");
                    final Color c = Color.valueOf(colorString);
                    final EyeListDetails details = new EyeListDetails(order, c, eye, sprite);
                    eyeMap.put(eye, details);
                }
            }
            res.put(gender, eyeMap);
        }
        return res;
    }

    private static Map<Gender, Map<Race, Map<Color, Map<Ears, EarListDetails>>>> buildEarsMap() {
        final Map<Gender, Map<Race, Map<Color, Map<Ears, EarListDetails>>>> res = new EnumMap<Gender, Map<Race, Map<Color, Map<Ears, EarListDetails>>>>(Gender.class);
        final String baseDir = "gfx/char-building/body";

        for (final Gender gender : Gender.values()) {
            if (!gender.isForCharacterBuilding()) {
                continue;
            }

            final Map<Race, Map<Color, Map<Ears, EarListDetails>>> raceMap = new EnumMap<Race, Map<Color, Map<Ears, EarListDetails>>>(Race.class);

            final String genderDir = baseDir + "/" + gender.name().toLowerCase();
            for (final Race race : Race.values()) {
                final String raceName = race.name().toLowerCase();

                final String earsDir = genderDir + "/" + raceName + "/ears";

                final Map<Color, Map<Ears, EarListDetails>> colorMap = new HashMap<Color, Map<Ears, EarListDetails>>();
                for (final Ears ears : Ears.values()) {
                    if (ears == Ears.DEFAULT) {
                        continue;
                    }
                    
                    final String earsName = ears.name().toLowerCase();

                    final FileHandle earFiles = Gdx.files.internal(earsDir);
                    
                    final FileHandle[] files = earFiles.list(new FileFilter() {

                        @Override
                        public boolean accept(final File pathname) {
                            final String name = pathname.getName();

                            return (StringUtils.contains(name, "-" + earsName + "-"));
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
                        final String colorString = fileName.substring(1).replace("-" + earsName + "-", "");
                        final Color c = Color.valueOf(colorString);
                        final Map<Ears, EarListDetails> earMap;
                        if (colorMap.containsKey(c)) {
                            earMap = colorMap.get(c);
                        } else {
                            earMap = new EnumMap<Ears, EarListDetails>(Ears.class);
                            colorMap.put(c, earMap);
                        }
                        
                        if (!(earMap.containsKey(Ears.DEFAULT))) {
                            final EarListDetails defaults = new EarListDetails(0, gender, race, c, Ears.DEFAULT, null);
                            earMap.put(Ears.DEFAULT, defaults);
                        }
                        
                        final UniversalDirectionalSprite theSprite = new UniversalDirectionalSprite(fh.path(), 1 / 15f, AnimationState.values());
                        final EarListDetails details = new EarListDetails(order, gender, race, c, ears, theSprite);
                        earMap.put(ears, details);
                        
                    }
                }
                raceMap.put(race, colorMap);
            }
            res.put(gender, raceMap);
        }
        return res;
    }
    
    private static Map<Gender, Map<Race, Map<Color, Map<Nose, NoseListDetails>>>> buildNoseMap() {
        final Map<Gender, Map<Race, Map<Color, Map<Nose, NoseListDetails>>>> res = new EnumMap<Gender, Map<Race, Map<Color, Map<Nose, NoseListDetails>>>>(Gender.class);
        final String baseDir = "gfx/char-building/body";

        for (final Gender gender : Gender.values()) {
            if (!gender.isForCharacterBuilding()) {
                continue;
            }

            final Map<Race, Map<Color, Map<Nose, NoseListDetails>>> raceMap = new EnumMap<Race, Map<Color, Map<Nose, NoseListDetails>>>(Race.class);

            final String genderDir = baseDir + "/" + gender.name().toLowerCase();
            for (final Race race : Race.values()) {
                final String raceName = race.name().toLowerCase();

                final String noseDir = genderDir + "/" + raceName + "/nose";

                final Map<Color, Map<Nose, NoseListDetails>> colorMap = new HashMap<Color, Map<Nose, NoseListDetails>>();
                for (final Nose nose : Nose.values()) {
                    if (nose == Nose.NONE) {
                        continue;
                    }
                    
                    final String noseName = nose.name().toLowerCase();

                    final FileHandle noseFiles = Gdx.files.internal(noseDir);
                    
                    final FileHandle[] files = noseFiles.list(new FileFilter() {

                        @Override
                        public boolean accept(final File pathname) {
                            final String name = pathname.getName();

                            return (StringUtils.contains(name, "-" + noseName + "-"));
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
                        final String colorString = fileName.substring(1).replace("-" + noseName + "-", "");
                        final Color c = Color.valueOf(colorString);
                        final Map<Nose, NoseListDetails> noseMap;
                        if (colorMap.containsKey(c)) {
                            noseMap = colorMap.get(c);
                        } else {
                            noseMap = new EnumMap<Nose, NoseListDetails>(Nose.class);
                            colorMap.put(c, noseMap);
                        }
                        
                        if (!(noseMap.containsKey(Nose.NONE))) {
                            final NoseListDetails defaults = new NoseListDetails(0, gender, race, c, Nose.NONE, null);
                            noseMap.put(Nose.NONE, defaults);
                        }
                        
                        final UniversalDirectionalSprite theSprite = new UniversalDirectionalSprite(fh.path(), 1 / 15f, AnimationState.values());
                        final NoseListDetails details = new NoseListDetails(order, gender, race, c, nose, theSprite);
                        noseMap.put(nose, details);
                    }
                }
                raceMap.put(race, colorMap);
            }
            res.put(gender, raceMap);
        }
        return res;
    }

    private final Map<Gender, Map<Race, Map<Color, SpriteListDetails>>> sprites;
    private final Map<Gender, Map<Eye, EyeListDetails>> eyeSprites;
    private final Map<Gender, Map<Race, Map<Color, Map<Ears, EarListDetails>>>> earsSprites;
    private final Map<Gender, Map<Race, Map<Color, Map<Nose, NoseListDetails>>>> noseSprites;

    private final UDSActor spriteActor;
    private final CharacterBuildTable parent;

    private Color activeColor;
    private Gender activeGender = Gender.MALE;
    private Race activeRace = Race.HUMAN;
    private Eye activeEye = Eye.BLUE;
    private Ears activeEars = Ears.DEFAULT;
    private Nose activeNose = Nose.NONE;
}
