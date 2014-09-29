/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.player;

import com.badlogic.gdx.utils.Disposable;
import com.github.skittishSloth.openSkies.engine.common.Direction;
import com.github.skittishSloth.openSkies.engine.equipment.PlayerEquipment;
import com.github.skittishSloth.openSkies.engine.player.details.CharacterAppearanceData;
import com.github.skittishSloth.openSkies.engine.player.details.CharacterData;
import com.github.skittishSloth.openSkies.engine.sprites.AnimationState;

/**
 *
 * @author mcory01
 */
public class Player implements Disposable {

    public Player(final CharacterData characterData) {
        this.characterData = characterData;
    }
    
    public void setPlayerGraphics(final PlayerGraphics playerGraphics) {
        this.graphics = playerGraphics;
        graphics.setCharacterData(characterData);
    }

    public PositionInformation getPositionInformation() {
        return position;
    }

    public void moveNorth(final float deltaTime) {
        if (!canMove()) {
            return;
        }

        final float curY = position.getY();
        previousPosition.setFrom(position);
        position.setY(curY + getMovementDelta(deltaTime));
        position.setDirection(Direction.UP);
        setCurrentDirection(Direction.UP);
        setMoving(true);
        setAnimationState(AnimationState.WALKING);
    }

    public void moveEast(final float deltaTime) {
        if (!canMove()) {
            return;
        }

        final float curX = position.getX();
        previousPosition.setFrom(position);
        position.setX(curX + getMovementDelta(deltaTime));
        position.setDirection(Direction.RIGHT);
        setCurrentDirection(Direction.RIGHT);
        setMoving(true);
        setAnimationState(AnimationState.WALKING);
    }

    public void moveSouth(final float deltaTime) {
        if (!canMove()) {
            return;
        }

        final float curY = position.getY();
        previousPosition.setFrom(position);
        position.setY(curY - getMovementDelta(deltaTime));
        position.setDirection(Direction.DOWN);
        setCurrentDirection(Direction.DOWN);
        setMoving(true);
        setAnimationState(AnimationState.WALKING);
    }

    public void moveWest(final float deltaTime) {
        if (!canMove()) {
            return;
        }

        final float curX = position.getX();
        previousPosition.setFrom(position);
        position.setX(curX - getMovementDelta(deltaTime));
        position.setDirection(Direction.LEFT);
        setCurrentDirection(Direction.LEFT);
        setMoving(true);
        setAnimationState(AnimationState.WALKING);
    }

    public PositionInformation getPreviousPosition() {
        return previousPosition;
    }

    public float getMovementSpeed() {
        return 200.0f;
    }

    public float getMovementDelta(final float deltaTime) {
        return getMovementSpeed() * deltaTime;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void takeHit(final int amount) {
        currentHealth -= amount;
        if (currentHealth <= 0) {
            currentHealth = 0;
            setAnimationState(AnimationState.HURT);
        }
    }

    public void attack() {
        setAnimationState(AnimationState.SLASHING);
    }
    
    public CharacterAppearanceData getCharacterAppearance() {
        return characterData.getAppearanceData();
    }
    
    public CharacterData getCharacterData() {
        return characterData;
    }
    
    public PlayerGraphics getPlayerGraphics() {
        return graphics;
    }
    
    @Override
    public void dispose() {
        equipment.dispose();
    }

    public float getHealthPercentage() {
        return ((float) currentHealth) / ((float) maxHealth);
    }
    
    public void setMoving(final boolean moving) {
        if (graphics == null) {
            throw new IllegalStateException("Player Graphics have not been initialized.");
        }
        graphics.setMoving(moving);
    }
    
    private void setAnimationState(final AnimationState animationState) {
        if (graphics == null) {
            throw new IllegalStateException("Player Graphics have not been initialized.");
        }
        graphics.setAnimationState(animationState);
    }
    
    private void setCurrentDirection(final Direction direction) {
        if (graphics == null) {
            throw new IllegalStateException("Player Graphics have not been initialized.");
        }
        graphics.setCurrentDirection(direction);
    }
    
    private boolean canMove() {
        if (graphics == null) {
            throw new IllegalStateException("Player Graphics have not been initialized.");
        }
        return graphics.canMove();
    }

    private final int maxHealth = 500;

    private int currentHealth = maxHealth;

    private final PositionInformation position = new PositionInformation();
    private final PositionInformation previousPosition = new PositionInformation();
    private final PlayerEquipment equipment = new PlayerEquipment();
    private final CharacterData characterData;
    private PlayerGraphics graphics;
}
