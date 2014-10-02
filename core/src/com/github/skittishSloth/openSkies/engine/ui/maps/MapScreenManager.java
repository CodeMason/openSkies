/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.ui.maps;

import com.badlogic.gdx.Gdx;
import com.github.skittishSloth.openSkies.OpenSkies;
import com.github.skittishSloth.openSkies.engine.ui.AbstractScreen;
import com.github.skittishSloth.openSkies.engine.ui.BaseGameAssets;
import com.github.skittishSloth.openSkies.engine.ui.BaseScreenManager;

/**
 *
 * @author mcory01
 */
public class MapScreenManager extends BaseScreenManager {

    public MapScreenManager(final OpenSkies game) {
        super(game);
        mapAssets = new MapAssets();
        mapAssets.registerArea(game.getCurrentArea());
    }

    public AbstractScreen getAfterLoadingScreen() {
        return afterLoadingScreen;
    }

    public void setAfterLoadingScreen(final AbstractScreen afterLoadingScreen) {
        this.afterLoadingScreen = afterLoadingScreen;
    }
    
    public MapAssets getMapAssets() {
        return mapAssets;
    }

    @Override
    public void loadingScreenFinished() {
        Gdx.app.log(getClass().getSimpleName(), "Finished loading!");
        if (afterLoadingScreen == null) {
            Gdx.app.log(getClass().getSimpleName(), "Next screen was null :/");
        }
        setGameScreen(afterLoadingScreen);
    }

    @Override
    protected BaseGameAssets getAssets() {
        return mapAssets;
    }
    
    private final MapAssets mapAssets;
    private AbstractScreen afterLoadingScreen;
}
