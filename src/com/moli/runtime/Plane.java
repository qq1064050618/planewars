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

public class Plane extends BaseSprite implements Drawable, Moveable {
    private Image image;
    private boolean up, right, down, left;
    private boolean fire;

    private final int SPEED=FrameConstant.GAME_SPEED*3;

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

        g.drawImage(image, getX(), getY(), image.getWidth(null), image.getHeight(null), null);
        move();
    }

    public void fire(){
        if (fire){
            GameFrame gameFrame= DataStore.get("gameFrame");
            gameFrame.bulletsList.add(new Bullet(
                    getX()+(image.getWidth(null)/2)-(ImageMap.get("mb01").getWidth(null)/2),
                    getY()- ImageMap.get("mb01").getHeight(null),
                    ImageMap.get("mb01")));
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
            setY(getY() +SPEED);
        }
        if (left) {
            setX(getX() -SPEED);
        }
        borderTesting();
    }
public void borderTesting(){
        if (getX()<0){
            setX(0);
        }
    if (getX()>FrameConstant.FRAME_WIDTH-image.getWidth(null)){
        setX(FrameConstant.FRAME_WIDTH-image.getWidth(null));
    }
    if (getY()<50){
        setY(50);
    }
    if (getY()>FrameConstant.FRAME_HEIGHT-image.getHeight(null)-20){
        setY(FrameConstant.FRAME_HEIGHT-image.getHeight(null)-20);
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
    }
    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(),getY(),image.getWidth(null),image.getHeight(null));
    }
}
