package com.github.skittishSloth.openSkies;

import com.badlogic.gdx.Game;
import com.github.skittishSloth.openSkies.engine.common.GdxUtils;
import com.github.skittishSloth.openSkies.engine.ui.characterBuilder.CharacterBuilderScreenManager;

public final class OpenSkies extends Game {
    
    private CharacterBuilderScreenManager characterBuilder;
    
    public OpenSkies() {
        
    }
    
    @Override
    public void create() {
        characterBuilder = new CharacterBuilderScreenManager(this);
        characterBuilder.startCharacterBuilder();
    }

    @Override
    public void dispose() {
        
        GdxUtils.safeDispose(characterBuilder);
        
        super.dispose();
    }
}
