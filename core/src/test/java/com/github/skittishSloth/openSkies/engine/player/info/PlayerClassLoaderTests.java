/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.player.info;

import com.badlogic.gdx.files.FileHandle;
import com.github.skittishSloth.openSkies.engine.common.DataCollection;
import com.github.skittishSloth.openSkies.testUtils.DataCollectionLoaderTests;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author mcory01
 */
public class PlayerClassLoaderTests extends DataCollectionLoaderTests<PlayerClass> {

    @Override
    protected String getDataPath() {
        return "/json/playerclasses.json";
    }

    @Override
    protected DataCollection<PlayerClass> loadData(final FileHandle fh) {
        return PlayerClassLoader.playerClassesFromJsonFile(fh);
    }

    @Override
    protected List<PlayerClass> buildExpectedData() {
        final List<PlayerClass> res = new ArrayList<>();
        
        final String mageName = "Mage";
        final String mageDesc = "A brilliant mind who has embraced the world of magic.  Bonus for intelligence, but those robes don't protect against much.";
        
        final ClassModifier mageIntBonus = new ClassModifier(ClassModifier.Type.BONUS, "intelligence", "1");
        final ClassModifier mageDefPenalty = new ClassModifier(ClassModifier.Type.PENALTY, "defense", "1");
        final Collection<ClassModifier> mageMods = new ArrayList<>();
        mageMods.add(mageIntBonus);
        mageMods.add(mageDefPenalty);
        
        final PlayerClass mage = new PlayerClass(mageName, mageDesc, mageMods);
        res.add(mage);
        
        final String engName = "Engineer";
        final String engDesc = "Loves building things.";
        
        final ClassModifier engCraftBonus = new ClassModifier(ClassModifier.Type.BONUS, "crafting", "1");
        final Collection<ClassModifier> engMods = new ArrayList<>();
        engMods.add(engCraftBonus);
        
        final PlayerClass engineer = new PlayerClass(engName, engDesc, engMods);
        res.add(engineer);
        return res;
    }
    
}
