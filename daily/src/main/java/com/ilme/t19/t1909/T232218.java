package com.ilme.t19.t1909;

import lombok.extern.slf4j.Slf4j;

/**
 * 死锁
 *
 * @author yuwenkai
 * @date 2019-09-23 22:18
 **/
@Slf4j
public class T232218 {

    static T232218 _1T = new T232218();

    static T232218 _2T = new T232218();

    // 必须使用main方法才可以产生死锁
    public static void main(String[] args) {
        Thread t1 = new Thread(()  -> {
            synchronized (T232218._1T) {
                log.debug("T1 -> _1T");
                synchronized (T232218._2T) {
                    log.debug("T1 -> _2T");
                }
            }
        });

        Thread t2 = new Thread (() -> {
            synchronized (T232218._2T) {
                log.debug("T2 -> _2T");
                synchronized (T232218._1T) {
                    log.debug("T2 -> _1T");
                }
            }
        });

        t1.start();
        t2.start();
    }

}
