package com.moli.runtime;

import com.moli.base.BaseSprite;
import com.moli.base.Drawable;
import com.moli.base.Moveable;
import com.moli.util.ImageMap;

import java.awt.*;

public class Explode extends BaseSprite implements Drawable {
    Image image;

    public Explode() {
    }

    public Explode(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, getX(), getY(), image.getWidth(null), image.getHeight(null), null);
    }

}
