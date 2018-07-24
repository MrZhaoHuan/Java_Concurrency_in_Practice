package com.zhao.nio;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-07-21 10:53
 * @描述 nio_server简单响应
 */
public class TestEchoServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        //ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(8080));
        serverSocketChannel.configureBlocking(false);
        //Selector
        Selector selector = Selector.open();
        //ServerSocketChannel注册到Selector,监听accept事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            int count = selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey next = iterator.next();

                if ((next.readyOps() & SelectionKey.OP_ACCEPT) != 0) {
                    System.out.println("aaaaaaaaa");
                    ServerSocketChannel serverChannel = (ServerSocketChannel) next.channel();

                    SocketChannel accept = serverChannel.accept();
                    accept.configureBlocking(false);
                    accept.register(selector, SelectionKey.OP_READ);
                } else if ((next.readyOps() & SelectionKey.OP_READ) != 0) {
                    SocketChannel clientChannel = (SocketChannel) next.channel();
                    processClient(clientChannel);
                    if(next!=null){
                        next.cancel();
                        next.channel().close();
                    }
                }

                iterator.remove();
            }
        }
    }

    private static void processClient(SocketChannel clientChannel) {
        try {
            receiveRead(clientChannel);
            sendWrite(clientChannel);
            clientChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sendWrite(SocketChannel clientChannel) {
        StringBuilder reply = new StringBuilder();
        try {
            reply.append("HTTP/1.1 200 ok").append(System.lineSeparator())
                    .append("Content-Length: 10").append(System.lineSeparator()).append(System.lineSeparator())
                    .append("abcdef1234");

            ByteBuffer message = ByteBuffer.allocate(reply.toString().getBytes("utf-8").length);
            message.put(reply.toString().getBytes("utf-8"));
            message.clear();
            clientChannel.write(message);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private static void receiveRead(SocketChannel clientChannel) {
        System.out.println("----------------------receive_read----------------------");
        byte[] data = new byte[0];
        ByteBuffer readData = ByteBuffer.allocate(1024 * 5);
        int len = 0;
        try {
            while ((len = clientChannel.read(readData)) != 0) {
                byte[] temp = new byte[data.length + readData.array().length];
                System.arraycopy(data, 0, temp, 0, data.length);
                System.arraycopy(readData.array(), 0, temp, data.length, readData.array().length);
                data = temp;
                readData.clear();
            }
            //readData.flip();
            System.out.print(new String(data, "utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}