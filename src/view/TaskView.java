package view;

import service.TaskService;
import util.InputUtil;

public class TaskView {
    private TaskService taskService;

    public TaskView(TaskService taskService) {
        this.taskService = taskService;
    }

    public void menu() {
        boolean status = true;
        while (status) {
            System.out.println("Aplikasi TodoMind");
            System.out.println("=================");

            if (taskService.showSumOfAllTasks() == 0) {
                System.out.println("Yeay, Anda tidak mempunyai tugas!");
            } else {
                taskService.showAllTasks();
            }

            System.out.println("Menu:");
            System.out.println("1. Melihat Detail Tugas");
            System.out.println("2. Menambah Pengingat Tugas");
            System.out.println("3. Mengedit Pengingat Tugas");
            System.out.println("4. Menghapus Pengingat Tugas");
            System.out.println("x. Keluar");
            var input = InputUtil.input("Pilihan Anda");
            switch (input) {
                case "1" -> taskDetail();
                case "2" -> addTask();
                case "3" -> editTask();
                case "4" -> removeTask();
                case "x" -> status = false;
                default -> System.out.println("\nPilihan Anda tidak valid!\n");
            }
        }
    }

    public void taskDetail() {
        if (taskService.showSumOfAllTasks() == 0) {
            System.out.println("\nDaftar tugas kosong.\n");
        } else {
            System.out.println("\nMelihat Detail Tugas");
            System.out.println("====================");
            taskService.showAllTasks();
            var task = InputUtil.input("Input nomor tugas yang ingin anda lihat (x untuk batal)");
            if (task.equals("x")) {
                System.out.print("\n");
            } else {
                taskService.showTaskDetails(Integer.valueOf(task));
            }
        }
    }

    public void addTask() {
        System.out.println("\nMenambah Pengingat Tugas");
        System.out.println("========================");
        String taskName = InputUtil.input("Nama tugas");
        String taskDescription = InputUtil.input("Deskripsi tugas");
        String taskDeadline = InputUtil.input("Deadline tugas (contoh: 20-05-2022 19:03:00)");
        var alert = InputUtil.input("Apakah data tugas sudah benar? (ketik \"y\" untuk lanjut atau \"x\" untuk batal)");
        if (alert.equals("x")) {
            System.out.print("\n");
        } else if (alert.equals("y")) {
            taskService.addTask(taskName, taskDescription, taskDeadline);
        } else {
            System.out.println("\nPilihan Anda tidak valid!\n");
        }
    }

    public void editTask() {
        if (taskService.showSumOfAllTasks() == 0) {
            System.out.println("\nDaftar tugas kosong.\n");
        } else {
            System.out.println("\nMengedit Pengingat Tugas");
            System.out.println("========================");
            taskService.showAllTasks();
            var task = InputUtil.input("Input nomor tugas yang akan diedit (x untuk batal)");
            if (task.equals("x")) {
                System.out.print("\n");
            } else {
                taskService.editTask(Integer.valueOf(task));
            }
        }
    }

    public void removeTask() {
        if (taskService.showSumOfAllTasks() == 0) {
            System.out.println("\nDaftar tugas kosong.\n");
        } else {
            System.out.println("\nMenghapus Pengingat Tugas");
            System.out.println("=========================");
            taskService.showAllTasks();
            var task = InputUtil.input("Input nomor tugas yang akan dihapus (x untuk batal)");
            if (task.equals("x")) {
                System.out.print("\n");
            } else {
                taskService.removeTask(Integer.valueOf(task));
            }
        }
    }
}
