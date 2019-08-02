package com.moli.runtime;

import com.moli.base.BaseSprite;
import com.moli.base.Drawable;
import com.moli.base.Moveable;
import com.moli.constant.FrameConstant;
import com.moli.main.GameFrame;
import com.moli.util.DataStore;
import com.moli.util.ImageMap;

import java.awt.*;
import java.util.Random;

public class EnemyPlanePlus extends BaseSprite implements Moveable, Drawable {

    private Image image;
    private final int SPEED = FrameConstant.GAME_SPEED *3;
    Random random = new Random();


    public EnemyPlanePlus() {
        this(0, 0, ImageMap.get("ep02"));
    }

    public EnemyPlanePlus(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, getX(), getY(), image.getWidth(null), image.getHeight(null), null);
        move();
        move1();
        fire();
    }

    public void fire() {
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (random.nextInt(1000) > 980) {
            gameFrame.enemyBulletPluses.add(new EnemyBulletPlus(
                    getX() + (image.getWidth(null) / 2) - (ImageMap.get("eb02").getWidth(null) / 2),
                    getY() + ImageMap.get("eb02").getHeight(null),
                    ImageMap.get("eb02")));
        }
    }

    @Override
    public void move() {
        setY(getY() + SPEED);
        borderTesting();
    }
    public void move1() {
        setX(getX()+10);
        borderTesting();
    }

    public void borderTesting() {
        if (getY() > FrameConstant.FRAME_HEIGHT||getX()<=0||
                getX()+ImageMap.get("ep02").getWidth(null)>=FrameConstant.FRAME_WIDTH) {
            GameFrame gameFrame = DataStore.get("gameFrame");
            gameFrame.enemyPlanePluses.remove(this);
        }
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), image.getWidth(null), image.getHeight(null));
    }

}

