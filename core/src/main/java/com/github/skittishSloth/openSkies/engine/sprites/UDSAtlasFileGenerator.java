/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.sprites;

import com.badlogic.gdx.files.FileHandle;
import com.github.skittishSloth.openSkies.engine.common.Direction;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author mcory01
 */
public class UDSAtlasFileGenerator {
    
    private static final int SPRITE_WIDTH = 64;
    private static final int SPRITE_HEIGHT = 64;
    
    private static final String HEADER
            = "${imageFileName}\n"
            + "format: RGBA8888\n"
            + "filter: Linear,Linear\n"
            + "repeat: none\n";

    private static final String BLOCK_CONTENTS
            = "${frameName}\n"
            + "  rotate: false\n"
            + "  xy: ${x}, ${y}\n"
            + "  size: 64, 64\n"
            + "  orig: 0, 0\n"
            + "  offset: 0, 0\n"
            + "  index: ${index}\n";

    private static final String[] PARAMS = new String[]{"${frameName}", "${x}", "${y}", "${index}"};
    
    private static final String ROOT_PATH = "/Users/mcory01/personal-projects/openSkies/core/assets/gfx/char-building";

    public static void main(final String... args) {
        final Direction[] directions = Direction.values();
        final AnimationState[] animations = AnimationState.values();

        final List<String> allFrames = new ArrayList<>();

        for (final Direction d : directions) {
            for (final AnimationState a : animations) {
                final List<String> frames;
                if (a == AnimationState.IDLE) {
                    final String frame = getIdleFrames(d);
                    frames = new ArrayList<>(1);
                    frames.add(frame);
                } else if ((a == AnimationState.HURT) && (d != Direction.UP)) {
                    continue;
                } else {
                    frames = getFrames(d, a);
                }
                
                allFrames.addAll(frames);
            }
        }
        
        System.err.println("Num Frames: " + allFrames.size());
        
        final String baseTemplate = framesToString(allFrames);
        final FileHandle rootFh = new FileHandle(ROOT_PATH);
        final int filesProcessed = processFiles(rootFh, baseTemplate);
        System.err.println("Done; processed " + filesProcessed + " files.");
    }
    
    private static int processFiles(final FileHandle path, final String baseTemplate) {
        int filesProcessed = 0;
        final String pathStr = path.path();
        if (!path.isDirectory()) {
            System.err.println("Path " + pathStr + " was not a directory, so skipping it.");
            return filesProcessed;
        }
        
        final FileHandle[] pngFiles = path.list(new FileFilter() {

            @Override
            public boolean accept(File pathname) {
                return (pathname.isDirectory() || pathname.getName().endsWith(".png"));
            }
        });
        System.err.println("Processing " + pngFiles.length + " entries in path " + pathStr);
        for (final FileHandle pf : pngFiles) {
            if (pf.isDirectory()) {
                filesProcessed += processFiles(pf, baseTemplate);
                continue;
            }
            
            final String fileName = pf.name();
            final String baseName = pf.nameWithoutExtension();
            final String atlas = baseTemplate.replace("${imageFileName}", fileName);
            final FileHandle atlasFile = new FileHandle(pathStr + "/" + baseName + ".pack");
            atlasFile.writeString(atlas, false);
            filesProcessed++;
        }
        
        return filesProcessed;
    }
    
    private static String framesToString(final List<String> frames) {
        final int size = HEADER.length() + (frames.size() * BLOCK_CONTENTS.length());
        final StringBuilder sb = new StringBuilder(size);
        sb.append(HEADER);
        
        for (final String frame : frames) {
            sb.append(frame);
        }
        
        return sb.toString();
    }

    private static List<String> getFrames(final Direction direction, final AnimationState animationState) {
        final int section = animationState.getSectionIndex();
        final int rowInSection = direction.getRowInSection();
        final int numFrames = animationState.getNumFrames();

        final List<String> frames = new ArrayList<>(numFrames);

        final int sectionOffset = section * 4 * SPRITE_HEIGHT;

        final int rowOffset = rowInSection * SPRITE_HEIGHT;

        final int y = sectionOffset + rowOffset;

        for (int i = 0; i < numFrames; ++i) {
            final int x = i * SPRITE_WIDTH;
            final String frameName = direction.getPrefix() + animationState.getFrameName();
            final int index = i + 1;

            final String[] vals = new String[]{frameName, Integer.toString(x), Integer.toString(y), Integer.toString(index)};
            final String frame = StringUtils.replaceEach(BLOCK_CONTENTS, PARAMS, vals);
            frames.add(frame);
//            frames[i] = new TextureRegion(texture, x, y, SPRITE_WIDTH, SPRITE_HEIGHT);
        }

        return frames;
    }

    private static String getIdleFrames(final Direction direction) {
        final List<String> walkingFrames = getFrames(direction, AnimationState.WALKING);
        return walkingFrames.get(0);
    }
}
