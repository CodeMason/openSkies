/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.ui.maps;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.github.skittishSloth.openSkies.engine.maps.areas.AreaDetails;
import com.github.skittishSloth.openSkies.engine.maps.areas.MapDetailNPCEntry;
import com.github.skittishSloth.openSkies.engine.maps.areas.MapDetails;
import com.github.skittishSloth.openSkies.engine.maps.npcs.NPCDetails;
import com.github.skittishSloth.openSkies.engine.sprites.AtlasSprite;
import com.github.skittishSloth.openSkies.engine.sprites.DirectionalSprite;
import com.github.skittishSloth.openSkies.engine.ui.BaseGameAssets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author mcory01
 */
public class MapAssets extends BaseGameAssets {
    
    public MapAssets() {
        super();
        getAssets().setLoader(TiledMap.class, new TmxMapLoader());
    }
    
    public void registerArea(final AreaDetails area, final Map<String, NPCDetails> npcDetails) {
        log.debug("Npc Details size: {}", npcDetails.size());
        if (area == null) {
            log.warn("Area was null.");
            return;
        }
        
        for (final MapDetails md : area.getMaps()) {
            getAssets().load("gfx/maps/" + md.getRelativePath(), TiledMap.class);
            final List<MapDetailNPCEntry> npcs = md.getNpcs();
            if (npcs == null) {
                log.warn("No NPCS in map {}", md.getName());
                continue;
            }
            
            for (final MapDetailNPCEntry npc : npcs) {
                final String id = npc.getId();
                final NPCDetails details = npcDetails.get(id);
                if (details == null) {
                    log.warn("No details found for id '{}", id);
                    continue;
                }
                
                final String imgFileName = details.getImageFileName();
                final String charPath = "gfx/characters/" + imgFileName.replace(".png", ".pack");
                
                getAssets().load(charPath, TextureAtlas.class);
                npcPathsById.put(id, charPath);
            }
        }
    }
    
    public TiledMap getMap(final String path) {
        final TiledMap res;
        if (getAssets().isLoaded(path)) {
            res = getAssets().get(path, TiledMap.class);
        } else {
            log.warn("Map at path {} not loaded.", path);
            res = null;
        }
        
        return res;
    }
    
    public DirectionalSprite getNPC(final String id) {
        final String path = npcPathsById.get(id);
        if (path == null) {
            log.warn("No character path registered for id '{}", id);
            return null;
        }
        
        final String atlasPath = path.replace(".png", ".pack");
        
        if (!getAssets().isLoaded(atlasPath)) {
            log.warn("Atlas Path {} has not been loaded.", atlasPath);
            return null;
        }
        
        final TextureAtlas texture = getAssets().get(atlasPath, TextureAtlas.class);
        final DirectionalSprite res = new AtlasSprite(texture);
        return res;
    }
    
    public Texture getItemSprite(final String path) {
        if (!getAssets().isLoaded(path)) {
            log.warn("Path {} has not been loaded.  Trying to force.", path);
            getAssets().load(path, Texture.class);
            getAssets().finishLoading();
            if (!getAssets().isLoaded(path)) {
                log.warn("Path {} still has not been loaded.  I tried.", path);
                return null;
            }
        }
        
        final Texture texture = getAssets().get(path, Texture.class);
        return texture;
    }
    
    private final Map<String, String> npcPathsById = new HashMap<>();

    
}
