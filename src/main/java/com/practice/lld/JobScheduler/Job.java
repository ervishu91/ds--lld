package com.practice.lld.JobScheduler;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Job implements Delayed {

    private final long startTime;
    private final int jobId;
    private final int priority;
    private final Runnable task;

    private JobStatus status = JobStatus.SCHEDULED;

    public Job(
            long startTime,
            int jobId,
            int priority,
            Runnable task) {

        this.startTime = startTime;
        this.jobId = jobId;
        this.priority = priority;
        this.task = task;
    }

    public long getJobId() {
        return jobId;
    }

    public int getPriority() {
        return priority;
    }

    public Runnable getTask() {
        return task;
    }

    public synchronized JobStatus getStatus() {
        return status;
    }

    public synchronized boolean cancel() {
        if (status != JobStatus.SCHEDULED) {
            return false;
        }

        status = JobStatus.CANCELLED;
        return true;
    }

    public synchronized boolean tryStart() {
        if (status != JobStatus.SCHEDULED) {
            return false;
        }

        status = JobStatus.RUNNING;
        return true;
    }

    public synchronized void complete() {
        if (status == JobStatus.RUNNING) {
            status = JobStatus.COMPLETED;
        }
    }

    public synchronized void fail() {
        if (status == JobStatus.RUNNING) {
            status = JobStatus.FAILED;
        }
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long remaining = startTime - System.currentTimeMillis();
        return unit.convert(remaining, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed other) {
        Job that = (Job) other;
        return Long.compare(this.startTime, that.startTime);
    }
}