package tasks;

import java.util.Objects;

public class Task {
    private final String name;
    private String description;
    private final int id;
    Status status;

    public Task(Status status, String name, int id, String description) {
        this.status = status;
        this.name = name;
        this.id = id;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id || (Objects.equals(name, task.name) && Objects.equals(description, task.description));
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, id);
    }
}
