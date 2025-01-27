package manager;

import history.HistoryManager;
import history.InMemoryHistoryManager;

public class Manager {
    public TaskManager getDefault() {
        return new InMemoryTaskManager();
    }

    public static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }
}
