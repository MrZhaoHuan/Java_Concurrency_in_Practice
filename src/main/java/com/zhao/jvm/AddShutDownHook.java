package com.zhao.jvm;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-07-16 13:48
 * @描述  jvm正常退出前做一些清理工作
 */
public class AddShutDownHook {

    private static void exitClear() {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("clear resources before jvm exit");
            }
        }));
    }


    public static void main(String[] args) throws InterruptedException {
         exitClear();

        //Thread.sleep(300);
        //System.out.println("-----main-----");


        Thread.sleep(300);
        System.exit(0);
        System.out.println("-----main-----");
    }
}