package com.zhao.ThreadBasic;

import java.util.ArrayDeque;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-07-13 17:56
 * @描述 生产者消费者
 */
@SuppressWarnings("all")
public class Produce_consumer {
    private ArrayDeque<Integer> queue = new ArrayDeque(); //生产的数据
    private int count;
    private boolean hasNew; //是否有新数据

    private void produce() {
        try {
            while (true) {
                Thread.sleep(1000);
                synchronized (this) {
                    if (hasNew) {
                        this.wait();
                    }
                    int productId = (++count);
                    queue.add(productId);
                    System.out.println("生产了新产品ID:" + productId);
                    hasNew = true;
                    this.notify();
                }
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace(); //todo:不能吞掉异常
        }
    }

    private void consumer() {
        try {
            while (true) {
                synchronized (this) {
                    if (!hasNew) {
                        this.wait();
                    }
                    System.out.println("消费了新产品ID:" + queue.poll());
                    this.notify();
                    hasNew = false;
                }
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace(); //todo:不能吞掉异常
        }
    }

    public static void main(String[] args) {
        Produce_consumer pc = new Produce_consumer();
        //生产者
        new Thread() {
            @Override
            public void run() {
                pc.produce();
            }
        }.start();

        //消费者
        new Thread() {
            @Override
            public void run() {
                pc.consumer();
            }
        }.start();
    }
}