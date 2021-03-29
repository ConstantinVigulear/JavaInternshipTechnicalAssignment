package Vigulear;

public class IncorrectCommandFormatException extends Exception {
    public IncorrectCommandFormatException() {
        super();
        System.out.println("\nError: Incorrect command format! Please refer to examples below.\nExamples:");
        System.out.println("\t- to create a new user:\n\t\tjava -jar myapplication.jar -createUser -fn='FirstName' -ln='LastName' -un='UserName'");
        System.out.println("\t- to show all users:\n\t\tjava -jar myapplication.jar -showAllUsers");
        System.out.println("\t- to add a new task:\n\t\tjava -jar myapplication.jar -addTask -tt='Task Title' -td='Task Description'");
        System.out.println("\t- to show user's tasks:\n\t\tjava -jar myapplication.jar -showTasks -un='userName'");
        System.out.println("\t- to assign task to users:\n\t\tjava -jar myapplication.jar -assignTaskToUsers -tt='taskTitle' -un='userName'");
        System.out.println("\t- to show users on task:\n\t\tjava -jar myapplication.jar -showUsersOnTask -tt='taskTitle'");
    }
}