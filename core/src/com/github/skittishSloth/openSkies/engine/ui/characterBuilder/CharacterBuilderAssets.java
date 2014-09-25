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
import com.github.skittishSloth.openSkies.engine.player.details.BaseDetails;
import com.github.skittishSloth.openSkies.engine.player.details.ColoredDetails;
import com.github.skittishSloth.openSkies.engine.player.details.DetailsCollection;
import com.github.skittishSloth.openSkies.engine.player.details.DetailsLoader;
import com.github.skittishSloth.openSkies.engine.player.details.Gender;
import com.github.skittishSloth.openSkies.engine.player.details.ShirtDetails;
import com.github.skittishSloth.openSkies.engine.player.details.SkinColorDetails;
import com.github.skittishSloth.openSkies.engine.player.info.BackStory;
import com.github.skittishSloth.openSkies.engine.player.info.BackStoryCollection;
import com.github.skittishSloth.openSkies.engine.player.info.PlayerClass;
import com.github.skittishSloth.openSkies.engine.player.info.PlayerClassCollection;
import com.github.skittishSloth.openSkies.engine.player.info.InformationLoader;
import com.github.skittishSloth.openSkies.engine.sprites.UniversalDirectionalSprite;
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
        final BaseDetails race = buildData.getRace();
        final Gender gender = buildData.getGender();

        if ((gender == null) || (race == null) || (skinColor == null)) {
            Gdx.app.log(getClass().getSimpleName(), "Build data incomplete.");
            return null;
        }

        final SkinColorDetails actualColor = normalizeColorForRace(skinColor, race);

        final String assetPath = actualColor.getTexturePath(buildData);
        if (!isTexturePathAvailable(assetPath)) {
            return null;
        }
        
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
        final ColoredDetails buildDataEyes = buildData.getEyeDetails();

        if ((gender == null) || (buildDataEyes == null)) {
            Gdx.app.log(getClass().getSimpleName(), "Build data incomplete.");
            return null;
        }

        final String assetPath = buildDataEyes.getTexturePath(buildData);
        
        if (!isTexturePathAvailable(assetPath)) {
            return null;
        }

        final Texture texture = assets.get(assetPath, Texture.class);
        final UniversalDirectionalSprite res = new UniversalDirectionalSprite(texture);
        return res;
    }

    public Collection<BaseDetails> getAvailableNoses() {
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
        final BaseDetails race = buildData.getRace();
        final SkinColorDetails buildDataSkinColor = buildData.getSkinColor();
        final SkinColorDetails skinColor = normalizeColorForRace(buildDataSkinColor, race);
        final BaseDetails buildNoseDetails = buildData.getNose();

        if ((gender == null) || (race == null) || (skinColor == null) || (buildNoseDetails == null)) {
            Gdx.app.log(getClass().getSimpleName(), "Build data incomplete.");
            return null;
        }

        if (!buildNoseDetails.hasTexture()) {
            return null;
        }

        final String texturePath = buildNoseDetails.getTexturePath(buildData);
        if (!isTexturePathAvailable(texturePath)) {
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
        
        final BaseDetails race = buildData.getRace();
        if (race == null) {
            Gdx.app.log(getClass().getSimpleName(), "Build data incomplete.");
            return null;
        }
        
        final Gender gender = buildData.getGender();
        final SkinColorDetails skinColor = buildData.getSkinColor();
        final SkinColorDetails actualSkinColor = normalizeColorForRace(skinColor, race);
        final BaseDetails buildEarDetails = buildData.getEarDetails();
        
        if ((actualSkinColor == null) || (gender == null) || (buildEarDetails == null)) {
            Gdx.app.log(getClass().getSimpleName(), "Build data incomplete.");
            return null;
        }
        
        if (!buildEarDetails.hasTexture()) {
            return null;
        }
        
        final String texturePath = buildEarDetails.getTexturePath(buildData);
        if (!isTexturePathAvailable(texturePath)) {
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
        final BaseDetails style = buildData.getHairStyle();
        final BaseDetails color = buildData.getHairColor();

        if ((gender == null) || (style == null) || (color == null)) {
            Gdx.app.log(getClass().getSimpleName(), "Build data incomplete.");
            return null;
        }

        if (!color.hasTexture()) {
            return null;
        }
        
        final String texturePath = color.getTexturePath(buildData);
        if (!isTexturePathAvailable(texturePath)) {
            return null;
        }
        
        final Texture texture = assets.get(texturePath, Texture.class);
        final UniversalDirectionalSprite res = new UniversalDirectionalSprite(texture);

        return res;
    }

    public UniversalDirectionalSprite getShirtSprite(final CharacterAppearanceData buildData) {
        if (buildData == null) {
            Gdx.app.error(getClass().getSimpleName(), "Character build data was null!");
            return null;
        }

        final Gender gender = buildData.getGender();
        final ShirtDetails shirt = buildData.getShirt();
        final ColoredDetails color = buildData.getShirtColor();

        if ((gender == null) || (shirt == null) || (color == null)) {
            return null;
        }

        if (!color.hasTexture()) {
            return null;
        }
        
        final String texturePath = color.getTexturePath(buildData);
        if (!isTexturePathAvailable(texturePath)) {
            return null;
        }
        
        final Texture texture = assets.get(texturePath, Texture.class);
        final UniversalDirectionalSprite res = new UniversalDirectionalSprite(texture);

        return res;
    }

    public UniversalDirectionalSprite getPantsSprite(final CharacterAppearanceData buildData) {
        if (buildData == null) {
            Gdx.app.error(getClass().getSimpleName(), "Character build data was null!");
            return null;
        }

        final Gender gender = buildData.getGender();
        final ColoredDetails color = buildData.getPantsColor();

        if ((gender == null) || (color == null)) {
            return null;
        }
        
        if (!color.hasTexture()) {
            return null;
        }
        
        final String texturePath = color.getTexturePath(buildData);
        if (!isTexturePathAvailable(texturePath)) {
            return null;
        }
        
        final Texture texture = assets.get(texturePath, Texture.class);
        final UniversalDirectionalSprite res = new UniversalDirectionalSprite(texture);

        return res;
    }

    public UniversalDirectionalSprite getShoeSprite(final CharacterAppearanceData buildData) {
        if (buildData == null) {
            Gdx.app.error(getClass().getSimpleName(), "Character build data was null!");
            return null;
        }

        final Gender gender = buildData.getGender();
        final ColoredDetails color = buildData.getShoeColor();

        if ((gender == null) || (color == null)) {
            return null;
        }
        
        if (!color.hasTexture()) {
            return null;
        }
        
        final String texturePath = color.getTexturePath(buildData);
        if (!isTexturePathAvailable(texturePath)) {
            return null;
        }
        
        final Texture texture = assets.get(texturePath, Texture.class);
        final UniversalDirectionalSprite res = new UniversalDirectionalSprite(texture);

        return res;
    }
    
    public Collection<SkinColorDetails> getAvailableSkinColors(final BaseDetails race) {
        return skinColorsByRace.get(race);
    }
    
    public SkinColorDetails getDefaultSkinColor() {
        return getDefaultDetails(skinColorDetails);
    }
    
    public BaseDetails getDefaultNose() {
        return getDefaultDetails(noseDetails);
    }
    
    public ColoredDetails getDefaultPantsColor() {
        return getDefaultDetails(pantsColorDetails);
    }
    
    public BaseDetails getDefaultRace() {
        return getDefaultDetails(raceDetails);
    }
    
    public ColoredDetails getDefaultShoeColor() {
        return getDefaultDetails(shoeColorDetails);
    }
    
    public ShirtDetails getDefaultShirt(final CharacterAppearanceData buildData) {
        ShirtDetails res = null;
        final Gender gender = buildData.getGender();
        for (final ShirtDetails shirt : shirtDetails) {
            if (shirt.isDefaultDetails(gender)) {
                res = shirt;
                break;
            }
        }
        
        return res;
    }
    
    public ColoredDetails getDefaultShirtColor() {
        return getDefaultDetails(shirtColorDetails);
    }

    public Collection<BaseDetails> getAvailableHairStyles() {
        return hairStyleDetails;
    }

    public Collection<BaseDetails> getAvailableHairColors() {
        return hairColorDetails;
    }
    
    public BaseDetails getHairColorByDisplayName(final String displayName) {
        return getByDisplayName(displayName, hairColorDetails);
    }
    
    public BaseDetails getHairStyleByDisplayName(final String displayName) {
        return getByDisplayName(displayName, hairStyleDetails);
    }

    public Collection<ColoredDetails> getAvailableShirtColors() {
        return shirtColorDetails;
    }

    public Collection<ColoredDetails> getAvailablePantsColors() {
        return pantsColorDetails;
    }

    public Collection<ColoredDetails> getAvailableShoeColors() {
        return shoeColorDetails;
    }

    public Collection<PlayerClass> getPlayerClasses() {
        return playerClasses;
    }

    public Collection<BackStory> getBackStories() {
        return backStories;
    }

    public Collection<ColoredDetails> getEyeDetails() {
        return eyeDetails;
    }

    public Collection<BaseDetails> getEarDetails() {
        return earDetails;
    }
    
    public Collection<BaseDetails> getRaceDetails() {
        return raceDetails;
    }
    
    public Collection<SkinColorDetails> getSkinColorDetails() {
        return skinColorDetails;
    }
    
    public SkinColorDetails normalizeColorForRace(final SkinColorDetails skinColor, final BaseDetails race) {
        if (skinColor.getRace().equalsIgnoreCase(race.getName())) {
            return skinColor;
        }
        
        final List<SkinColorDetails> raceColors = skinColorsByRace.get(race);
        return raceColors.get(0);
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
        
        loadPantsColorDetails();
        
        loadShirtDetails();
        
        loadShirtColorDetails();
        
        loadHairColorDetails();
        
        loadHairStyleDetails();
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
        loadAppearanceDetails("eyes.json", ColoredDetails.class, eyeDetails);
    }

    private void loadEarDetails() {
        loadAppearanceDetails("ears.json", BaseDetails.class, earDetails);
    }
    
    private void loadRaceDetails() {
        loadAppearanceDetails("races.json", BaseDetails.class, raceDetails);
    }
    
    private void loadSkinColorDetails() {
        loadAppearanceDetails("skinColors.json", SkinColorDetails.class, skinColorDetails);
    }
    
    private void buildSkinColorAssociation() {
        for (final BaseDetails race : raceDetails) {
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
        loadAppearanceDetails("noses.json", BaseDetails.class, noseDetails);
    }
    
    private void loadShoeColorDetails() {
        loadAppearanceDetails("shoeColors.json", ColoredDetails.class, shoeColorDetails);
    }
    
    private void loadPantsColorDetails() {
        loadAppearanceDetails("pantsColors.json", ColoredDetails.class, pantsColorDetails);
    }
    
    private void loadShirtDetails() {
        loadAppearanceDetails("shirts.json", ShirtDetails.class, shirtDetails);
    }
    
    private void loadShirtColorDetails() {
        loadAppearanceDetails("shirtColors.json", ColoredDetails.class, shirtColorDetails);
    }
    
    private void loadHairColorDetails() {
        loadAppearanceDetails("hairColors.json", BaseDetails.class, hairColorDetails);
    }
    
    private void loadHairStyleDetails() {
        loadAppearanceDetails("hairStyles.json", BaseDetails.class, hairStyleDetails);
    }
    
    private boolean isTexturePathAvailable(final String texturePath) {
        if (StringUtils.isBlank(texturePath)) {
            return false;
        }
        
        if (!assets.isLoaded(texturePath)) {
            assets.load(texturePath, Texture.class);
            assets.finishLoading();
            if (!assets.isLoaded(texturePath)) {
                return false;
            }
        }
        
        return true;
    }
    
    private static <T extends BaseDetails> T getDefaultDetails(final Collection<T> detailCollection) {
        T res = null;
        for (final T details : detailCollection) {
            if (details.isDefaultDetails()) {
                res = details;
                break;
            }
        }
        
        return res;
    }
    
    private static <T extends BaseDetails> void loadAppearanceDetails(final String fileName, final Class<T> clazz, final Collection<T> collection) {
        final FileHandle fh = Gdx.files.internal("data/char-building/appearances/" + fileName);
        final DetailsCollection<T> detailsCollection = DetailsLoader.fromJson(clazz, fh);
        collection.clear();
        if (detailsCollection != null) {
            final Collection<T> detailsCollectionItems = detailsCollection.getItems();
            if (detailsCollectionItems != null) {
                collection.addAll(detailsCollectionItems);
            }
        }
    }
    
    private static <T extends BaseDetails> T getByDisplayName(final String displayName, final Collection<T> collection) {
        if (StringUtils.isBlank(displayName)) {
            return null;
        }
        
        T res = null;
        for (final T details : collection) {
            if (StringUtils.equalsIgnoreCase(details.getDisplayName(), displayName)) {
                res = details;
                break;
            }
        }
        
        return res;
    }

    private final AssetManager assets;
    private final Set<String> loadedPaths = new HashSet<String>();
    private final Collection<PlayerClass> playerClasses = new ArrayList<PlayerClass>();
    private final Collection<BackStory> backStories = new ArrayList<BackStory>();
    private final Collection<ColoredDetails> eyeDetails = new ArrayList<ColoredDetails>();
    private final Collection<BaseDetails> earDetails = new ArrayList<BaseDetails>();
    private final Collection<BaseDetails> raceDetails = new ArrayList<BaseDetails>();
    private final Collection<SkinColorDetails> skinColorDetails = new ArrayList<SkinColorDetails>();
    private final Collection<BaseDetails> noseDetails = new ArrayList<BaseDetails>();
    private final Collection<ColoredDetails> shoeColorDetails = new ArrayList<ColoredDetails>();
    private final Collection<ColoredDetails> pantsColorDetails = new ArrayList<ColoredDetails>();
    private final Collection<ShirtDetails> shirtDetails = new ArrayList<ShirtDetails>();
    private final Collection<ColoredDetails> shirtColorDetails = new ArrayList<ColoredDetails>();
    private final Collection<BaseDetails> hairStyleDetails = new ArrayList<BaseDetails>();
    private final Collection<BaseDetails> hairColorDetails = new ArrayList<BaseDetails>();
    
    private final Map<BaseDetails, List<SkinColorDetails>> skinColorsByRace = new HashMap<BaseDetails, List<SkinColorDetails>>();
}
