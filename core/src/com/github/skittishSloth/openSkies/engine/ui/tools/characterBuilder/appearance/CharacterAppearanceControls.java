/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.skittishSloth.openSkies.engine.ui.tools.characterBuilder.appearance;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.github.skittishSloth.openSkies.engine.ui.tools.characterBuilder.core.CharacterBuilderControls;

/**
 *
 * @author mcory01
 */
public final class CharacterAppearanceControls extends CharacterBuilderControls {

    public CharacterAppearanceControls(final Skin skin, final CharacterAppearanceTable parent) {
        super(skin);
        this.parent = parent;
    }

    @Override
    protected String getAcceptButtonText() {
        return "Next";
    }

    @Override
    protected void handleCancelButtonClicked() {
        parent.handleCancel();
    }

    @Override
    protected void handleAcceptButtonClicked() {
        parent.handleNext();
    }
    
    private final CharacterAppearanceTable parent;
}
