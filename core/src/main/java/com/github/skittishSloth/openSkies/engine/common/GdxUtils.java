/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.skittishSloth.openSkies.engine.common;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.Disposable;

/**
 *
 * @author mcory01
 */
public final class GdxUtils {
    private GdxUtils() { }
    
    public static void safeDispose(final Disposable d) {
        if (d != null) {
            d.dispose();
        }
    }
    
    public static void safeScreenDispose(final Screen s) {
        if (s != null) {
            s.dispose();
        }
    }
}
