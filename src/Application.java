import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Logger;

public class Application {

    private static Logger logger = Logger.getLogger(Application.class.getName());
    private static int numberOfTasksToCreate = 10;
    private static int randomMin = 1000;
    private static int randomMax = 5000;

    public static void main(String[] args) {

        List<Task> taskList = new ArrayList<>();
        Random random = new Random();
        CountDownLatch countDownLatch = new CountDownLatch(numberOfTasksToCreate - 2);

        // Creating n no. of Tasks
        for (int i = 1; i <= numberOfTasksToCreate; i++) {
            String taskName = "TaskNumber-" + i;
            int duration = random.nextInt(randomMax + 1 - randomMin) + randomMin;
            //int duration = i*2000;
            taskList.add(new Task(taskName, duration, countDownLatch));
        }
        Task firstTask = taskList.get(0);
        Task lastTask = taskList.get(numberOfTasksToCreate - 1);
        firstTask.setCountDownLatch(null);
        lastTask.setCountDownLatch(null);

        //For Testing use this :
        //taskList.get(5).setDuration(10000);
        try {
            // Starting first task and waiting for it to complete
            logger.info("Starting : " + firstTask.getTaskName() + " with Duration : " + firstTask.getDuration());
            firstTask.start();
            logger.info("Waiting for : " + firstTask.getTaskName() + " to Finish");
            firstTask.join();

            // Starting tasks and countdowning the latch
            taskList.subList(1,numberOfTasksToCreate-1).forEach(item->{
                logger.info("Starting : " + item.getTaskName() + " with Duration : " + item.getDuration());
                item.start();
            });

            // Waiting for the n-2 Task to complete and then start final task
            countDownLatch.await();
            logger.info("Starting Final Task : " + lastTask.getTaskName() + " with Duration : " + lastTask.getDuration());
            lastTask.start();
        } catch (InterruptedException ie) {
            logger.info(ie.getMessage());
        }
    }
}
