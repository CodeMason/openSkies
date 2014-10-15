/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.github.skittishSloth.openSkies.engine.common.Direction;
import com.github.skittishSloth.openSkies.engine.player.Player;
import com.github.skittishSloth.openSkies.engine.player.PlayerGraphics;
import com.github.skittishSloth.openSkies.engine.player.details.CharacterData;
import com.github.skittishSloth.openSkies.engine.sprites.AnimationState;
import com.github.skittishSloth.openSkies.engine.sprites.DirectionalSprite;
import com.github.skittishSloth.openSkies.engine.ui.AbstractScreen;

/**
 *
 * @author mcory01
 */
public class AnimatedSpriteScreen extends AbstractScreen {

    public AnimatedSpriteScreen(final OpenSkies game) {
        super(game);

        final CharacterData characterData = game.getCurrentCharacter();
        player = new Player(characterData);
        
        playerAssets = new AssetManager();
        playerGraphics = new PlayerGraphics(playerAssets);
        
        player.setPlayerGraphics(playerGraphics);
        
        sprite = playerGraphics.getMergedSprite();
        sprite.setMoving(true);
        sprite.setAnimationState(AnimationState.WALKING);
        x = (Gdx.graphics.getWidth() / 2) - (sprite.getWidth() / 2);
        y = (Gdx.graphics.getHeight() / 2) - (sprite.getHeight() / 2);
        
        directionLabel = new Label("Direction: ", getSkin());
        
        stateLabel = new Label("Animation State: ", getSkin());
        final VerticalGroup labelGroup = new VerticalGroup();
        labelGroup.addActor(directionLabel);
        labelGroup.addActor(stateLabel);
        labelGroup.setX(0f);
        labelGroup.setY(Gdx.graphics.getHeight());
        labelGroup.align(Align.topLeft);
        getStage().addActor(labelGroup);
    }

    @Override
    public void render(final float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        handleInput();

        final Batch batch = getBatch();

        batch.begin();
        final TextureRegion reg = sprite.getTextureRegion(delta);
        batch.draw(reg, x, y);
        batch.end();
        directionLabel.setText("Direction: " + sprite.getCurrentDirection());
        stateLabel.setText("Animation State: " + sprite.getAnimationState());
        getStage().act();
        getStage().draw();
    }

    private void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            sprite.setCurrentDirection(Direction.LEFT);
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            sprite.setCurrentDirection(Direction.RIGHT);
        } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            sprite.setCurrentDirection(Direction.UP);
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            sprite.setCurrentDirection(Direction.DOWN);
        }
        
        if (Gdx.input.isKeyJustPressed(Input.Keys.I)) {
            sprite.setAnimationState(AnimationState.IDLE);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            sprite.setAnimationState(AnimationState.WALKING);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            sprite.setAnimationState(AnimationState.SLASHING);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.T)) {
            sprite.setAnimationState(AnimationState.THRUSTING);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.C)) {
            sprite.setAnimationState(AnimationState.CASTING);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.H)) {
            sprite.setAnimationState(AnimationState.HURT);
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        sprite.dispose();
        player.dispose();
        playerAssets.dispose();
    }

    private final Player player;
    private final PlayerGraphics playerGraphics;
    private final AssetManager playerAssets;
    private final DirectionalSprite sprite;
    private final float x, y;
    
    private final Label directionLabel, stateLabel;
}
