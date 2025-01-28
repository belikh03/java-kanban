package manager;

import history.HistoryManager;
import tasks.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTaskManager implements TaskManager {
    private int idCounter;
    private final Map<Integer, Task> tasks;
    private final Map<Integer, Epic> epics;
    private final Map<Integer, Subtask> subtasks;
    private HistoryManager historyManager = Manager.getDefaultHistory();

    public InMemoryTaskManager() {
        tasks = new HashMap<>();
        epics = new HashMap<>();
        subtasks = new HashMap<>();
        idCounter = 1;
    }

    @Override
    public List<Task> getAllTasks() {
        List<Task> allTasks = new ArrayList<>();
        for (Task task : tasks.values()) {
            allTasks.add(task);
        }
        return allTasks;
    }

    @Override
    public List<Epic> getAllEpics() {
        List<Epic> allEpics = new ArrayList<>();
        for (Epic epic : epics.values()) {
            allEpics.add(epic);
        }
        return allEpics;
    }

    @Override
    public List<Subtask> getAllSubtasks() {
        List<Subtask> allSubtasks = new ArrayList<>();
        for (Subtask subtask : subtasks.values()) {
            allSubtasks.add(subtask);
        }
        return allSubtasks;
    }

    @Override
    public void deleteAllTasks() {
        tasks.clear();
    }

    @Override
    public void deleteAllEpics() {
        subtasks.clear();
        epics.clear();
    }

    @Override
    public void deleteAllSubtask() {
        for (Epic epic : epics.values()) {
            epic.deleteAllSubtasks();
        }
        subtasks.clear();
    }

    @Override
    public Task getTaskById(int id) {
        historyManager.add(tasks.get(id));
        return tasks.get(id);
    }

    @Override
    public Epic getEpicById(int id) {
        historyManager.add(epics.get(id));
        return epics.get(id);
    }

    @Override
    public Subtask getSubtaskById(int id) {
        historyManager.add(subtasks.get(id));
        return subtasks.get(id);
    }

    @Override
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

    @Override
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

    @Override
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

    @Override
    public void changeTask(Task task) {
        if (task != null && tasks.containsKey(task.getId())) {
            tasks.put(task.getId(), task);
        }
    }

    @Override
    public void changeEpic(Epic epic) {
        if (epic != null && epics.containsKey(epic.getId())) {
            Epic changeableEpic = epics.get(epic.getId());
            changeableEpic.setName(epic.getName());
            changeableEpic.setDescription(epic.getDescription());
        }
    }

    @Override
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

    @Override
    public void removeTask(int id) {
        tasks.remove(id);
    }

    @Override
    public void removeEpic(int id) {
        if (epics.containsKey(id)) {
            Epic epic = epics.get(id);
            for (Subtask subtask : epic.getSubtaskList()) {
                subtasks.remove(subtask.getId());
            }
            epics.remove(id);
        }
    }

    @Override
    public void removeSubtask(int id) {
        if (subtasks.containsKey(id)) {
            Subtask subtask = subtasks.get(id);
            epics.get(subtask.getEpicId()).removeSubtaskFromEpicSubtaskList(subtask);
            subtasks.remove(id);
        }
    }

    @Override
    public List<Subtask> getEpicSubtasks(Epic epic) {
        if (epics.containsValue(epic)) {
            return epic.getSubtaskList();
        } else {
            return null;
        }
    }

    @Override
    public List<Task> getHistory() {
        return historyManager.getHistory();
    }

    private int getIdForAnyTask() {
        return idCounter++;
    }
}
