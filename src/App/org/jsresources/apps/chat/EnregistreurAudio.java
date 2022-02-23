//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jsresources.apps.chat;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.AudioFileFormat.Type;

public class EnregistreurAudio extends Thread {
    private TargetDataLine m_line;
    private Type m_targetType;
    private AudioInputStream m_audioInputStream;
    private File m_outputFile;

    public EnregistreurAudio(TargetDataLine line, Type targetType, File file) {
        this.m_line = line;
        this.m_audioInputStream = new AudioInputStream(line);
        this.m_targetType = targetType;
        this.m_outputFile = file;
    }

    public void start() {
        this.m_line.start();
        super.start();
    }

    public void stopRecording() {
        this.m_line.stop();
        this.m_line.close();
    }

    public void run() {
        try {
            AudioSystem.write(this.m_audioInputStream, this.m_targetType, this.m_outputFile);
        } catch (IOException var2) {
            var2.printStackTrace();
        }

    }
}
