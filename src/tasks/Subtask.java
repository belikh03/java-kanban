package tasks;

public class Subtask extends Task {
    private final int epicId;


    public Subtask(Status status, String name, String description, int epicId) {
        super(status, name, description);
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }
}
