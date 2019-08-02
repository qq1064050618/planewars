package lx;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Tesr {
    public static void main(String[] args) {
        InputStream in=null;
        OutputStream out=null;
        try {
            in=new FileInputStream("src\\lx\\1.txt");
            out=new FileOutputStream("src\\lx\\one\\1.txt");
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
