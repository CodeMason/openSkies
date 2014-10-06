package com.github.skittishSloth.openSkies;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.github.skittishSloth.openSkies.engine.common.GdxUtils;
import com.github.skittishSloth.openSkies.engine.maps.areas.AreaDetails;
import com.github.skittishSloth.openSkies.engine.maps.areas.AreaDetailsLoader;
import com.github.skittishSloth.openSkies.engine.maps.local.LocalScreen;
import com.github.skittishSloth.openSkies.engine.maps.npcs.NPCDetails;
import com.github.skittishSloth.openSkies.engine.maps.npcs.NPCDetailsCollection;
import com.github.skittishSloth.openSkies.engine.maps.npcs.NPCDetailsLoader;
import com.github.skittishSloth.openSkies.engine.menu.MainMenuScreen;
import com.github.skittishSloth.openSkies.engine.player.details.CharacterData;
import com.github.skittishSloth.openSkies.engine.player.details.DetailsLoader;
import com.github.skittishSloth.openSkies.engine.ui.dialog.DialogTestScreen;
import com.github.skittishSloth.openSkies.engine.ui.maps.MapScreenManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class OpenSkies extends Game {
    
    private MainMenuScreen mainMenu;
    private LocalScreen localScreen;
    
    private DialogTestScreen dlgTest;
    
    private MapScreenManager mapScreenManager;
    
    public OpenSkies() {
        
    }
    
    @Override
    public void create() {
        
        final FileHandle outputFile = new FileHandle("/Users/mcory01/character.json");
        this.characterData = DetailsLoader.loadCharacterData(outputFile);
        
        final FileHandle areaDetailsFile = Gdx.files.internal("data/areas/island.json");
        currentArea = AreaDetailsLoader.fromJson(areaDetailsFile);
        mapScreenManager = new MapScreenManager(this);
        
        final FileHandle npcDetailsFile = Gdx.files.internal("data/npcs.json");
        final NPCDetailsCollection npcColl = NPCDetailsLoader.fromJson(npcDetailsFile);
        System.err.println("Number of npcs: " + npcColl.size());
        final List<NPCDetails> npcDetailsList = npcColl.getNpcs();
        for (final NPCDetails npc : npcDetailsList) {
            this.npcDetails.put(npc.getId(), npc);
        }
        
//        dlgTest = new DialogTestScreen(this);
//        setScreen(dlgTest);
        
        localScreen = new LocalScreen(this, mapScreenManager.getMapAssets());        
        mapScreenManager.setAfterLoadingScreen(localScreen);
        mapScreenManager.start();

        
//        mainMenu = new MainMenuScreen(this);
//        setScreen(mainMenu);
    }
    
    public CharacterData getCurrentCharacter() {
        return characterData;
    }
    
    public AreaDetails getCurrentArea() {
        return currentArea;
    }
    
    public Map<String, NPCDetails> getNPCDetails() {
        return npcDetails;
    }

    @Override
    public void dispose() {
        
        GdxUtils.safeScreenDispose(mainMenu);
        GdxUtils.safeScreenDispose(localScreen);
        GdxUtils.safeDispose(mapScreenManager);
        
        GdxUtils.safeScreenDispose(dlgTest);
        
        super.dispose();
    }
    
    private CharacterData characterData;
    private AreaDetails currentArea;
    private final Map<String, NPCDetails> npcDetails = new HashMap<String, NPCDetails>();
}
