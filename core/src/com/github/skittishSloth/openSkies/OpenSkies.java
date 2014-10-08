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
import com.github.skittishSloth.openSkies.engine.player.state.PlayerState;
import com.github.skittishSloth.openSkies.engine.player.state.QuestState;
import com.github.skittishSloth.openSkies.engine.quests.QuestManager;
import com.github.skittishSloth.openSkies.engine.ui.dialog.DialogTestScreen;
import com.github.skittishSloth.openSkies.engine.ui.maps.MapScreenManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class OpenSkies extends Game {

    private static final Logger log = LoggerFactory.getLogger(OpenSkies.class);

    public OpenSkies() {

    }

    @Override
    public void create() {

        final FileHandle outputFile = new FileHandle("/Users/mcory01/character.json");
        this.characterData = DetailsLoader.loadCharacterData(outputFile);

        final FileHandle areaDetailsFile = Gdx.files.internal("data/areas/island.json");
        currentArea = AreaDetailsLoader.fromJson(areaDetailsFile);

        final FileHandle npcDetailsFile = Gdx.files.internal("data/npcs.json");
        final NPCDetailsCollection npcColl = NPCDetailsLoader.fromJson(npcDetailsFile);
        log.debug("Number of npcs: {}", npcColl.size());
        final List<NPCDetails> npcDetailsList = npcColl.getNpcs();
        for (final NPCDetails npc : npcDetailsList) {
            this.npcDetails.put(npc.getId(), npc);
        }
        
        final QuestState questState = new QuestState();
        playerState = new PlayerState();
        playerState.setQuestState(questState);
        
        final String questPath = "data/quests/quests.json";
        questManager.loadQuests(questPath);
        log.debug("Number of quests: {}", questManager.getAvailableQuests().size());

        mapScreenManager = new MapScreenManager(this);

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
    
    public QuestManager getQuestManager() {
        return questManager;
    }
    
    public PlayerState getPlayerState() {
        return playerState;
    }

    @Override
    public void dispose() {

        GdxUtils.safeScreenDispose(mainMenu);
        GdxUtils.safeScreenDispose(localScreen);
        GdxUtils.safeDispose(mapScreenManager);

        GdxUtils.safeScreenDispose(dlgTest);

        super.dispose();
    }


    private MainMenuScreen mainMenu;
    private LocalScreen localScreen;

    private DialogTestScreen dlgTest;

    private MapScreenManager mapScreenManager;
    
    private CharacterData characterData;
    private AreaDetails currentArea;
    private final Map<String, NPCDetails> npcDetails = new HashMap<String, NPCDetails>();
    
    private final QuestManager questManager = new QuestManager();
    
    private PlayerState playerState;
}
