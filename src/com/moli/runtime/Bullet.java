package com.moli.runtime;

import com.moli.base.BaseSprite;
import com.moli.base.Drawable;
import com.moli.base.Moveable;
import com.moli.constant.FrameConstant;
import com.moli.main.GameFrame;
import com.moli.util.DataStore;
import com.moli.util.ImageMap;

import java.awt.*;
import java.util.List;


public class Bullet extends BaseSprite implements Moveable, Drawable {
    private Image image;
    private int SPEED = FrameConstant.GAME_SPEED * 5;

    public Bullet() {
        this(0, 0, ImageMap.get("mb01"));
    }

    public Bullet(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, getX(), getY(), image.getWidth(null), image.getHeight(null), null);
        move();
        borderTesting();
    }

    @Override
    public void move() {
        setY(getY() - SPEED);
    }
    public void borderTesting(){
        if (getY()<30-image.getHeight(null)){
            GameFrame gameFrame= DataStore.get("gameFrame");
            gameFrame.bulletsList.remove(this);
        }
    }
    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(),getY(),image.getWidth(null),image.getHeight(null));
    }
    public  void  collisionTesting(List<EnemyPlane> enemyPlaneList){
        GameFrame gameFrame=DataStore.get("gameFrame");
        for (EnemyPlane e:enemyPlaneList
             ) {
            if (e.getRectangle().intersects(this.getRectangle())){
                enemyPlaneList.remove(e);
                gameFrame.bulletsList.remove(this);
            }
        }

    }
}
