/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.ui.tools.characterBuilder;

import com.badlogic.gdx.files.FileHandle;
import com.github.skittishSloth.openSkies.engine.player.details.CharacterData;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Json;
import com.github.skittishSloth.openSkies.OpenSkies;
import com.github.skittishSloth.openSkies.engine.common.GdxUtils;
import com.github.skittishSloth.openSkies.engine.player.info.BackStory;
import com.github.skittishSloth.openSkies.engine.player.info.PlayerClass;
import com.github.skittishSloth.openSkies.engine.player.details.CharacterAppearanceData;
import com.github.skittishSloth.openSkies.engine.player.details.CharacterClothingData;
import com.github.skittishSloth.openSkies.engine.ui.tools.characterBuilder.appearance.CharacterAppearanceScreen;
import com.github.skittishSloth.openSkies.engine.player.details.CharacterInformationData;
import com.github.skittishSloth.openSkies.engine.player.details.DetailsLoader;
import com.github.skittishSloth.openSkies.engine.ui.tools.characterBuilder.information.CharacterInformationScreen;
import java.util.Collection;

/**
 *
 * @author mcory01
 */
public class CharacterBuilderScreenManager implements Disposable {
    
    public CharacterBuilderScreenManager(final OpenSkies game) {
        this.game = game;
    }
    
    public void startCharacterBuilder() {
        if (cbAssets == null) {
            cbAssets = new CharacterBuilderAssets();
        }
        
        if (loadScreen == null) {
            loadScreen = new CharacterBuilderLoadScreen(game, this, cbAssets);
        }
        game.setScreen(loadScreen);
    }
    
    public void loadingScreenFinished() {
        if (appearanceScreen == null) {
            appearanceScreen = new CharacterAppearanceScreen(game, this, cbAssets);
        }
        game.setScreen(appearanceScreen);
    }
    
    public void appearanceScreenNext() {
        if (informationScreen == null) {
            informationScreen = new CharacterInformationScreen(game, this);
        }
        game.setScreen(informationScreen);
    }
    
    public void appearanceScreenCancel() {
        
    }
    
    public void informationScreenNext() {
        final CharacterAppearanceData appearanceData = appearanceScreen.getCharacterAppearanceData();
        final CharacterClothingData clothing = appearanceScreen.getCharacterClothingData();
        final CharacterInformationData infoData = informationScreen.getCharacterInformationData();
        
        buildCharacter(appearanceData, clothing, infoData);
    };
    
    public void informationScreenCancel() {
        
    }
    
    public Collection<PlayerClass> getAvailablePlayerClasses() {
        return cbAssets.getPlayerClasses();
    }
    
    public Collection<BackStory> getAvailableBackStories() {
        return cbAssets.getBackStories();
    }
    
    private void buildCharacter(final CharacterAppearanceData appearanceData, final CharacterClothingData clothing, final CharacterInformationData infoData) {
        final CharacterData data = new CharacterData(appearanceData, clothing, infoData);
        final FileHandle outputFile = new FileHandle("/Users/mcory01/character.json");
        DetailsLoader.saveJson(data, outputFile);
        final Json json = new Json();
        final String output = json.prettyPrint(data);
        System.err.println(output);
    }
    
    @Override
    public void dispose() {
        GdxUtils.safeDispose(cbAssets);
        GdxUtils.safeScreenDispose(loadScreen);
        GdxUtils.safeScreenDispose(appearanceScreen);
    }
    
    private final OpenSkies game;
    private CharacterBuilderAssets cbAssets;
    private CharacterBuilderLoadScreen loadScreen;
    private CharacterAppearanceScreen appearanceScreen;
    private CharacterInformationScreen informationScreen;
}
