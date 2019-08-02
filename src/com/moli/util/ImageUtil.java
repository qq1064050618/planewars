package com.moli.util;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.IOException;

public class ImageUtil {
    //用来封装读图片的方法


    public static Image getImage(String path){
        Image image=null;
        try {
            image= ImageIO.read(ImageUtil.class.getClassLoader().getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
