//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jsresources.apps.chat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

public class TCPNetwork extends BaseNetwork {
    private ServerSocket m_serverSocket;
    private Socket m_commSocket;

    public TCPNetwork(MasterModel masterModel) {
        super(masterModel);
    }

    public void connect(InetAddress addr) {
        Debug.out("connect(): begin");

        try {
            this.m_commSocket = new Socket(addr, this.getPort());
            setSocketOptions(this.m_commSocket);
        } catch (IOException var3) {
            Debug.out(var3);
        }

    }

    public InetAddress getPeer() {
        return this.m_commSocket.getInetAddress();
    }

    public void disconnect() {
        try {
            this.m_commSocket.shutdownInput();
            this.m_commSocket.shutdownOutput();
        } catch (IOException var3) {
            Debug.out(var3);
        }

        try {
            this.m_commSocket.close();
        } catch (IOException var2) {
            Debug.out(var2);
        }

    }

    public boolean isConnected() {
        return this.m_commSocket != null && !this.m_commSocket.isClosed();
    }

    public void setListen(boolean bListen) {
        if (bListen != this.isListening()) {
            if (bListen) {
                try {
                    this.m_serverSocket = new ServerSocket(this.getPort());
                    this.m_serverSocket.setSoTimeout(0);
                } catch (IOException var4) {
                    Debug.out(var4);
                }
            } else {
                try {
                    this.m_serverSocket.close();
                } catch (IOException var3) {
                    Debug.out(var3);
                }

                this.m_serverSocket = null;
            }
        }

    }

    private boolean isListening() {
        return this.m_serverSocket != null;
    }

    public boolean listen() {
        Socket s = null;

        try {
            s = this.m_serverSocket.accept();
            setSocketOptions(s);
        } catch (SocketTimeoutException var3) {
        } catch (IOException var4) {
            Debug.out(var4);
        }

        if (s != null) {
            this.m_commSocket = s;
            return true;
        } else {
            return false;
        }
    }

    public InputStream createReceiveStream() throws IOException {
        return this.m_commSocket.getInputStream();
    }

    public OutputStream createSendStream() throws IOException {
        return this.m_commSocket.getOutputStream();
    }

    private static void setSocketOptions(Socket socket) {
        try {
            socket.setTcpNoDelay(false);
            socket.setSendBufferSize(1024);
            socket.setReceiveBufferSize(1024);
        } catch (SocketException var2) {
            Debug.out(var2);
        }

    }
}
