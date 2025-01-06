import tasks.*;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private int idCounter;
    private final HashMap<Integer, Task> tasks; // = new HashMap<>();
    private final HashMap<Integer, Epic> epics; // = new HashMap<>();
    private final HashMap<Integer, Subtask> subtasks; // = new HashMap<>();

    public TaskManager() {
        tasks = new HashMap<>();
        epics = new HashMap<>();
        subtasks = new HashMap<>();
        idCounter = 0;
    }

    public ArrayList<Task> getAllTasks() {
        ArrayList<Task> allTasks = new ArrayList<>();
        for (Task task : tasks.values()) {
            allTasks.add(task);
        }
        return allTasks;
    }

    public ArrayList<Epic> getAllEpics() {
        ArrayList<Epic> allEpics = new ArrayList<>();
        for (Epic epic : epics.values()) {
            allEpics.add(epic);
        }
        return allEpics;
    }

    public ArrayList<Subtask> getAllSubtasks() {
        ArrayList<Subtask> allSubtasks = new ArrayList<>();
        for (Subtask subtask : subtasks.values()) {
            allSubtasks.add(subtask);
        }
        return allSubtasks;
    }

    public void deleteAllTasks() {
        tasks.clear();
    }

    public void deleteAllEpics() {
        for (Epic epic : epics.values()) {
            for (Subtask subtask : epic.getSubtaskList()) {
                subtasks.remove(subtask.getId());
            }
        }
        epics.clear();
    }

    public void deleteAllSubtask() {
        for (Subtask subtask : subtasks.values()) {
            subtask.getCurrentEpic().removeSubtaskFromEpicSubtaskList(subtask);
        }
        subtasks.clear();
    }

    public Task getTaskById(int id) {
        if (tasks.containsKey(id)) {
            return tasks.get(id);
        }
        return null;
    }

    public Epic getEpicById(int id) {
        if (epics.containsKey(id)) {
            return epics.get(id);
        }
        return null;
    }

    public Subtask getSubtaskById(int id) {
        if (subtasks.containsKey(id)) {
            return subtasks.get(id);
        }
        return null;
    }

    public void createNewTask(Task task) {
        tasks.put(task.getId(), task);
    }

    public void createNewEpic(Epic epic) {
        epics.put(epic.getId(), epic);
    }

    public void createNewSubtask(Subtask subtask) {
        subtasks.put(subtask.getId(), subtask);
    }

    public void changeTask(Task task) {
        tasks.put(task.getId(), task);
    }

    public void changeEpic(Epic epic) {
        epics.put(epic.getId(), epic);
    }

    public void changeSubtask(Subtask subtask) {
        subtasks.put(subtask.getId(), subtask);
    }

    public void removeTask(int id) {
        tasks.remove(id);
    }

    public void removeEpic(int id) {
        Epic epic = epics.get(id);
        System.out.println(epic.getSubtaskList());
        for (Subtask subtask : epic.getSubtaskList()) {
            subtasks.remove(subtask.getId());
        }
        epics.remove(id);
    }

    public void removeSubtask(int id) {
        Subtask subtask = subtasks.get(id);
        subtask.getCurrentEpic().removeSubtaskFromEpicSubtaskList(subtask);
        subtasks.remove(id);
    }

    public ArrayList<Subtask> getEpicSubtasks(Epic epic) {
        if (epics.containsValue(epic)) {
            return epic.getSubtaskList();
        } else {
            return null;
        }
    }

    public int getIdForAnyTask() {
        return idCounter++;
    }
}
