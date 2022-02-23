//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jsresources.apps.chat;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.LineEvent.Type;

public abstract class AudioBase implements LineListener {
    protected AudioFormat lineFormat;
    protected AudioFormat netFormat;
    protected int formatCode = -1;
    protected int bufferSizeMillis;
    protected int bufferSize;
    protected Mixer mixer;
    protected String title;
    protected DataLine line;
    protected int lastLevel = -1;
    protected boolean muted = false;

    protected AudioBase(String title, int formatCode, Mixer mixer, int bufferSizeMillis) {
        this.title = title;
        this.bufferSizeMillis = bufferSizeMillis;
        this.mixer = mixer;

        try {
            this.setFormatCode(formatCode);
        } catch (Exception var6) {
            var6.printStackTrace();
        }

    }

    public void update(LineEvent event) {
        if (Constants.DEBUG) {
            if (event.getType().equals(Type.STOP)) {
                Constants.out(this.title + ": Stop");
            } else if (event.getType().equals(Type.START)) {
                Constants.out(this.title + ": Start");
            } else if (event.getType().equals(Type.OPEN)) {
                Constants.out(this.title + ": Open");
            } else if (event.getType().equals(Type.CLOSE)) {
                Constants.out(this.title + ": Close");
            }
        }

    }

    public void open() throws Exception {
        this.closeLine(false);
        this.destroyLine();
        this.createLine();
        this.openLine();
    }

    protected abstract void createLineImpl() throws Exception;

    private void createLine() throws Exception {
        try {
            this.line = null;
            this.createLineImpl();
            this.line.addLineListener(this);
            if (Constants.DEBUG) {
                Constants.out("Got line for " + this.title + ": " + this.line.getClass());
            }

        } catch (LineUnavailableException var2) {
            throw new Exception("Unable to open " + this.title + ": " + var2.getMessage());
        }
    }

    protected abstract void openLineImpl() throws Exception;

    private void openLine() throws Exception {
        try {
            this.bufferSize = (int)AudioUtils.millis2bytes((long)this.bufferSizeMillis, this.lineFormat);
            this.bufferSize -= this.bufferSize % this.lineFormat.getFrameSize();
            this.openLineImpl();
            if (Constants.DEBUG) {
                Constants.out(this.title + ": opened line");
            }

            this.bufferSize = this.line.getBufferSize();
            if (Constants.VERBOSE) {
                Constants.out(this.title + ": buffersize=" + this.bufferSize + " bytes.");
            }

        } catch (LineUnavailableException var2) {
            throw new Exception("Unable to open " + this.title + ": " + var2.getMessage());
        }
    }

    public void start() throws Exception {
        if (this.line == null) {
            if (Constants.DEBUG) {
                Constants.out(this.title + ": Call to start(), but line not created!");
            }

            throw new Exception(this.title + ": cannot start");
        } else {
            this.line.flush();
            this.line.start();
            if (Constants.DEBUG) {
                Constants.out(this.title + ": started line");
            }

        }
    }

    public void close() {
        this.close(false);
    }

    public void close(boolean willReopen) {
        this.closeLine(willReopen);
        this.destroyLine();
    }

    protected void closeLine(boolean willReopen) {
        if (!willReopen) {
            this.lastLevel = -1;
        }

        if (this.line != null) {
            this.line.flush();
            this.line.stop();
            this.line.close();
            if (Constants.DEBUG && this.title != null) {
                Constants.out(this.title + ": line closed.");
            }
        }

    }

    private void destroyLine() {
        if (this.line != null) {
            this.line.removeLineListener(this);
        }

        this.line = null;
    }

    public boolean isStarted() {
        return this.line != null && this.line.isActive();
    }

    public boolean isOpen() {
        return this.line != null && this.line.isOpen();
    }

    public int getBufferSize() {
        return this.bufferSize;
    }

    public int getBufferSizeMillis() {
        return this.bufferSizeMillis;
    }

    public void setBufferSizeMillis(int bufferSizeMillis) throws Exception {
        if (this.bufferSizeMillis != bufferSizeMillis) {
            boolean wasOpen = this.isOpen();
            boolean wasStarted = this.isStarted();
            this.closeLine(true);
            this.bufferSizeMillis = bufferSizeMillis;
            if (wasOpen) {
                this.openLine();
                if (wasStarted) {
                    this.start();
                }
            }

        }
    }

    public int getFormatCode() {
        return this.formatCode;
    }

    public void setFormatCode(int formatCode) throws Exception {
        if (this.formatCode != formatCode) {
            boolean wasOpen = this.isOpen();
            if (wasOpen) {
                throw new Exception("cannot change format while open");
            } else {
                this.lineFormat = AudioUtils.getLineAudioFormat(formatCode);
                this.netFormat = AudioUtils.getNetAudioFormat(formatCode);
            }
        }
    }

    public void setMixer(Mixer mixer) throws Exception {
        if (this.mixer != mixer) {
            boolean wasOpen = this.isOpen();
            boolean wasStarted = this.isStarted();
            this.close(true);
            this.mixer = mixer;
            if (wasOpen) {
                this.createLine();
                this.openLine();
                if (wasStarted) {
                    this.start();
                }
            }

        }
    }

    public void setMuted(boolean muted) {
        this.muted = muted;
    }

    public boolean isMuted() {
        return this.muted;
    }

    public int getLevel() {
        return this.lastLevel;
    }

    protected void calcCurrVol(byte[] b, int off, int len) {
        int end = off + len;
        int sampleSize = (this.lineFormat.getSampleSizeInBits() + 7) / 8;
        int max = 0;
        int sample;
        if (sampleSize != 1) {
            if (sampleSize == 2) {
                if (this.lineFormat.isBigEndian()) {
                    for(; off < end; off += 2) {
                        sample = (short)(b[off] << 8 | b[off + 1] & 255);
                        if (sample < 0) {
                            sample = -sample;
                        }

                        if (sample > max) {
                            max = sample;
                        }
                    }
                } else {
                    for(; off < end; off += 2) {
                        sample = (short)(b[off + 1] << 8 | b[off] & 255);
                        if (sample < 0) {
                            sample = -sample;
                        }

                        if (sample > max) {
                            max = sample;
                        }
                    }
                }

                this.lastLevel = max >> 8;
            } else {
                this.lastLevel = -1;
            }
        } else {
            while(true) {
                if (off >= end) {
                    this.lastLevel = max;
                    break;
                }

                sample = (byte)(b[off] + 128);
                if (sample < 0) {
                    sample = -sample;
                }

                if (sample > max) {
                    max = sample;
                }

                ++off;
            }
        }

    }

    protected void muteBuffer(byte[] b, int off, int len) {
        int end = off + len;
        int sampleSize = (this.lineFormat.getSampleSizeInBits() + 7) / 8;
        byte filler = 0;
        if (sampleSize == 1) {
            filler = -128;
        }

        while(off < end) {
            b[off] = filler;
            ++off;
        }

    }
}
