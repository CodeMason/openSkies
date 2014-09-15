package com.github.skittishSloth.openSkies.engine.ui.colorPicker;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.utils.Scaling;

public class ColorPicker extends Table {

    private static final int COLORS_WIDTH = 256;
    private static final int COLORS_HEIGHT = 256;
    private static final int BRIGHTNESS_WIDTH = 32;
    private static final int BRIGHTNESS_HEIGHT = 256;

    private Color selectedColor;

    private Color colorPicked = new Color(1, 1, 1, 1);
    private float luminancePicked = 1.0f;

    private final Image imgColor;

    private Pixmap pmColors;
    private Texture texColors;

    private Pixmap pmBrightness;
    private Texture texBrightness;
    
    private final TextButton updateBtn;

    public ColorPicker(Skin skin) {
        // colors
        pmColors = new Pixmap(256, 256, Format.RGB888);
        for (int x = 0; x < COLORS_WIDTH; x++) {
            for (int y = 0; y < COLORS_HEIGHT; y++) {
                float h = x / (float) COLORS_WIDTH;
                float s = (COLORS_HEIGHT - y) / (float) COLORS_HEIGHT;
                float l = 0.5f;
                Color color = HSLtoRGB(h, s, l);

                pmColors.setColor(color);
                pmColors.drawPixel(x, y);
            }
        }

        texColors = new Texture(pmColors);
        TextureRegion imageColors = new TextureRegion(texColors, COLORS_WIDTH, COLORS_HEIGHT);

        final Image colors = new Image(imageColors);
        colors.setScaling(Scaling.stretch);
        colors.addListener(new DragListener() {
            @Override
            public void drag(InputEvent event, float x, float y, int pointer) {
                y = COLORS_HEIGHT - y;

                Color.rgba8888ToColor(colorPicked, pmColors.getPixel((int) x, (int) y));
                updateSelectedColor((int) x, (int) y);
                updateBrightness(luminancePicked);

                super.drag(event, x, y, pointer);
            }
        });
        colors.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                y = COLORS_HEIGHT - y;

                Color.rgba8888ToColor(colorPicked, pmColors.getPixel((int) x, (int) y));
                updateSelectedColor((int) x, (int) y);
                updateBrightness(luminancePicked);

                return super.touchDown(event, x, y, pointer, button);
            }
        });

        // brightness
        pmBrightness = new Pixmap(32, 256, Format.RGB888);
        texBrightness = new Texture(pmBrightness);

        TextureRegion imageBrightness = new TextureRegion(texBrightness, BRIGHTNESS_WIDTH, BRIGHTNESS_HEIGHT);
        final Image brightness = new Image(imageBrightness);

        brightness.addListener(new DragListener() {
            @Override
            public void drag(InputEvent event, float x, float y, int pointer) {
                y = BRIGHTNESS_HEIGHT - y;

                luminancePicked = (BRIGHTNESS_HEIGHT - y) / (float) BRIGHTNESS_HEIGHT;

                updateSelectedColor();
                updateBrightness(luminancePicked);

                super.drag(event, x, y, pointer);
            }
        });

        brightness.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                luminancePicked = y / (float) BRIGHTNESS_HEIGHT;

                updateSelectedColor();
                updateBrightness(luminancePicked);

                return super.touchDown(event, x, y, pointer, button);
            }
        });

        // color selected
        Pixmap pixPixel = new Pixmap(1, 1, Format.RGBA8888);
        pixPixel.setColor(Color.WHITE);
        pixPixel.fill();
        Texture texPixel = new Texture(pixPixel);
        imgColor = new Image(texPixel);

        add(colors).size(COLORS_WIDTH, COLORS_HEIGHT).top().left().spaceBottom(10);
        add(brightness).size(BRIGHTNESS_WIDTH, BRIGHTNESS_HEIGHT).top().right();

        Table t2 = new Table(skin);
        t2.add(imgColor).size(50, 50).top().left().padRight(10f);
        
        updateBtn = new TextButton("Update Sprite", skin);
        t2.add(updateBtn).center();

        row();
        add(t2).colspan(2);

        pack();

        updateBrightness(0.5f);
        updateSelectedColor();
    }

    private void updateBrightness(float luminance) {
        int y = BRIGHTNESS_HEIGHT - (int) (luminance * BRIGHTNESS_HEIGHT);

        for (int i = 0; i < BRIGHTNESS_HEIGHT; i++) {
            float[] hsl = RGBtoHSL(colorPicked);
            hsl[2] = (BRIGHTNESS_HEIGHT - i) / (float) BRIGHTNESS_HEIGHT;

            pmBrightness.setColor(HSLtoRGB(hsl[0], hsl[1], hsl[2]));

            if (i != y) {
                pmBrightness.drawLine(0, i, BRIGHTNESS_WIDTH, i);
            } else {
                for (int j = 0; j < BRIGHTNESS_WIDTH; j++) {
                    if (j % 2 == 0) {
                        pmBrightness.drawPixel(j, y, 0);
                    } else {
                        pmBrightness.drawPixel(j, y, 0xffffffff);
                    }
                }
            }
        }

        texBrightness.draw(pmBrightness, 0, 0);
    }

    private void updateSelectedColor() {
        float[] hsl = RGBtoHSL(colorPicked);

        selectedColor = HSLtoRGB(hsl[0], hsl[1], luminancePicked);

        imgColor.setColor(selectedColor);
    }

    private void updateSelectedColor(int a, int b) {
        for (int x = 0; x < COLORS_WIDTH; x++) {
            for (int y = 0; y < COLORS_HEIGHT; y++) {
                float h = x / (float) COLORS_WIDTH;
                float s = (COLORS_HEIGHT - y) / (float) COLORS_HEIGHT;
                float l = 0.5f;
                Color color = HSLtoRGB(h, s, l);

                pmColors.setColor(color);
                pmColors.drawPixel(x, y);
            }
        }

        pmColors.setColor(Color.WHITE);
        pmColors.drawPixel(a, b);

        texColors.draw(pmColors, 0, 0);

        updateSelectedColor();
    }
    
    public void addUpdateListener(final EventListener listener) {
        updateBtn.addListener(listener);
    }

    public Color getSelectedColor() {
        return selectedColor;
    }

    public static Color HSLtoRGB(float h, float s, float l) {
        final float q;
        if (l < 0.5) {
            q = l * (1 + s);
        } else {
            q = (l + s) - (s * l);
        }

        float p = 2 * l - q;

        float r = Math.max(0, HueToRGB(p, q, h + (1.0f / 3.0f)));
        float g = Math.max(0, HueToRGB(p, q, h));
        float b = Math.max(0, HueToRGB(p, q, h - (1.0f / 3.0f)));

        return new Color(r, g, b, 1);
    }

    private static float HueToRGB(float p, float q, float h) {
        if (h < 0) {
            h += 1;
        }

        if (h > 1) {
            h -= 1;
        }

        if (6 * h < 1) {
            return p + ((q - p) * 6 * h);
        }

        if (2 * h < 1) {
            return q;
        }

        if (3 * h < 2) {
            return p + ((q - p) * 6 * ((2.0f / 3.0f) - h));
        }

        return p;
    }

    public static float[] RGBtoHSL(Color color) {
// Get RGB values in the range 0 - 1
        float r = color.r;
        float g = color.g;
        float b = color.b;

// Minimum and Maximum RGB values are used in the HSL calculations
        float min = Math.min(r, Math.min(g, b));
        float max = Math.max(r, Math.max(g, b));

// Calculate the Hue
        float h = 0;

        if (max == min) {
            h = 0;
        } else if (max == r) {
            h = ((60 * (g - b) / (max - min)) + 360) % 360;
        } else if (max == g) {
            h = (60 * (b - r) / (max - min)) + 120;
        } else if (max == b) {
            h = (60 * (r - g) / (max - min)) + 240;
        }

// Calculate the Luminance
        float l = (max + min) / 2;

// Calculate the Saturation
        final float s;

        if (max == min) {
            s = 0;
        } else if (l <= .5f) {
            s = (max - min) / (max + min);
        } else {
            s = (max - min) / (2 - max - min);
        }

        return new float[]{h / 360.0f, s, l};
    }

}
