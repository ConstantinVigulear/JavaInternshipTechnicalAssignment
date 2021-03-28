package Vigulear;

public class IncorrectCommandFormatException extends Exception {
    public IncorrectCommandFormatException() {
        super();
        System.out.println("\nError: Incorrect command format! Please refer to examples below.\nExamples:");
        System.out.println("\t- to create a new user:\n\t\tjava -jar myaplication.jar -createUser -fn='FirstName' -ln='LastName' -un='UserName'");
        System.out.println("\t- to show all users:\n\t\tjava -jar myaplication.jar -showAllUsers");
        System.out.println("\t- to add a new task:\n\t\tjava -jar myaplication.jar -addTask -un='userName' -tt='Task Title' -td='Task Description'");
        System.out.println("\t- to show all tasks:\n\t\tjava -jar myaplication.jar -showTasks -un='userName'");
    }
}