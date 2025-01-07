import tasks.*;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private int idCounter;
    private final HashMap<Integer, Task> tasks;
    private final HashMap<Integer, Epic> epics;
    private final HashMap<Integer, Subtask> subtasks;

    public TaskManager() {
        tasks = new HashMap<>();
        epics = new HashMap<>();
        subtasks = new HashMap<>();
        idCounter = 1;
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
        subtasks.clear();
        epics.clear();
    }

    public void deleteAllSubtask() {
        for (Epic epic : epics.values()) {
            epic.deleteAllSubtasks();
        }
        subtasks.clear();
    }

    public Task getTaskById(int id) {
        return tasks.get(id);
    }

    public Epic getEpicById(int id) {
        return epics.get(id);
    }

    public Subtask getSubtaskById(int id) {
        return subtasks.get(id);
    }

    public int createNewTask(Task task) {
        if (task != null) {
            task.setId(getIdForAnyTask());
            tasks.put(task.getId(), task);
            if (tasks.containsValue(task)) {
                return task.getId();
            }
        }
        return -1;
    }

    public int createNewEpic(Epic epic) {
        if (epic != null) {
            epic.setId(getIdForAnyTask());
            epics.put(epic.getId(), epic);
            if (epics.containsValue(epic)) {
                return epic.getId();
            }
        }
        return -1;
    }

    public int createNewSubtask(Subtask subtask) {
        if (subtask != null) {
            int subtaskEpicId = subtask.getEpicId();
            if (epics.containsKey(subtaskEpicId)) {
                subtask.setId(getIdForAnyTask());
                epics.get(subtaskEpicId).addSubtaskToEpicSubtaskList(subtask);
                subtasks.put(subtask.getId(), subtask);
            }
            if (subtasks.containsValue(subtask)) {
                return subtask.getId();
            }
        }
        return -1;
    }

    public void changeTask(Task task) {
        if (task != null && tasks.containsKey(task.getId())) {
            tasks.put(task.getId(), task);
        }
    }

    public void changeEpic(Epic epic) {
        if (epic != null && epics.containsKey(epic.getId())) {
            Epic changeableEpic = epics.get(epic.getId());
            changeableEpic.setName(epic.getName());
            changeableEpic.setDescription(epic.getDescription());
        }
    }

    public void changeSubtask(Subtask subtask) {
        if (subtask != null) {
            int subtaskId = subtask.getId();
            if (subtasks.containsKey(subtaskId) && subtasks.get(subtaskId).getEpicId() == subtask.getEpicId()) {
                epics.get(subtask.getEpicId()).removeSubtaskFromEpicSubtaskList(subtasks.get(subtaskId));
                epics.get(subtask.getEpicId()).addSubtaskToEpicSubtaskList(subtask);
                subtasks.put(subtask.getId(), subtask);
            }
        }
    }

    public void removeTask(int id) {
        tasks.remove(id);
    }

    public void removeEpic(int id) {
        if (epics.containsKey(id)) {
            Epic epic = epics.get(id);
            for (Subtask subtask : epic.getSubtaskList()) {
                subtasks.remove(subtask.getId());
            }
            epics.remove(id);
        }
    }

    public void removeSubtask(int id) {
        if (subtasks.containsKey(id)) {
            Subtask subtask = subtasks.get(id);
            epics.get(subtask.getEpicId()).removeSubtaskFromEpicSubtaskList(subtask);
            subtasks.remove(id);
        }
    }

    public ArrayList<Subtask> getEpicSubtasks(Epic epic) {
        if (epics.containsValue(epic)) {
            return epic.getSubtaskList();
        } else {
            return null;
        }
    }

    private int getIdForAnyTask() {
        return idCounter++;
    }
}
