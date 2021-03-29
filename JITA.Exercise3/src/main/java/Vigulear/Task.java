package Vigulear;

public class Task {
    private String taskTitle;
    private String description;

    public Task(String taskTitle, String description) {
        this.taskTitle = taskTitle;
        this.description = description;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("%-15s", this.getTaskTitle()) + String.format("%-45s", this.getDescription());
    }
}
