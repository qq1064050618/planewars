package com.moli.runtime;

import com.moli.base.BaseSprite;
import com.moli.base.Drawable;
import com.moli.base.Moveable;
import com.moli.constant.FrameConstant;
import com.moli.main.GameFrame;
import com.moli.util.DataStore;
import com.moli.util.ImageMap;

import java.awt.*;
import java.util.Map;

public class Tools extends BaseSprite implements Drawable, Moveable {
    Image image;
    int type;

    public Tools() {
        this(0, 0, ImageMap.get("blood"),0);
    }

    public Tools(int x, int y, Image image, int type) {
        super(x, y);
        this.image = image;
        this.type = type;
    }

    @Override
    public void draw(Graphics g) {
        move();
        g.drawImage(image, getX(), getY(), image.getWidth(null), image.getHeight(null), null);
    }

    @Override
    public void move() {
        setY(getY() + FrameConstant.GAME_SPEED);
    }

    public void collisionTesting(Plane plane) {
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (plane.getRectangle().intersects(this.getRectangle())) {
            gameFrame.tools.remove(this);
            if (this.type==0&&gameFrame.blood<5){
                gameFrame.blood++;
            }
            if (type==1&&Plane.type<3){
                Plane.type++;
            }
        }

    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), image.getWidth(null), image.getHeight(null));
    }

}
