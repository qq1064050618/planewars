package com.moli.runtime;

import com.moli.base.BaseSprite;
import com.moli.base.Drawable;
import com.moli.base.Moveable;
import com.moli.constant.FrameConstant;
import com.moli.util.ImageMap;

import java.awt.*;

public class Background extends BaseSprite implements Drawable, Moveable {
//实现BaseSprite的构造属性方法，实现接口Drawable，Moveable的画和移动的方法
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
            if (getY()==0){
                setY(FrameConstant.FRAME_HEIGHT-ImageMap.get("bg01").getHeight(null));
            }
    }

    @Override
    public void draw(Graphics g) {
        move();
        g.drawImage(image, getX(), getY(), image.getWidth(null), image.getHeight(null), null);

    }
}
