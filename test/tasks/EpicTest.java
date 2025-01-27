package tasks;

import manager.InMemoryTaskManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EpicTest {
    InMemoryTaskManager taskManager = new InMemoryTaskManager();

    @Test
    void checkStatus() {
        Epic epic1 = new Epic("Большая задача №1", "Очень большая задача c двумя подзадачами");
        taskManager.createNewEpic(epic1);
        Subtask subtask1_1 = new Subtask(Status.NEW, "Подзадача №1", "Подзадача большой задачи №1", epic1.getId());
        taskManager.createNewSubtask(subtask1_1);
        assertEquals(epic1.getStatus(), Status.NEW, "Неверный статус");
        Subtask subtask1_2 = new Subtask(Status.IN_PROGRESS, "Подзадача №2", "Подзадача большой задачи №1", epic1.getId());
        taskManager.createNewSubtask(subtask1_2);
        assertEquals(epic1.getStatus(), Status.IN_PROGRESS, "Неверный статус");

    }
}