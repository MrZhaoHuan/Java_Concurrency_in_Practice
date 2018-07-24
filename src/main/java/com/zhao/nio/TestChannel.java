package com.zhao.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-07-18 13:36
 * @描述 从FileChannel中读取字节数据
 */
public class TestChannel {
    public static void main(String[] args) throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("d:/test.txt", "rw");
        FileChannel inChannel = aFile.getChannel();

        ByteBuffer buf = ByteBuffer.allocate(1024);
        List<Byte> datas = new ArrayList<>();
        int bytesRead = inChannel.read(buf);
        while (bytesRead != -1) {
            System.out.println("Read " + bytesRead);
            buf.flip();
            while (buf.hasRemaining()) {
                datas.add(buf.get());
            }
            buf.clear();
            bytesRead = inChannel.read(buf);
        }
        aFile.close();

        //打印读取到的数据
        byte[] dataBytes = new byte[datas.size()];
        for (int i = 0, len = datas.size(); i < len; i++) {
            dataBytes[i] = datas.get(i);
        }
        System.out.println(new String(dataBytes, "gbk"));
    }
}