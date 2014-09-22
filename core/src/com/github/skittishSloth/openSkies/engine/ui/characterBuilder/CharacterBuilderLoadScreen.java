/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.ui.characterBuilder;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.github.skittishSloth.openSkies.OpenSkies;
import com.github.skittishSloth.openSkies.engine.ui.AbstractScreen;

/**
 *
 * @author mcory01
 */
public class CharacterBuilderLoadScreen extends AbstractScreen {
    
    private static final int BAR_X_OFFSET = 40;
    private static final int BAR_HEIGHT = 20;

    public CharacterBuilderLoadScreen(final OpenSkies game, final CharacterBuilderScreenManager manager, final CharacterBuilderAssets assets) {
        super(game);

        this.assets = assets;
        this.manager = manager;
        final Skin skin = super.getSkin();
        font = skin.getFont("default-font");
        emptyTexture = new Texture("gfx/ui/loading/empty.png");
        empty = new NinePatch(new TextureRegion(emptyTexture, 24, 24), 8, 8, 8, 8);

        fullTexture = new Texture("gfx/ui/loading/full.png");
        full = new NinePatch(new TextureRegion(fullTexture, 24, 24), 8, 8, 8, 8);

        batch = new SpriteBatch();
    }

    public boolean isFinished() {
        return assets.isFinishedLoading();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        if (isFinished()) {
            manager.loadingScreenFinished();
            Gdx.app.log(getName(), "Loading Screen is finished!");
            return;
        }
        
        final float progress = assets.getProgress();
        
        final int screenWidth = Gdx.graphics.getWidth();
        final int screenHeight = Gdx.graphics.getHeight();
        final int y = (screenHeight / 2) - (BAR_HEIGHT / 2);
        final int barWidth = screenWidth - (2 * BAR_X_OFFSET);
        
        batch.begin();
        empty.draw(batch, BAR_X_OFFSET, y, barWidth, BAR_HEIGHT);
        full.draw(batch, BAR_X_OFFSET, y, progress * barWidth, BAR_HEIGHT);
        font.drawMultiLine(batch, (int) (progress * 100) + "% loaded", BAR_X_OFFSET + 40, y, 0, BitmapFont.HAlignment.CENTER);
        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose(); //To change body of generated methods, choose Tools | Templates.
        font.dispose();
        emptyTexture.dispose();
        fullTexture.dispose();
        batch.dispose();
    }

    private final CharacterBuilderScreenManager manager;
    private final CharacterBuilderAssets assets;
    private final SpriteBatch batch;
    private final Texture emptyTexture, fullTexture;
    private final NinePatch empty, full;
    private final BitmapFont font;
}
