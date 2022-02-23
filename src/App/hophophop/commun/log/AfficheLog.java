package hophophop.commun.log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

class AfficheLog extends Thread {
    BufferedReader br = null;
    Socket socket = null;

    public AfficheLog(Socket s) {
        this.socket = s;

        try {
            InputStream is = this.socket.getInputStream();
            this.br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void run() {
        String line = null;

        try {
            while((line = this.br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (this.socket != null) {
                try {
                    this.socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }
}
