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

public class Boss extends BaseSprite implements Moveable, Drawable {
    private Image image;
    private final int SPEED = FrameConstant.GAME_SPEED *2;
    public final long start=System.currentTimeMillis();
    int index=0;
    Random random=new Random();

    public Boss() {
        this(0, 0, ImageMap.get("boss"));
    }

    public Boss(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }


    @Override
    public void draw(Graphics g) {
            if (getX() + ImageMap.get("boss").getWidth(null) >= FrameConstant.FRAME_WIDTH) {
                index = 1;
            }
            if (index == 1) {
                move1();
                if (getX() <= 0) {
                    index = 0;
                }
            }
            move();
            fire();
            g.drawImage(image, getX(), getY(), image.getWidth(null), image.getHeight(null), null);
    }
    public void fire() {
        GameFrame gameFrame = DataStore.get("gameFrame");
       if (random.nextInt(1000)>990) {
            gameFrame.bossBullets.add(new BossBullet(
                    getX() + (image.getWidth(null) / 2) - (ImageMap.get("bossb").getWidth(null) / 2),
                    getY() + ImageMap.get("bossb").getHeight(null),
                    ImageMap.get("bossb")));
        }
     if (random.nextInt(1000)<15){
            gameFrame.bossBullets.add(new BossBullet(
                    getX() + (image.getWidth(null) / 3) - (ImageMap.get("bossb").getWidth(null) ),
                    getY() + ImageMap.get("boss").getHeight(null),
                    ImageMap.get("bossb")));
            gameFrame.bossBullets.add(new BossBullet(
                    getX() + (image.getWidth(null) / 3*2) - (ImageMap.get("bossb").getWidth(null) )+20,
                    getY() + ImageMap.get("boss").getHeight(null),
                    ImageMap.get("bossb")));
        }
    }
    @Override
    public void move() {
        setX(getX() + SPEED);
        borderTesting();
    }
    public void move1() {
        setX(getX() - SPEED*2);
        borderTesting();
    }

    public void borderTesting() {
        if (getX()+ImageMap.get("boss").getWidth(null)>=FrameConstant.FRAME_WIDTH) {
          setX(FrameConstant.FRAME_WIDTH-ImageMap.get("boss").getWidth(null));
        }
        if (getX()<=0){
            setX(0);
        }
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(),getY(),image.getWidth(null),image.getHeight(null));
    }

}
