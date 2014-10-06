/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.ui.dialog;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

/**
 *
 * @author mcory01
 */
public class DialogWindow extends Window {

    public DialogWindow(final String title, final Skin skin) {
        super(title, skin);
        this.dialog = new BaseDialog(title, skin);
        add(dialog);
        
        setFillParent(true);

//        dialog.invalidate();
        debugAll();
    }
    
    

    public BaseDialog getDialog() {
        return dialog;
    }

    private final BaseDialog dialog;
}
