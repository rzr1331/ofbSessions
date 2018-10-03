package Assignment2;

import java.util.concurrent.CountDownLatch;
import java.util.logging.Logger;

public class Task extends Thread{
    private int taskId;
    private int duration;
    private int sequenceNo;
    private Logger logger = Logger.getLogger(Task.class.getName());


    public Task(int taskId, int duration, int sequenceNo) {
        this.taskId = taskId;
        this.duration = duration;
        this.sequenceNo = sequenceNo;
    }

    @Override
    public void run() {
        try {
            sleep(getDuration());
            logger.info("Task"+getTaskId()+" & Sequence :"+getSequenceNo()+" completed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getSequenceNo() {
        return sequenceNo;
    }

    public void setSequenceNo(int sequenceNo) {
        this.sequenceNo = sequenceNo;
    }
}
