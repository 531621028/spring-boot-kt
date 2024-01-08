package com.hkk.spring.kt.service.pool;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONUtil;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import lombok.SneakyThrows;

public class ThreadPoolExecutorDemo extends ThreadPoolExecutor {

    private final ThreadLocal<Long> startTime = new ThreadLocal<>();
    private final Map<Long, AtomicLong> successCntMap = new ConcurrentHashMap<>();
    private final Map<Long, AtomicLong> failCntMap = new ConcurrentHashMap<>();

    @SneakyThrows
    public static void main(String[] args) {

        ThreadPoolExecutorDemo demo = new ThreadPoolExecutorDemo();

        for (int i = 0; i < 30; i++) {
            demo.execute(() -> {
                try {
                    int randomInt = RandomUtil.randomInt(1000);
                    if (randomInt < 200) {
                        throw new RuntimeException();
                    }
                    Thread.sleep(randomInt);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        demo.shutdown();
        Thread.sleep(10000);
        System.out.println(JSONUtil.toJsonStr(demo.getSuccessCntMap()));
        System.out.println(JSONUtil.toJsonStr(demo.getFailCntMap()));
    }

    public ThreadPoolExecutorDemo() {
        this(1, 20, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>(10));

    }

    public ThreadPoolExecutorDemo(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
        BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        startTime.set(System.currentTimeMillis());
        System.out.println("开始执行" + r);
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        long costTime = System.currentTimeMillis() - this.startTime.get();
        System.out.printf("线程执行耗时：%d mils%n", costTime);
        startTime.remove();
        if (t == null) {
            AtomicLong atomicLong = successCntMap.putIfAbsent(System.currentTimeMillis() / 1000, new AtomicLong());
            assert atomicLong != null;
            atomicLong.incrementAndGet();
        } else {
            AtomicLong atomicLong = failCntMap.putIfAbsent(System.currentTimeMillis() / 1000, new AtomicLong());
            assert atomicLong != null;
            atomicLong.incrementAndGet();
        }
    }

    public Map<Long, AtomicLong> getSuccessCntMap() {
        return successCntMap;
    }

    public Map<Long, AtomicLong> getFailCntMap() {
        return failCntMap;
    }
}
