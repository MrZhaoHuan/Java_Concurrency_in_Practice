package com.zhao.Juc;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-07-15 11:36
 * @描述   测试copyOnWrite
 */
public class TestCopyOnWriteMap {
    public static void main(String[] args) {
        //HashMap map = new HashMap();
        MyCopyOnWriteMap map = new MyCopyOnWriteMap();
        for(int i=0;i<10000;i++){
            new Thread(i+""){
                @Override
                public void run() {
                    try {
                        Thread.sleep(20);
                        map.put(Thread.currentThread().getName(),Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }

        while(Thread.activeCount()>2){
            Thread.yield();
        }
        System.out.println(map.entrySet().size());
    }
}