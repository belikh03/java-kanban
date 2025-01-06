import java.util.ArrayList;

public class Epic extends Task {
    private final ArrayList<Subtask> subtaskList;

    public Epic(String name, int id, String description) {
        super(Status.NEW, name, id, description);
        subtaskList = new ArrayList<>();
    }

    public void checkStatus() {

        if (subtaskList.isEmpty()) {
            status = Status.NEW;
        } else if (isSubtasksStatusIsNew()) {
            status = Status.NEW;
        } else if (isSubtasksStatusIsDone()) {
            status = Status.DONE;
        } else {
            status = Status.IN_PROGRESS;
        }
    }

    private boolean isSubtasksStatusIsNew() {
        for (Subtask subtask : subtaskList) {
            if (subtask.getStatus() != Status.NEW) {
                return false;
            }
        }
        return true;
    }

    private boolean isSubtasksStatusIsDone() {
        for (Subtask subtask : subtaskList) {
            if (subtask.getStatus() != Status.DONE) {
                return false;
            }
        }
        return true;
    }

    public void addSubtaskToEpicSubtaskList(Subtask subtask) {
        subtaskList.add(subtask);
        checkStatus();
    }

    public void removeSubtaskFromEpicSubtaskList(Subtask subtask) {
        subtaskList.remove(subtask);
        checkStatus();
    }

    public ArrayList<Subtask> getSubtaskList() {
        return subtaskList;
    }
}
