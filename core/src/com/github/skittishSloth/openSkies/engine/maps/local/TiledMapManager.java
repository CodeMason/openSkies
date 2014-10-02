/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.skittishSloth.openSkies.engine.maps.local;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.Disposable;
import com.github.skittishSloth.openSkies.engine.ui.maps.MapAssets;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author mcory01
 */
public class TiledMapManager implements Disposable {
    
    public TiledMapManager(final MapAssets mapAssets) {
        this.mapAssets = mapAssets;
    }
    
    public void addMap(final String name, final String path) {
        final TiledMap tiledMap = mapAssets.getMap(path);
        final ManagedMap map = new ManagedMap(name, tiledMap);
        maps.put(name, map);
    }
    
    public ManagedMap getMap(final String name) {
        return maps.get(name);
    }

    @Override
    public void dispose() {
        for (final ManagedMap map : maps.values()) {
            map.dispose();
        }
        maps.clear();
    }
    
    private final MapAssets mapAssets;
    private final Map<String, ManagedMap> maps = new HashMap<String, ManagedMap>();
}
