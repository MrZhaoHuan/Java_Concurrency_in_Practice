package com.zhao.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-07-21 14:42
 * @描述      nio管道
 */
public class TestPipe {
    public static void main(String[] args) throws IOException {
        Pipe pipe = Pipe.open();

        //往sink通道写数据
        ByteBuffer writeData = ByteBuffer.allocate(6);
        writeData.put("abcdef".getBytes());
        writeData.flip();

        Pipe.SinkChannel sink = pipe.sink();
        sink.write(writeData);

        //从source通道取数据
        ByteBuffer readData = ByteBuffer.allocate(6);

        Pipe.SourceChannel source = pipe.source();
        source.read(readData);
        //输出读取到的数据
        System.out.println(new String(readData.array()));
    }
}