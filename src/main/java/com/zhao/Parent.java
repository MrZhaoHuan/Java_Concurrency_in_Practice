package com.zhao;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-07-12 13:04
 * @描述
 */
public class Parent {
    public static boolean isRun;
    public static  int age;

    public int getAge() {
        return age;
    }

    public synchronized void incr(){
        age++;
        isRun = true;
    }
}