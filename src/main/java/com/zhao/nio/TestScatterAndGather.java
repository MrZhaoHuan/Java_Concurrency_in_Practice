package com.zhao.nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-07-18 16:39
 * @描述  测试nio的 scatter(分散)和gather(收集)
 *        test.txt内容:hello,你好
 */
public class TestScatterAndGather {
    public static void main(String[] args) throws Exception {
        RandomAccessFile accessFile = new RandomAccessFile("D:\\test.txt", "rw");
        FileChannel fileChannel = accessFile.getChannel();

        ByteBuffer b1 = ByteBuffer.allocate(6);
        ByteBuffer b2 = ByteBuffer.allocate(4);
        ByteBuffer[] b1b2 = new ByteBuffer[]{b1, b2};
        //scatter
        fileChannel.read(b1b2);

        byte[] b1Arr = new byte[6];
        byte[] b2Arr = new byte[4];
        int b1Index = 0;
        int b2Index = 0;
        //切换读模式
        b1.flip();
        while (b1.hasRemaining()) {
            b1Arr[b1Index++] = b1.get();
        }
        //切换读模式
        b2.flip();
        while (b2.hasRemaining()) {
            b2Arr[b2Index++] = b2.get();
        }
        System.out.println(new String(b1Arr, "gbk"));
        System.out.println(new String(b2Arr, "gbk"));

        //gather
        b1.flip();
        b2.flip();
        b1.put("abcdef".getBytes());
        b2.put("6666".getBytes());
        b1.flip();
        b2.flip();
        fileChannel.write(b1b2);
        accessFile.close();
    }
}