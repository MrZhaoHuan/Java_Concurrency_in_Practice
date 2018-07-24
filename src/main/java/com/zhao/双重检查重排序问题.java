package com.zhao;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-07-13 11:35
 * @描述
 *          2处可能导致jvm重排序:
 *               temp = allocationMemory();
 *               instance  = temp;
 *               初始化temp实例数据
 *
 *       假设线程A执行到instance  = temp;时，线程B刚好执行1处，此时instance!=null,直接返回，
 *       但是返回的instance其实是并不完整的。
 *
 *
 *       解决办法:给变量instance增加volatile修饰(作用是禁止jvm指令的重排序)
 *
 */
public class 双重检查重排序问题{
    private static 双重检查重排序问题 instance;
    private 双重检查重排序问题(){

    }

    public static  双重检查重排序问题 getInstance(){
          if(null==instance){  //1
              synchronized (Object.class){
                  if(null==instance){
                      instance = new 双重检查重排序问题(); //2
                  }
              }
          }
          return instance;
    }
}