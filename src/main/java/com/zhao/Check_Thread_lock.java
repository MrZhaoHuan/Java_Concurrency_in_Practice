package com.zhao;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-08-14 18:08
 * @描述      检测某个线程是否持有某个对象上的锁🔒
 **/
public class Check_Thread_lock {
    private static Object lock = new Object();

    public static void main(String[] args) {

        System.out.println(Thread.holdsLock(lock));

        synchronized (lock){
            System.out.println(Thread.holdsLock(lock));
        }

    }
}