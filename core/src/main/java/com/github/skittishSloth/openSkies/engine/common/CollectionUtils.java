/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.common;

import java.util.Collection;

/**
 *
 * @author mcory01
 */
public final class CollectionUtils {
    private CollectionUtils() {
        
    }
    
    public static boolean isEmpty(final Collection<?> collection) {
        return ((collection == null) || (collection.isEmpty()));
    }
    
    public static boolean isNotEmpty(final Collection<?> collection) {
        return ((collection != null) && (!(collection.isEmpty())));
    }
}
