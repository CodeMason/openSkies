package com.github.skittishSloth.openSkies;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.github.skittishSloth.openSkies.engine.common.GdxUtils;
import com.github.skittishSloth.openSkies.engine.ui.characterBuilder.CharacterBuildScreen;
import com.github.skittishSloth.openSkies.engine.ui.UIDemoScreen;

public final class OpenSkies extends Game {
    
    private UIDemoScreen uiDemo;
    private CharacterBuildScreen buildScreen;
    
    public OpenSkies() {
        this.assetManager = new AssetManager();
    }
    
    @Override
    public void create() {
//        uiDemo = new UIDemoScreen();
//        setScreen(uiDemo);
        buildScreen = new CharacterBuildScreen(this);
        setScreen(buildScreen);
    }

    @Override
    public void dispose() {
        GdxUtils.safeScreenDispose(uiDemo);
        GdxUtils.safeScreenDispose(buildScreen);
        
        super.dispose();
    }
    
    public AssetManager getAssetManager() {
        return assetManager;
    }
    
    private final AssetManager assetManager;
}
