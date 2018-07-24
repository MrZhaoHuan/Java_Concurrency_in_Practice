package com.zhao.fork_join;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-07-13 17:56
 * @描述  自定义: 任务拆分，并发执行
 */
public class SelfForkJoin {
    private static final int[] result = new int[3];
    public static void main(String[] args) {
        //计算1到10的和,分成3个任务  1-3  4-6  7-10
        Thread[] threads = new Thread[3];
        for(int i=0;i<3;i++){
            final  int index = i;
            threads[i] = new Thread(""+i){
                @Override
                public void run() {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    int end  = index==2?3*(index+1)+1:3*(index+1);
                    int sum =  0;
                    for(int j=(1+3*index);j<=end;j++){
                        sum += j;
                    }
                    result[Integer.parseInt(Thread.currentThread().getName())]  =  sum;
                }
            };
        }
        for(int i=0;i<3;i++){
            threads[i].start();
        }

        while(Thread.activeCount()>2){
            Thread.yield();
        }

        int sum = 0;
        for(int i=0;i<3;i++){
            sum += result[i];
        }
        System.out.println("1到10的和为:"+sum);
    }
}