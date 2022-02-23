//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jsresources.apps.chat;

public class ConnectionSettings {
    private int m_nPort;
    private int m_nConnectionType;
    private int m_nFormatCode;

    public ConnectionSettings(MasterModel masterModel) {
        this.setPort(8765);
        this.setFormatCode(3);
        this.setConnectionType(1);
    }

    public void setPort(int nPort) {
        this.m_nPort = nPort;
    }

    public int getPort() {
        return this.m_nPort;
    }

    public void setConnectionType(int nConnectionType) {
        this.m_nConnectionType = nConnectionType;
    }

    public int getConnectionType() {
        return this.m_nConnectionType;
    }

    public void setFormatCode(int nFormatCode) {
        this.m_nFormatCode = nFormatCode;
    }

    public int getFormatCode() {
        return this.m_nFormatCode;
    }
}
