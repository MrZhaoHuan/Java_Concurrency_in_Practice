package com.zhao;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-07-11 20:57
 * @描述  方式1线程不安全, 当线程A执行setClose(true)，B线程执行run()读到的变量close有可能还是false
 *        方式2线程安全，volatile保证，A线程直接操作主内存_通知B线程,B线程每次读到的都是主内存的最新值
            原因:
                线程内存模型:线程——>工作内存——>主内存
          方式3:使用重量级锁(内置锁)synchronized也能保证线程安全
 */
public class 测试可见性_volatile {

    //private boolean close;  //方式1

    private volatile boolean close; //方式2

    public  void setClose(boolean close) {
        this.close = close;
    }

    public void run(){
        while(!close){
            System.out.println("只要没关闭，我就一直执行");
        }
    }
}