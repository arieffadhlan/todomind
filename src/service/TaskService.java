package service;

public interface TaskService {
    void showAllTasks();
    Integer showSumOfAllTasks();
    void showTaskDetails(Integer id);
    void addTask(String taskName, String taskDescription, String taskDeadline);
    void editTask(Integer taskId);
    void removeTask(Integer taskId);
}
