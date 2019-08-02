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

public class EnemyBullet extends BaseSprite implements Drawable, Moveable {
    private Image image;
    private final int SPEED = FrameConstant.GAME_SPEED *3;

    public EnemyBullet() {
        this(0, 0, ImageMap.get("eb01"));
    }

    public EnemyBullet(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {
        move();
        g.drawImage(image, getX(), getY(), image.getWidth(null), image.getHeight(null), null);
    }

    @Override
    public void move() {
        setY(getY() + SPEED);
        borderTesting();
    }

    public void borderTesting() {
        if (getY() > FrameConstant.FRAME_HEIGHT) {
            GameFrame gameFrame = DataStore.get("gameFrame");
            gameFrame.enemyBullets.remove(this);
        }
    }
    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(),getY(),image.getWidth(null),image.getHeight(null));
    }
    public  void  collisionTesting(Plane plane){
        GameFrame gameFrame=DataStore.get("gameFrame");
            if (plane.getRectangle().intersects(this.getRectangle())){
                if (Plane.type>1){
                    Plane.type--;
                    gameFrame.enemyBullets.remove(this);
                }else {
                    gameFrame.blood--;
                }
                if (gameFrame.blood==0){
                    gameFrame.gameOver=true;
                }
            }
    }
}
