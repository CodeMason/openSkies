package com.github.skittishSloth.openSkies;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.github.skittishSloth.openSkies.engine.common.GdxUtils;
import com.github.skittishSloth.openSkies.engine.ui.characterBuilder.CharacterBuildScreen;
import com.github.skittishSloth.openSkies.engine.ui.UIDemoScreen;
import com.github.skittishSloth.openSkies.engine.ui.characterBuilder.CharacterBuilderAssets;
import com.github.skittishSloth.openSkies.engine.ui.characterBuilder.CharacterBuilderLoadScreen;

public final class OpenSkies extends Game {
    
    private UIDemoScreen uiDemo;
    private CharacterBuildScreen buildScreen;
    private CharacterBuilderLoadScreen loadScreen;
    
    public OpenSkies() {
        this.assetManager = new AssetManager();
    }
    
    @Override
    public void create() {
//        uiDemo = new UIDemoScreen();
//        setScreen(uiDemo);
//        buildScreen = new CharacterBuildScreen(this);
//        setScreen(buildScreen);
        
        cbAssets = new CharacterBuilderAssets();
        loadScreen = new CharacterBuilderLoadScreen(this, cbAssets);
        
        setScreen(loadScreen);
    }

    @Override
    public void render() {
        super.render(); //To change body of generated methods, choose Tools | Templates.
        if (loadScreen.isFinished() && printLoadScreenFinished) {
            printLoadScreenFinished = false;
            Gdx.app.log(getClass().getSimpleName(), "Finished loading!");
            if (buildScreen == null) {
                buildScreen = new CharacterBuildScreen(this, cbAssets);
            }
            setScreen(buildScreen);
        }
    }

    @Override
    public void dispose() {
        GdxUtils.safeScreenDispose(uiDemo);
        GdxUtils.safeScreenDispose(buildScreen);
        GdxUtils.safeScreenDispose(loadScreen);
        
        super.dispose();
    }
    
    public AssetManager getAssetManager() {
        return assetManager;
    }
    
    private CharacterBuilderAssets cbAssets;
    private final AssetManager assetManager;
    private boolean printLoadScreenFinished = true;
}
