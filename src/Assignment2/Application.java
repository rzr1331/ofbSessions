package Assignment2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Logger;

public class Application {

    private static Logger logger = Logger.getLogger(Assignment1.Application.class.getName());
    private static int numberOfTasksToCreate = 10;
    private static int minId = 1;
    private static int maxId = 2;
    private static int minSeq = 1;
    private static int maxSeq = 10;

    public static void main(String[] args) {

        List<Assignment2.Task> taskList = new ArrayList<>();
        Random random = new Random();
        CountDownLatch countDownLatch = new CountDownLatch(numberOfTasksToCreate - 2);

        // Creating Dummy Task List
        for (int i = 1; i <= numberOfTasksToCreate; i++) {
            int taskId = random.nextInt(maxId - minId + 1) + minId;
            int duration = random.nextInt(3000 - 1000 + 1) + 1000;
            int sequenceNumber = random.nextInt(maxSeq - minSeq + 1) + minSeq;
            taskList.add(new Assignment2.Task(taskId, duration, sequenceNumber));
        }

        // Creating Group Task Executor
        Map<Integer, List<Task>> groupTaskMap = new HashMap<>();
        taskList.forEach(task -> {
            int taskId = task.getTaskId();
            if (groupTaskMap.containsKey(taskId)) {
                List<Task> miniTaskList = new ArrayList<>();
                miniTaskList.addAll(groupTaskMap.get(taskId));
                miniTaskList.add(task);
                groupTaskMap.replace(taskId, miniTaskList);
            } else {
                groupTaskMap.put(task.getTaskId(),
                    Arrays.asList(new Task(taskId, task.getDuration(), task.getSequenceNo())));
            }
        });

        groupTaskMap.keySet().forEach(id-> {
            logger.info("Starting Group Tasks with Id : " + id);
            GroupExecuter groupExecuter = new GroupExecuter(groupTaskMap.get(id));
            groupExecuter.start();
        });
    }
}
