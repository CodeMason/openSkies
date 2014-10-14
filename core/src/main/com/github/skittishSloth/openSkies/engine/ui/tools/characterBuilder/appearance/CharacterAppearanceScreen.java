/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.skittishSloth.openSkies.engine.ui.tools.characterBuilder.appearance;

import com.github.skittishSloth.openSkies.engine.player.details.CharacterAppearanceData;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.github.skittishSloth.openSkies.OpenSkies;
import com.github.skittishSloth.openSkies.engine.common.GdxUtils;
import com.github.skittishSloth.openSkies.engine.player.details.CharacterClothingData;
import com.github.skittishSloth.openSkies.engine.ui.AbstractScreen;
import com.github.skittishSloth.openSkies.engine.ui.tools.characterBuilder.CharacterBuilderAssets;
import com.github.skittishSloth.openSkies.engine.ui.tools.characterBuilder.CharacterBuilderScreenManager;

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
        return charAppTable.getCharacterAppearance();
    }
    
    public CharacterClothingData getCharacterClothingData() {
        return charAppTable.getCharacterClothing();
    }
    
    private final CharacterBuilderAssets assets;
    private final CharacterBuilderScreenManager manager;
    private final CharacterAppearanceTable charAppTable;
}
