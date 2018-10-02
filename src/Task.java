import java.util.concurrent.CountDownLatch;
import java.util.logging.Logger;

public class Task extends Thread{
    private String taskName;
    private int duration;
    private CountDownLatch countDownLatch;

    public Task(String taskName, int duration, CountDownLatch countDownLatch) {
        this.taskName = taskName;
        this.duration = duration;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            sleep(getDuration());
            if (countDownLatch!=null){
            countDownLatch.countDown();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }

    public void setCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }
}
