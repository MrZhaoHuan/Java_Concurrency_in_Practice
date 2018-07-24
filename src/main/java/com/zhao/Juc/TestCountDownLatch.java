package com.zhao.Juc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-07-15 12:42
 * @描述  10个线程计算1到100的和,等到计算完成，再执行主线程打印汇总结果(CountDownLatch)
 *
 *         使用场景:不同的业务数据，汇总后再处理
 */
public class TestCountDownLatch {

    private static class CalcSumRunable implements Runnable {
        private static final AtomicLong atomicSum = new AtomicLong(0);
        private long start,end;
        private CountDownLatch latch;
        private CalcSumRunable(long start,long end,CountDownLatch latch){
            this.start = start;
            this.end  = end;
            this.latch = latch;
        }
        @Override
        public void run() {
            long sum = 0;
            for(;start<=end;start++){
                sum += start;
            }
            atomicSum.addAndGet(sum);
            synchronized (Object.class){
                latch.countDown();
            }
        }

        public static long getTotalSum(){
            return  atomicSum.get();
        }
    }


    private static long calcSum(long start,long end){
        long sum = 0;
        for(;start<=end;start++){
            sum += start;
        }
        return  sum;
    }

    public static void main(String[] args) throws InterruptedException {
        List<Map<Long,Long>> digitalList = Arrays.asList(
                Collections.singletonMap(1L,200000000L),
                Collections.singletonMap(200000001L,400000000L),
                Collections.singletonMap(400000001L,600000000L),
                Collections.singletonMap(600000001L,800000000L),
                Collections.singletonMap(800000001L,1000000000L),
                Collections.singletonMap(1000000001L,1200000000L),
                Collections.singletonMap(1200000001L,1400000000L),
                Collections.singletonMap(1400000001L,1600000000L),
                Collections.singletonMap(1600000001L,1800000000L),
                Collections.singletonMap(1800000001L,2000000000L)
        );

        CountDownLatch latch = new CountDownLatch(digitalList.size());

        for(int i=0;i<digitalList.size();i++){
            Map<Long,Long> digitalMap = digitalList.get(i);
            Long start = digitalMap.keySet().iterator().next();
            new Thread(new CalcSumRunable(start,digitalMap.get(start),latch)).start();
        }
        latch.await();
        System.out.println(CalcSumRunable.getTotalSum());
        System.out.println(calcSum(1L,2000000000L));
    }
}