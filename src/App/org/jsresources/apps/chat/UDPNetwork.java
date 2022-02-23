//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jsresources.apps.chat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;

public class UDPNetwork extends BaseNetwork {
    public UDPNetwork(MasterModel masterModel) {
        super(masterModel);
    }

    public void connect(InetAddress addr) {
    }

    public void disconnect() {
    }

    public boolean isConnected() {
        return false;
    }

    public InetAddress getPeer() {
        return null;
    }

    public void setListen(boolean bListen) {
    }

    public boolean listen() {
        return this.isConnected();
    }

    public InputStream createReceiveStream() throws IOException {
        return null;
    }

    public OutputStream createSendStream() throws IOException {
        return null;
    }
}
