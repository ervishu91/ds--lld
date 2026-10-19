package com.practice.lld.JobScheduler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

class JobExecutionService {

    private final PriorityBlockingQueue<Job> readyQueue;
    private final ExecutorService executorService;

    public JobExecutionService(int workerCount) {

        readyQueue = new PriorityBlockingQueue<>(
                11,
                (a, b) -> {
                    int cmp = Integer.compare(
                            b.getPriority(),
                            a.getPriority()
                    );

                    if (cmp != 0) {
                        return cmp;
                    }

                    return Long.compare(
                            a.getJobId(),
                            b.getJobId()
                    );
                }
        );

        executorService =
                Executors.newFixedThreadPool(workerCount);

        for (int i = 0; i < workerCount; i++) {
            executorService.submit(this::consume);
        }
    }

    public void submit(Job job) {
        readyQueue.offer(job);
    }

    private void consume() {
        try {
            while (!Thread.currentThread().isInterrupted()) {

                Job job = readyQueue.take();

                if (!job.tryStart()) {
                    continue;
                }

                try {
                    job.getTask().run();
                    job.complete();

                } catch (Exception e) {
                    job.fail();
                }
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
