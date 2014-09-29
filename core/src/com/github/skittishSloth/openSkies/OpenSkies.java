package com.github.skittishSloth.openSkies;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.files.FileHandle;
import com.github.skittishSloth.openSkies.engine.common.GdxUtils;
import com.github.skittishSloth.openSkies.engine.maps.local.LocalScreen;
import com.github.skittishSloth.openSkies.engine.menu.MainMenuScreen;
import com.github.skittishSloth.openSkies.engine.player.details.CharacterData;
import com.github.skittishSloth.openSkies.engine.player.details.DetailsLoader;

public final class OpenSkies extends Game {
    
    private MainMenuScreen mainMenu;
    private LocalScreen localScreen;
    
    public OpenSkies() {
        
    }
    
    @Override
    public void create() {
        
        final FileHandle outputFile = new FileHandle("/Users/mcory01/character.json");
        this.characterData = DetailsLoader.loadCharacterData(outputFile);
        
        localScreen = new LocalScreen(this);
        setScreen(localScreen);
        
//        mainMenu = new MainMenuScreen(this);
//        setScreen(mainMenu);
    }
    
    public CharacterData getCurrentCharacter() {
        return characterData;
    }

    @Override
    public void dispose() {
        
        GdxUtils.safeScreenDispose(mainMenu);
        GdxUtils.safeScreenDispose(localScreen);
        super.dispose();
    }
    
    private CharacterData characterData;
}
