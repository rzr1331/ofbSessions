package Assignment2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class GroupExecuter extends Thread {
    private List<Task> taskList;
    private Logger logger = Logger.getLogger(GroupExecuter.class.getName());

    public GroupExecuter(List<Task> taskList) {
        this.taskList = taskList;
    }

    @Override
    public void run() {
        try {
            List<Task> sequencedTaskList =
                getTaskList().stream().sorted(Comparator.comparing(Task::getSequenceNo)).collect(
                    Collectors.toList());
            sequencedTaskList.forEach(item -> {
                try {
                    item.start();
                    item.join();
                } catch (InterruptedException ie){
                    logger.info(ie.getMessage());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }
}
