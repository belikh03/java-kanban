package manager;

import tasks.Epic;
import tasks.Subtask;
import tasks.Task;

import java.util.List;

public interface TaskManager {
    List<Task> getAllTasks();

    List<Epic> getAllEpics();

    List<Subtask> getAllSubtasks();

    List<Subtask> getEpicSubtasks(Epic epic);

    void deleteAllTasks();

    void deleteAllEpics();

    void deleteAllSubtask();

    Task getTaskById(int id);

    Epic getEpicById(int id);

    Subtask getSubtaskById(int id);

    int createNewTask(Task task);

    int createNewEpic(Epic epic);

    int createNewSubtask(Subtask subtask);

    void changeTask(Task task);

    void changeEpic(Epic epic);

    void changeSubtask(Subtask subtask);

    void removeTask(int id);

    void removeEpic(int id);

    void removeSubtask(int id);

    List<Task> getHistory();
}
