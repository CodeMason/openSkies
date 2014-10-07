/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.ui.maps;

import com.badlogic.gdx.Gdx;
import com.github.skittishSloth.openSkies.OpenSkies;
import com.github.skittishSloth.openSkies.engine.maps.npcs.NPCDetails;
import com.github.skittishSloth.openSkies.engine.ui.AbstractScreen;
import com.github.skittishSloth.openSkies.engine.ui.BaseGameAssets;
import com.github.skittishSloth.openSkies.engine.ui.BaseScreenManager;
import java.util.Map;

/**
 *
 * @author mcory01
 */
public class MapScreenManager extends BaseScreenManager {

    public MapScreenManager(final OpenSkies game) {
        super(game);
        mapAssets = new MapAssets();
        
        npcDetails = game.getNPCDetails();
        mapAssets.registerArea(game.getCurrentArea(), npcDetails);
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
        log.debug("Finished loading!");
        if (afterLoadingScreen == null) {
            log.warn("Next screen was null :/");
        }
        setGameScreen(afterLoadingScreen);
    }

    @Override
    protected BaseGameAssets getAssets() {
        return mapAssets;
    }
    
    private final MapAssets mapAssets;
    private AbstractScreen afterLoadingScreen;
    private final Map<String, NPCDetails> npcDetails;
}
