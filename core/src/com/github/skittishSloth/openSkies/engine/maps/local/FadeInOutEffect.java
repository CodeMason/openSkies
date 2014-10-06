/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.maps.local;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 *
 * @author mcory01
 */
public class FadeInOutEffect {

    public FadeInOutEffect(final Stage stage) {
        this.stage = stage;
        img = new Image();
        img.setColor(Color.WHITE);
        img.setSize(stage.getWidth(), stage.getHeight());
    }
    
    public FadeInOutEffect(final Stage stage, final Runnable afterFadeOut, final Runnable afterFadeIn) {
        this(stage);
        
        this.afterFadeOut = afterFadeOut;
        this.afterFadeIn = afterFadeIn;
    }
    
    public void runEffect() {
        runEffect(this.afterFadeOut, this.afterFadeIn);
    }

    public void runEffect(final Runnable afterFadeOut, final Runnable afterFadeIn) {
        img.getActions().clear();
        img.addAction(
                Actions.sequence(
                        Actions.alpha(0f, 0.5f),
                        Actions.run(new Runnable() {
                            @Override
                            public void run() {
                                if (afterFadeOut != null) {
                                    afterFadeOut.run();
                                }
                            }
                        }),
                        Actions.alpha(1f, 0.5f),
                        Actions.run(new Runnable() {
                            @Override
                            public void run() {
                                if (afterFadeIn != null) {
                                    System.err.println("Running after fade in.");
                                    afterFadeIn.run();
                                    System.err.println("After fade in executed.");
                                }
                                stage.getActors().removeValue(img, true);
                                
                                System.err.println("All fade effects finished.");
                            }
                        })));
        stage.addActor(img);
    }

    public void setAfterFadeOut(final Runnable afterFadeOut) {
        this.afterFadeOut = afterFadeOut;
    }

    public void setAfterFadeIn(final Runnable afterFadeIn) {
        this.afterFadeIn = afterFadeIn;
    }

    private Runnable afterFadeOut, afterFadeIn;
    private final Stage stage;
    private final Image img;
}
