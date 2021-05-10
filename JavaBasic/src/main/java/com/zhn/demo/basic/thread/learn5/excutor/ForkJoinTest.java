package com.zhn.demo.basic.thread.learn5.excutor;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.function.DoublePredicate;

/**
 * Fork-Join框架
 * 并行计算在处处都有大数据的今天已经不是一个新鲜的词汇了，现在已经有单机多核甚至多机集群并行计算。(这里说的是并行，而不是并发。)
 * 严格的将，并行是指系统内有多个任务同时执行，而并发是指系统内有多个任务同时存在，不同的任务按时间分片的方式切换执行，由于切换的
 * 时间很短，给人的感觉好像是在同时执行。
 * Java在JDK7之后加入了并行计算的框架Fork/Join，可以解决我们系统中大数据计算的性能问题。Fork/Join采用的是分治法，Fork是将一个大
 * 任务拆分成若干个子任务，子任务分别去计算，而Join是获取到子任务的计算结果，然后合并，这个是递归的过程。子任务被分配到不同的核上
 * 执行时，效率最高。
 * <p>
 * Result solve(Problem problem) {
 * if (problem is small)
 * directly solve problem
 * else {
 * split problem into independent parts
 * fork new subtasks to solve each part
 * join all subtasks
 * compose result from subresults
 * }
 * }
 */
public class ForkJoinTest {

    public static void main(String[] args) {

        final int SIZE = 10000000;
        double[] numbers = new double[SIZE];
        for (int i = 0; i < SIZE; i++) numbers[i] = Math.random();
        Counter counter = new Counter(numbers, 0, SIZE, x -> x > 0.5);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(counter);
        System.out.println(counter.join());
    }
}

class Counter extends RecursiveTask<Integer> {

    private static final int THRESHOLD = 1000;
    private double[] values;
    private int from;
    private int to;
    private DoublePredicate filter;

    public Counter(double[] values, int from, int to, DoublePredicate filter) {
        this.values = values;
        this.from = from;
        this.to = to;
        this.filter = filter;
    }

    @Override
    protected Integer compute() {
        if (to - from < THRESHOLD) {
            int count = 0;
            for (int i = from; i < to; i++)
                if (filter.test(values[i])) count++;
            return count;
        } else {
            int mid = (from + to) / 2;
            Counter first = new Counter(values, from, mid, filter);
            Counter second = new Counter(values, mid, to, filter);
            invokeAll(first, second);
            return first.join() + second.join();
        }
    }
}