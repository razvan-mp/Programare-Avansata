package networking;

import db.dao.UserDAO;
import objects.User;
import utilitaries.ConnectionTimeout;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

public class ClientCommand implements Runnable {
    DataInputStream read;
    DataOutputStream write;
    Socket socket;

    public ClientCommand(Socket socket) throws IOException {
        this.read = new DataInputStream(socket.getInputStream());
        this.write = new DataOutputStream(socket.getOutputStream());
        this.socket = socket;
    }

    @Override
    public void run() {
        String line;
        User connectedUser = null;
        boolean exit = false;
        ConnectionTimeout connectionTimeout = new ConnectionTimeout(300);
        Thread thread = new Thread(connectionTimeout);
        thread.start();
        while (!exit) {
            try {
                line = read.readUTF();
                System.out.print("Received command: ");
                System.out.println(line);
                String[] components = line.split(" ");

                System.out.println(Server.getUserCounter());
                System.out.println(Server.isUseCounter());

                if (connectionTimeout.isConnectionTimedOut() && connectedUser != null) {
                    write.writeUTF("Connection timed out.\n");
                    exit = true;
                    System.exit(0);
                }

                switch (components[0]) {
                    case "exit" -> {
                        Integer userCounter = Server.getUserCounter();
                        Server.setUserCounter(userCounter - 1);
                        write.writeUTF("Client exited.\n");
                        exit = true;
                        connectionTimeout.resetTimeout();

                        if (Server.isUseCounter() && Server.getUserCounter() == 0) {
                            System.exit(0);
                        }
                    }
                    case "stop" -> {
                        if (connectedUser == null)
                            write.writeUTF("Must be logged in to use this command.\nEnter command: ");
                        else if (!Server.isUseCounter()) {
                            Server.setUseCounter(true);
                            write.writeUTF("Sent 'stop' command to server. It will shut down when no other users are connected.\nEnter command: ");
                        } else
                            write.writeUTF("Sent 'stop' command to server. It will shut down when no other users are connected.\nEnter command: ");
                    }
                    case "register" -> {
                        if (components.length != 2)
                            write.writeUTF("Name should be a word!\nEnter command: ");
                        else if (connectedUser != null)
                            write.writeUTF("Can't register while logged in.\nEnter command: ");
                        else {
                            Integer id = UserDAO.findByName(components[1]);

                            if (id != null)
                                write.writeUTF("User already exists.\nEnter command: ");
                            else {
                                UserDAO.create(components[1]);
                                write.writeUTF("User " + components[1] + " was registered.\nEnter command: ");
                            }
                        }
                        connectionTimeout.resetTimeout();
                    }
                    case "login" -> {
                        if (components.length != 2)
                            write.writeUTF("Name should be a word!\nEnter command: ");
                        else if (connectedUser != null)
                            write.writeUTF("Can't log in while logged in.\nEnter command: ");
                        else {
                            Integer id = UserDAO.findByName(components[1]);

                            if (id == null)
                                write.writeUTF("User doesn't exist.\nEnter command: ");
                            else {
                                connectedUser = new User(components[1]);
                                write.writeUTF("User " + components[1] + " connected.\nEnter command: ");
                            }
                        }
                        connectionTimeout.resetTimeout();
                    }
                    case "logout" -> {
                        if (connectedUser != null) {
                            write.writeUTF("Logged out successfully.\nEnter command: ");
                            connectedUser = null;
                        } else {
                            write.writeUTF("Cannot log out while not being logged in.\nEnter command: ");
                        }
                        connectionTimeout.resetTimeout();
                    }
                    case "friend" -> {
                        if (components.length < 2)
                            write.writeUTF("Command incomplete.\nEnter command: ");
                        else if (connectedUser == null)
                            write.writeUTF("Must be logged in to add a friend.\nEnter command: ");
                        else {
                            Integer firstId = UserDAO.findByName(connectedUser.getName());

                            for (int index = 1; index < components.length; ++index) {
                                Integer secondId = UserDAO.findByName(components[index]);
                                if (secondId == null)
                                    write.writeUTF("User to be added as friend does not exist.\nEnter command: ");
                                else if (UserDAO.friendshipExists(firstId, secondId))
                                    write.writeUTF("Friendship already exists.\nEnter command: ");
                                else
                                    UserDAO.addFriendship(firstId, secondId);
                            }
                            write.writeUTF("Friendships added.\nEnter command: ");
                        }
                        connectionTimeout.resetTimeout();
                    }
                    case "send" -> {
                        if (connectedUser == null)
                            write.writeUTF("Must be logged in to use this command!\nEnter command: ");
                        else {
                            if (components.length < 2)
                                write.writeUTF("Command incomplete.\nEnter command: ");
                            else {
                                Integer senderId = UserDAO.findByName(connectedUser.getName());

                                if (senderId == null)
                                    write.writeUTF("The user does not exist.\nEnter command: ");

                                StringBuilder message = new StringBuilder();
                                for (int index = 1; index < components.length; index++)
                                    message.append(components[index]).append(" ");

                                UserDAO.sendMessage(senderId, message.toString());

                                write.writeUTF("Message sent.\nEnter command: ");
                            }
                            connectionTimeout.resetTimeout();
                        }
                    }
                    case "read" -> {
                        if (connectedUser == null)
                            write.writeUTF("Must be logged in to use this command!\n");
                        else {
                            if (components.length != 1)
                                write.writeUTF("Command does not need arguments!");
                            else {
                                Integer connectedUserId = UserDAO.findByName(connectedUser.getName());

                                String messages = "Your messages are:\n";
                                messages += UserDAO.readMessages(connectedUserId);
                                write.writeUTF(messages + "\nEnter command:");
                            }
                            connectionTimeout.resetTimeout();
                        }
                    }
                    case "help" -> {
                        String message;
                        if (connectedUser == null) {
                            message = """
                                    List of available commands:
                                    \t1. login
                                    \t2. register
                                    \t3. exit
                                    """;
                        } else {
                            message = """
                                    List of available commands:
                                    \t1. friend <friend1, friend2, ...>
                                    \t2. send <message_body>
                                    \t3. read
                                    \t4. stop
                                    \t5. exit
                                    """;
                        }
                        write.writeUTF(message + "\nEnter command:");
                    }
                    default -> {
                        write.writeUTF("Command unknown.\nEnter command: ");
                        connectionTimeout.resetTimeout();
                    }
                }
            } catch (IOException | SQLException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("Closing connection...");

        try {
            socket.close();
            read.close();
            write.close();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}

