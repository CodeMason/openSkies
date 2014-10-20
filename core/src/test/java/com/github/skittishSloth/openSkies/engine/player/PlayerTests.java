/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.player;

import com.badlogic.gdx.files.FileHandle;
import com.github.skittishSloth.openSkies.engine.player.details.CharacterData;
import com.github.skittishSloth.openSkies.engine.player.details.DetailsLoader;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import static org.junit.Assert.*;
import org.junit.Before;

import org.junit.Test;

/**
 *
 * @author mcory01
 */
public class PlayerTests {
    
    @Before
    public void setup() throws URISyntaxException {
        final FileHandle ffh = getFileHandle("/json/female-character-data.json");
        characterData = DetailsLoader.loadCharacterData(ffh);
        
        player = new Player(characterData);
    }
    
    @Test
    public void ensureConstructorProperlyInitializesCharacterData() throws URISyntaxException {
        assertEquals(characterData, player.getCharacterData());
    }

    protected FileHandle getFileHandle(final String path) throws URISyntaxException {
        final URL fileUrl = getClass().getResource(path);
        final File file = new File(fileUrl.toURI());
        final FileHandle fh = new FileHandle(file);

        return fh;
    }
    
    private Player player;
    private CharacterData characterData;
}
