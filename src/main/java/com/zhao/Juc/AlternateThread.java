package com.zhao.Juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-07-15 16:07
 * @描述 ABCABCABC 线程交替执行
 */
public class AlternateThread {

    private static class MyRunnable implements Runnable {
        private ReentrantLock lock = new ReentrantLock();
        private Condition A = lock.newCondition();
        private Condition B = lock.newCondition();
        private Condition C = lock.newCondition();
        private String threadName = "A";

        @Override
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    String name = Thread.currentThread().getName();
                    if (name.equals("A")) {
                        lock.lock();
                        if (!threadName.equals("A")) {
                            A.await();
                        }
                        System.out.println(name);
                        threadName = "B";
                        B.signal();
                        A.await();
                        lock.unlock();
                    } else if (name.equals("B")) {
                        lock.lock();
                        if (!threadName.equals("B")) {
                            B.await();
                        }
                        System.out.println(name);
                        threadName = "C";
                        C.signal();
                        B.await();
                        lock.unlock();
                    } else {
                        lock.lock();
                        if (!threadName.equals("C")) {
                            C.await();
                        }
                        System.out.println(name);
                        threadName = "A";
                        A.signal();
                        B.signal();
                        lock.unlock();
                    }
                    System.out.println("------------第" + (i + 1) + "次-----------------");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        MyRunnable runnable = new MyRunnable();
        new Thread(runnable, "C").start();
        new Thread(runnable, "B").start();
        new Thread(runnable, "A").start();
    }

}
