/*
Task:
You need to create a java application that will work as a task manager, with this application we should be able to do:

create new users (insert: FirstName, LastName, userName)
show all users (print: FirstName, LastName, number of tasks)
add a task to the user (insert username, Task Title, Description)
show user's tasks (print: Task title, Description)
All data should be kept in the file, writing and reading should happen via serialization and deserialization operations.


Create new users - by running this command:
java -jar myapplication.jar -createUser -fn='FirstName' - ln='LastName' -un='UserName'
UserName should be unique,  don't forget about validation


Show All Users - by running this command:
java -jar myapplication.jar -showAllUsers


Add a task - by running this command:
java -jar myapplication.jar -addTask -tt='Task Title' -td='Task Description'

Show user's tasks - by running this command:
java -jar myapplication.jar -showTasks -un='userName'

Add one more logical functionality (e. g. task assigned to a group of users
By using function 'showAllJobs' user can see, that one task may be assigned to different users (group of users)
 */

package Vigulear;

import java.sql.*;

public class Exercise3 {
    static Connection connection = null;
    static Statement statement = null;
    static ResultSet resultSet;
    static String query;

    // making the connection
    public static Connection makeJDBCConnection() {
        String url = "jdbc:mysql://localhost:3306/jitaexercise3database";
        String login = "root";
        String password = "root";
        try {
            connection = DriverManager.getConnection(url, login, password);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return connection;
    }

    public static void createUser(String[] args) {
        try {
            // Validating command format
            if (args.length != 4) {
                throw new IncorrectCommandFormatException();
            } else {
                connection = makeJDBCConnection();

                String firstName = args[1].substring(5, args[1].length() - 1);
                String lastName = args[2].substring(5, args[2].length() - 1);
                String userName = args[3].substring(5, args[3].length() - 1);

                query = "insert into users values('" + firstName + "', '" + lastName + "', '" + userName + "')";

                try {
                    statement = connection.createStatement();
                    statement.executeUpdate(query);
                    System.out.println("\nSuccess! User has been created.");
                } catch (SQLException exception) {
                    System.out.println("\nError: user with username '" + userName + "' already exists!");
                }
            }
        } catch (
                IncorrectCommandFormatException ignored) {
        }
        System.exit(0);
    }

    public static void showAllUsers() {
        connection = makeJDBCConnection();
        User user;
        try {
            statement = connection.createStatement();
            String query = "select * from users";
            resultSet = statement.executeQuery(query);
            System.out.println("\nUsers:");
            while (resultSet.next()) {
                user = new User(resultSet.getString("first_name"), resultSet.getString("last_name"), resultSet.getString("user_name"));
                System.out.println(user.toString());
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }

    public static void addTask(String[] args) {
        try {
            // Validating command format
            if (args.length != 3) {
                throw new IncorrectCommandFormatException();
            } else {
                connection = makeJDBCConnection();
                String taskTitle = args[1].substring(5, args[1].length() - 1);
                String taskDescription = args[2].substring(5, args[2].length() - 1);

                query = "insert into tasks(task_title, task_description) values('" + taskTitle + "', '" + taskDescription + "')";

                try {
                    statement = connection.createStatement();
                    statement.executeUpdate(query);
                    System.out.println("\nSuccess! Task has been added.");
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        } catch (IncorrectCommandFormatException ignored) {
        }
        System.exit(0);
    }

    public static void showTasks(String[] args) {
        connection = makeJDBCConnection();
        Task task;
        try {
            statement = connection.createStatement();
            String user = args[1].substring(5, args[1].length() - 1);
            String query = "select task_title, task_description from jobs j join tasks t on j.task_id = t.task_id where user_name = '" + user + "'";
            resultSet = statement.executeQuery(query);
            System.out.println("\n" + user + "'s tasks:");
            while (resultSet.next()) {
                task = new Task( resultSet.getString("task_title"), resultSet.getString("task_description"));
                System.out.println(task.toString());

            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public static void assignTaskToUsers(String[] args) {
        try {
            // Validating command format
            if (args.length != 3) {
                throw new IncorrectCommandFormatException();
            } else {
                connection = makeJDBCConnection();

                String taskTitle = args[1].substring(5, args[1].length() - 1);
                String userName = args[2].substring(5, args[2].length() - 1);

                query = "insert into jobs(task_id, user_name) values((select task_id from tasks where task_title = '" + taskTitle + "'), '" + userName + "')";
                try {
                    statement = connection.createStatement();
                    statement.executeUpdate(query);
                    System.out.println("\nSuccess! Task has been assigned to users.");
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        } catch (IncorrectCommandFormatException ignored) {
        }
        System.exit(0);
    }

    public static void showUsersOnTask(String[] args) {
        connection = makeJDBCConnection();
        try {
            statement = connection.createStatement();
            String taskTitle = args[1].substring(5, args[1].length() - 1);
            String query = "select user_name from jobs as j join tasks as t on j.task_id = t.task_id where task_title = '" + taskTitle + "'";
            System.out.println(query);
            resultSet = statement.executeQuery(query);
            System.out.println("\nUsers on task '" + taskTitle + "':");
            while (resultSet.next()) {
                System.out.println("\t" + resultSet.getString("user_name"));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public static void main(String[] args) {
        switch (args[0]) {
            case "-createUser" -> createUser(args);
            case "-showAllUsers" -> showAllUsers();
            case "-addTask" -> addTask(args);
            case "-showTasks" -> showTasks(args);
            case "-assignTaskToUsers" -> assignTaskToUsers(args);
            case "-showUsersOnTask" -> showUsersOnTask(args);
            default -> {
                try {
                    throw new IncorrectCommandFormatException();
                } catch (IncorrectCommandFormatException ignored) {}
            }
        }
    }
}