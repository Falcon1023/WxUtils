package org.trier.wechat.util;

import java.io.*;

/**
 * 对象序列化后写入文件的工具类
 */

public class IOUtil {
    public static <T> void writeObject(String filename,T object) {
        ObjectOutputStream out=null;
        try {
            out=new ObjectOutputStream(new FileOutputStream(filename));
            out.writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(out != null)
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    public static <T> T readObject(String filename){
        ObjectInputStream in = null;
        T object = null;
        try {
            in = new ObjectInputStream(new FileInputStream(filename));
            object = (T) in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (in != null)
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return object;
    }
}
