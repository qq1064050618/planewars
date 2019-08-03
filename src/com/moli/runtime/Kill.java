package com.moli.runtime;

import com.moli.base.BaseSprite;
import com.moli.base.Drawable;
import com.moli.base.Moveable;
import com.moli.main.GameFrame;
import com.moli.util.DataStore;
import com.moli.util.ImageMap;

import java.awt.*;

public class Kill extends BaseSprite implements Drawable, Moveable {
    Image image;
    int type;

    public Kill() {
        this(320,400, ImageMap.get("kill1"),1);
    }

    public Kill(int x, int y, Image image, int type) {
        super(x, y);
        this.image = image;
        this.type = type;
    }

    @Override
    public void draw(Graphics g) {
        move1();
        move2();
        move3();
        move4();
        g.drawImage(image, getX(), getY(), image.getWidth(null), image.getHeight(null), null);
        kill();
    }

    @Override
    public void move() {
        GameFrame gameFrame=DataStore.get("gameFrame");
        gameFrame.kills.add(new Kill(320,400,ImageMap.get("kill1"),1));
        gameFrame.kills.add(new Kill(320,400,ImageMap.get("kill2"),2));
        gameFrame.kills.add(new Kill(320,400,ImageMap.get("kill3"),3));
        gameFrame.kills.add(new Kill(320,400,ImageMap.get("kill4"),4));
        //gameFrame.kills.add(new Kill(320,400,ImageMap.get("kill5"),5));
        gameFrame.kills.add(new Kill(320,400,ImageMap.get("kill6"),6));
    }
    public void  move1(){
       GameFrame gameFrame=DataStore.get("gameFrame");
     Kill kill=gameFrame.kills.get(0);
      int x=320;
      int y=400;
      if (x-kill.getX()<=300){
      kill.setX(kill.getX()-1);
        if (x-kill.getX()<=150){
            kill.setY(kill.getY()+1);
        }else {
            kill.setY(kill.getY()-1);
        }
        if (x-kill.getX()==300){
            gameFrame.indexKill=true;
        }
     }
    }
    public void  move2(){
        GameFrame gameFrame=DataStore.get("gameFrame");
        Kill kill=gameFrame.kills.get(1);
        int x=320;
        int y=400;
        if (kill.getX()-x<=300){
            kill.setX(kill.getX()+1);
            if (kill.getX()-x<=150){
                kill.setY(kill.getY()-1);
            }else {
                kill.setY(kill.getY()+1);
            }
        }
    }
    public void  move3(){
        GameFrame gameFrame=DataStore.get("gameFrame");
        Kill kill=gameFrame.kills.get(2);
        int x=320;
        int y=400;
        if (y-kill.getY()<=300){
            kill.setY(kill.getY()-1);
            if (y-kill.getY()<=150){
                kill.setX(kill.getX()-1);
            }else {
                kill.setX(kill.getX()+1);
            }
        }
    }
    public void  move4(){
        GameFrame gameFrame=DataStore.get("gameFrame");
        Kill kill=gameFrame.kills.get(3);
        int x=320;
        int y=400;
        if (kill.getY()-y<=300){
            kill.setY(kill.getY()+1);
            if (kill.getY()-y<=150){
                kill.setX(kill.getX()+1);
            }else {
                kill.setX(kill.getX()-1);
            }
        }
    }
public void  kill(){
        GameFrame gameFrame=DataStore.get("gameFrame");
        gameFrame.enemyPlane.removeAll(gameFrame.enemyPlane);
        gameFrame.enemyPlanePluses.removeAll(gameFrame.enemyPlanePluses);
}
}
