package com.zhao.ThreadBasic;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-07-14 13:27
 * @描述
 */
public class TestBoundedQueue {
    private static BoundedQueue boundedQueue = new BoundedQueue();

    public static void produce() {
        new Thread() {
            @Override
            public void run() {
                try {
                    int data = 0;
                    while (true) {
                        Thread.sleep(500);
                        boundedQueue.putData((++data) + "");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public static void consumer() {
        new Thread() {
            @Override
            public void run() {
                try {
                    while (true) {
                        boundedQueue.fetchFIFO();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }

    public static void main(String[] args) throws InterruptedException {
        produce();
        Thread.sleep(5000);
        consumer();
    }
}