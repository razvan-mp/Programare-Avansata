import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

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
        boolean exit = false;
        Thread thread = new Thread();
        thread.start();
        while (!exit) {
            try {
                line = read.readUTF();
                System.out.print("Received command: ");
                System.out.println(line);
                String[] components = line.split(" ");
                if (components[0].equals("exit")) {
                    write.writeUTF("Client exited.\n");
                    exit = true;
                } else if (components[0].equals("stop")) {
                    write.writeUTF("Server stopped.\n");
                    System.exit(0);
                } else {
                    write.writeUTF("Command received.\n");
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        System.out.println("Closing connection");

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
