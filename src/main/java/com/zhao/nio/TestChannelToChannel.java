package com.zhao.nio;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-07-18 17:58
 * @描述      通道到通道的数据传输
 */
public class TestChannelToChannel {
    public static void main(String[] args) throws Exception {
        RandomAccessFile aFile = null;
        RandomAccessFile bFile = null;
        try {
            //a通道
            aFile = new RandomAccessFile("D:\\a.txt", "rw");
            FileChannel aFileChannel = aFile.getChannel();

            //b通道
            bFile = new RandomAccessFile("D:\\b.txt", "rw");
            FileChannel bFileChannel = bFile.getChannel();

            //a通道——b通道
            bFileChannel.transferFrom(aFileChannel, 0, aFileChannel.size());
        } finally {
            //关闭
            aFile.close();
            bFile.close();
        }
    }
}