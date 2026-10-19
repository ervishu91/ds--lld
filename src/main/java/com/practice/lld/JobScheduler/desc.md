Enities
Job 
    +id
    +priority
    +scheduleAt/ time-- future time also
    +status
Task
    it will keep the job running , get from th equeue and run it 

jobSchedule
    schedule(job);
    cancel(Job);

flow
jobscheduler will put the job in delayQueue
or we can have ask executor running and submit task to taks executor and give the delay queue as quee
queue will be prirotise forst by time then by prioirty
taks will see if job status is cancel it will just skip the job