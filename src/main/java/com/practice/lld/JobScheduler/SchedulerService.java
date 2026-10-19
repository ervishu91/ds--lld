package com.practice.lld.JobScheduler;

import java.util.concurrent.DelayQueue;

class SchedulerService {

    private final DelayQueue<Job> delayQueue;
    private final JobExecutionService executionService;

    public SchedulerService(
            DelayQueue<Job> delayQueue,
            JobExecutionService executionService) {

        this.delayQueue = delayQueue;
        this.executionService = executionService;

        Thread schedulerThread =
                new Thread(this::consumeDelayQueue);

        schedulerThread.start();
    }

    public void schedule(Job job) {
        delayQueue.offer(job);
    }

    private void consumeDelayQueue() {
        try {
            while (!Thread.currentThread().isInterrupted()) {

                Job job = delayQueue.take();

                if (job.getStatus() == JobStatus.CANCELLED) {
                    continue;
                }

                executionService.submit(job);
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
