package com.moli.util;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImageUtil {
    //用来封装读图片的方法
//Util工具库

    public static Image getImage(String path){
        Image image=null;
        InputStream in=null;
        try {
            in= new FileInputStream("src\\"+path);
            image= ImageIO.read(/*ImageUtil.class.getClassLoader().getResourceAsStream(path)*/in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
