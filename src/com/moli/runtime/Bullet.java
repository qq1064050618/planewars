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
    private int SPEED = FrameConstant.GAME_SPEED * 3;

    public Bullet(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {
        move();
        borderTesting();
        g.drawImage(image, getX(), getY(), image.getWidth(null), image.getHeight(null), null);
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
            //intersects 交叉
            if (e.getRectangle().intersects(this.getRectangle())){
                enemyPlaneList.remove(e);
                gameFrame.bulletsList.remove(this);
                gameFrame.score+=2;
            }
            for (int i=1;i<10;i++) {
                gameFrame.explodes.add(new Explode(e.getX(), e.getY(), ImageMap.get("explod"+i)));
                gameFrame.isExplode=true;
            }
            for (int i=1;i<10;i++) {
                gameFrame.explodes.add(new Explode(e.getX(), e.getY(), ImageMap.get("explod"+i)));
                gameFrame.isExplode=true;
            }
        }

    }
    public  void  collisionTestingPlus(List<EnemyPlanePlus> enemyPlanePluses){
        GameFrame gameFrame=DataStore.get("gameFrame");
        for (EnemyPlanePlus e:enemyPlanePluses
        ) {
            //intersects 交叉
            if (e.getRectangle().intersects(this.getRectangle())){
                enemyPlanePluses.remove(e);
                gameFrame.bulletsList.remove(this);
                gameFrame.score+=5;
            }
            for (int i=1;i<10;i++) {
                gameFrame.explodes.add(new Explode(e.getX(), e.getY(), ImageMap.get("explod"+i)));
                gameFrame.isExplode=true;
            }
        }
    }
    public  void  collisionTestingBoss(Boss boss){
        GameFrame gameFrame=DataStore.get("gameFrame");
        if (boss.getRectangle().intersects(this.getRectangle())){
            gameFrame.bossBlood--;
        }
        if (gameFrame.bossBlood<=0){
            gameFrame.score+=50;
        for (int i=1;i<10;i++) {
            gameFrame.explodes.add(new Explode(boss.getX(), boss.getY(), ImageMap.get("explod"+i)));
            gameFrame.isExplode=true;
        }
    }  }
}
