/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.maps.areas;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 *
 * @author mcory01
 */
public class AreaDetailsLoaderTests {
    
    @Test
    public void AreaDetailsShouldBeCorrectlyLoaded() throws URISyntaxException {
        final URL fileUrl = getClass().getResource("/json/island.json");
        final File file = new File(fileUrl.toURI());
        final FileHandle fh = new FileHandle(file);
        
        final AreaDetails ad = AreaDetailsLoader.fromJson(fh);
        
        assertNotNull(ad);
        
        
        final String areaName = "Island";
        final String areaStartingMap = "island";
        
        final String mapName = "island";
        final String mapRelativePath ="island/island.tmx";
        
        final String npcId = "arthur";
        final Vector2 npcLocation = new Vector2(54f, 54f);
        final MapDetailNPCEntry npc = new MapDetailNPCEntry();
        npc.setId(npcId);
        npc.setLocation(npcLocation);
        final Collection<MapDetailNPCEntry> npcs = new ArrayList<>();
        npcs.add(npc);
        
        final MapDetails islandMap = new MapDetails();
        islandMap.setName(mapName);
        islandMap.setRelativePath(mapRelativePath);
        islandMap.setNpcs(npcs);
        
        final Collection<MapDetails> areaMaps = new ArrayList<>();
        areaMaps.add(islandMap);
        
        final String tentName = "arthurs_tent";
        final String tentRelativePath = "island/arthurs_tent.tmx";
        
        final MapDetails tentMap = new MapDetails();
        tentMap.setName(tentName);
        tentMap.setRelativePath(tentRelativePath);
        areaMaps.add(tentMap);
        
        final AreaDetails exp = new AreaDetails();
        exp.setName(areaName);
        exp.setStartingMap(areaStartingMap);
        exp.setMaps(areaMaps);
        
        assertEquals(exp, ad);
    }
}
