import repository.TaskRepository;
import repository.TaskRepositoryImpl;
import service.TaskService;
import service.TaskServiceImpl;
import view.TaskView;

public class TodoMind {
    public static void main(String[] args) {
        TaskRepository taskRepository = new TaskRepositoryImpl();
        TaskService taskService = new TaskServiceImpl(taskRepository);
        TaskView taskView = new TaskView(taskService);

        taskView.menu();
    }
}
