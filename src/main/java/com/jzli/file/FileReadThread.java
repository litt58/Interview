package com.jzli.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

/**
 * =======================================================
 *
 * @Company 技术中心-共享服务部-后端服务部
 * @Date ：2018/8/24
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：读取文件最新内容
 * ========================================================
 */
public class FileReadThread extends Thread {
    private String filePath;
    private long lastModified;
    private long fileLength;

    public FileReadThread(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void run() {
        File file = new File(filePath);
        RandomAccessFile randomAccessFile = null;
        ByteBuffer buf = ByteBuffer.allocateDirect(Short.MAX_VALUE);
        try {
            randomAccessFile = new RandomAccessFile(file, "r");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (file.exists()) {
            try {
                if (file.lastModified() > lastModified) {
                    randomAccessFile.seek(fileLength);
                    byte[] bytes = new byte[1024];
                    int hasRead;
                    while ((hasRead = randomAccessFile.read(bytes)) > 0) {
                        System.out.println(new String(bytes, 0, hasRead));
                    }
                    lastModified = file.lastModified();
                    fileLength = file.length();
                }
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new FileReadThread("h:\\1.txt").start();
    }
}
