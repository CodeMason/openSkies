/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.ui.maps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.github.skittishSloth.openSkies.engine.maps.areas.AreaDetails;
import com.github.skittishSloth.openSkies.engine.maps.areas.MapDetails;
import com.github.skittishSloth.openSkies.engine.ui.BaseGameAssets;

/**
 *
 * @author mcory01
 */
public class MapAssets extends BaseGameAssets {
    
    public MapAssets() {
        super();
        getAssets().setLoader(TiledMap.class, new TmxMapLoader());
    }
    
    public void registerArea(final AreaDetails area) {
        if (area == null) {
            Gdx.app.log(getClass().getSimpleName(), "Area was null.");
            return;
        }
        
        for (final MapDetails md : area.getMaps()) {
            getAssets().load("gfx/maps/" + md.getRelativePath(), TiledMap.class);
        }
    }
    
    public TiledMap getMap(final String path) {
        final TiledMap res;
        if (getAssets().isLoaded(path)) {
            res = getAssets().get(path, TiledMap.class);
        } else {
            res = null;
        }
        
        return res;
    }
}
