package com.zhao.nio;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-07-18 16:39
 * @描述      存取无符号数据
 */
public class TestPutGetUnsigned {
    public static void main(String[] args) throws Exception {
        ByteBuffer byteBuffer = ByteBuffer.allocate(8).order(ByteOrder.BIG_ENDIAN);
        byteBuffer.put((byte) 0b10000000);
        byteBuffer.put((byte) 0b00110000);
        byteBuffer.put((byte) 0b00000100);
        byteBuffer.put((byte) 0b01010100);
        byteBuffer.flip();
        //取出无符号int
        System.out.println(((long) byteBuffer.getInt()) & 0xffffffffL);

        byteBuffer.clear();
        //存放无符号int
        //Integer.MAX_VALUE =   2147483647;
        byteBuffer.putInt((int)(2150666666L & 0xffffffffL));
        byteBuffer.flip();
        System.out.println(((long) byteBuffer.getInt()) & 0xffffffffL);
    }
}