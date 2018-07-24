package com.zhao.nio;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-07-21 10:28
 * @描述
 */
public class TestCharset {
    public static void main(String[] args) throws CharacterCodingException, UnsupportedEncodingException {
        Charset gbk = Charset.forName("utf-8");
        CharsetEncoder encoder = gbk.newEncoder();

        CharBuffer buffer = CharBuffer.allocate(5);
        buffer.put("abc中国");
        buffer.flip();

        ByteBuffer byteBuffer = encoder.encode(buffer);
        byteBuffer.get();
        byteBuffer.get();
        byteBuffer.get();
        byte[] a_Arr = new byte[3];
        a_Arr[0] = byteBuffer.get();
        a_Arr[1] = byteBuffer.get();
        a_Arr[2] = byteBuffer.get();
        System.out.println(new String(a_Arr, "utf-8"));

        a_Arr[0] = byteBuffer.get();
        a_Arr[1] = byteBuffer.get();
        a_Arr[2] = byteBuffer.get();
        System.out.println(new String(a_Arr, "utf-8"));
    }

}