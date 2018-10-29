package Assignment5;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Logger;

public class Client implements Runnable {
    private static Logger logger = Logger.getLogger(Client.class.getName());
    private static int COMPORT = 8829;

    private String clientId;

    public Client(String clientId) {
        this.clientId = clientId;
    }

    @Override public void run() {
        try {
            Socket socket = new Socket("127.0.0.1", COMPORT);
            logger.info("Client " + clientId + " Connected to Server");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject("This is Client : " + clientId);
            //ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            //String message = (String) objectInputStream.readObject();
            //logger.info("Message: " + message);
            //objectInputStream.close();
            objectOutputStream.close();
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }
}
