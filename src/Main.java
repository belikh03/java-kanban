public class Main {

    public static void main(String[] args) {
        System.out.println("Поехали!");
        TaskManager taskManager = new TaskManager();
        Task task1 = new Task(Status.NEW, "Задача №1", taskManager.getIdForAnyTask(), "Купить хлеб");
        Task task2 = new Task(Status.NEW, "Задача №2", taskManager.getIdForAnyTask(), "Купить бритву");
        taskManager.createNewTask(task1);
        taskManager.createNewTask(task2);

        Epic epic1 = new Epic("Большая задача №1", taskManager.getIdForAnyTask(), "Очень большая задача c двумя подзадачами");
        Epic epic2 = new Epic("Большая задача №2", taskManager.getIdForAnyTask(), "Очень большая задача с одной подзадачей");
        taskManager.createNewEpic(epic1);
        taskManager.createNewEpic(epic2);

        Subtask subtask1_1 = new Subtask(Status.IN_PROGRESS, "Подзадача №1", taskManager.getIdForAnyTask(), "Подзадача большой задачи №1", epic1);
        Subtask subtask1_2 = new Subtask(Status.NEW, "Подзадача №2", taskManager.getIdForAnyTask(), "Подзадача большой задачи №1", epic1);
        Subtask subtask2_1 = new Subtask(Status.NEW, "Подзадача №3", taskManager.getIdForAnyTask(), "Подзадача большой задачи №2", epic2);
        taskManager.createNewSubtask(subtask1_1);
        taskManager.createNewSubtask(subtask1_2);
        taskManager.createNewSubtask(subtask2_1);

        System.out.println(taskManager.getAllEpics());
        System.out.println(taskManager.getAllTasks());
        System.out.println(taskManager.getAllSubtasks());
        System.out.println(epic1.status);
        System.out.println(epic2.status);
        System.out.println(epic1.getSubtaskList());
        System.out.println(epic2.getSubtaskList());

    }
}
