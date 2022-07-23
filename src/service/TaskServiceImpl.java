package service;

import entity.Task;
import repository.TaskRepository;
import util.EpochConverterUtil;
import util.InputUtil;

import java.util.ArrayList;
import java.util.List;

public class TaskServiceImpl implements TaskService {
    // Menggunakan repository untuk mengakses semua data task
    private TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void showAllTasks() {
        int taskId = 1;
        String[] tasks = taskRepository.getAll();

        for (String task : tasks) {
            String[] taskData = task.split(",");
            System.out.println(taskId + ". " + taskData[0]);
            taskId++;
        }
    }

    @Override
    public Integer showSumOfAllTasks() {
        return taskRepository.getSumOfAllTasks();
    }

    @Override
    public void showTaskDetails(Integer id) {
        if ((id > taskRepository.getSumOfAllTasks()) || id <= 0) {
            System.out.println("\nPilihan Anda tidak valid!\n");
        } else {
            String[] tasks = taskRepository.getAll();

            for (Integer i = 0; i < tasks.length; i++) {
                if (i.equals(id-1)) {
                    String[] taskData = tasks[i].split(",");
                    String taskDeadline = EpochConverterUtil.convertEpochToDate(taskData[2]);
                    System.out.println("\nDetail Pengingat Tugas");
                    System.out.println("======================");
                    System.out.println("Nama tugas: " + taskData[0]);
                    System.out.println("Deskripsi tugas: " + taskData[1]);
                    System.out.println("Deadline tugas: " + taskDeadline + "\n");
                }
            }
        }
    }

    @Override
    public void addTask(String taskName, String taskDescription, String taskDeadline) {
        Task task = new Task(taskName, taskDescription, taskDeadline);
        taskRepository.addTask(task);
    }

    @Override
    public void editTask(Integer taskId) {
        if ((taskId > taskRepository.getSumOfAllTasks()) || taskId <= 0) {
            System.out.println("\nPilihan Anda tidak valid!\n");
        } else {
            System.out.println("\nEdit Pengingat Tugas");
            System.out.println("====================");
            taskRepository.editTask(taskId);
        }
    }

    @Override
    public void removeTask(Integer taskId) {
        if ((taskId > taskRepository.getSumOfAllTasks()) || taskId <= 0) {
            System.out.println("\nPilihan Anda tidak valid!\n");
        } else {
            var alert = InputUtil.input("Apakah Anda yakin? (ketik \"y\" untuk lanjut atau \"x\" untuk batal)");
            if (alert.equals("x")) {
                System.out.print("\n");
            } else if (alert.equals("y")) {
                taskRepository.removeTask(taskId);
            } else {
                System.out.println("\nPilihan Anda tidak valid!\n");
            }
        }
    }
}
