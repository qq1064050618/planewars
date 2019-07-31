package com.moli.runtime;

import com.moli.base.BaseSprite;
import com.moli.base.Drawable;
import com.moli.base.Moveable;
import com.moli.constant.FrameConstant;
import com.moli.util.ImageMap;
import com.moli.util.ImageUtil;

import java.awt.*;

public class Background extends BaseSprite implements Drawable, Moveable {

    private Image image;

    public Background() {
        this(0,FrameConstant.FRAME_HEIGHT -ImageMap.get("bg01").getHeight(null), ImageMap.get("bg01"));

    }

    public Background(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void move() {
            setY(getY() +FrameConstant.GAME_SPEED);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, getX(), getY(), image.getWidth(null), image.getHeight(null), null);
        move();
    }
}
