package com.moli.main;

import com.moli.constant.FrameConstant;
import com.moli.runtime.Background;
import com.moli.runtime.Bullet;
import com.moli.runtime.EnemyBullet;
import com.moli.runtime.EnemyPlane;
import com.moli.runtime.Plane;
import com.moli.util.ImageMap;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameFrame extends Frame {


    private Background background = new Background();
    private Plane plane=new Plane();
    public final List<Bullet> bulletsList=new CopyOnWriteArrayList<>();
    public final List<EnemyPlane> enemyPlane=new CopyOnWriteArrayList<>();
    public final List<EnemyBullet> enemyBullets=new CopyOnWriteArrayList<>();
    public boolean gameOver=false;




    @Override
    public void paint(Graphics g) {
        if (!gameOver){
            background.draw(g);
            plane.draw(g);
            for (Bullet b:bulletsList
            ) {
                b.draw(g);
            }
            for (EnemyBullet e:enemyBullets
            ) {
                e.draw(g);
            }
            for (EnemyPlane e:enemyPlane
            ) {
                e.draw(g);
            }

            for (Bullet b:bulletsList
            ) {
                b.collisionTesting(enemyPlane);
            }
            for (EnemyBullet e:enemyBullets
            ) {
               e.collisionTesting(plane);
            }
      /* g.setColor(Color.red);
        g.drawString(""+enemyBullets.size(),100,100);*/
        }

    }



    public void init() {
        setSize(FrameConstant.FRAME_WIDTH, FrameConstant.FRAME_HEIGHT);
        setLocationRelativeTo(null);
        enableInputMethods(false);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        setResizable(false);

        //飞机键盘监听
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
               plane.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
               plane.keyReleased(e);
            }
        });
        Random random=new Random();
        enemyPlane.add(new EnemyPlane(random.nextInt(800), 50, ImageMap.get("ep01")));
        enemyPlane.add(new EnemyPlane(random.nextInt(800), 50, ImageMap.get("ep01")));
        enemyPlane.add(new EnemyPlane(random.nextInt(800), 50, ImageMap.get("ep01")));
        enemyPlane.add(new EnemyPlane(random.nextInt(800), 50, ImageMap.get("ep01")));

        setVisible(true);

        new Thread(){
            @Override
            public void run() {
                while (true) {
                    repaint();
                    try {
                        Thread.sleep(20);
                      /* EnemyPlane enemyPlane=new EnemyPlane();
                       enemyPlane.newEnemyPlane();*/
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

    }
    //取消閃屏
    private Image offScreenImage=null;
    @Override
    public void update(Graphics g){
        if (offScreenImage == null){
            offScreenImage = this.createImage(FrameConstant.FRAME_WIDTH,FrameConstant.FRAME_HEIGHT);
        }
        Graphics gOff = offScreenImage.getGraphics();
        paint(gOff);
        g.drawImage(offScreenImage,0,0,null);
    }
}
