//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jsresources.apps.chat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;

public interface Network {
    void connect(InetAddress var1);

    void disconnect();

    boolean isConnected();

    InetAddress getPeer();

    void setListen(boolean var1);

    boolean listen();

    InputStream createReceiveStream() throws IOException;

    OutputStream createSendStream() throws IOException;
}
