package com.zhao.nio;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-07-18 16:39
 * @描述  取出不同类型的数据元素
 */
public class TestGetTypesElements {
    public static void main(String[] args) throws Exception {
        ByteBuffer byteBuffer =
                ByteBuffer.allocate(7).order(ByteOrder.BIG_ENDIAN);
        byteBuffer.put((byte) 1);
        byteBuffer.put((byte) 2);
        byteBuffer.put((byte) 0);
        byteBuffer.put((byte) 3);
        byteBuffer.flip();
        //00000001 00000010 00000000 00000011
        //16908291
        System.out.println(byteBuffer.getInt());
    }
}