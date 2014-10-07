/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author mcory01
 */
public abstract class BaseGameAssets implements Disposable {
    
    protected BaseGameAssets() {
        assets = new AssetManager();
        loadedPaths = new HashSet<String>();
        log = LoggerFactory.getLogger(getClass());
    }
    
    public final boolean isFinishedLoading() {
        return assets.update();
    }

    public final int getLoadedAssetsCount() {
        return assets.getLoadedAssets();
    }

    public final int getQueuedAssetsCount() {
        return assets.getQueuedAssets();
    }

    public final float getProgress() {
        return assets.getProgress();
    }

    @Override
    public void dispose() {
        assets.dispose();
    }
    
    protected final AssetManager getAssets() {
        return assets;
    }
    
    protected final void loadPngFilesInPath(final String path) {
        final FileHandle dir = Gdx.files.internal(path);
        for (final FileHandle file : dir.list()) {
            final String filePath = file.path();
            if (loadedPaths.contains(filePath)) {
                continue;
            }

            if (file.extension().equalsIgnoreCase("png")) {
                assets.load(file.path(), Texture.class);
                loadedPaths.add(file.path());
            } else if (file.isDirectory() && !(file.name().endsWith("_ignore"))) {
                loadPngFilesInPath(file.path());
            } else {
                log.warn("Unrecognized file: {}", file.name());
            }
        }
    }
    
    protected final boolean isTexturePathAvailable(final String texturePath) {
        if (StringUtils.isBlank(texturePath)) {
            return false;
        }
        
        if (texturePath.contains("${")) {
            return false;
        }
        
        if (!assets.isLoaded(texturePath)) {
            assets.load(texturePath, Texture.class);
            assets.finishLoading();
            if (!assets.isLoaded(texturePath)) {
                return false;
            }
        }
        
        return true;
    }
    
    private final AssetManager assets;
    private final Set<String> loadedPaths;
    protected final Logger log;
}
