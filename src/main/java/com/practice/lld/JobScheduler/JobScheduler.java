package com.practice.lld.JobScheduler;

public class JobScheduler {

    private final SchedulerService schedulerService;

    public JobScheduler(SchedulerService schedulerService) {
        this.schedulerService = schedulerService;
    }

    public void schedule(Job job) {
        schedulerService.schedule(job);
    }

    public boolean cancel(Job job) {
        return job.cancel();
    }

    public JobStatus getStatus(Job job) {
        return job.getStatus();
    }
}