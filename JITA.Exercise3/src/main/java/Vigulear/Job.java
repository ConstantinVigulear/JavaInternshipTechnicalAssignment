package Vigulear;

public class Job {
    private String taskTitle;
    private String userName;

    public Job(String taskTitle, String userName) {
        this.taskTitle = taskTitle;
        this.userName = userName;
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

    @Override
    public String toString() {
        return String.format("%-15s", this.getTaskTitle()) + String.format("%-15s", this.getUserName());
    }
}
