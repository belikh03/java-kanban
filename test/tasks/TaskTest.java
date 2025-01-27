package tasks;

import history.InMemoryHistoryManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import manager.*;


import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    Task task = new Task(Status.NEW, "Первая задача", "Описание");
    TaskManager manager = new InMemoryTaskManager();

    @Test
    void getDescription() {
        Assertions.assertEquals(task.getDescription(), "Описание", "Неверное описание");
    }

    @Test
    void getId() {
        int currentId = manager.createNewTask(task);
        Assertions.assertEquals(currentId, task.getId(), "Неверный ID");
    }

    @Test
    void getName() {
        Assertions.assertEquals(task.name, "Первая задача", "Неверное имя");
    }
}