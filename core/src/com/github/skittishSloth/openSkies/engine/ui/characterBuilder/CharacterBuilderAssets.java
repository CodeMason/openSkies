/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.ui.characterBuilder;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;
import com.github.skittishSloth.openSkies.engine.player.details.Ears;
import com.github.skittishSloth.openSkies.engine.player.details.Eye;
import com.github.skittishSloth.openSkies.engine.player.details.Gender;
import com.github.skittishSloth.openSkies.engine.player.details.HairColors;
import com.github.skittishSloth.openSkies.engine.player.details.HairStyles;
import com.github.skittishSloth.openSkies.engine.player.details.Nose;
import com.github.skittishSloth.openSkies.engine.player.details.Race;
import com.github.skittishSloth.openSkies.engine.player.details.SkinColor;
import com.github.skittishSloth.openSkies.engine.sprites.UniversalDirectionalSprite;
import com.github.skittishSloth.openSkies.engine.ui.characterBuilder.partDetails.EyeListDetails;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
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

    public UniversalDirectionalSprite getBodySprite(final CharacterBuildData buildData) {
        if (buildData == null) {
            Gdx.app.error(getClass().getSimpleName(), "Character build data was null!");
            return null;
        }

        final SkinColor skinColor = buildData.getSkinColor();
        final Race race = buildData.getRace();
        final Gender gender = buildData.getGender();

        if ((gender == null) || (race == null) || (skinColor == null)) {
            Gdx.app.log(getClass().getSimpleName(), "Build data incomplete.");
            return null;
        }

        final SkinColor actualColor = normalizeColor(skinColor, race);

        final String colorPath = actualColor.name().toLowerCase() + ".png";
        final String assetPath = BODY_PATH + "/" + gender.name().toLowerCase() + "/" + race.name().toLowerCase() + "/body/" + colorPath;
        final Texture texture = assets.get(assetPath, Texture.class);
        final UniversalDirectionalSprite res = new UniversalDirectionalSprite(texture);
        return res;
    }

    public UniversalDirectionalSprite getEyesSprite(final CharacterBuildData buildData) {
        if (buildData == null) {
            Gdx.app.error(getClass().getSimpleName(), "Character build data was null!");
            return null;
        }

        if ((buildData.getGender() == null) || (buildData.getEyes() == null)) {
            Gdx.app.log(getClass().getSimpleName(), "Build data incomplete.");
            return null;
        }

        final Gender gender = buildData.getGender();
        final Eye eyes = buildData.getEyes();
        final String assetPath = BODY_PATH + "/" + gender.name().toLowerCase() + "/eyes/" + eyes.name().toLowerCase() + ".png";
        final Texture texture = assets.get(assetPath, Texture.class);
        final UniversalDirectionalSprite res = new UniversalDirectionalSprite(texture);

        return res;
    }

    public Collection<Nose> getAvailableNoses() {
        return enumToCollection(Nose.class);
    }

    public UniversalDirectionalSprite getNoseSprite(final CharacterBuildData buildData) {
        if (buildData == null) {
            Gdx.app.error(getClass().getSimpleName(), "Character build data was null!");
            return null;
        }

        if (buildData.getSkinColor() == null) {
            Gdx.app.log(getClass().getSimpleName(), "Build data incomplete.");
            return null;
        }

        final Gender gender = buildData.getGender();
        final Race race = buildData.getRace();
        final SkinColor skinColor = normalizeColor(buildData.getSkinColor(), race);
        final Nose nose = buildData.getNose();

        if ((gender == null) || (race == null) || (skinColor == null) || (nose == null)) {
            Gdx.app.log(getClass().getSimpleName(), "Build data incomplete.");
            return null;
        }

        if (nose == Nose.NONE) {
            return null;
        }

        final String nosePathStr = BODY_PATH + "/" + gender.name().toLowerCase() + "/" + race.name().toLowerCase() + "/nose";
        final String noseFileStr = nose.name().toLowerCase() + "nose_" + skinColor.name().toLowerCase() + ".png";
        final String assetPath = nosePathStr + "/" + noseFileStr;
        final Texture texture = assets.get(assetPath, Texture.class);
        final UniversalDirectionalSprite res = new UniversalDirectionalSprite(texture);

        return res;
    }

    public UniversalDirectionalSprite getEarSprite(final CharacterBuildData buildData) {
        if (buildData == null) {
            Gdx.app.error(getClass().getSimpleName(), "Character build data was null!");
            return null;
        }

        if (buildData.getSkinColor() == null) {
            Gdx.app.log(getClass().getSimpleName(), "Build data incomplete.");
            return null;
        }

        final Gender gender = buildData.getGender();
        final Race race = buildData.getRace();
        final SkinColor skinColor = normalizeColor(buildData.getSkinColor(), race);
        final Ears ear = buildData.getEars();

        if ((gender == null) || (race == null) || (skinColor == null) || (ear == null)) {
            Gdx.app.log(getClass().getSimpleName(), "Build data incomplete.");
            return null;
        }

        if (ear == Ears.DEFAULT) {
            return null;
        }

        final String earPathStr = BODY_PATH + "/" + gender.name().toLowerCase() + "/" + race.name().toLowerCase() + "/ears";
        final String earFileStr = ear.name().toLowerCase() + "ears_" + skinColor.name().toLowerCase() + ".png";
        final String assetPath = earPathStr + "/" + earFileStr;
        final Texture texture = assets.get(assetPath, Texture.class);
        final UniversalDirectionalSprite res = new UniversalDirectionalSprite(texture);

        return res;
    }
    
    public UniversalDirectionalSprite getHairSprite(final CharacterBuildData buildData) {
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

    public Collection<SkinColor> getAvailableColors(final Race race) {
        final SkinColor[] availColors = SkinColor.getAvailableByRace(race);
        final Collection<SkinColor> res = new ArrayList<SkinColor>(availColors.length);
        for (final SkinColor c : availColors) {
            res.add(c);
        }
        return res;
    }

    public Collection<Ears> getAvailableEars() {
        return enumToCollection(Ears.class);
    }

    public Collection<EyeListDetails> getAvailableEyes(final Gender gender) {
        final String eyePathStr = BODY_PATH + "/" + gender.name().toLowerCase() + "/eyes/";
        final FileHandle eyePath = Gdx.files.internal(eyePathStr);
        final FileHandle[] eyeFiles = eyePath.list(new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {
                return (StringUtils.endsWithIgnoreCase(name, ".png"));
            }
        });
        final int numFiles = eyeFiles.length;
        final Collection<EyeListDetails> res = new ArrayList<EyeListDetails>(numFiles);
        for (int i = 0; i < numFiles; ++i) {
            final FileHandle eyeFile = eyeFiles[i];
            if (!assets.isLoaded(eyeFile.path())) {
                Gdx.app.log(getClass().getSimpleName(), "Asset not loaded: " + eyeFile.path());
                continue;
            }
            final Texture eyeTexture = assets.get(eyeFile.path(), Texture.class);
            final UniversalDirectionalSprite sprite = new UniversalDirectionalSprite(eyeTexture);

            final Eye eye = Eye.valueOf(eyeFile.nameWithoutExtension().toUpperCase());
            final EyeListDetails details = new EyeListDetails(i, eye, sprite);
            res.add(details);
        }

        return res;
    }
    
    public Collection<HairStyles> getAvailableHairStyles() {
        return enumToCollection(HairStyles.class);
    }
    
    public Collection<HairColors> getAvailableHairColors(final CharacterBuildData buildData) {
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

    @Override
    public void dispose() {
        assets.dispose();
    }

    private static SkinColor normalizeColor(final SkinColor color, final Race race) {
        if (color.getRace() == race) {
            return color;
        }

        final SkinColor[] availColors = SkinColor.getAvailableByRace(race);
        return availColors[0];
    }

    private static <T extends Enum> Collection<T> enumToCollection(Class<T> clazz) {
        final T[] vals = clazz.getEnumConstants();
        final Collection<T> res = new ArrayList<T>(vals.length);
        for (final T v : vals) {
            res.add(v);
        }

        return res;
    }

    private void registerAssets() {

        final String elfPath = "/elf";
        final String humanPath = "/human";
        final String eyesPath = "/eyes";

        loadFilesInPath(FEMALE_BODY_PATH + elfPath);
        loadFilesInPath(MALE_BODY_PATH + elfPath);

        loadFilesInPath(FEMALE_BODY_PATH + humanPath);
        loadFilesInPath(MALE_BODY_PATH + humanPath);

        loadFilesInPath(FEMALE_BODY_PATH + eyesPath);
        loadFilesInPath(MALE_BODY_PATH + eyesPath);

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

    private final AssetManager assets;
    private final Set<String> loadedPaths = new HashSet<String>();
}