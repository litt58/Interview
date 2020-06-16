package com.jzli.singleton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * =======================================================
 *
 * @Company 技术中心-共享服务部-后端服务部
 * @Date ：2020/6/16 15:08
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：反序列化破坏单例
 * ========================================================
 */
public class SingletonStaticInnerSerializeTest {
    public static void main(String[] args) {
        LazyInnerClassSingleton s1 = LazyInnerClassSingleton.getInstance();
        FileOutputStream fos;
        LazyInnerClassSingleton s2;
        try {
            //序列化到文件中
            fos = new FileOutputStream("SeriableSingleton.obj");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(s1);
            oos.flush();
            oos.close();

            //从文件中反序列化为对象
            FileInputStream fis = new FileInputStream("SeriableSingleton.obj");
            ObjectInputStream ois = new ObjectInputStream(fis);
            s2 = (LazyInnerClassSingleton) ois.readObject();
            ois.close();
            //对比结果,这里输出的结果为false
            System.out.println(s1 == s2);
            System.out.println(s1);
            System.out.println(s2);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
