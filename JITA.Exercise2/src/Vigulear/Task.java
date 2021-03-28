package Vigulear;

public class Task {
    private String userName;
    private String taskTitle;
    private String description;

    public Task(String userName, String taskTitle, String description) {
        this.userName = userName;
        this.taskTitle = taskTitle;
        this.description = description;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
        return this.getUserName() + " " + this.getTaskTitle() + " " + this.getDescription();
    }
}
