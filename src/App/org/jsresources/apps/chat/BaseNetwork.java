//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jsresources.apps.chat;

public abstract class BaseNetwork implements Network {
    private MasterModel m_masterModel;

    protected BaseNetwork(MasterModel masterModel) {
        this.m_masterModel = masterModel;
    }

    private MasterModel getMasterModel() {
        return this.m_masterModel;
    }

    private ConnectionSettings getConnectionSettings() {
        return this.getMasterModel().getConnectionSettings();
    }

    protected int getPort() {
        return this.getConnectionSettings().getPort();
    }
}
