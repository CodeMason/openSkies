/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.testUtils;

import static org.junit.Assert.*;

import org.junit.Test;

import com.badlogic.gdx.files.FileHandle;
import com.github.skittishSloth.openSkies.engine.common.DataCollection;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

/**
 *
 * @author mcory01
 */
public abstract class DataCollectionLoaderTests<T> {

    @Test
    public void ensureCollectionIsProperlyLoaded() throws URISyntaxException {
        final String dataPath = getDataPath();
        final FileHandle fh = getFileHandle(dataPath);

        final DataCollection<T> dataCollection = loadData(fh);
        assertNotNull(dataCollection);
        final List<T> data = dataCollection.getData();
        assertNotNull(data);

        final List<T> expData = buildExpectedData();

        final int size = expData.size();
        assertEquals(size, data.size());

        for (int i = 0; i < size; ++i) {
            assertEquals(expData.get(i), data.get(i));
        }
    }

    ;
    
    
    
    protected final FileHandle getFileHandle(final String path) throws URISyntaxException {
        final URL fileUrl = getClass().getResource(path);
        final File file = new File(fileUrl.toURI());
        final FileHandle fh = new FileHandle(file);

        return fh;
    }

    protected abstract String getDataPath();

    protected abstract DataCollection<T> loadData(FileHandle fh);

    protected abstract List<T> buildExpectedData();
}
