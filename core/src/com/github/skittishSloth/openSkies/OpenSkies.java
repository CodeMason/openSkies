package com.github.skittishSloth.openSkies;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.github.skittishSloth.openSkies.engine.common.GdxUtils;
import com.github.skittishSloth.openSkies.engine.maps.areas.AreaDetails;
import com.github.skittishSloth.openSkies.engine.maps.areas.AreaDetailsLoader;
import com.github.skittishSloth.openSkies.engine.maps.local.LocalScreen;
import com.github.skittishSloth.openSkies.engine.menu.MainMenuScreen;
import com.github.skittishSloth.openSkies.engine.player.details.CharacterData;
import com.github.skittishSloth.openSkies.engine.player.details.DetailsLoader;
import com.github.skittishSloth.openSkies.engine.ui.maps.MapScreenManager;

public final class OpenSkies extends Game {
    
    private MainMenuScreen mainMenu;
    private LocalScreen localScreen;
    
    private MapScreenManager mapScreenManager;
    
    public OpenSkies() {
        
    }
    
    @Override
    public void create() {
        
        final FileHandle outputFile = new FileHandle("/Users/mcory01/character.json");
        this.characterData = DetailsLoader.loadCharacterData(outputFile);
        
        final FileHandle areaDetailsFile = Gdx.files.internal("data/areas/island.json");
        currentArea = AreaDetailsLoader.fromJson(areaDetailsFile);
        mapScreenManager = new MapScreenManager(this);
        
        localScreen = new LocalScreen(this, mapScreenManager.getMapAssets());        
        mapScreenManager.setAfterLoadingScreen(localScreen);
        mapScreenManager.start();

//        setScreen(localScreen);
        
//        mainMenu = new MainMenuScreen(this);
//        setScreen(mainMenu);
    }
    
    public CharacterData getCurrentCharacter() {
        return characterData;
    }
    
    public AreaDetails getCurrentArea() {
        return currentArea;
    }

    @Override
    public void dispose() {
        
        GdxUtils.safeScreenDispose(mainMenu);
        GdxUtils.safeScreenDispose(localScreen);
        GdxUtils.safeDispose(mapScreenManager);
        super.dispose();
    }
    
    private CharacterData characterData;
    private AreaDetails currentArea;
}
