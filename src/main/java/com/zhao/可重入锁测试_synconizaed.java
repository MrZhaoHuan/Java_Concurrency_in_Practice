package com.zhao;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-07-12 11:10
 * @描述
 */
public class 可重入锁测试_synconizaed extends Parent {

    @Override
    public synchronized void incr() {
        try {
            super.incr();
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws InterruptedException {
       new Thread(){
            @Override
            public void run() {
                new 可重入锁测试_synconizaed().incr();
            }
       }.start();

        while(true){
            Thread.sleep(100);
            if(Parent.isRun){
                new Thread(){
                    @Override
                    public void run() {
                        new Parent().incr();
                    }
                }.start();
                break;
            }
        }

        //只要其他线程没搞完
        while(Thread.activeCount()>2){
            //就让你继续搞^_^
            Thread.yield();
        }
        //其他线程搞完了，再搞主线程(*^__^*)
        System.out.println(Parent.age);
    }
}