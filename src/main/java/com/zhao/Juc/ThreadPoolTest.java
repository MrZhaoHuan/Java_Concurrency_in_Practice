package com.zhao.Juc;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-07-14 17:48
 * @描述  线程池
 *       Executors.静态方法
 */
public class ThreadPoolTest {
    public static void main(String[] args) {
        System.out.println("处理器核心数:" + Runtime.getRuntime().availableProcessors());

         /*
         * corePoolSize：核心大小，线程池初始化的时候，就会有这么大
         * maximumPoolSize：线程池最大线程数
         * keepAliveTime：如果当前线程池中线程数大于corePoolSize
         * 多余的线程，在等待keepAliveTime时间后如果还没有新的线程任务指派给它，它就会被回收
         *
         * unit：等待时间keepAliveTime的单位
         *
         * workQueue：等待队列。
         * */
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5, 20, 1, TimeUnit.MINUTES, new SynchronousQueue<Runnable>());
        for(int index = 0 ; index < 10 ; index ++) {
            final int num  = index;
            poolExecutor.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(num);
                }
            });
        }

        poolExecutor.shutdown();
    }
}