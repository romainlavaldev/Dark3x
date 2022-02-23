//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jsresources.apps.chat;

public class MasterModel {
    private ChatModel m_chatModel = new ChatModel(this);
    private ConnectionSettings m_connectionSettings = new ConnectionSettings(this);
    private AudioSettings m_audioSettings = new AudioSettings(this);

    public MasterModel() {
    }

    public ChatModel getChatModel() {
        return this.m_chatModel;
    }

    public ConnectionSettings getConnectionSettings() {
        return this.m_connectionSettings;
    }

    public AudioSettings getAudioSettings() {
        return this.m_audioSettings;
    }
}
