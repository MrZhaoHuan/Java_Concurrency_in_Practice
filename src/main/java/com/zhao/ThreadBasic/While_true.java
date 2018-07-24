package com.zhao.ThreadBasic;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-07-13 20:40
 * @描述
 *       线程stopThread中的if语句块代码不会执行,因为while(true)速度太快，cpu来不及从主内存中去拿最新的stop变量值
 *
 *       解决办法:
 *              1.用volatile修饰stop变量，保证某个线程的写对另1个线程可见
 *              2.在while(true)中随便加入1行代码，使得cpu有时间刷新本地缓存
 */
public class While_true{
    private  boolean stop;
    //private volatile   boolean stop;

    public boolean isStop() {
        return stop;
    }

    public static void main(String[] args) {
            final While_true obj = new While_true();


            new Thread("setValueThread"){
                @Override
                public void run(){
                    try {
                        Thread.sleep(1000); //让线程stopThread执行后，我才去设置stop变量值
                        obj.stop = true;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();


            new Thread("stopThread"){
                @Override
                public void run(){
                   while (true){
                       //System.out.println("随便加入一行代码，可以使得cpu从主内存中去拿最新的stop值");
                       if(obj.isStop()){
                           System.out.println("-----停止线程----");
                           break;
                       }
                   }
                }
            }.start();

    }
}