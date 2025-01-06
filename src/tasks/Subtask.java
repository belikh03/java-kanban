package tasks;

public class Subtask extends Task {
    private final Epic currentEpic;


    public Subtask(Status status, String name, int id, String description, Epic currentEpic) {
        super(status, name, id, description);
        this.currentEpic = currentEpic;
        currentEpic.addSubtaskToEpicSubtaskList(this);
    }

    public Epic getCurrentEpic() {
        return currentEpic;
    }
}
