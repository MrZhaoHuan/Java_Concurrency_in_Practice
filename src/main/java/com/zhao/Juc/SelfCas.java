package com.zhao.Juc;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-07-13 17:56
 * @描述 模拟cas算法实现原子性
 */
@SuppressWarnings("all")
public class SelfCas {

    private static class SelfAtomicInteger {
        private volatile int count; //保证内存可见性

        public SelfAtomicInteger(int initCount) {
            this.count = initCount;
        }

        /**
         * @描述 模拟cas算法——compareAndSwap
         * @返回值 void
         */
        public void getAndInc() {
            int temp = count;
            synchronized (this) {
                //如果线程本地内存中的值和主内存中的值不相同
                if (temp != count) {
                    //则重新从主内存中拿
                    temp = count;
                }
                count = temp + 1;


                //if(temp == count){
                //    count++;  //如果线程本地内存中的值和主内存中的值相同，说明其他线程没有修改
                //}else{
                //    //否则,什么也不做
                //}
            }
        }

        public int get() {
            return count;
        }
    }

    //自增任务
    private static class LoopRunnable implements Runnable {
        private SelfAtomicInteger count = new SelfAtomicInteger(0);

        @Override
        public void run() {
            try {
                Thread.sleep(50);
                count.getAndInc();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public int getCount() {
            return count.get();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        LoopRunnable runnable = new LoopRunnable();

        for (int i = 0; i < 20000; i++) {
            new Thread(runnable).start();
        }
        //让自增线程搞完
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        //再搞主线程
        System.out.println(runnable.getCount());
    }
}