//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jsresources.apps.chat;

import java.awt.Component;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.swing.JOptionPane;

public class ChatModel {
    private MasterModel m_masterModel;
    private PropertyChangeSupport m_propertyChangeSupport;
    private Network m_network;
    private ChatModel.ListenThread m_listenThread;
    private DataInputStream m_receiveStream;
    private OutputStream m_sendStream;
    private AudioBase[] audio = new AudioBase[2];
    private boolean m_audioActive;

    public ChatModel(MasterModel masterModel) {
        this.m_masterModel = masterModel;
        this.m_propertyChangeSupport = new PropertyChangeSupport(this);
        this.initNetwork();
        this.audio[0] = new AudioCapture(this.getConnectionSettings().getFormatCode(), this.getAudioSettings().getSelMixer(0), this.getAudioSettings().getBufferSizeMillis(0));
        this.audio[1] = new AudioPlayback(this.getConnectionSettings().getFormatCode(), this.getAudioSettings().getSelMixer(1), this.getAudioSettings().getBufferSizeMillis(1));
        this.m_audioActive = false;
    }

    private MasterModel getMasterModel() {
        return this.m_masterModel;
    }

    private ConnectionSettings getConnectionSettings() {
        return this.getMasterModel().getConnectionSettings();
    }

    private AudioSettings getAudioSettings() {
        return this.getMasterModel().getAudioSettings();
    }

    private void initNetwork() {
        if (this.getConnectionSettings().getConnectionType() == 1) {
            this.m_network = new TCPNetwork(this.getMasterModel());
        } else {
            this.m_network = new UDPNetwork(this.getMasterModel());
        }

    }

    private Network getNetwork() {
        return this.m_network;
    }

    public AudioBase getAudio(int d) {
        return this.audio[d];
    }

    public void connect(String strHostname) {
        InetAddress addr = null;

        try {
            addr = InetAddress.getByName(strHostname);
        } catch (UnknownHostException var4) {
            Debug.out(var4);
            JOptionPane.showMessageDialog((Component)null, "Unknown host " + strHostname);
        }

        if (addr != null) {
            this.getNetwork().connect(addr);
        }

        if (!this.getNetwork().isConnected()) {
            JOptionPane.showMessageDialog((Component)null, new Object[]{"Could not establish connection to " + strHostname}, "Error", 0);
        } else {
            this.initConnection(true);
        }

    }

    public void disconnect() {
        this.closeAudio();
        Debug.out("audio...closed");
        if (this.isConnected()) {
            this.getNetwork().disconnect();
            this.notifyConnection();
        }

    }

    public void setListen(boolean bListen) {
        if (bListen != this.isListening()) {
            if (bListen) {
                this.m_listenThread = new ChatModel.ListenThread();
                this.m_listenThread.start();
            } else {
                this.m_listenThread.setTerminate();
            }
        }

    }

    public boolean isListening() {
        return this.m_listenThread != null;
    }

    private void initConnection(boolean bActive) {
        try {
            this.m_receiveStream = new DataInputStream(this.getNetwork().createReceiveStream());
            this.m_sendStream = this.getNetwork().createSendStream();
        } catch (IOException var3) {
            Debug.out(var3);
            this.streamError("Problems while setting up the connection");
        }

        boolean bHandshakeSuccessful = false;
        if (bActive) {
            bHandshakeSuccessful = true;
        } else {
            bHandshakeSuccessful = true;
        }

        if (bHandshakeSuccessful) {
            if (this.isConnected()) {
                this.initNetworkAudio();
            }

            this.notifyConnection();
        } else {
            this.m_receiveStream = null;
            this.m_sendStream = null;
        }

    }

    private boolean doHandshakeActive() {
        DataOutputStream dos = new DataOutputStream(this.getSendStream());

        try {
            dos.writeInt(1128808788);
            dos.writeInt(1);
            dos.writeInt(this.getConnectionSettings().getFormatCode());
        } catch (IOException var5) {
            Debug.out(var5);
            this.streamError("I/O error during handshake (active, phase I)");
            return false;
        }

        byte[] abBuffer = new byte[4];

        try {
            this.getReceiveStream().readFully(abBuffer);
        } catch (IOException var4) {
            Debug.out(var4);
            this.streamError("I/O error during handshake (active, phase II)");
            return false;
        }

        int w = (abBuffer[0] & 255) << 24 | (abBuffer[1] & 255) << 16 | (abBuffer[2] & 255) << 8 | abBuffer[3] & 255;
        if (w != 1001) {
            this.streamError("error on remote peer");
            return false;
        } else {
            return true;
        }
    }

    private boolean doHandshakePassive() {
        boolean bSuccess = true;
        byte[] abBuffer = new byte[12];

        int w;
        try {
            w = this.getReceiveStream().read(abBuffer);
            if (w != 12) {
                System.out.println("NREAD = " + w);
                this.streamError("I/O Error during handshake (passive, phase I)");
                bSuccess = false;
            }
        } catch (IOException var6) {
            Debug.out(var6);
            System.out.println("Exception phase I");
            this.streamError("I/O error during handshake (passive, phase I)");
            bSuccess = false;
        }

        if (bSuccess) {
            w = abBuffer[0] << 24 | abBuffer[1] << 16 | abBuffer[2] << 8 | abBuffer[3];
            if (w != 1128808788) {
                this.streamError("wrong magic");
                bSuccess = false;
            } else {
                w = abBuffer[4] << 24 | abBuffer[5] << 16 | abBuffer[6] << 8 | abBuffer[7];
                if (w != 1) {
                    this.streamError("wrong protocol version");
                    bSuccess = false;
                } else {
                    w = abBuffer[8] << 24 | abBuffer[9] << 16 | abBuffer[10] << 8 | abBuffer[11];
                    if (w < 0 || w > Constants.FORMAT_CODES.length) {
                        this.streamError("wrong format code");
                        bSuccess = false;
                    }
                }
            }
        }

        DataOutputStream dos = new DataOutputStream(this.getSendStream());

        try {
            if (bSuccess) {
                dos.writeInt(1001);
            } else {
                dos.writeInt(1002);
            }
        } catch (IOException var5) {
            Debug.out(var5);
            System.out.println("Exception phase II");
            this.streamError("I/O error during handshake (passive, phase II)");
            bSuccess = false;
        }

        return true;
    }

    public boolean isConnected() {
        return this.getNetwork().isConnected();
    }

    private void initNetworkAudio() {
        try {
            ((AudioCapture)this.getAudio(0)).setOutputStream(this.getSendStream());
            ((AudioPlayback)this.getAudio(1)).setAudioInputStream(AudioUtils.createNetAudioInputStream(this.getConnectionSettings().getFormatCode(), this.getReceiveStream()));
            this.startAudio(0);
            this.startAudio(1);
            this.setAudioActive(true);
        } catch (Exception var2) {
            Debug.out(var2);
            this.streamError(var2.getMessage());
        }

    }

    public void initAudioStream() {
        if (this.isMicrophoneTest()) {
            ((AudioPlayback)this.getAudio(1)).setAudioInputStream(((AudioCapture)this.getAudio(0)).getAudioInputStream());
        }

    }

    private void closeAudio() {
        this.setAudioActive(false);
        this.closeAudio(1);
        this.closeAudio(0);
    }

    public boolean isMicrophoneTest() {
        return this.isAudioActive() && ((AudioCapture)this.getAudio(0)).getOutputStream() == null;
    }

    public boolean isAudioActive() {
        return this.m_audioActive;
    }

    public void setAudioActive(boolean active) {
        this.m_audioActive = active;
        this.notifyAudio();
    }

    private void closeAudio(int d) {
        if (this.getAudio(d) != null) {
            this.getAudio(d).close();
        }

    }

    private void startAudio(int d) throws Exception {
        if (!this.isAudioActive()) {
            this.getAudio(d).setFormatCode(this.getConnectionSettings().getFormatCode());
        }

        this.getAudio(d).open();
        this.getAudio(d).start();
    }

    public void toggleTestAudio() {
        if (!this.isConnected()) {
            try {
                if (this.m_audioActive) {
                    this.closeAudio(0);
                    this.closeAudio(1);
                    this.setAudioActive(false);
                } else {
                    this.startAudio(0);
                    ((AudioCapture)this.getAudio(0)).setOutputStream((OutputStream)null);
                    this.startAudio(1);
                    this.setAudioActive(true);
                    this.initAudioStream();
                }
            } catch (Exception var2) {
                JOptionPane.showMessageDialog((Component)null, new Object[]{"Error: ", var2.getMessage()}, "Error", 0);
                this.closeAudio(0);
                this.closeAudio(1);
                this.setAudioActive(false);
                this.notifyAudio();
            }

        }
    }

    public DataInputStream getReceiveStream() {
        return this.m_receiveStream;
    }

    public OutputStream getSendStream() {
        return this.m_sendStream;
    }

    private void streamError(String strError) {
        JOptionPane.showMessageDialog((Component)null, new Object[]{strError, "Connection will be terminated"}, "Error", 0);
        this.getNetwork().disconnect();
        this.closeAudio();
        this.notifyConnection();
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.m_propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.m_propertyChangeSupport.removePropertyChangeListener(listener);
    }

    private void notifyConnection() {
        this.m_propertyChangeSupport.firePropertyChange("CONNECTION", this.isConnected(), !this.isConnected());
    }

    private void notifyAudio() {
        this.m_propertyChangeSupport.firePropertyChange("AUDIO", this.isAudioActive(), !this.isAudioActive());
    }

    private class ListenThread extends Thread {
        private boolean m_bTerminate;

        private ListenThread() {
            this.m_bTerminate = false;
        }

        public void setTerminate() {
            this.m_bTerminate = true;
        }

        public void run() {
            ChatModel.this.getNetwork().setListen(true);

            while(!this.m_bTerminate) {
                if (ChatModel.this.getNetwork().listen()) {
                    ChatModel.this.initConnection(false);
                    ChatModel.this.setListen(false);
                }
            }

            ChatModel.this.getNetwork().setListen(false);
        }
    }
}
