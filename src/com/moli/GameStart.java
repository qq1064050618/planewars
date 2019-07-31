package com.moli;

import com.moli.main.GameFrame;
import com.moli.util.DataStore;

public class GameStart {
    public static void main(String[] args) {
      GameFrame gameFrame=new GameFrame();
        DataStore.put("gameFrame",gameFrame);
        gameFrame.init();
    }

}
