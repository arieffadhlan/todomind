package entity;

public class Task {
    private String taskName;
    private String taskDescription;
    private String taskDeadline;

    public Task() {};

    public Task(String task, String taskDescription, String taskDeadline) {
        this.taskName = task;
        this.taskDescription = taskDescription;
        this.taskDeadline = taskDeadline;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public String getTaskDeadline() {
        return taskDeadline;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public void setTaskDeadline(String taskDeadline) {
        this.taskDeadline = taskDeadline;
    }
}
