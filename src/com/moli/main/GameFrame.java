package com.moli.main;

import com.moli.constant.FrameConstant;
import com.moli.runtime.Background;
import com.moli.runtime.Boss;
import com.moli.runtime.BossBullet;
import com.moli.runtime.Bullet;
import com.moli.runtime.EnemyBullet;
import com.moli.runtime.EnemyBulletPlus;
import com.moli.runtime.EnemyPlane;
import com.moli.runtime.EnemyPlanePlus;
import com.moli.runtime.Explode;
import com.moli.runtime.Kill;
import com.moli.runtime.Plane;
import com.moli.runtime.Tools;
import com.moli.util.ImageMap;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameFrame extends Frame {


    private Background background = new Background();
    private Plane plane = new Plane();
    private Boss boss = new Boss();
    public final List<Start> start=new CopyOnWriteArrayList<>();
    public final List<Bullet> bulletsList = new CopyOnWriteArrayList<>();
    public final List<EnemyPlane> enemyPlane = new CopyOnWriteArrayList<>();
    public final List<EnemyPlanePlus> enemyPlanePluses = new CopyOnWriteArrayList<>();
    public final List<EnemyBullet> enemyBullets = new CopyOnWriteArrayList<>();
    public final List<EnemyBulletPlus> enemyBulletPluses = new CopyOnWriteArrayList<>();
    public final List<BossBullet> bossBullets = new CopyOnWriteArrayList<>();
    public final List<Tools> tools = new CopyOnWriteArrayList<>();
    public final List<Explode> explodes = new CopyOnWriteArrayList<>();
    public final List<Kill> kills=new CopyOnWriteArrayList<>();

    public boolean startB = false;
    public boolean gameOver = false;
    public int score = 0;
    public int blood = 5;
    public int bossBlood = 1000;
    public boolean isExplode=false;
    public int kill=0;


    @Override
    public void paint(Graphics g) {
        if (bossBlood <= 0) {
            gameOver = true;
            g.setColor(new Color(255, 0, 0));
            g.setFont(new Font("宋体", Font.ITALIC, 100));
            g.drawString("victory", 200, 500);
        }
        background.draw(g);
        for (Start s:start
             ) {
            s.draw(g);
        }
        if (/*!gameOver*/true&&startB) {
            plane.draw(g);
            boss.draw(g);

            for (Start s:start
            ) {
                start.remove(s);
            }
             /*   g.setColor(new Color(255, 0, 0));
                g.setFont(new Font("黑体", Font.BOLD, 60));
                g.drawString(""+bossBlood,
                        boss.getX()+ImageMap.get("boss").getWidth(null)/2-40,
                        boss.getY()+ ImageMap.get("boss").getHeight(null)/2+40);*/
            for (Kill k : kills
            ) {
                k.draw(g);
            }
            for (Bullet b : bulletsList
            ) {
                b.draw(g);
            }
            for (EnemyBullet e : enemyBullets
            ) {
                e.draw(g);
            }
            for (EnemyBulletPlus e : enemyBulletPluses
            ) {
                e.draw(g);
            }
            for (EnemyPlane e : enemyPlane
            ) {
                e.draw(g);
            }
            for (EnemyPlanePlus e : enemyPlanePluses
            ) {
                e.draw(g);
            }
            for (BossBullet e : bossBullets
            ) {
                e.draw(g);
            }
            for (Tools t : tools
            ) {
                t.draw(g);
            }
            if (isExplode){
                for (Explode e:explodes
                     ) {
                    e.draw(g);
                    explodes.remove(e);
                }
                isExplode=false;
            }
//碰撞检测
            for (Tools t : tools
            ) {
                t.collisionTesting(plane);
            }
            for (Bullet b : bulletsList
            ) {
                b.collisionTesting(enemyPlane);
            }
            for (Bullet b : bulletsList
            ) {
                b.collisionTestingPlus(enemyPlanePluses);
            }
            for (Bullet b : bulletsList
            ) {
                b.collisionTestingBoss(boss);
            }
            for (EnemyBullet e : enemyBullets
            ) {
                e.collisionTesting(plane);
            }
            for (EnemyBulletPlus e : enemyBulletPluses
            ) {
                e.collisionTesting(plane);
            }
            for (BossBullet e : bossBullets
            ) {
                e.collisionTesting(plane);
            }


            //得分一系列的设置
            g.setColor(new Color(151, 255, 166));
            g.setFont(new Font("楷体", 0, 25));
            g.drawString("得分：", 50, 100);
            g.setColor(new Color(255, 30, 28));
            g.setFont(new Font("宋体", 0, 36));
            g.drawString("" + score, 120, 105);

            g.setColor(new Color(255, 0, 0));
            g.setFont(new Font("黑体", Font.BOLD, 50));
            g.drawString("" + blood,
                    plane.getX() + ImageMap.get("my01").getWidth(null) / 2 - 14,
                    plane.getY() + ImageMap.get("my01").getHeight(null) / 2);

            /* g.setColor(Color.red);
        g.drawString(""+enemyBullets.size(),100,100);*/
        } else if (blood == 0) {
            g.setColor(new Color(255, 0, 0));
            g.setFont(new Font("宋体", Font.ITALIC, 100));
            g.drawString("GAME  OVER!", 100, 500);
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
        start.add(new Start());
//鼠標監聽
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                for (Start s:start
                     ) {
                    if (e.getX()>s.getX()&&
                            e.getX()<s.getX()+ImageMap.get("start").getWidth(null)&&
                            e.getY()<ImageMap.get("start").getHeight(null)+s.getY()&&
                            e.getY()>s.getY()
                    ){
                        startB=true;
                    }
                }

            }
        });
        kills.add(new Kill(320,400,ImageMap.get("kill1"),1));
        kills.add(new Kill(320,400,ImageMap.get("kill2"),2));
        kills.add(new Kill(320,400,ImageMap.get("kill3"),3));
        kills.add(new Kill(320,400,ImageMap.get("kill4"),4));
        kills.add(new Kill(320,400,ImageMap.get("kill5"),5));
        kills.add(new Kill(320,400,ImageMap.get("kill6"),6));
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
        Random random = new Random();
        tools.add(new Tools(random.nextInt(800- ImageMap.get("blood").getWidth(null)) ,
                -200,
                ImageMap.get("blood"),
                0));
        tools.add(new Tools(random.nextInt(800- ImageMap.get("blood").getWidth(null)),
                -800 * 4,
                ImageMap.get("tool"),
                1));
        tools.add(new Tools(random.nextInt(800- ImageMap.get("blood").getWidth(null)),
                -800 * 6,
                ImageMap.get("blood"),
                0));
        tools.add(new Tools(random.nextInt(800- ImageMap.get("blood").getWidth(null)),
                -800 * 8,
                ImageMap.get("tool"),
                1));
        tools.add(new Tools(random.nextInt(800- ImageMap.get("blood").getWidth(null)),
                -800 * 10,
                ImageMap.get("blood"),
                0));
        tools.add(new Tools(random.nextInt(800- ImageMap.get("blood").getWidth(null)),
                -800 * 12,
                ImageMap.get("tool"),
                1));
        tools.add(new Tools(random.nextInt(800- ImageMap.get("blood").getWidth(null)),
                -800 * 14,
                ImageMap.get("blood"),
                0));


        setVisible(true);


        new Thread() {
            @Override
            public void run() {
                while (true) {
                    repaint();
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

    }

    //取消閃屏
    private Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(FrameConstant.FRAME_WIDTH, FrameConstant.FRAME_HEIGHT);
        }
        Graphics gOff = offScreenImage.getGraphics();
        paint(gOff);
        g.drawImage(offScreenImage, 0, 0, null);
    }
}
