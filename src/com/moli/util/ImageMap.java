package com.moli.util;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ImageMap {
    //用来存储图片地址

    private static final Map<String, Image> map = new HashMap<>();

    static {
        map.put("bg01", ImageUtil.getImage("com\\moli\\imgs\\bg\\1.jpg"));

        map.put("my01", ImageUtil.getImage("com\\moli\\imgs\\plane\\my_1.png"));

        map.put("mb01", ImageUtil.getImage("com\\moli\\imgs\\bullet\\myb_1.png"));
        map.put("mb02", ImageUtil.getImage("com\\moli\\imgs\\bullet\\myb_2.png"));
        map.put("mb03", ImageUtil.getImage("com\\moli\\imgs\\bullet\\myb_3.png"));

        map.put("eb01", ImageUtil.getImage("com\\moli\\imgs\\bullet\\eb_1.png"));
        map.put("eb02", ImageUtil.getImage("com\\moli\\imgs\\bullet\\eb_2.png"));
        map.put("bossb", ImageUtil.getImage("com\\moli\\imgs\\bullet\\BossBullet.png"));

        map.put("ep01", ImageUtil.getImage("com\\moli\\imgs\\plane\\ep_1.png"));
        map.put("ep02", ImageUtil.getImage("com\\moli\\imgs\\plane\\ep_2.png"));
        map.put("boss", ImageUtil.getImage("com\\moli\\imgs\\plane\\Boss.png"));

        map.put("blood", ImageUtil.getImage("com\\moli\\imgs\\tool\\blood.png"));
        map.put("tool", ImageUtil.getImage("com\\moli\\imgs\\tool\\tool.png"));

        map.put("start", ImageUtil.getImage("com\\moli\\imgs\\start\\start.png"));
        //爆炸
        map.put("explod1", ImageUtil.getImage("com\\moli\\imgs\\explode\\1.png"));
        map.put("explod2", ImageUtil.getImage("com\\moli\\imgs\\explode\\2.png"));
        map.put("explod3", ImageUtil.getImage("com\\moli\\imgs\\explode\\3.png"));
        map.put("explod4", ImageUtil.getImage("com\\moli\\imgs\\explode\\4.png"));
        map.put("explod5", ImageUtil.getImage("com\\moli\\imgs\\explode\\5.png"));
        map.put("explod6", ImageUtil.getImage("com\\moli\\imgs\\explode\\6.png"));
        map.put("explod7", ImageUtil.getImage("com\\moli\\imgs\\explode\\7.png"));
        map.put("explod8", ImageUtil.getImage("com\\moli\\imgs\\explode\\8.png"));
        map.put("explod9", ImageUtil.getImage("com\\moli\\imgs\\explode\\9.png"));
//大招
        map.put("kill1", ImageUtil.getImage("com\\moli\\imgs\\bullet\\1.png"));
        map.put("kill2", ImageUtil.getImage("com\\moli\\imgs\\bullet\\2.png"));
        map.put("kill3", ImageUtil.getImage("com\\moli\\imgs\\bullet\\3.png"));
        map.put("kill4", ImageUtil.getImage("com\\moli\\imgs\\bullet\\4.png"));
        map.put("kill5", ImageUtil.getImage("com\\moli\\imgs\\bullet\\5.png"));
        map.put("kill6", ImageUtil.getImage("com\\moli\\imgs\\bullet\\6.png"));


    }

    public static Image get(String key) {
        return map.get(key);
    }
}