/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.sprites;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectSet;
import com.badlogic.gdx.utils.ScreenUtils;
import com.github.skittishSloth.openSkies.engine.common.Direction;
import com.github.skittishSloth.openSkies.engine.common.GdxUtils;
import java.util.EnumMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author mcory01
 */
public class AtlasSprite implements DirectionalSprite {
    
    private static final Logger log = LoggerFactory.getLogger(AtlasSprite.class);
    
    public static DirectionalSprite createdMergedSprite(final TextureAtlas[] textures) {
        return createdMergedSprite(AnimationState.values(), textures);
    }

    public static DirectionalSprite createdMergedSprite(final AnimationState[] availableAnimations, final TextureAtlas[] atlases) {
        // get the first texture as a baseline.
        final Array<AtlasRegion> regions = atlases[0].getRegions();
        final AtlasRegion baselineRegion = regions.get(0);
        
        final Texture baselineTexture = baselineRegion.getTexture();
        final int width = baselineTexture.getWidth();
        final int height = baselineTexture.getHeight();

        final FrameBuffer fb = new FrameBuffer(Pixmap.Format.RGBA8888, width, height, false);

        final int numTextures = atlases.length;

        //Set the projection matrix for the SpriteBatch.
        final Matrix4 projectionMatrix = new Matrix4();

        //because Pixmap has its origin on the topleft and everything else in LibGDX has the origin left bottom
        //we flip the projection matrix on y and move it -height. So it will end up side up in the .png
        projectionMatrix.setToOrtho2D(0, -height, width, height).scale(1, -1, 1);

        //create our sprite batch
        final SpriteBatch sb = new SpriteBatch();

        //Set the projection matrix on the SpriteBatch
        sb.setProjectionMatrix(projectionMatrix);
        fb.begin();
        sb.begin();
        for (int i = 0; i < numTextures; ++i) {
            final TextureAtlas atlas = atlases[i];
            if (atlas == null) {
                continue;
            }

            final ObjectSet<Texture> textures = atlas.getTextures();
            for (final Texture texture : textures) {
                sb.draw(texture, 0, 0);
            }
            
        }
        sb.end();

        final Pixmap pm = ScreenUtils.getFrameBufferPixmap(0, 0, width, height);

        fb.end();

        final Texture fullTexture = new Texture(pm);
        final TextureAtlas atlas = new TextureAtlas();
        final TextureAtlas firstAtlas = atlases[0];
        final Array<AtlasRegion> firstAtlasRegions = firstAtlas.getRegions();
        for (final AtlasRegion faReg : firstAtlasRegions) {
            atlas.addRegion(faReg.name, fullTexture, faReg.getRegionX(), faReg.getRegionY(), faReg.getRegionWidth(), faReg.getRegionHeight());
        }
        final DirectionalSprite res = new AtlasSprite(atlas, availableAnimations);
        pm.dispose();
        sb.dispose();
        fb.dispose();
        return res;
    }
    
    public AtlasSprite(final TextureAtlas atlas) {
        this(atlas, AnimationState.values());
    }
    
    public AtlasSprite(final TextureAtlas atlas, final AnimationState... availableAnimations) {
        this.atlas = atlas;
        this.availableAnimations = availableAnimations;
        Integer tempWidth = null, tempHeight = null;
        for (final AnimationState mState : this.availableAnimations) {
            final Map<Direction, Animation> directionAnimations = new EnumMap<>(Direction.class);
            for (final Direction dir : Direction.values()) {
                if (mState == AnimationState.IDLE) {
                    final TextureRegion idle = getIdleRegion(dir, atlas);
                    final Animation idleAnimation = new Animation(mState.getFrameDuration(), idle);
                    directionAnimations.put(dir, idleAnimation);
                    if (tempWidth == null) {
                        tempWidth = idle.getRegionWidth();
                    }

                    if (tempHeight == null) {
                        tempHeight = idle.getRegionHeight();
                    }
                } else if (mState != AnimationState.HURT) {
                    final Array<? extends TextureRegion> frames = getFrames(dir, mState, atlas);
                    final Animation anim = new Animation(mState.getFrameDuration(), frames);
                    directionAnimations.put(dir, anim);
                } else {
                    final Array<? extends TextureRegion> frames = getFrames(Direction.UP, mState, atlas);
                    final Animation anim = new Animation(mState.getFrameDuration(), frames);
                    directionAnimations.put(dir, anim);
                }
            }
            this.animations.put(mState, directionAnimations);
        }

        if (tempWidth == null) {
            this.width = 0;
        } else {
            this.width = tempWidth;
        }

        if (tempHeight == null) {
            this.height = 0;
        } else {
            this.height = tempHeight;
        }
    }@Override
    public Direction getCurrentDirection() {
        return currentDirection;
    }

    @Override
    public void setCurrentDirection(final Direction currentDirection) {
        this.currentDirection = currentDirection;
    }

    @Override
    public AnimationState getAnimationState() {
        return animationState;
    }

    @Override
    public void setAnimationState(final AnimationState animationState) {
        this.animationState = animationState;
    }

    @Override
    public boolean isMoving() {
        return moving;
    }

    @Override
    public void setMoving(final boolean moving) {
        if (!moving) {
            if (animationState == AnimationState.WALKING) {
                movementTime = 0;
                for (final AnimationState available : availableAnimations) {
                    if (available == AnimationState.IDLE) {
                        setAnimationState(AnimationState.IDLE);
                        break;
                    }
                }
            } else if (animationState != AnimationState.HURT) {
                final Map<Direction, Animation> stateAnimations = animations.get(animationState);
                final Animation animation = stateAnimations.get(currentDirection);
                if (animation.isAnimationFinished(movementTime)) {
                    movementTime = 0;
                    for (final AnimationState available : availableAnimations) {
                        if (available == AnimationState.IDLE) {
                            setAnimationState(AnimationState.IDLE);
                            break;
                        }
                    }
                }
            }
        }

        this.moving = moving;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public boolean isMoveable() {
        return animationState.isMoveable();
    }

    @Override
    public boolean isAnimationFinished() {
        final Map<Direction, Animation> stateAnimations = animations.get(animationState);
        final Animation animation = stateAnimations.get(currentDirection);
        return (animation.isAnimationFinished(movementTime));
    }

    @Override
    public TextureRegion getTextureRegion(final float deltaTime) {
        final Map<Direction, Animation> stateAnimations = animations.get(animationState);
        final Animation animation = stateAnimations.get(currentDirection);
        if (animationState != AnimationState.IDLE) {
            movementTime += deltaTime;
        }

        final TextureRegion res = animation.getKeyFrame(movementTime, animationState.isLoopable());
        return res;
    }

    @Override
    public void dispose() {
        GdxUtils.safeDispose(atlas);
    }

    private TextureRegion getIdleRegion(final Direction dir, final TextureAtlas atlas) {
        final String name = dir.getPrefix() + AnimationState.WALKING.getFrameName();
        return atlas.findRegion(name);
    }
    
    private static Array<? extends TextureRegion> getFrames(final Direction direction, final AnimationState animationState, final TextureAtlas atlas) {
        final String name = direction.getPrefix() + animationState.getFrameName();
        final Array<? extends TextureRegion> frames = atlas.findRegions(name);
        return frames;
    }

    private final TextureAtlas atlas;
    private Direction currentDirection = Direction.DOWN;
    private AnimationState animationState = AnimationState.IDLE;

    private final AnimationState[] availableAnimations;

    private boolean moving = false;
    private float movementTime = 0f;
    private final int width, height;

    private final Map<AnimationState, Map<Direction, Animation>> animations = new EnumMap<>(AnimationState.class);
}
