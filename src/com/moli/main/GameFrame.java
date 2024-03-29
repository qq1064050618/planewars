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
import com.moli.runtime.Guard;
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
    public Plane plane = new Plane();
    private Boss boss = new Boss();
    public final List<Start> start = new CopyOnWriteArrayList<>();
    public final List<Bullet> bulletsList = new CopyOnWriteArrayList<>();
    public final List<EnemyPlane> enemyPlane = new CopyOnWriteArrayList<>();
    public final List<EnemyPlanePlus> enemyPlanePluses = new CopyOnWriteArrayList<>();
    public final List<EnemyBullet> enemyBullets = new CopyOnWriteArrayList<>();
    public final List<EnemyBulletPlus> enemyBulletPluses = new CopyOnWriteArrayList<>();
    public final List<BossBullet> bossBullets = new CopyOnWriteArrayList<>();
    public final List<Tools> tools = new CopyOnWriteArrayList<>();
    public final List<Explode> explodes = new CopyOnWriteArrayList<>();
    public final List<Kill> kills = new CopyOnWriteArrayList<>();
    public final List<Guard> guards = new CopyOnWriteArrayList<>();

    public boolean indexTool = false;
    public boolean guard = false;
    public boolean startB = false;
    public boolean gameOver = false;
    public int score = 0;
    public int blood = 5;
    public int bossBlood = 100;
    public boolean isExplode = false;
    public int killEnergy = 0;
    public boolean suspend = false;
    //用作消除时判断
    public static boolean indexKill = false;
    //用作键盘监听判断
    public static boolean isIndexKill = false;
    int bossTimer = 0;

    /*采用单例模式，保证GameFrame界面的唯一性*/
    private GameFrame() {

    }

    private static GameFrame gameFrame = null;

    public static GameFrame getGameFrame() {
        if (gameFrame == null) {
            gameFrame = new GameFrame();
            return gameFrame;
        } else {
            return gameFrame;
        }
    }

    @Override
    public void paint(Graphics g) {

        background.draw(g);
        for (Start s : start
        ) {
            s.draw(g);
        }
        if (bossBlood <= 0) {
            gameOver = true;
            g.setColor(new Color(255, 0, 0));
            g.setFont(new Font("宋体", Font.ITALIC, 100));
            g.drawString("victory", 200, 500);
        }
        if (!gameOver && startB) {
            if (guard) {
                for (Guard e : guards
                ) {
                    e.draw(g);
                }
            }
            plane.draw(g);
            if (bossTimer >= 50 * 50) {
                boss.draw(g);
                g.setColor(new Color(255, 0, 0));
                g.setFont(new Font("黑体", Font.BOLD, 60));
                g.drawString("" + bossBlood,
                        boss.getX() + ImageMap.get("boss").getWidth(null) / 2 - 60,
                        boss.getY() + ImageMap.get("boss").getHeight(null) / 2 + 40);
                for (Bullet b : bulletsList
                ) {
                    b.collisionTestingBoss(boss);
                }
            }
            for (Start s : start
            ) {
                start.remove(s);
            }
            /*  */
            if (kills.size() > 3 && isIndexKill) {
                System.out.println("3333");
                for (Kill k : kills
                ) {
                    k.draw(g);
                }
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
            if (isExplode) {
                for (Explode e : explodes
                ) {
                    e.draw(g);
                    explodes.remove(e);
                }
                isExplode = false;
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
            if (indexKill) {
                for (Kill k : kills
                ) {
                    kills.remove(k);
                }
                isIndexKill = false;
                indexKill = false;
            }

            g.setFont(new Font("楷体", Font.BOLD, 20));
            g.setColor(Color.MAGENTA);
            g.drawRect(600, 100, 80, 30);


            //怒气值的设定
            if (killEnergy < 20) {
                g.fillRect(600, 100, killEnergy * 4, 30);
                g.drawString("怒气值:" + killEnergy, 600, 150);
            } else {
                g.setColor(Color.RED);
                g.fillRect(600, 100, killEnergy * 4, 30);
                g.drawString("大招充能完毕", 600, 150);
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
            System.out.println(blood);
            g.setColor(new Color(255, 0, 0));
            g.setFont(new Font("宋体", Font.ITALIC, 100));
            g.drawString("GAME  OVER!", 100, 500);
        }

    }


    public void init() {
        setSize(FrameConstant.FRAME_WIDTH, FrameConstant.FRAME_HEIGHT);
        //设置窗口居中
        setLocationRelativeTo(null);
        //不允许输入法输入
        enableInputMethods(false);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        //不能改变窗口大小
        setResizable(false);
        //添加开始按钮
        start.add(new Start());
//鼠標監聽
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                for (Start s : start
                ) {
                    if (e.getX() > s.getX() &&
                            e.getX() < s.getX() + ImageMap.get("start").getWidth(null) &&
                            e.getY() < ImageMap.get("start").getHeight(null) + s.getY() &&
                            e.getY() > s.getY()
                    ) {
                        startB = true;
                    }
                }

            }
        });
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
        //道具添加
        Random random = new Random();
        tools.add(new Tools(random.nextInt(800 - ImageMap.get("blood").getWidth(null)),
                -200,
                ImageMap.get("blood"),
                0));
        tools.add(new Tools(random.nextInt(800 - ImageMap.get("blood").getWidth(null)),
                -800 * 4,
                ImageMap.get("tool"),
                1));
        tools.add(new Tools(random.nextInt(800 - ImageMap.get("blood").getWidth(null)),
                -800 * 6,
                ImageMap.get("blood"),
                0));
        tools.add(new Tools(random.nextInt(800 - ImageMap.get("blood").getWidth(null)),
                -800 * 8,
                ImageMap.get("tool"),
                1));
        tools.add(new Tools(random.nextInt(800 - ImageMap.get("blood").getWidth(null)),
                -800 * 10,
                ImageMap.get("blood"),
                0));
        tools.add(new Tools(random.nextInt(800 - ImageMap.get("blood").getWidth(null)),
                -800 * 12,
                ImageMap.get("tool"),
                1));
        tools.add(new Tools(random.nextInt(800 - ImageMap.get("blood").getWidth(null)),
                -800 * 14,
                ImageMap.get("blood"),
                0));


        setVisible(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    if (suspend == false) {
                        suspend = true;
                    } else {
                        suspend = false;
                        new GameThread().start();
                    }
                }
            }
        });
        new GameThread().start();
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
