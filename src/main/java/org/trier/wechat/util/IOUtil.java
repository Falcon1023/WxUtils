package org.trier.wechat.util;

import java.io.*;

/**
 * 对象序列化写入文件工具类
 * @param <T>
 */

public class IOUtil<T> {
    private String filename;
    private T object;

    public IOUtil(String filename) {
        this.filename = filename;
    }

    public IOUtil(String filename, T object) {
        this.filename = filename;
        this.object = object;
    }

    public void writeObject() {
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

    public T readObject(){
        ObjectInputStream in = null;
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
