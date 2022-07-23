package repository;

import entity.Task;
import util.EpochConverterUtil;
import util.InputUtil;

import java.awt.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TaskRepositoryImpl implements TaskRepository {
    private final String DATABASE = "src\\db_todomind.csv";

    @Override
    public String[] getAll() {
        List<String> tasks = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(DATABASE))){
            String task;
            while ((task = br.readLine()) != null) {
                tasks.add(task);
            }
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
        }
        return tasks.toArray(new String[0]);
    }

    @Override
    public Integer getSumOfAllTasks() {
        Integer count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(DATABASE))){
            while (true) {
                String task = br.readLine();
                if (task == null) {
                    break;
                }
                count++;
            }
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
        }
        return count;
    }

    public void checkDatabase(File db) {
        try {
            db.createNewFile();
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
        }
    }

    @Override
    public void addTask(Task task) {
        File db = new File(DATABASE);
        checkDatabase(db);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(db, true))) {
            String taskDeadline = EpochConverterUtil.convertDateToEpoch(task.getTaskDeadline());
            bw.write(task.getTaskName() + "," + task.getTaskDescription() + "," + taskDeadline);
            bw.newLine();
            System.out.println("\nTugas \"" + task.getTaskName() + "\" berhasil ditambahkan!\n");
            notification(task.getTaskDeadline());
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
        }
    }

    @Override
    public void editTask(Integer taskId) {
        File db = new File(DATABASE);
        checkDatabase(db);
        try (BufferedReader br = new BufferedReader(new FileReader(DATABASE))){
            StringBuffer buffer = new StringBuffer();
            String task;
            while ((task = br.readLine()) != null) {
                buffer.append(task);
                buffer.append("\n");
            }

            String contents = buffer.toString();
            String[] taskData = contents.split("\n");
            for (Integer i = 0; i < contents.split("\n").length; i++) {
                if (i.equals(taskId-1)) {
                    String oldTaskData = taskData[i];
                    String taskName = InputUtil.input("Update nama tugas");
                    String taskDescription = InputUtil.input("Update deskripsi tugas");
                    String taskDeadline = InputUtil.input("Update deadline tugas (contoh: 22-05-2022 19:03:00)");
                    String deadline = EpochConverterUtil.convertDateToEpoch(taskDeadline);
                    String newTaskData = taskName + "," + taskDescription + "," + deadline;

                    var alert = InputUtil.input("Apakah data tugas sudah benar? (ketik \"y\" untuk lanjut atau \"x\" untuk batal)");
                    if (alert.equals("x")) {
                        System.out.print("\n");
                        break;
                    } else if (alert.equals("y")) {
                        contents = contents.replaceAll(oldTaskData, newTaskData);

                        FileWriter writer = new FileWriter(DATABASE);
                        writer.append(contents);
                        writer.close();
                        notification(taskDeadline);
                        System.out.println("\nTugas \"" + taskId + "\" berhasil diubah!\n");
                    }
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
        }
    }

    @Override
    public void removeTask(Integer taskId) {
        File db = new File(DATABASE);
        checkDatabase(db);
        File temp = new File("src\\temp.csv");
        checkDatabase(temp);

        try (BufferedReader br = new BufferedReader(new FileReader(db))) {
            Integer currentTaskId = 1;
            String task;

            while ((task = br.readLine()) != null) {
                if (!(currentTaskId.equals(taskId))) {
                    try (BufferedWriter bw = new BufferedWriter(new FileWriter("src\\temp.csv", true))) {
                        bw.write(task);
                        bw.newLine();
                    } catch (IOException e) {
                        System.out.println("Terjadi kesalahan: " + e.getMessage());
                    }
                }
                currentTaskId++;
            }
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
        }

        // Hapus db lama, kemudian rename db sementara dengan nama db lama
        if (!db.delete()) System.out.println("\nFile tidak dapat dihapus!\n");
        if (!temp.renameTo(db)) System.out.println("\nNama file tidak dapat diubah\n");

        System.out.println("\nTugas \"" + taskId + "\" berhasil dihapus!\n");
    }

    @Override
    public void notification(String tDeadline) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date date = new Date();
            String taskDeadline = EpochConverterUtil.convertDateToEpoch(tDeadline);
            String currentDate = EpochConverterUtil.convertDateToEpoch(formatter.format(date));
            var deadline = (Long.parseLong(taskDeadline) - Long.parseLong(currentDate)) * 1000;

            var timerTask = new TimerTask() {
                public void run() {
                    // Menampilkan notifikasi
                    try {
                        if (SystemTray.isSupported()) {
                            SystemTray tray = SystemTray.getSystemTray();
                            Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
                            TrayIcon trayIcon = new TrayIcon(image, "TodoMind");
                            trayIcon.setImageAutoSize(true);
                            trayIcon.setToolTip("TodoMind");
                            tray.add(trayIcon);

                            trayIcon.displayMessage("TodoMind", "Tugas Anda sudah mencapai deadline!", TrayIcon.MessageType.INFO);
                        } else {
                            System.err.println("Sistem tidak support!");
                        }
                    } catch (AWTException e) {
                        e.printStackTrace();
                    }
                }
            };

            var timer = new Timer();
            timer.schedule(timerTask, TimeUnit.MILLISECONDS.toMillis(deadline));
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
