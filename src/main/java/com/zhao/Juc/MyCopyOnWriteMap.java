package com.zhao.Juc;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-07-15 10:08
 * @描述  自定义:写时复制map
 */
public class MyCopyOnWriteMap<K,V>{
    private HashMap<K,V> map = new HashMap();
    private ReentrantLock lock = new ReentrantLock();

    public V put(K key, V value) {
        V ret = null;
        lock.lock();
        try{
            HashMap<K, V> copyMap = new HashMap<K, V>(this.map);
            ret = copyMap.put(key,value);
            map = copyMap;
        }finally {
            lock.unlock();
        }
        return ret;
    }

    public void putAll(Map<? extends K, ? extends V> m) {
        lock.lock();
        try{
            HashMap<K, V> copyMap = new HashMap<K, V>(this.map);
            Set<? extends Map.Entry<? extends K, ? extends V>> entries = m.entrySet();
            for(Map.Entry<? extends K, ? extends V> entry:entries){
                 copyMap.put(entry.getKey(),entry.getValue());
            }
            map = copyMap;
        }finally {
            lock.unlock();
        }
    }

    public V get(K key){
        return this.map.get(key);
    }

    public Set<Map.Entry<K,V>> entrySet(){
        return this.map.entrySet();
    }
}