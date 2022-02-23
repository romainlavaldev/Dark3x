//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jsresources.apps.chat;

import java.io.InputStream;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.AudioFormat.Encoding;

public class AudioUtils {
    private static final float[] netFrameSize = new float[]{1.0F, 2.0F, 2.0F, 1.0F, 33.0F};
    private static final float[] netSampleRate = new float[]{1.0F, 44100.0F, 22050.0F, 8000.0F, 8000.0F};
    private static final float[] netFrameRate = new float[]{1.0F, 44100.0F, 22050.0F, 8000.0F, 50.0F};

    public AudioUtils() {
    }

    public static long bytes2millis(long bytes, AudioFormat format) {
        return (long)((float)bytes / format.getFrameRate() * 1000.0F / (float)format.getFrameSize());
    }

    public static long millis2bytes(long ms, AudioFormat format) {
        return (long)((float)ms * format.getFrameRate() / 1000.0F * (float)format.getFrameSize());
    }

    public static AudioFormat getLineAudioFormat(int formatCode) {
        return getLineAudioFormat(netSampleRate[formatCode]);
    }

    public static AudioFormat getLineAudioFormat(float sampleRate) {
        return new AudioFormat(Encoding.PCM_SIGNED, sampleRate, 16, 1, 2, sampleRate, false);
    }

    public static AudioFormat getNetAudioFormat(int formatCode) throws UnsupportedAudioFileException {
        if (formatCode == 1) {
            return new AudioFormat(Encoding.PCM_SIGNED, 44100.0F, 16, 1, 2, 44100.0F, true);
        } else if (formatCode == 2) {
            return new AudioFormat(Encoding.PCM_SIGNED, 22050.0F, 16, 1, 2, 22050.0F, true);
        } else if (formatCode == 3) {
            return new AudioFormat(Encoding.ULAW, 8000.0F, 8, 1, 1, 8000.0F, false);
        } else if (formatCode == 4) {
            return new AudioFormat(new Encoding("GSM0610"), 8000.0F, -1, 1, 33, 50.0F, false);
        } else {
            throw new RuntimeException("Wrong format code!");
        }
    }

    public static AudioInputStream createNetAudioInputStream(int formatCode, InputStream stream) {
        try {
            AudioFormat format = getNetAudioFormat(formatCode);
            return new AudioInputStream(stream, format, -1L);
        } catch (UnsupportedAudioFileException var3) {
            Debug.out(var3);
            return null;
        }
    }

    public static int getFormatCode(AudioFormat format) {
        Encoding encoding = format.getEncoding();
        if (encoding.equals(Encoding.PCM_SIGNED)) {
            return format.getSampleRate() == 44100.0F ? 1 : 2;
        } else if (encoding.equals(Encoding.ULAW)) {
            return 3;
        } else if (encoding.toString().equals("GSM0610")) {
            return 4;
        } else {
            throw new RuntimeException("Wrong Format");
        }
    }
}
