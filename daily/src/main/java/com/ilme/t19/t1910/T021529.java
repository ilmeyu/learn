package com.ilme.t19.t1910;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.*;

/**
 * 线程池不允许使用Executors去创建，而是通过ThreadPoolExecutor的方式，这样的处理方式让写的同学更加明确线程池的运行规则，规避资源耗尽的风险。
 *
 * 说明：Executors返回的线程池对象的弊端如下：
 * 1）`FixedThreadPool`和`SingleThreadPool`:
 *   允许的请求队列长度为Integer.MAX_VALUE，可能会堆积大量的请求，从而导致OOM。
 * 2）`CachedThreadPool`:
 *   允许的创建线程数量为Integer.MAX_VALUE，可能会创建大量的线程，从而导致OOM。
 *
 * @author yuwenkai
 * @date 2019-10-02 15:30
 **/
@Slf4j
public class T021529 {

    public static void main(String[] args) {
        T021529 test = new T021529();
        test.example_1();
        test.example_2();
    }

    void example_1() {
        int poolSize = 10;
        ThreadFactory threadFactory = new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build();

        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(poolSize, threadFactory);
    }

    void example_2() {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();

        ThreadPoolExecutor pool;
        pool = new ThreadPoolExecutor(
            10,
            200,
            0L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(1024),
            threadFactory,
            new ThreadPoolExecutor.AbortPolicy()
        );

        pool.execute(() -> log.info(Thread.currentThread().getName()));
        pool.shutdown();
    }
}
