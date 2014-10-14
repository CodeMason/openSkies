/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.player.info;

import com.badlogic.gdx.files.FileHandle;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import org.apache.commons.lang3.StringUtils;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 *
 * @author mcory01
 */
public class InformationLoaderTests {

    /*
     Sample Data:
     {
     name: Vendetta,
     description: "They killed your family.  They killed your dog.  They killed their only hope of survival."
     },
     {
     name: Glory,
     description: "You want your name in the books, and that's all that matters."
     }
     */
    @Test
    public void ensureBackStoryCollectionIsProperlyLoaded() throws URISyntaxException {
        final URL fileUrl = getClass().getResource("/json/backstories.json");
        final File file = new File(fileUrl.toURI());
        final FileHandle fh = new FileHandle(file);

        final BackStoryCollection col = InformationLoader.backStoriesFromJsonFile(fh);
        final int expSize = 2;

        assertNotNull(col);
        assertEquals(expSize, col.size());

        final String name1 = "Vendetta";
        final String description1 = "They killed your family.  They killed your dog.  They killed their only hope of survival.";

        final BackStory bs1 = new BackStory(name1, description1);

        final String name2 = "Glory";
        final String description2 = "You want your name in the books, and that's all that matters.";

        final BackStory bs2 = new BackStory(name2, description2);

        for (final BackStory bs : col.getBackStories()) {
            final String bsName = bs.getName();
            final String bsDesc = bs.getDescription();

            if (StringUtils.equals(bsName, bs1.getName())) {
                assertEquals(bs1.getDescription(), bsDesc);
            } else if (StringUtils.equals(bsName, bs2.getName())) {
                assertEquals(bs2.getDescription(), bsDesc);
            } else {
                fail("Unexpected name: '" + bsName + "', desc: '" + bsDesc + "'");
            }
        }
    }

    /*
     {
     name : Mage,
     description : "A brilliant mind who has embraced the world of magic.  Bonus for intelligence, but those robes don't protect against much.",
     modifiers : [
     {
     type: BONUS,
     area: intelligence,
     amount: 1
     },
     {
     type : PENALTY,
     area : defense,
     amount: 1
     }
     ]
     },
     {
     name : Engineer,
     description : "Loves building things.",
     modifiers : [
     {
     type: BONUS,
     area: crafting,
     amount: 1
     }
     ]
     }
     */
    @Test
    public void ensurePlayerClassesCollectionIsProperlyLoaded() throws URISyntaxException {
        final URL fileUrl = getClass().getResource("/json/playerclasses.json");
        final File file = new File(fileUrl.toURI());
        final FileHandle fh = new FileHandle(file);
        
        final PlayerClassCollection col = InformationLoader.playerClassesFromJsonFile(fh);
        assertNotNull(col);
        
        final int expSize = 2;
        assertEquals(expSize, col.size());
        
        final String mageName = "Mage";
        final String mageDesc = "A brilliant mind who has embraced the world of magic.  Bonus for intelligence, but those robes don't protect against much.";
        
        final ClassModifier mageIntBonus = new ClassModifier(ClassModifier.Type.BONUS, "intelligence", "1");
        final ClassModifier mageDefPenalty = new ClassModifier(ClassModifier.Type.PENALTY, "defense", "1");
        final Collection<ClassModifier> mageMods = new ArrayList<>();
        mageMods.add(mageIntBonus);
        mageMods.add(mageDefPenalty);
        
        final PlayerClass mage = new PlayerClass(mageName, mageDesc, mageMods);
        
        final String engName = "Engineer";
        final String engDesc = "Loves building things.";
        
        final ClassModifier engCraftBonus = new ClassModifier(ClassModifier.Type.BONUS, "crafting", "1");
        final Collection<ClassModifier> engMods = new ArrayList<>();
        engMods.add(engCraftBonus);
        
        final PlayerClass engineer = new PlayerClass(engName, engDesc, engMods);
        
        for (final PlayerClass pc : col.getClasses()) {
            if (!mage.equals(pc) && !engineer.equals(pc)) {
                fail("Player class wasn't expected: " + pc);
            }
        }
    }
}
