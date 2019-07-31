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

public class EnemyPlane extends BaseSprite implements Moveable, Drawable {
    private Image image;
    private final int SPEED = FrameConstant.GAME_SPEED * 3;
    Random random=new Random();


    public EnemyPlane() {
        this(0, 0, ImageMap.get("ep01"));
    }
    public EnemyPlane(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {
            g.drawImage(image, getX(), getY(), image.getWidth(null), image.getHeight(null), null);
            move();
            fire();
      //  newEnemyPlane();
    }
public void fire() {

    GameFrame gameFrame = DataStore.get("gameFrame");
    if (random.nextInt(1000) > 980) {
        gameFrame.enemyBullets.add(new EnemyBullet(
                getX() + (image.getWidth(null) / 2) - (ImageMap.get("eb01").getWidth(null) / 2),
                getY() + ImageMap.get("eb01").getHeight(null),
                ImageMap.get("eb01")));
    }
}
    @Override
    public void move() {
        setY(getY() + SPEED);
        borderTesting();
    }

    public void borderTesting() {
        if (getY() > FrameConstant.FRAME_HEIGHT) {
            GameFrame gameFrame = DataStore.get("gameFrame");
            gameFrame.enemyPlane.remove(this);
        }
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(),getY(),image.getWidth(null),image.getHeight(null));
    }


/*    public void newEnemyPlane(){
        GameFrame gameFrame = new GameFrame();
            if (random.nextInt(800) < 800) {
                gameFrame.enemyPlane.add(new EnemyPlane(random.nextInt(800), 50, ImageMap.get("ep01")));
                System.out.println(gameFrame.enemyPlane.size());
            }
    }*/
}
