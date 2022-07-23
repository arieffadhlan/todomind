package test.view;

import repository.TaskRepository;
import repository.TaskRepositoryImpl;
import service.TaskService;
import service.TaskServiceImpl;
import view.TaskView;

public class TaskViewTest {
    public static void main(String[] args) {
        testShowMenu();
    }

    public static void testShowMenu() {
        TaskRepository taskRepository = new TaskRepositoryImpl();
        TaskService taskService = new TaskServiceImpl(taskRepository);
        TaskView taskView = new TaskView(taskService);

        for (var i = 1; i <= 5; i++) {
            taskService.addTask("Tugas ke-" + i, "Deskripsi tugas ke-" + i, "Deadline");
        }

        taskView.menu();
    }

    public static void testTaskDetails() {
        TaskRepository taskRepository = new TaskRepositoryImpl();
        TaskService taskService = new TaskServiceImpl(taskRepository);
        TaskView taskView = new TaskView(taskService);
        taskView.taskDetail();
    }

    public static void testAddTask() {
        TaskRepository taskRepository = new TaskRepositoryImpl();
        TaskService taskService = new TaskServiceImpl(taskRepository);
        TaskView taskView = new TaskView(taskService);
        taskView.addTask();
    }

    public static void testEditTask() {
        TaskRepository taskRepository = new TaskRepositoryImpl();
        TaskService taskService = new TaskServiceImpl(taskRepository);
        TaskView taskView = new TaskView(taskService);
        taskView.editTask();
    }

    public static void testRemoveTask() {
        TaskRepository taskRepository = new TaskRepositoryImpl();
        TaskService taskService = new TaskServiceImpl(taskRepository);
        TaskView taskView = new TaskView(taskService);
        taskView.removeTask();
    }
}
