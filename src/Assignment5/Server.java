package Assignment5;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class Server {

    private static Logger logger = Logger.getLogger(Server.class.getName());
    private static ServerSocket server;
    private static int COMPORT = 8829;

    public static void main(String[] args) throws Exception{

        server = new ServerSocket(COMPORT);
        while(true){
            Socket socket = server.accept();
            logger.info("Server started successfully");
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            String message = (String) objectInputStream.readObject();
            logger.info("Client's Message: " + message);
            objectInputStream.close();
            socket.close();
        }
    }
}
