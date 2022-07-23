package test.service;

import repository.TaskRepository;
import repository.TaskRepositoryImpl;
import service.TaskService;
import service.TaskServiceImpl;

import java.text.ParseException;

public class TaskServiceTest {
    public static void main(String[] args) {
        testShowAllTask();
    }

    public static void testShowAllTask() {
        TaskRepository taskRepository = new TaskRepositoryImpl();
        TaskService taskService = new TaskServiceImpl(taskRepository);
        taskService.showAllTasks();
    }

    public static void testShowSumOfAllTasks() {
        TaskRepository taskRepository = new TaskRepositoryImpl();
        TaskService taskService = new TaskServiceImpl(taskRepository);
        System.out.println(taskService.showSumOfAllTasks());
    }

    public static void testShowTaskDetails() throws ParseException {
        TaskRepository taskRepository = new TaskRepositoryImpl();
        TaskService taskService = new TaskServiceImpl(taskRepository);
        taskService.showTaskDetails(2);
    }

    public static void testAddtask(){
        TaskRepository taskRepository = new TaskRepositoryImpl();
        TaskService taskService = new TaskServiceImpl(taskRepository);

        taskService.addTask("Tubes Pemrograman Berorientasi Objek Lanjutan", "Membuat program TodoMind menggunakan Java", "1655726400");
        taskService.addTask("Tubes Manajemen Sistem Basis Data", "Membuat web sekolah menggunakan Laravel", "1655726400");
        taskService.addTask("Design Figma ADS", "Mendesain mockup dan prototype aplikasi PYOURD menggunakan Figma", "1655726400");

        System.out.print("\n");
        taskService.showAllTasks();
    }

    public static void testEditTask() {
        TaskRepository taskRepository = new TaskRepositoryImpl();
        TaskService taskService = new TaskServiceImpl(taskRepository);
        taskService.editTask(3);
    }

    public static void testRemoveTask() {
        TaskRepository taskRepository = new TaskRepositoryImpl();
        TaskService taskService = new TaskServiceImpl(taskRepository);

        taskService.addTask("Tubes Pemrograman Berorientasi Objek Lanjutan", "Membuat program TodoMind menggunakan Java", "1655726400");
        taskService.addTask("Tubes Manajemen Sistem Basis Data", "Membuat web sekolah menggunakan Laravel", "1655726400");
        taskService.addTask("Design Figma ADS", "Mendesain mockup dan prototype aplikasi PYOURD menggunakan Figma", "1655726400");

        taskService.removeTask(3);
    }
}
