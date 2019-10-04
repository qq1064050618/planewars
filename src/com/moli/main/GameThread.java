package com.moli.main;

import com.moli.util.DataStore;

public class GameThread extends Thread {
    @Override
    public void run() {
        GameFrame gameFrame=DataStore.get("gameFrame");
        while (gameFrame.suspend == false) {
            gameFrame.repaint();
            try {
                Thread.sleep(20);
                gameFrame.bossTimer++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
