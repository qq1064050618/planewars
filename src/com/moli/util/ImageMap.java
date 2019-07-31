package com.moli.util;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ImageMap {
    private static final Map<String, Image> map = new HashMap<>();

    static {
        map.put("bg01", ImageUtil.getImage("com\\moli\\imgs\\bg\\1.png"));

        map.put("my01", ImageUtil.getImage("com\\moli\\imgs\\plane\\my_1.png"));

        map.put("mb01", ImageUtil.getImage("com\\moli\\imgs\\bullet\\myb_1.png"));

        map.put("eb01", ImageUtil.getImage("com\\moli\\imgs\\bullet\\eb_1.png"));

        map.put("ep01", ImageUtil.getImage("com\\moli\\imgs\\plane\\ep_1.png"));
    }

    public static Image get(String key) {
        return map.get(key);
    }
}