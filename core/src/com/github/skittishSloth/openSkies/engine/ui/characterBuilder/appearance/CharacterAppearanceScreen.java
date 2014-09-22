/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.skittishSloth.openSkies.engine.ui.characterBuilder.appearance;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.github.skittishSloth.openSkies.OpenSkies;
import com.github.skittishSloth.openSkies.engine.common.GdxUtils;
import com.github.skittishSloth.openSkies.engine.ui.AbstractScreen;
import com.github.skittishSloth.openSkies.engine.ui.characterBuilder.CharacterBuilderAssets;
import com.github.skittishSloth.openSkies.engine.ui.characterBuilder.CharacterBuilderScreenManager;

/**
 *
 * @author mcory01
 */
public class CharacterAppearanceScreen extends AbstractScreen {
    public CharacterAppearanceScreen(final OpenSkies game, final CharacterBuilderScreenManager manager, final CharacterBuilderAssets assets) {
        super(game);
        
        final Skin skin = getSkin();
        this.assets = assets;
        this.manager = manager;
        charAppTable = new CharacterAppearanceTable(skin, this, assets);
        charAppTable.setFillParent(true);
        getStage().addActor(charAppTable);
    }
    
    @Override
    public void dispose() {
        super.dispose();
        GdxUtils.safeDispose(charAppTable);
        GdxUtils.safeDispose(assets);
    }
    
    public void appearanceScreenNext() {
        manager.appearanceScreenNext();
    }
    
    public void appearanceScreenCancel() {
        manager.appearanceScreenCancel();
    }
    
    public CharacterAppearanceData getCharacterAppearanceData() {
        return charAppTable.getCurrentState();
    }
    
    private final CharacterBuilderAssets assets;
    private final CharacterBuilderScreenManager manager;
    private final CharacterAppearanceTable charAppTable;
}
