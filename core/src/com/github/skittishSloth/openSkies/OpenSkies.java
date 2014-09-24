package com.github.skittishSloth.openSkies;

import com.badlogic.gdx.Game;
import com.github.skittishSloth.openSkies.engine.common.GdxUtils;
import com.github.skittishSloth.openSkies.engine.maps.local.LocalScreen;
import com.github.skittishSloth.openSkies.engine.menu.MainMenuScreen;

public final class OpenSkies extends Game {
    
    private MainMenuScreen mainMenu;
    private LocalScreen localScreen;
    
    public OpenSkies() {
        
    }
    
    @Override
    public void create() {
//        localScreen = new LocalScreen(this);
//        setScreen(localScreen);
        
        mainMenu = new MainMenuScreen(this);
        setScreen(mainMenu);
    }

    @Override
    public void dispose() {
        
        GdxUtils.safeScreenDispose(mainMenu);
        GdxUtils.safeScreenDispose(localScreen);
        super.dispose();
    }
}
