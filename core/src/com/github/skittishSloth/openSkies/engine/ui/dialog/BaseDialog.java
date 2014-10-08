/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.ui.dialog;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author mcory01
 */
public class BaseDialog extends Window {
    
    public BaseDialog(final String title, final Skin skin) {
        super(title, skin);
        
        setMovable(false);
        setResizable(false);
        
        setTitleAlignment(Align.top | Align.left);
        
        textLabel = new Label("<Uninitialized>", skin);
//        textLabel.setFillParent(true);
        textLabel.setWrap(true);
        
        textScroll = new ScrollPane(textLabel, skin);
        
//        textScroll.setFillParent(true);
        add(textScroll).top().left();
        row();
        
        options = new ArrayList<DialogOption>();
        optionsGroup = new VerticalGroup();
        optionsScroll = new ScrollPane(optionsGroup, skin);
        
        add(optionsScroll).top().left();
        
        final int screenWidth = Gdx.graphics.getWidth();
        final int screenHeight = Gdx.graphics.getHeight();
        
        setWidth(screenWidth * 0.8f);
        setHeight(screenHeight * 0.8f);
        
        setX(screenWidth * 0.1f);
        setY(screenHeight * 0.1f);
    }
    
    @Override
    public void layout() {
        getCell(textScroll).height(getHeight() * 0.75f).width(getWidth() * 0.99f).left();
        getCell(optionsScroll).height(getHeight() * 0.2f).width(getWidth() * 0.99f).left();
        super.layout();
    }
    
    public final void setText(final String text) {
        textLabel.setText(text);
        textLabel.setAlignment(Align.top | Align.left);
    }
    
    public final void setOptions(final Collection<DialogOption> options) {
        this.options.clear();
        
        if (options != null) {
            this.options.addAll(options);
        }
        
        fillOptionsGroup();
    }
    
    public final void setOptions(final DialogOption... options) {
        this.options.clear();
        
        if (options != null) {
            for (final DialogOption option : options) {
                this.options.add(option);
            }
        }
        
        fillOptionsGroup();
    }
    
    protected final ScrollPane getTextScrollPane() {
        return textScroll;
    }
    
    protected final ScrollPane getOptionsScrollPane() {
        return optionsScroll;
    }
    
    private void fillOptionsGroup() {
        optionsGroup.clear();
        for (final DialogOption option : this.options) {
            optionsGroup.addActor(option);
        }
        optionsGroup.align(Align.left);
    }
    
    private final Label textLabel;
    private final ScrollPane textScroll, optionsScroll;
    
    private final List<DialogOption> options;
    private final VerticalGroup optionsGroup;
    
    protected final Logger log = LoggerFactory.getLogger(getClass());
}
