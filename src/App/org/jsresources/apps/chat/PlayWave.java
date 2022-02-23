//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jsresources.apps.chat;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.DataLine.Info;
import javax.sound.sampled.FloatControl.Type;

public class PlayWave {
    private String filename;
    private PlayWave.Position curPosition;
    private final int EXTERNAL_BUFFER_SIZE = 524288;

    public PlayWave(String wavfile) {
        this.filename = wavfile;
        this.curPosition = PlayWave.Position.NORMAL;
    }

    public void joue() {
        File soundFile = new File(this.filename);
        if (!soundFile.exists()) {
            System.err.println("Wave file not found: " + this.filename);
        } else {
            AudioInputStream audioInputStream = null;

            try {
                audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            } catch (UnsupportedAudioFileException var18) {
                var18.printStackTrace();
                return;
            } catch (IOException var19) {
                var19.printStackTrace();
                return;
            }

            AudioFormat format = audioInputStream.getFormat();
            SourceDataLine auline = null;
            Info info = new Info(SourceDataLine.class, format);

            try {
                auline = (SourceDataLine)AudioSystem.getLine(info);
                auline.open(format);
            } catch (LineUnavailableException var16) {
                var16.printStackTrace();
                return;
            } catch (Exception var17) {
                var17.printStackTrace();
                return;
            }

            if (auline.isControlSupported(Type.PAN)) {
                FloatControl pan = (FloatControl)auline.getControl(Type.PAN);
                if (this.curPosition == PlayWave.Position.RIGHT) {
                    pan.setValue(1.0F);
                } else if (this.curPosition == PlayWave.Position.LEFT) {
                    pan.setValue(-1.0F);
                }
            }

            auline.start();
            int nBytesRead = 0;
            byte[] abData = new byte[524288];

            try {
                while(nBytesRead != -1) {
                    nBytesRead = audioInputStream.read(abData, 0, abData.length);
                    if (nBytesRead >= 0) {
                        auline.write(abData, 0, nBytesRead);
                    }
                }

                return;
            } catch (IOException var20) {
                var20.printStackTrace();
            } finally {
                auline.drain();
                auline.close();
            }

        }
    }

    static enum Position {
        LEFT,
        RIGHT,
        NORMAL;

        private Position() {
        }
    }
}
