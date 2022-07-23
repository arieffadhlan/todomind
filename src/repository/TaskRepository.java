package repository;

import entity.Task;

public interface TaskRepository {
    String[] getAll();
    Integer getSumOfAllTasks();
    void addTask(Task task);
    void editTask(Integer taskId);
    void removeTask(Integer taskId);
    void notification(String tDeadline);
}
