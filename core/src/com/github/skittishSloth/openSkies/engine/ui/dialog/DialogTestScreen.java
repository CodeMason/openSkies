/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.ui.dialog;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.github.skittishSloth.openSkies.OpenSkies;
import com.github.skittishSloth.openSkies.engine.ui.AbstractScreen;

/**
 *
 * @author mcory01
 */
public class DialogTestScreen extends AbstractScreen {

    public DialogTestScreen(final OpenSkies game) {
        super(game);
        final Skin skin = getSkin();
        
        dialog = new BaseDialog("Dialog Test", skin);
        
        dialog.setText("This is the dialog's test!");
        
        final DialogOption okay = new DialogOption("Okay", skin);
        final DialogOption cancel = new DialogOption("Cancel", skin);
        
        dialog.setOptions(okay, cancel);
        getStage().addActor(dialog);
    }
    
//    private final DialogWindow window;
    private final BaseDialog dialog;
}
