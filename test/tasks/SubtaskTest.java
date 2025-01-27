package tasks;

import manager.InMemoryTaskManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubtaskTest {
    InMemoryTaskManager taskManager = new InMemoryTaskManager();

    @Test
    void getEpicId() {
        Epic epic = new Epic("Большая задача №1", "Очень большая задача c двумя подзадачами");
        final int epicId = taskManager.createNewEpic(epic);

        Subtask subtask = new Subtask(Status.NEW, "Подзадача №1", "Подзадача большой задачи №1", epic.getId());
        taskManager.createNewSubtask(subtask);
        assertEquals(epicId, subtask.getEpicId(), "Epic Id не совпадают");
    }
}