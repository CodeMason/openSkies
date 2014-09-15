/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.skittishSloth.openSkies.engine.equipment;

import com.badlogic.gdx.utils.Disposable;

/**
 *
 * @author mcory01
 */
public class PlayerEquipment implements Disposable {

    @Override
    public void dispose() {
        
    }
    
    private HeadWear headWear;
    private Weapon weapon;
    private boolean updateTextureRequired;
}
