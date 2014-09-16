/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.skittishSloth.openSkies.engine.ui.characterBuilder;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.github.skittishSloth.openSkies.OpenSkies;
import com.github.skittishSloth.openSkies.engine.common.GdxUtils;
import com.github.skittishSloth.openSkies.engine.ui.AbstractScreen;

/**
 *
 * @author mcory01
 */
public class CharacterBuildScreen extends AbstractScreen {
    public CharacterBuildScreen(final OpenSkies game, final CharacterBuilderAssets assets) {
        super(game);
        
        final Skin skin = getSkin();
        this.assets = assets;
        window = new Window("Character Builder", skin);
        window.setFillParent(true);
        window.setTitleAlignment(Align.left);
        window.defaults().bottom().left();
        cbt = new CharacterBuildTable(skin, assets);
        cbt.setFillParent(true);
        getStage().addActor(cbt);
        
        Gdx.input.setInputProcessor(getStage());
    }

    @Override
    public void dispose() {
        super.dispose();
        GdxUtils.safeDispose(cbt);
        GdxUtils.safeDispose(assets);
    }
    
    private final CharacterBuilderAssets assets;
    private final CharacterBuildTable cbt;
    private final Window window;
}
