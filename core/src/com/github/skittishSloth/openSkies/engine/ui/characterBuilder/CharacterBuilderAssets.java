/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.ui.characterBuilder;

import com.github.skittishSloth.openSkies.engine.player.details.CharacterAppearanceData;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;
import com.github.skittishSloth.openSkies.engine.player.details.DetailsCollection;
import com.github.skittishSloth.openSkies.engine.player.details.DetailsLoader;
import com.github.skittishSloth.openSkies.engine.player.details.EarDetails;
import com.github.skittishSloth.openSkies.engine.player.details.EyeDetails;
import com.github.skittishSloth.openSkies.engine.player.details.Gender;
import com.github.skittishSloth.openSkies.engine.player.details.HairColors;
import com.github.skittishSloth.openSkies.engine.player.details.HairStyles;
import com.github.skittishSloth.openSkies.engine.player.details.NoseDetails;
import com.github.skittishSloth.openSkies.engine.player.details.PantsColors;
import com.github.skittishSloth.openSkies.engine.player.details.RaceDetails;
import com.github.skittishSloth.openSkies.engine.player.details.ShirtColors;
import com.github.skittishSloth.openSkies.engine.player.details.Shirts;
import com.github.skittishSloth.openSkies.engine.player.details.ShoeColorDetails;
import com.github.skittishSloth.openSkies.engine.player.details.SkinColorDetails;
import com.github.skittishSloth.openSkies.engine.player.info.BackStory;
import com.github.skittishSloth.openSkies.engine.player.info.BackStoryCollection;
import com.github.skittishSloth.openSkies.engine.player.info.PlayerClass;
import com.github.skittishSloth.openSkies.engine.player.info.PlayerClassCollection;
import com.github.skittishSloth.openSkies.engine.player.info.InformationLoader;
import com.github.skittishSloth.openSkies.engine.sprites.UniversalDirectionalSprite;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author mcory01
 */
public class CharacterBuilderAssets implements Disposable {

    private static final String BASE_CB_PATH = "gfx/char-building";
    private static final String BODY_PATH = BASE_CB_PATH + "/body";
    private static final String HAIR_PATH = BASE_CB_PATH + "/hair";
    private static final String FEMALE_BODY_PATH = BODY_PATH + "/female";
    private static final String MALE_BODY_PATH = BODY_PATH + "/male";

    private static final String TORSO_PATH = BASE_CB_PATH + "/torso";
    private static final String SHIRT_PATH = TORSO_PATH + "/shirts";

    private static final String LEGS_PATH = BASE_CB_PATH + "/legs";
    private static final String PANTS_PATH = LEGS_PATH + "/pants";

    private static final String FEET_PATH = BASE_CB_PATH + "/feet";
    private static final String SHOE_PATH = FEET_PATH + "/shoes";

    private static final String DATA_PATH = "data/char-building";

    public CharacterBuilderAssets() {
        this.assets = new AssetManager();
        registerAssets();
    }

    public boolean isFinishedLoading() {
        return assets.update();
    }

    public int getLoadedAssetsCount() {
        return assets.getLoadedAssets();
    }

    public int getQueuedAssetsCount() {
        return assets.getQueuedAssets();
    }

    public float getProgress() {
        return assets.getProgress();
    }

    public UniversalDirectionalSprite getBodySprite(final CharacterAppearanceData buildData) {
        if (buildData == null) {
            Gdx.app.error(getClass().getSimpleName(), "Character build data was null!");
            return null;
        }

        final SkinColorDetails skinColor = buildData.getSkinColor();
        final RaceDetails race = buildData.getRace();
        final Gender gender = buildData.getGender();

        if ((gender == null) || (race == null) || (skinColor == null)) {
            Gdx.app.log(getClass().getSimpleName(), "Build data incomplete.");
            return null;
        }

        final SkinColorDetails actualColor = normalizeColorForRace(skinColor, race);

        final String colorPath = actualColor.getName().toLowerCase() + ".png";
        final String assetPath = BODY_PATH + "/" + gender.name().toLowerCase() + "/" + race.getName().toLowerCase() + "/body/" + colorPath;
        final Texture texture = assets.get(assetPath, Texture.class);
        final UniversalDirectionalSprite res = new UniversalDirectionalSprite(texture);
        return res;
    }

    public UniversalDirectionalSprite getEyeDetailsSprite(final CharacterAppearanceData buildData) {
        if (buildData == null) {
            Gdx.app.error(getClass().getSimpleName(), "Character build data was null!");
            return null;
        }
        final Gender gender = buildData.getGender();
        final EyeDetails buildDataEyes = buildData.getEyeDetails();

        if ((gender == null) || (buildDataEyes == null)) {
            Gdx.app.log(getClass().getSimpleName(), "Build data incomplete.");
            return null;
        }

        final String assetPath;
        switch (gender) {
            case FEMALE:
                assetPath = buildDataEyes.getFemaleTexturePath();
                break;
            case MALE:
                assetPath = buildDataEyes.getMaleTexturePath();
                break;
            default:
                throw new IllegalStateException("Unexpected gender: " + gender);
        }

        if (StringUtils.isBlank(assetPath)) {
            return null;
        }

        final Texture texture = assets.get(assetPath, Texture.class);
        final UniversalDirectionalSprite res = new UniversalDirectionalSprite(texture);
        return res;
    }

    public Collection<NoseDetails> getAvailableNoses() {
        return noseDetails;
    }

    public UniversalDirectionalSprite getNoseSprite(final CharacterAppearanceData buildData) {
        if (buildData == null) {
            Gdx.app.error(getClass().getSimpleName(), "Character build data was null!");
            return null;
        }

        if (buildData.getSkinColor() == null) {
            Gdx.app.log(getClass().getSimpleName(), "Build data incomplete.");
            return null;
        }

        final Gender gender = buildData.getGender();
        final RaceDetails race = buildData.getRace();
        final SkinColorDetails buildDataSkinColor = buildData.getSkinColor();
        final SkinColorDetails skinColor = normalizeColorForRace(buildDataSkinColor, race);
        final NoseDetails buildNoseDetails = buildData.getNose();

        if ((gender == null) || (race == null) || (skinColor == null) || (buildNoseDetails == null)) {
            Gdx.app.log(getClass().getSimpleName(), "Build data incomplete.");
            return null;
        }

        if (!buildNoseDetails.hasTexture()) {
            return null;
        }

        final String texturePath = buildNoseDetails.getTexturePath(buildData);
        if (!assets.isLoaded(texturePath)) {
            Gdx.app.log(getClass().getSimpleName(), "Didn't have path loaded: " + texturePath);
            return null;
        }
        
        final Texture texture = assets.get(texturePath, Texture.class);
        final UniversalDirectionalSprite res = new UniversalDirectionalSprite(texture);

        return res;
    }
    
    public UniversalDirectionalSprite getEarDetailsSprite(final CharacterAppearanceData buildData) {
        if (buildData == null) {
            Gdx.app.error(getClass().getSimpleName(), "Character build data was null!");
            return null;
        }
        
        final RaceDetails race = buildData.getRace();
        if (race == null) {
            Gdx.app.log(getClass().getSimpleName(), "Build data incomplete.");
            return null;
        }
        
        final Gender gender = buildData.getGender();
        final SkinColorDetails skinColor = buildData.getSkinColor();
        final EarDetails buildEarDetails = buildData.getEarDetails();
        
        if ((skinColor == null) || (gender == null) || (buildEarDetails == null)) {
            Gdx.app.log(getClass().getSimpleName(), "Build data incomplete.");
            return null;
        }
        
        if (!buildEarDetails.hasTexture()) {
            return null;
        }
        
        final String texturePath = buildEarDetails.getTexturePath(buildData);
        if (!assets.isLoaded(texturePath)) {
            return null;
        }
        
        final Texture texture = assets.get(texturePath, Texture.class);
        final UniversalDirectionalSprite res = new UniversalDirectionalSprite(texture);

        return res;
    }

    public UniversalDirectionalSprite getHairSprite(final CharacterAppearanceData buildData) {
        if (buildData == null) {
            Gdx.app.error(getClass().getSimpleName(), "Character build data was null!");
            return null;
        }

        final Gender gender = buildData.getGender();
        final HairStyles style = buildData.getHairStyle();
        if (style == HairStyles.BALD) {
            return null;
        }
        final HairColors color = buildData.getHairColor();

        if ((gender == null) || (style == null) || (color == null)) {
            Gdx.app.log(getClass().getSimpleName(), "Build data incomplete.");
            return null;
        }

        final String basePathStr = HAIR_PATH + "/" + gender.name().toLowerCase() + "/" + style.name().toLowerCase();
        final String colorPathStr = basePathStr + "/" + color.toFileNameString() + ".png";

        if (!(assets.isLoaded(colorPathStr))) {
            assets.load(colorPathStr, Texture.class);
            assets.finishLoading();
        }

        final Texture hairTexture = assets.get(colorPathStr, Texture.class);
        final UniversalDirectionalSprite res = new UniversalDirectionalSprite(hairTexture);
        return res;
    }

    public UniversalDirectionalSprite getShirtSprite(final CharacterAppearanceData buildData) {
        if (buildData == null) {
            Gdx.app.error(getClass().getSimpleName(), "Character build data was null!");
            return null;
        }

        final Gender gender = buildData.getGender();
        final Shirts shirt = buildData.getShirt();
        final ShirtColors color = buildData.getShirtColor();

        if ((gender == null) || (shirt == null) || (color == null)) {
            return null;
        }

        final String basePathStr = SHIRT_PATH + "/" + shirt.name().toLowerCase() + "/" + gender.name().toLowerCase();
        final String colorPathStr = basePathStr + "/" + color.name().toLowerCase() + "_" + shirt.name().toLowerCase() + ".png";

        final Texture texture = assets.get(colorPathStr, Texture.class);
        final UniversalDirectionalSprite res = new UniversalDirectionalSprite(texture);
        return res;
    }

    public UniversalDirectionalSprite getPantsSprite(final CharacterAppearanceData buildData) {
        if (buildData == null) {
            Gdx.app.error(getClass().getSimpleName(), "Character build data was null!");
            return null;
        }

        final Gender gender = buildData.getGender();
        final PantsColors color = buildData.getPantsColor();

        if ((gender == null) || (color == null)) {
            Gdx.app.log(getClass().getSimpleName(), "Pants Sprite: Build data incomplete.");
            if (gender == null) {
                Gdx.app.log(getClass().getSimpleName(), "--Gender null");
            } else if (color == null) {
                Gdx.app.log(getClass().getSimpleName(), "--Color null");
            }
            return null;
        }

        final String basePathStr = PANTS_PATH + "/" + gender.name().toLowerCase();
        final String colorPathStr = basePathStr + "/" + color.name().toLowerCase() + "_pants_" + gender.name().toLowerCase() + ".png";

        final Texture texture = assets.get(colorPathStr, Texture.class);
        final UniversalDirectionalSprite res = new UniversalDirectionalSprite(texture);
        return res;
    }

    public UniversalDirectionalSprite getShoeSprite(final CharacterAppearanceData buildData) {
        if (buildData == null) {
            Gdx.app.error(getClass().getSimpleName(), "Character build data was null!");
            return null;
        }

        final Gender gender = buildData.getGender();
        final ShoeColorDetails color = buildData.getShoeColor();

        if ((gender == null) || (color == null)) {
            return null;
        }
        
        if (!color.hasTexture()) {
            return null;
        }
        
        final String texturePath = color.getTexturePath(buildData);
        if (!assets.isLoaded(texturePath)) {
            return null;
        }
        
        final Texture texture = assets.get(texturePath, Texture.class);
        final UniversalDirectionalSprite res = new UniversalDirectionalSprite(texture);

        return res;
    }
    
    public Collection<SkinColorDetails> getAvailableSkinColors(final RaceDetails race) {
        return skinColorsByRace.get(race);
    }
    
    public SkinColorDetails getDefaultSkinColor() {
        SkinColorDetails res = null;
        for (final SkinColorDetails skinColor : skinColorDetails) {
            if (skinColor.isDefaultColor()) {
                res = skinColor;
                break;
            }
        }
        
        return res;
    }
    
    public NoseDetails getDefaultNose() {
        NoseDetails res = null;
        for (final NoseDetails nose : noseDetails) {
            if (nose.isDefaultNose()) {
                res = nose;
                break;
            }
        }
        
        return res;
    }
    
    public RaceDetails getDefaultRace() {
        RaceDetails res = null;
        for (final RaceDetails race : raceDetails) {
            if (race.isDefaultRace()) {
                res = race;
                break;
            }
        }
        
        return res;
    }
    
    public ShoeColorDetails getDefaultShoeColor() {
        ShoeColorDetails res = null;
        for (final ShoeColorDetails shoeColor : shoeColorDetails) {
            if (shoeColor.isDefaultShoeColor()) {
                res = shoeColor;
                break;
            }
        }
        
        return res;
    }

    public Collection<HairStyles> getAvailableHairStyles() {
        return enumToCollection(HairStyles.class);
    }

    public Collection<HairColors> getAvailableHairColors(final CharacterAppearanceData buildData) {
        if (buildData == null) {
            Gdx.app.error(getClass().getSimpleName(), "Character build data was null!");
            return null;
        }

        final Gender gender = buildData.getGender();
        final HairStyles style = buildData.getHairStyle();

        if (style == null) {
            Gdx.app.log(getClass().getSimpleName(), "Character Style was null.");
            return null;
        }

        final String stylePathStr = HAIR_PATH + "/" + gender.name().toLowerCase() + "/" + style.name().toLowerCase();
        final FileHandle stylePath = Gdx.files.internal(stylePathStr);
        final FileHandle[] colorFiles = stylePath.list(new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".png");
            }
        });

        final Collection<HairColors> res = new ArrayList<HairColors>(colorFiles.length);
        for (final FileHandle fh : colorFiles) {
            final String name = fh.nameWithoutExtension();
            final HairColors hc = HairColors.fromStringIgnoreCase(name);
            if (hc != null) {
                res.add(hc);
            }
        }

        return res;
    }

    public Collection<ShirtColors> getAvailableShirtColors() {
        return enumToCollection(ShirtColors.class);
    }

    public Collection<PantsColors> getAvailablePantsColors() {
        return enumToCollection(PantsColors.class);
    }

    public Collection<ShoeColorDetails> getAvailableShoeColors() {
        return shoeColorDetails;
    }

    public Collection<PlayerClass> getPlayerClasses() {
        return playerClasses;
    }

    public Collection<BackStory> getBackStories() {
        return backStories;
    }

    public Collection<EyeDetails> getEyeDetails() {
        return eyeDetails;
    }

    public Collection<EarDetails> getEarDetails() {
        return earDetails;
    }
    
    public Collection<RaceDetails> getRaceDetails() {
        return raceDetails;
    }
    
    public Collection<SkinColorDetails> getSkinColorDetails() {
        return skinColorDetails;
    }

    @Override
    public void dispose() {
        assets.dispose();
    }

    private void registerAssets() {

        final String elfPath = "/elf";
        final String humanPath = "/human";

        loadFilesInPath(FEMALE_BODY_PATH + elfPath);
        loadFilesInPath(MALE_BODY_PATH + elfPath);

        loadFilesInPath(FEMALE_BODY_PATH + humanPath);
        loadFilesInPath(MALE_BODY_PATH + humanPath);

        loadFilesInPath(SHIRT_PATH);

        loadFilesInPath(PANTS_PATH);

        loadFilesInPath(SHOE_PATH);

        loadPlayerClasses();

        loadBackStories();

        loadEyeDetails();

        loadEarDetails();
        
        loadRaceDetails();
        
        loadSkinColorDetails();
        
        buildSkinColorAssociation();
        
        loadNoseDetails();
        
        loadShoeColorDetails();
    }

    private void loadFilesInPath(final String path) {
        final FileHandle dir = Gdx.files.internal(path);
        for (final FileHandle file : dir.list()) {
            final String filePath = file.path();
            if (loadedPaths.contains(filePath)) {
                continue;
            }

            if (file.extension().equalsIgnoreCase("png")) {
                assets.load(file.path(), Texture.class);
                loadedPaths.add(file.path());
            } else if (file.isDirectory() && !(file.name().endsWith("_ignore"))) {
                loadFilesInPath(file.path());
            } else {
                Gdx.app.log(getClass().getSimpleName(), "Unrecognized file: " + file.name());
            }
        }
    }

    private void loadPlayerClasses() {
        final FileHandle fh = Gdx.files.internal("data/char-building/class-descriptions.json");
        final PlayerClassCollection classCollection = InformationLoader.playerClassesFromJsonFile(fh);
        playerClasses.clear();
        if (classCollection != null) {
            final Collection<PlayerClass> classCollectionClasses = classCollection.getClasses();
            if (classCollectionClasses != null) {
                playerClasses.addAll(classCollectionClasses);
            }
        }
    }

    private void loadBackStories() {
        final FileHandle fh = Gdx.files.internal("data/char-building/backstories.json");
        final BackStoryCollection backStoryCollection = InformationLoader.backStoriesFromJsonFile(fh);
        backStories.clear();
        if (backStoryCollection != null) {
            final Collection<BackStory> backStoryCollectionItems = backStoryCollection.getBackStories();
            if (backStoryCollectionItems != null) {
                backStories.addAll(backStoryCollectionItems);
            }
        }
    }

    private void loadEyeDetails() {
        final FileHandle fh = Gdx.files.internal("data/char-building/appearances/eyes.json");
        final DetailsCollection<EyeDetails> eyesCollection = DetailsLoader.fromJson(EyeDetails.class, fh);
        eyeDetails.clear();
        if (eyesCollection != null) {
            final Collection<EyeDetails> eyeDetailsCollectionItems = eyesCollection.getItems();
            if (eyeDetailsCollectionItems != null) {
                eyeDetails.addAll(eyeDetailsCollectionItems);

                for (final EyeDetails eyes : eyeDetails) {
                    final String malePath = eyes.getMaleTexturePath();
                    final String femalePath = eyes.getFemaleTexturePath();
                    if (isLoadablePath(malePath)) {
                        assets.load(malePath, Texture.class);
                        loadedPaths.add(malePath);
                    }

                    if (isLoadablePath(femalePath)) {
                        assets.load(femalePath, Texture.class);
                        loadedPaths.add(femalePath);
                    }

                }
            }
        }
    }

    private void loadEarDetails() {
        final FileHandle fh = Gdx.files.internal("data/char-building/appearances/ears.json");
        final DetailsCollection<EarDetails> earsCollection = DetailsLoader.fromJson(EarDetails.class, fh);
        earDetails.clear();
        if (earsCollection != null) {
            final Collection<EarDetails> earDetailsCollectionItems = earsCollection.getItems();
            if (earDetailsCollectionItems != null) {
                earDetails.addAll(earDetailsCollectionItems);
            }
        }
    }
    
    private void loadRaceDetails() {
        final FileHandle fh = Gdx.files.internal("data/char-building/appearances/races.json");
        final DetailsCollection<RaceDetails> raceCollection = DetailsLoader.fromJson(RaceDetails.class, fh);
        raceDetails.clear();
        if (raceCollection != null) {
            final Collection<RaceDetails> raceDetailsCollectionItems = raceCollection.getItems();
            if (raceDetailsCollectionItems != null) {
                raceDetails.addAll(raceDetailsCollectionItems);
            }
        }
    }
    
    private void loadSkinColorDetails() {
        final FileHandle fh = Gdx.files.internal("data/char-building/appearances/skinColors.json");
        final DetailsCollection<SkinColorDetails> skinColorCollection = DetailsLoader.fromJson(SkinColorDetails.class, fh);
        skinColorDetails.clear();
        if (skinColorCollection != null) {
            final Collection<SkinColorDetails> skinColorDetailsCollectionItems = skinColorCollection.getItems();
            if (skinColorDetailsCollectionItems != null) {
                skinColorDetails.addAll(skinColorDetailsCollectionItems);
            }
        }
    }
    
    private void buildSkinColorAssociation() {
        for (final RaceDetails race : raceDetails) {
            final List<SkinColorDetails> skinColors = new ArrayList<SkinColorDetails>();
            for (final SkinColorDetails scDetails : skinColorDetails) {
                if (scDetails.getRace().equalsIgnoreCase(race.getName())) {
                    skinColors.add(scDetails);
                }
            }
            skinColorsByRace.put(race, skinColors);
        }
    }
    
    private void loadNoseDetails() {
        final FileHandle fh = Gdx.files.internal("data/char-building/appearances/noses.json");
        final DetailsCollection<NoseDetails> noseCollection = DetailsLoader.fromJson(NoseDetails.class, fh);
        noseDetails.clear();
        if (noseCollection != null) {
            final Collection<NoseDetails> noseDetailsCollectionItems = noseCollection.getItems();
            if (noseDetailsCollectionItems != null) {
                noseDetails.addAll(noseDetailsCollectionItems);
            }
        }
    }
    
    private void loadShoeColorDetails() {
        final FileHandle fh = Gdx.files.internal("data/char-building/appearances/shoeColors.json");
        final DetailsCollection<ShoeColorDetails> shoeColorCollection = DetailsLoader.fromJson(ShoeColorDetails.class, fh);
        shoeColorDetails.clear();
        if (shoeColorCollection != null) {
            final Collection<ShoeColorDetails> noseDetailsCollectionItems = shoeColorCollection.getItems();
            if (noseDetailsCollectionItems != null) {
                shoeColorDetails.addAll(noseDetailsCollectionItems);
            }
        }
    }

    private static <T extends Enum> Collection<T> enumToCollection(Class<T> clazz) {
        final T[] vals = clazz.getEnumConstants();
        final Collection<T> res = new ArrayList<T>(vals.length);
        for (final T v : vals) {
            res.add(v);
        }

        return res;
    }

    private boolean isLoadablePath(final String path) {
        return ((StringUtils.isNotBlank(path)) && !(loadedPaths.contains(path)));
    }
    
    private SkinColorDetails normalizeColorForRace(final SkinColorDetails skinColor, final RaceDetails race) {
        if (skinColor.getRace().equalsIgnoreCase(race.getName())) {
            return skinColor;
        }
        
        final List<SkinColorDetails> raceColors = skinColorsByRace.get(race);
        return raceColors.get(0);
    }

    private final AssetManager assets;
    private final Set<String> loadedPaths = new HashSet<String>();
    private final Collection<PlayerClass> playerClasses = new ArrayList<PlayerClass>();
    private final Collection<BackStory> backStories = new ArrayList<BackStory>();
    private final Collection<EyeDetails> eyeDetails = new ArrayList<EyeDetails>();
    private final Collection<EarDetails> earDetails = new ArrayList<EarDetails>();
    private final Collection<RaceDetails> raceDetails = new ArrayList<RaceDetails>();
    private final Collection<SkinColorDetails> skinColorDetails = new ArrayList<SkinColorDetails>();
    private final Collection<NoseDetails> noseDetails = new ArrayList<NoseDetails>();
    private final Collection<ShoeColorDetails> shoeColorDetails = new ArrayList<ShoeColorDetails>();
    
    private final Map<RaceDetails, List<SkinColorDetails>> skinColorsByRace = new HashMap<RaceDetails, List<SkinColorDetails>>();
}
