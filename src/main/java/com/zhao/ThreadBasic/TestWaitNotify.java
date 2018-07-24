package com.zhao.ThreadBasic;

import java.util.Random;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-07-17 22:44
 * @描述  wait/notify
 */
public class TestWaitNotify {
    private static int sum;
    private static boolean sumThreadStart;
    private static Object lock = new Object();
    public static void main(String[] args) {
            new Thread(){
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (lock){
                       try {
                           while(true){
                               sumThreadStart = true;
                               lock.wait();
                               System.out.println("求和的结果:" + sum);
                               lock.notify();
                           }
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
                   }
                }
            }.start();



         new Thread(){
             @Override
             public void run() {
                 waitSumThread(); //等待求和线程启动
                 synchronized (lock){
                     while(true){
                         try{
                             Thread.sleep(1000);
                             int a = new Random().nextInt(100);
                             int b = new Random().nextInt(100);
                             sum = a + b;
                             System.out.print(a + "+" + b);
                             lock.notify();
                             lock.wait();
                         }catch (InterruptedException ex){
                             ex.printStackTrace();
                         }
                     }
                 }
             }

             private void waitSumThread() {
                 while(!sumThreadStart){
                     try {
                         Thread.sleep(800);
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }
                 }
             }
         }.start();
    }
}