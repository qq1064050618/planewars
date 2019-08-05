package com.moli.runtime;

import com.moli.base.BaseSprite;
import com.moli.base.Drawable;
import com.moli.base.Moveable;
import com.moli.constant.FrameConstant;
import com.moli.main.GameFrame;
import com.moli.util.DataStore;
import com.moli.util.ImageMap;

import java.awt.*;


public class EnemyBulletPlus extends BaseSprite implements Drawable, Moveable {
    private Image image;
    private final int SPEED = FrameConstant.GAME_SPEED * 4;

    public EnemyBulletPlus() {
        this(0, 0, ImageMap.get("eb02"));
    }

    public EnemyBulletPlus(int x, int y, Image image) {
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
            gameFrame.enemyBulletPluses.remove(this);
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
                gameFrame.enemyBulletPluses.remove(this);
                if (gameFrame.guards.size()!=0){
                    gameFrame.guards.removeAll(gameFrame.guards);
                }else {
                    Plane.type--;}
            }else {
                gameFrame.enemyBulletPluses.remove(this);
                gameFrame.blood--;
            }
            if (gameFrame.blood==0)
            gameFrame.gameOver=true;
        }
    }
}
