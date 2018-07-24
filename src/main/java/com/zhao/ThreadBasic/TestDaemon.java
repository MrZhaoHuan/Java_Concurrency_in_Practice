package com.zhao.ThreadBasic;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-07-13 19:10
 * @描述  程序中不存在非daemon线程时，jvm退出，daemon线程的finaly不会执行
 */
public class TestDaemon {

    public static void main(String[] args) {
         new Thread(){
            {
                this.setDaemon(true);  //守护线程
            }
            @Override
            public void run() {
                try {
                    Thread.sleep(100000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    System.out.println("finaly也不会执行");
                }
            }
        }.start();


        System.out.println("main线程退出");
    }
}