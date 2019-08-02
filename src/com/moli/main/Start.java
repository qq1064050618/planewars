package com.moli.main;

import com.moli.base.BaseSprite;
import com.moli.base.Drawable;
import com.moli.util.ImageMap;

import java.awt.*;

public class Start extends BaseSprite implements Drawable {
    Image image;

    public Start() {
        this(150,400,ImageMap.get("start"));
    }

    public Start(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, getX(), getY(), image.getWidth(null), image.getHeight(null), null);
    }
}
