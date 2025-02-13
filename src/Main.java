import manager.InMemoryTaskManager;
import manager.TaskManager;
import tasks.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Поехали!");

        InMemoryTaskManager taskManager = new InMemoryTaskManager();
        Task task1 = new Task(Status.NEW, "Задача №1", "Купить хлеб");
        Task task2 = new Task(Status.NEW, "Задача №2", "Купить бритву");
        taskManager.createNewTask(task1);
        taskManager.createNewTask(task2);

        Epic epic1 = new Epic("Большая задача №1", "Очень большая задача c двумя подзадачами");
        Epic epic2 = new Epic("Большая задача №2", "Очень большая задача с одной подзадачей");
        int epic1Int = taskManager.createNewEpic(epic1);
        taskManager.createNewEpic(epic2);

        Subtask subtask1_1 = new Subtask(Status.NEW, "Подзадача №1", "Подзадача большой задачи №1", epic1.getId());
        Subtask subtask1_2 = new Subtask(Status.NEW, "Подзадача №2", "Подзадача большой задачи №1", epic1.getId());
        Subtask subtask2_1 = new Subtask(Status.NEW, "Подзадача №3", "Подзадача большой задачи №2", epic2.getId());
        int subtask1Int = taskManager.createNewSubtask(subtask1_1);
        taskManager.createNewSubtask(subtask1_2);
        taskManager.createNewSubtask(subtask2_1);
        taskManager.getEpicById(epic1Int);
        printAllTasks(taskManager);

/*        System.out.println("Проверка охранения исходных объектов");
        System.out.println(taskManager.getAllEpics());
        System.out.println(taskManager.getAllTasks());
        System.out.println(taskManager.getAllSubtasks());
        System.out.println(epic1.getStatus());
        System.out.println(epic2.getStatus());
        System.out.println("-----------------------");

//        System.out.println("Проверка удаления всех Epic");
//        taskManager.deleteAllEpics();
//        System.out.println(taskManager.getAllEpics());
//        System.out.println(taskManager.getAllSubtasks());
//        System.out.println("-----------------------");

        System.out.println("Проверка удаления всех Task");
        taskManager.deleteAllTasks();
        System.out.println(taskManager.getAllTasks());
        System.out.println("-----------------------");

//        System.out.println("Проверка удаления Epic по id");
//        taskManager.removeEpic(epic1Int);
//        System.out.println(taskManager.getAllEpics());
//        System.out.println(taskManager.getAllSubtasks());
//        System.out.println("-----------------------");

        System.out.println("Проверка удаления Subtask по id");
        taskManager.removeSubtask(subtask1Int);
        System.out.println(taskManager.getAllSubtasks());
        System.out.println(epic1.getSubtaskList());

//        System.out.println(epic1.getSubtaskList());
//        System.out.println(epic2.getSubtaskList());
//        System.out.println(epic1.getStatus());
//        System.out.println(epic2.getStatus());

        System.out.println("Проверка изменения Epic");
        epic1.setName("Новое имя");
        epic1.setDescription("Новое описание");
        taskManager.changeEpic(epic1);*/
    }

    private static void printAllTasks(TaskManager manager) {
        System.out.println("Задачи:");
        for (Task task : manager.getAllTasks()) {
            System.out.println(task);
        }
        System.out.println("Эпики:");
        for (Epic epic : manager.getAllEpics()) {
            System.out.println(epic);

            for (Task task : manager.getEpicSubtasks(epic)) {
                System.out.println("--> " + task);
            }
        }
        System.out.println("Подзадачи:");
        for (Task subtask : manager.getAllSubtasks()) {
            System.out.println(subtask);
        }

        System.out.println("История:");
        for (Task task : manager.getHistory()) {
            System.out.println(task);
        }
    }
}
