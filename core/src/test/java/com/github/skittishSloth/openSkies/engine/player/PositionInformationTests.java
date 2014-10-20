/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.player;

import com.github.skittishSloth.openSkies.engine.common.Direction;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 *
 * @author mcory01
 */
public class PositionInformationTests {
    
    @Test
    public void ensureDefaultConstructorInitializesNothing() {
        final PositionInformation pi = new PositionInformation();
        
        assertNull(pi.getDirection());
        assertEquals(0f, pi.getX(), 0f);
        assertEquals(0f, pi.getY(), 0f);
    }
    
    @Test
    public void ensureSettersWorkProperly() {
        final PositionInformation pi = new PositionInformation();
        
        assertNull(pi.getDirection());
        assertEquals(0f, pi.getX(), 0f);
        assertEquals(0f, pi.getY(), 0f);
        
        final Direction direction = Direction.UP;
        final float x = 1f;
        final float y = 2f;
        
        pi.setDirection(direction);
        pi.setX(x);
        pi.setY(y);
        
        assertEquals(direction, pi.getDirection());
        assertEquals(x, pi.getX(), 0f);
        assertEquals(y, pi.getY(), 0f);
    }
    
    @Test
    public void ensureSetFromWorksProperly() {
        final PositionInformation orig = new PositionInformation();
        
        assertNull(orig.getDirection());
        assertEquals(0f, orig.getX(), 0f);
        assertEquals(0f, orig.getY(), 0f);
        
        final Direction origDirection = Direction.UP;
        final float origX = 1f;
        final float origY = 2f;
        
        orig.setDirection(origDirection);
        orig.setX(origX);
        orig.setY(origY);
        
        assertEquals(origDirection, orig.getDirection());
        assertEquals(origX, orig.getX(), 0f);
        assertEquals(origY, orig.getY(), 0f);
        
        final PositionInformation from = new PositionInformation();
        assertNull(from.getDirection());
        assertEquals(0f, from.getX(), 0f);
        assertEquals(0f, from.getY(), 0f);
        
        final Direction fromDirection = Direction.DOWN;
        final float fromX = 5f;
        final float fromY = 6f;
        
        from.setDirection(fromDirection);
        from.setX(fromX);
        from.setY(fromY);
        
        orig.setFrom(from);
        
        assertEquals(fromDirection, orig.getDirection());
        assertEquals(fromX, orig.getX(), 0f);
        assertEquals(fromY, orig.getY(), 0f);
    }
}
