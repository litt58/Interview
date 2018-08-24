package com.jzli.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
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
public class FileReadThread2 extends Thread {
    private String filePath;
    private long lastModified;
    private long fileLength;

    public FileReadThread2(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void run() {
        File file = new File(filePath);
        RandomAccessFile randomAccessFile = null;
        FileChannel channel;
        ByteBuffer buf = ByteBuffer.allocateDirect(1024);
        try {
            randomAccessFile = new RandomAccessFile(file, "r");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (file.exists()) {
            try {
                if (file.lastModified() > lastModified) {
                    randomAccessFile.seek(fileLength);
                    channel = randomAccessFile.getChannel();
                    buf.clear();
                    while (channel.read(buf) > 0) {
                        buf.flip();
                        int remaining = buf.remaining();
                        byte[] body = new byte[remaining];
                        buf.get(body, 0, remaining);
                        String line = new String(body, Charset.forName("UTF-8"));
                        System.out.println(line);
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
        new FileReadThread2("h:\\1.txt").start();
    }
}
