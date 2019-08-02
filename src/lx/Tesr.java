package lx;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class Tesr {
    public static void main(String[] args) {
        InputStream in=null;
        OutputStream out=null;
        Scanner sr=new Scanner(System.in);
        System.out.println("请输入被复制文件地址：");
        String s=sr.nextLine();
        sr.nextLine();
        System.out.println("请输入目标地址：");
        String ss=sr.nextLine();
        try {
            in=new FileInputStream(s);
            out=new FileOutputStream(ss);
            byte[] bytes=new byte[8];
            String str="";
            int i=0;
            while ((i=in.read(bytes)) != -1){
                str+=new String(bytes,0,i,"utf-16");
            }
            out.write(str.getBytes("utf-16"));
            //System.out.println(str);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
