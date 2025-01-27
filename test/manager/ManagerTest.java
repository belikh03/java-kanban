package manager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ManagerTest {
    Manager manager = new Manager();

    @Test
    void getDefault() {
        Assertions.assertNotNull(manager.getDefault());
    }

    @Test
    void getDefaultHistory() {
        Assertions.assertNotNull(manager.getDefaultHistory());
    }
}