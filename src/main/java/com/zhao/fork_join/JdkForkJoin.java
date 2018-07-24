package com.zhao.fork_join;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-07-14 16:14
 * @描述   jdk并发包提供的forkJoin
 *          计算start到end的和
 */
@SuppressWarnings("all")
public class JdkForkJoin{

    private static  class CountTask extends RecursiveTask<Long>{
        private static final long serialVersionUID = 1L;
        private static final long criticalValue = 5000000000L;
        private long start;
        private long end;

        public CountTask(long start,long end){
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            long sum = 0;
            boolean isPlit = (end-start) > criticalValue;
            if(isPlit){
                long middle = (end+start)/2;
                CountTask task1 = new CountTask(start, middle);
                CountTask task2 = new CountTask(middle+1, end);
                task1.fork();
                task2.fork();
                return task1.join()+task2.join();
            }else{
                for(;start<=end;start++){
                    sum += start;
                }
            }
            return sum;
        }
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long sum = 0;
        //jdk提供的fork/join方式
        long startTime = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> result = forkJoinPool.submit(new CountTask(1L,50000000000L));
        sum = result.get();
        long endTime = System.currentTimeMillis();
        System.out.println("fork/join方式——>花费毫秒:"+(endTime-startTime)+"  计算结果:"+ sum);
    }


    @Test
    public void commonCalc(){
        long sum = 0;
        //普通方式计算
        long startTime = System.currentTimeMillis();
        for(long i=1L,len=50000000000L;i<=len;i++){
            sum += i;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("普通方式——>花费毫秒:"+(endTime-startTime)+"  计算结果:"+sum);  //16941ms
    }
}