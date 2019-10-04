package com.moli.runtime;

import com.moli.base.BaseSprite;
import com.moli.base.Drawable;
import com.moli.base.Moveable;
import com.moli.constant.FrameConstant;
import com.moli.main.GameFrame;
import com.moli.util.DataStore;
import com.moli.util.ImageMap;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Plane extends BaseSprite implements Drawable, Moveable {
    //实现BaseSprite的构造属性方法，实现接口Drawable，Moveable的画和移动的方法


    private Image image;
    private boolean up, right, down, left;
    private boolean fire;
    private final int SPEED = FrameConstant.GAME_SPEED *2;
    private final int WIDTH=ImageMap.get("ep01").getWidth(null);
    private final int WIDTHPLUS=ImageMap.get("ep02").getWidth(null);
   // private final int BOSSWIDTH=ImageMap.get("boss").getWidth(null);
    public final long start=System.currentTimeMillis();
    static int type=1;


    public Plane() {
        this(FrameConstant.FRAME_WIDTH / 2 - ImageMap.get("my01").getWidth(null) / 2,
                FrameConstant.FRAME_HEIGHT - ImageMap.get("my01").getHeight(null) - 20,
                ImageMap.get("my01"));
    }
    public Plane(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {
        long end=System.currentTimeMillis();
        if (end-start<20000){
            newEnemyPlane();
        }else if (end -start<50000){
            newEnemyPlanePlus();
        }else {
            newEnemyPlane();
            newEnemyPlanePlus();
        }
        move();
        g.drawImage(image, getX(), getY(), image.getWidth(null), image.getHeight(null), null);
    }

    public void fire() {
        if (fire) {
            GameFrame gameFrame = DataStore.get("gameFrame");
            if (type==1){
                gameFrame.bulletsList.add(new Bullet(
                        getX() + (image.getWidth(null) / 2) - (ImageMap.get("mb01").getWidth(null) / 2),
                        getY() - ImageMap.get("mb01").getHeight(null),
                        ImageMap.get("mb01")));
            }else if (type==2){
                gameFrame.bulletsList.add(new Bullet(
                        getX() + (image.getWidth(null) / 2) - (ImageMap.get("mb02").getWidth(null) / 2),
                        getY() - ImageMap.get("mb02").getHeight(null),
                        ImageMap.get("mb02")));
            }else if (type==3){
                gameFrame.bulletsList.add(new Bullet(
                        getX() + (image.getWidth(null) / 2) - (ImageMap.get("mb03").getWidth(null) / 2),
                        getY() - ImageMap.get("mb03").getHeight(null),
                        ImageMap.get("mb03")));

            }

        }
    }


    @Override
    public void move() {
        if (up) {
            setY(getY() - SPEED);
        }
        if (right) {
            setX(getX() + SPEED);
        }
        if (down) {
            setY(getY() + SPEED);
        }
        if (left) {
            setX(getX() - SPEED);
        }
        borderTesting();
    }

    public void isKill(){
        GameFrame gameFrame=DataStore.get("gameFrame");
        if (gameFrame.killEnergy == 20) {
            gameFrame.kills.add(new Kill(320, 400, ImageMap.get("kill1"), 1));
            gameFrame.kills.add(new Kill(320, 400, ImageMap.get("kill2"), 2));
            gameFrame.kills.add(new Kill(320, 400, ImageMap.get("kill3"), 3));
            gameFrame.kills.add(new Kill(320, 400, ImageMap.get("kill4"), 4));
            //kills.add(new Kill(320,400,ImageMap.get("kill5"),5));
            gameFrame.kills.add(new Kill(320, 400, ImageMap.get("kill6"), 6));
            gameFrame.killEnergy = 0;
        }
    }
    public void borderTesting() {
        if (getX() < 0) {
            setX(0);
        }
        if (getX() > FrameConstant.FRAME_WIDTH - image.getWidth(null)) {
            setX(FrameConstant.FRAME_WIDTH - image.getWidth(null));
        }
        if (getY() < 50) {
            setY(50);
        }
        if (getY() > FrameConstant.FRAME_HEIGHT - image.getHeight(null) - 20) {
            setY(FrameConstant.FRAME_HEIGHT - image.getHeight(null) - 20);
        }
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            up = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            right = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            down = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            left = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_J) {
            fire = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_K) {
            isKill();
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            up = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            right = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            down = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            left = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_J) {
            fire();
            fire = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_K) {
            GameFrame gameFrame=DataStore.get("gameFrame");
            gameFrame.isIndexKill=true;
        }
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), image.getWidth(null), image.getHeight(null));
    }


    //自动创建敌方飞机
    public void newEnemyPlane(){
        GameFrame gameFrame = DataStore.get("gameFrame");
        Random random=new Random();
        if (random.nextInt(1000) < 3||random.nextInt(1000)>995)
        {
            gameFrame.enemyPlane.add(new EnemyPlane(random.nextInt(800-WIDTH), 50, ImageMap.get("ep01")));
        }
    }
    public void newEnemyPlanePlus(){
        GameFrame gameFrame = DataStore.get("gameFrame");
        Random random=new Random();
        if (random.nextInt(1000) < 3||random.nextInt(1000)>995)
        {
            gameFrame.enemyPlanePluses.add(new EnemyPlanePlus(random.nextInt(800-WIDTHPLUS), 50, ImageMap.get("ep02")));
        }
    }
   /* public void newBoss(){
        Random random=new Random();
           new EnemyPlanePlus(random.nextInt(800-BOSSWIDTH), 50, ImageMap.get("boss"));
    }*/
}
