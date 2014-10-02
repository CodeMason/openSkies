/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.ui;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.Disposable;
import com.github.skittishSloth.openSkies.OpenSkies;
import com.github.skittishSloth.openSkies.engine.common.GdxUtils;

/**
 *
 * @author mcory01
 */
public abstract class BaseScreenManager implements Disposable {
    
    protected BaseScreenManager(final OpenSkies game) {
        this.game = game;
    }
    
    public void start() {
        if (gameAssets == null) {
            gameAssets = getAssets();
        }
        
        if (loadScreen == null) {
            loadScreen = new LoadingScreen(game, this, gameAssets);
        }
        
        setGameScreen(loadScreen);
    }

    @Override
    public void dispose() {
        GdxUtils.safeDispose(gameAssets);
        GdxUtils.safeScreenDispose(loadScreen);
    }
    
    protected final void setGameScreen(final Screen screen) {
        game.setScreen(screen);
    }
    
    protected final OpenSkies getGame() {
        return game;
    }
    
    public abstract void loadingScreenFinished();
    
    protected abstract BaseGameAssets getAssets();
    
    private final OpenSkies game;
    private BaseGameAssets gameAssets;
    private LoadingScreen loadScreen;
}
