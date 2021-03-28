/*
Task:
You need to create a java application that will work as a task manager, with this application we should be able to do:

create new users (insert: FirstName, LastName, userName)
show all users (print: FirstName, LastName, number of tasks)
add a task to the user (insert username, Task Title, Description)
show user's tasks (print: Task title, Description)
All data should be kept in the file, writing and reading should happen via serialization and deserilization operations.


Create new users - by running this command:
java -jar myapplication.jar -createUser -fn='FirstName' - ln='LastName' -un='UserName'
UserName should be unique,  don't forget about validation


Show All Users - by running this command:
java -jar myapplication.jar -showAllUsers


Add a task to the user - by running this command:
java -jar myapplication.jar -addTask -un='userName' -tt='Task Title' -td='Task Description'

Show user's tasks - by running this command:
java -jar myapplication.jar -showTasks -un='userName'

BONUS: Combined commands are available: java -jar myapplication.jar -addTask -un='userName' -tt='Task Title' -td='Task Description' -showTasks -showAllUsers etc.
 */

package Vigulear;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Vector;

public class Exercise2 {
    private static final Vector<User> users = new Vector<>();
    private static final Vector<Task> tasks = new Vector<>();

    public static void createUser(String firstName, String lastName, String userName) {
        users.add(new User(firstName, lastName, userName));

        // Opens a file, appends new user and goes to a new line
        try {
            Files.write(Paths.get("users.txt"), (users.lastElement().toString() + "\n").getBytes(), StandardOpenOption.APPEND);
            System.out.println("Successfully wrote User to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void showAllUsers() {
        try {
            FileReader fileReader = new FileReader("users.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;

            // If users.txt is empty print the message
            if (bufferedReader.readLine() == null) {
                System.out.println("File users.txt is empty!");
            } else {
                System.out.println("\nUsers:");
            }

            // Flush fileReader and bufferedReader for future readings
            fileReader = new FileReader("users.txt");
            bufferedReader = new BufferedReader(fileReader);

            // Loop: read the first line, assigns in to variable "line", if "line" is not null prints it
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception exception) {
            System.out.println("\nError: file users.txt not found!");
        }
    }

    public static void addTask(String userName, String taskTitle, String description) {
        tasks.add(new Task(userName, taskTitle, description));

        // Opens a file, appends new task and goes to a new line
        try {
            Files.write(Paths.get("tasks.txt"), (tasks.lastElement().toString() + "\n").getBytes(), StandardOpenOption.APPEND);
            System.out.println("Successfully wrote Task to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void showTasks() {
        try {
            FileReader fileReader = new FileReader("tasks.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;

            // If tasks.txt is empty print the message
            if (bufferedReader.readLine() == null) {
                System.out.println("\nFile tasks.txt is empty!");
            } else {
                System.out.println("\nTasks:");
            }

            // Flush fileReader and bufferedReader for future readings
            fileReader = new FileReader("tasks.txt");
            bufferedReader = new BufferedReader(fileReader);

            // Loop: read the first line, assigns in to variable "line", if "line" is not null prints it
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (Exception exception) {
            System.out.println("\nError: file tasks.txt not found!");
        }
    }

    public static void createOrOpenUsersFile() {
        // This block creates files if they do not exist otherwise it does nothing
        try {
            File usersFile = new File("users.txt");
            if (usersFile.createNewFile()) {
                System.out.println("\nFile " + usersFile.getName() + " has been created.");
            } else {
                System.out.println("\nFile " + usersFile.getName() + " is ready.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void createOrOpenTasksFile() {
        // This block creates files if they do not exist otherwise it does nothing
        try {
            File tasksFile = new File("tasks.txt");
            if (tasksFile.createNewFile()) {
                System.out.println("\nFiles " + tasksFile.getName() + " has been created.");
            } else {
                System.out.println("\nFile " + tasksFile.getName() + " is ready.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        // Defines the operation, yields error message and examples
        for (int iterator = 0; iterator < args.length; iterator++) {
            switch (args[iterator]) {
                case "-createUser" -> {
                    String firstName, lastName, userName;

                    try {

                        // Validating command format
                        if (args.length != 4) {
                            throw new IncorrectCommandFormatException();

                        } else {
                            createOrOpenUsersFile();
                            if (args[iterator + 1].startsWith("-fn")) {
                                firstName = args[iterator + 1].substring(5, args[iterator + 1].length() - 1);
                                if (args[iterator + 2].startsWith("-ln")) {
                                    lastName = args[iterator + 2].substring(5, args[iterator + 2].length() - 1);
                                    userName = args[iterator + 3].substring(5, args[iterator + 3].length() - 1);
                                } else {
                                    lastName = args[iterator + 3].substring(5, args[iterator + 3].length() - 1);
                                    userName = args[iterator + 2].substring(5, args[iterator + 2].length() - 1);
                                }
                            } else if (args[iterator + 2].startsWith("-fn")) {
                                firstName = args[iterator + 2].substring(5, args[iterator + 2].length() - 1);
                                if (args[iterator + 1].startsWith("-ln")) {
                                    lastName = args[iterator + 1].substring(5, args[iterator + 1].length() - 1);
                                    userName = args[iterator + 3].substring(5, args[iterator + 3].length() - 1);
                                } else {
                                    lastName = args[iterator + 3].substring(5, args[iterator + 3].length() - 1);
                                    userName = args[iterator + 1].substring(5, args[iterator + 1].length() - 1);
                                }
                            } else {
                                firstName = args[iterator + 3].substring(5, args[iterator + 3].length() - 1);
                                if (args[iterator + 1].startsWith("-ln")) {
                                    lastName = args[iterator + 1].substring(5, args[iterator + 1].length() - 1);
                                    userName = args[iterator + 2].substring(5, args[iterator + 2].length() - 1);
                                } else {
                                    lastName = args[iterator + 2].substring(5, args[iterator + 2].length() - 1);
                                    userName = args[iterator + 1].substring(5, args[iterator + 1].length() - 1);
                                }
                            }

                            // Validation for userName uniqueness
                            try {
                                FileReader fileReader = new FileReader("users.txt");
                                BufferedReader bufferedReader = new BufferedReader(fileReader);

                                String line;

                                // If users.txt is empty print the message
                                if (bufferedReader.readLine() == null) {
                                    System.out.println("File users.txt is empty!");
                                } else {
                                    System.out.println("\nUsers:");
                                }

                                // Flush fileReader and bufferedReader for future readings
                                fileReader = new FileReader("users.txt");
                                bufferedReader = new BufferedReader(fileReader);

                                // Loop: read the first line, assigns in to variable "line", if "line" is not null, compare user name with new user name
                                while ((line = bufferedReader.readLine()) != null) {
                                    if (line.split(" ")[2].equals(userName)) {
                                        System.out.println("Error: such user name already exists!");
                                        System.exit(0);
                                    }
                                }
                            } catch (Exception exception) {
                                System.out.println("\nError: file users.txt not found!");
                            }

                            createUser(firstName, lastName, userName);
                            iterator = iterator + 3;
                        }
                    } catch (IncorrectCommandFormatException ignored) {
                    }

                    System.exit(0);
                }

                case "-showAllUsers" -> showAllUsers();
                case "-addTask" -> {
                    String userName, taskTitle, taskDescription;

                    try {

                        // Validating command format
                        if (args.length != 4) {
                            throw new IncorrectCommandFormatException();
                        } else {
                            createOrOpenTasksFile();
                            if (args[iterator + 1].startsWith("un", 1)) {
                                userName = args[iterator + 1].substring(5, args[iterator + 1].length() - 1);
                                if (args[iterator + 2].startsWith("tt", 1)) {
                                    taskTitle = args[iterator + 2].substring(5, args[iterator + 2].length() - 1);
                                    taskDescription = args[iterator + 3].substring(5, args[iterator + 3].length() - 1);
                                } else {
                                    taskDescription = args[iterator + 3].substring(5, args[iterator + 3].length() - 1);
                                    taskTitle = args[iterator + 2].substring(5, args[iterator + 2].length() - 1);
                                }
                            } else if (args[iterator + 2].startsWith("un", 1)) {
                                userName = args[iterator + 2].substring(5, args[iterator + 2].length() - 1);
                                if (args[iterator + 1].startsWith("tt", 1)) {
                                    taskTitle = args[iterator + 1].substring(5, args[iterator + 1].length() - 1);
                                    taskDescription = args[iterator + 3].substring(5, args[iterator + 3].length() - 1);
                                } else {
                                    taskTitle = args[iterator + 3].substring(5, args[iterator + 3].length() - 1);
                                    taskDescription = args[iterator + 1].substring(5, args[iterator + 1].length() - 1);
                                }
                            } else {
                                userName = args[iterator + 3].substring(5, args[iterator + 3].length() - 1);
                                if (args[iterator + 1].startsWith("tt", 1)) {
                                    taskTitle = args[iterator + 1].substring(5, args[iterator + 1].length() - 1);
                                    taskDescription = args[iterator + 2].substring(5, args[iterator + 2].length() - 1);
                                } else {
                                    taskTitle = args[iterator + 2].substring(5, args[iterator + 2].length() - 1);
                                    taskDescription = args[iterator + 1].substring(5, args[iterator + 1].length() - 1);
                                }
                            }

                            addTask(userName, taskTitle, taskDescription);
                            iterator = iterator + 3;
                        }
                    } catch (IncorrectCommandFormatException ignored) {
                    }

                    System.exit(0);
                }

                case "-showTasks" -> showTasks();
                default -> {
                    try {
                        throw new IncorrectCommandFormatException();
                    } catch (IncorrectCommandFormatException exception) {
                        exception.printStackTrace();
                    }
                }
            }
        }
    }
}