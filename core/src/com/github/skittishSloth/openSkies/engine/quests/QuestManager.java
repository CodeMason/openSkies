/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.quests;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author mcory01
 */
public class QuestManager {
    
    private static final Logger log = LoggerFactory.getLogger(QuestManager.class);
    
    public QuestManager() {
        
    }
    
    public void loadQuests(final String path) {
        final FileHandle file = Gdx.files.internal(path);
        final QuestDetailsCollection qdc = QuestLoader.listFromJson(file);
        
        setAvailableQuests(qdc);
    }
    
    public List<QuestDetails> getAvailableQuests() {
        return availableQuests;
    }
    
    public List<QuestDetails> getAvailableQuestsForMap(final String mapName) {
        final List<QuestDetails> res = new ArrayList<QuestDetails>(availableQuests.size());
        
        for (final QuestDetails qd : availableQuests) {
            final QuestGiver giver = qd.getGiver();
            if (StringUtils.equalsIgnoreCase(giver.getMap(), mapName)) {
                res.add(qd);
            }
        }
        
        return res;
    }
    
    private void setAvailableQuests(final QuestDetailsCollection quests) {
        availableQuests.clear();
        
        if (quests == null) {
            return;
        }
        
        final Collection<QuestDetails> questDetails = quests.getQuests();
        if (questDetails != null) {
            availableQuests.addAll(questDetails);
        }
        
        for (final QuestDetails quest : availableQuests) {
            switch (quest.getType()) {
                case OPTIONAL:
                    optionalQuestsById.put(quest.getId(), quest);
                    break;
                case STORY:
                    storyQuestsById.put(quest.getId(), quest);
                    break;
                default:
                    log.error("Unrecognized quest type: {}.", quest.getType());
            }
        }
    }
    
    private final List<QuestDetails> availableQuests = new ArrayList<QuestDetails>();
    private final Map<Integer, QuestDetails> storyQuestsById = new HashMap<Integer, QuestDetails>();
    private final Map<Integer, QuestDetails> optionalQuestsById = new HashMap<Integer, QuestDetails>();
}
