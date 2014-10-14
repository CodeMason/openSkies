/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.common;

import java.util.Collection;
import java.util.List;

/**
 *
 * @author mcory01
 * @param <T> the type of data being stored.
 * 
 */
public interface DataCollection<T> {
    
    List<T> getData();
    
    void setData(Collection<T> data);
    
    int size();
}
