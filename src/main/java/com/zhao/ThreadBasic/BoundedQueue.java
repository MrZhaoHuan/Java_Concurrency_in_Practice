package com.zhao.ThreadBasic;

import java.util.ArrayDeque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-07-13 17:56
 * @描述 有界队列
 */
public class BoundedQueue extends ArrayDeque<String> {
    private ReentrantLock lock = new ReentrantLock();
    private Condition fullCondition = lock.newCondition();
    private Condition emptyCondition = lock.newCondition();
    private int fullNum;
    private boolean isFull;

    public String fetchFIFO() throws InterruptedException {
        lock.lock();
        while (this.isEmpty()) {
            emptyCondition.await();
        }
        String data = this.poll();
        System.out.println("消费数据:" + data);
        --fullNum;
        fullCondition.signal();
        isFull = false;
        lock.unlock();
        return data;
    }


    public void putData(String data) throws InterruptedException {
        lock.lock();
        while (isFull) {
            fullCondition.await();
        }
        this.push(data);
        System.out.println("生产数据:" + data);
        ++fullNum;
        if (fullNum >= 10) {
            isFull = true;
            System.out.println("生产数据已经满了,等待消费");
        }
        emptyCondition.signal();
        lock.unlock();
    }
}