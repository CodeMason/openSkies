/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.maps.local;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * @author mcory01
 */
public class OrthogonalTiledMapRendererWithSprites extends OrthogonalTiledMapRenderer {
    
    private static final Logger log = LoggerFactory.getLogger(OrthogonalTiledMapRendererWithSprites.class);

    public OrthogonalTiledMapRendererWithSprites(final TiledMap map) {
        super(map);
    }
    
    public OrthogonalTiledMapRendererWithSprites(final ManagedMap map) {
        super(map.getMap());
    }
    
    public OrthogonalTiledMapRendererWithSprites(final ManagedMap map, final Batch batch) {
        super(map.getMap(), batch);
    }
    
    @Override
    public void renderObject(final MapObject object) {
        if (object instanceof TextureMapObject) {
            final TextureMapObject textureObj = (TextureMapObject) object;
            if (textureObj.getTextureRegion() != null) {
                spriteBatch.draw(textureObj.getTextureRegion(), textureObj.getX(), textureObj.getY());
            }
        }
    }
    
    public void setMap(final ManagedMap currentMap) {
        log.debug("Setting map: {}", currentMap.getName());
        log.debug("Map null? {}", (currentMap.getMap() == null));
        setMap(currentMap.getMap());
    }
}
