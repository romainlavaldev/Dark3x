package hophophop.commun.log;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.net.ServerSocketFactory;

public class H3LogServeur {
    private static final int PORT_NUM = 2752;
    private static ServerSocket serverSocket;

    public static void main(String[] args) {
        new H3LogServeur();
    }

    public H3LogServeur() {
        (new Thread() {
            public void run() {
                H3LogServeur.serverSocket = H3LogServeur.createServerSocket();
                H3LogServeur.listenAndPrintLogMessages();
            }
        }).start();
    }

    private static void listenAndPrintLogMessages() {
        while(true) {
            Socket socket = null;

            try {
                System.out.println(" >> Server waiting for log messages...");
                socket = serverSocket.accept();
                (new AfficheLog(socket)).start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static ServerSocket createServerSocket() {
        ServerSocketFactory serverSocketFactory = ServerSocketFactory.getDefault();

        try {
            serverSocket = serverSocketFactory.createServerSocket(2752);
        } catch (IOException e) {
            System.err.println("Unable to create server");
            e.printStackTrace();
            System.exit(-1);
        }

        return serverSocket;
    }
}
