package com.moli.runtime;

import com.moli.base.BaseSprite;
import com.moli.base.Drawable;
import com.moli.base.Moveable;
import com.moli.main.GameFrame;
import com.moli.util.DataStore;

import java.awt.*;
//防护罩
public class Guard extends BaseSprite implements Moveable, Drawable {
    Image image;

    public Guard() {
    }

    public Guard(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {
        GameFrame gameFrame= DataStore.get("gameFrame");
        g.drawImage(image,gameFrame.plane.getX()-15, gameFrame.plane.getY()-55, image.getWidth(null), image.getHeight(null), null);
    }

    @Override
    public void move() {

    }
}
