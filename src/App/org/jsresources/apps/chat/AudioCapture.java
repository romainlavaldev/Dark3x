//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jsresources.apps.chat;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.DataLine.Info;

public class AudioCapture extends AudioBase {
    private static final boolean DEBUG_TRANSPORT = false;
    protected AudioInputStream ais;
    protected OutputStream outputStream;
    private AudioCapture.CaptureThread thread;

    public AudioCapture(int formatCode, Mixer mixer, int bufferSizeMillis) {
        super("Microphone", formatCode, mixer, bufferSizeMillis);
    }

    protected void createLineImpl() throws Exception {
        Info info = new Info(TargetDataLine.class, this.lineFormat);
        if (this.mixer != null) {
            this.line = (TargetDataLine)this.mixer.getLine(info);
        } else {
            this.line = AudioSystem.getTargetDataLine(this.lineFormat);
        }

    }

    protected void openLineImpl() throws Exception {
        TargetDataLine tdl = (TargetDataLine)this.line;
        tdl.open(this.lineFormat, this.bufferSize);
        this.ais = new AudioCapture.TargetDataLineMeter(tdl);
        this.ais = AudioSystem.getAudioInputStream(this.netFormat, this.ais);
    }

    public synchronized void start() throws Exception {
        boolean needStartThread = false;
        if (this.thread != null && (this.thread.isTerminating() || this.outputStream == null)) {
            this.thread.terminate();
            needStartThread = true;
        }

        if (Constants.VERBOSE) {
            Constants.out("AudioCapture: start with OutputStream = " + this.outputStream);
        }

        if ((this.thread == null || needStartThread) && this.outputStream != null) {
            this.thread = new AudioCapture.CaptureThread();
            this.thread.start();
        }

        super.start();
    }

    protected void closeLine(boolean willReopen) {
        AudioCapture.CaptureThread oldThread = null;
        synchronized(this) {
            if (!willReopen && this.thread != null) {
                this.thread.terminate();
            }

            super.closeLine(willReopen);
            if (!willReopen) {
                if (this.ais != null) {
                    if (Constants.VERBOSE) {
                        Constants.out("AudioCapture.closeLine(): closing input stream");
                    }

                    try {
                        this.ais.close();
                    } catch (IOException var7) {
                    }
                }

                if (this.thread != null) {
                    if (this.outputStream != null) {
                        try {
                            this.outputStream.close();
                        } catch (IOException var6) {
                        }

                        this.outputStream = null;
                    }

                    oldThread = this.thread;
                }
            }
        }

        if (oldThread != null) {
            if (Constants.VERBOSE) {
                Constants.out("AudioCapture.closeLine(): closing thread, waiting for it to die");
            }

            oldThread.waitFor();
            if (Constants.VERBOSE) {
                Constants.out("AudioCapture.closeLine(): thread closed");
            }
        }

    }

    public AudioInputStream getAudioInputStream() {
        return this.ais;
    }

    public synchronized void setOutputStream(OutputStream stream) {
        this.outputStream = stream;
        if (this.outputStream == null && this.thread != null) {
            this.thread.terminate();
            this.thread = null;
        }

    }

    public synchronized OutputStream getOutputStream() {
        return this.outputStream;
    }

    private class TargetDataLineMeter extends AudioInputStream {
        private TargetDataLine line;
        private boolean printedBytes = false;

        TargetDataLineMeter(TargetDataLine line) {
            super(new ByteArrayInputStream(new byte[0]), line.getFormat(), -1L);
            this.line = line;
        }

        public int available() throws IOException {
            return this.line.available();
        }

        public int read() throws IOException {
            throw new IOException("illegal call to TargetDataLineMeter.read()!");
        }

        public int read(byte[] b, int off, int len) throws IOException {
            try {
                int ret = this.line.read(b, off, len);
                if (ret > 50) {
                }

                if (AudioCapture.this.isMuted()) {
                    AudioCapture.this.muteBuffer(b, off, ret);
                }

                if (ret > 0) {
                    AudioCapture.this.calcCurrVol(b, off, ret);
                }

                return ret;
            } catch (IllegalArgumentException var5) {
                throw new IOException(var5.getMessage());
            }
        }

        public void close() throws IOException {
            if (this.line.isActive()) {
                this.line.flush();
                this.line.stop();
            }

            this.line.close();
        }

        public int read(byte[] b) throws IOException {
            return this.read(b, 0, b.length);
        }

        public long skip(long n) throws IOException {
            return 0L;
        }

        public void mark(int readlimit) {
        }

        public void reset() throws IOException {
        }

        public boolean markSupported() {
            return false;
        }
    }

    class CaptureThread extends Thread {
        private boolean doTerminate = false;
        private boolean terminated = false;

        CaptureThread() {
        }

        public void run() {
            byte[] buffer = new byte[AudioCapture.this.getBufferSize()];
            if (Constants.VERBOSE) {
                Constants.out("Start AudioCapture push thread");
            }

            try {
                AudioInputStream localAIS = AudioCapture.this.ais;

                label83:
                while(true) {
                    while(true) {
                        while(true) {
                            if (this.doTerminate) {
                                break label83;
                            }

                            if (localAIS != null) {
                                int r = localAIS.read(buffer, 0, buffer.length);
                                if (r > 0) {
                                    synchronized(AudioCapture.this) {
                                        if (AudioCapture.this.outputStream != null) {
                                            AudioCapture.this.outputStream.write(buffer, 0, r);
                                        }
                                    }

                                    if (AudioCapture.this.outputStream == null) {
                                        synchronized(this) {
                                            this.wait(100L);
                                        }
                                    }
                                } else if (r == 0) {
                                    synchronized(this) {
                                        this.wait(20L);
                                    }
                                }
                            } else {
                                synchronized(this) {
                                    this.wait(50L);
                                }
                            }
                        }
                    }
                }
            } catch (IOException var13) {
            } catch (InterruptedException var14) {
                if (Constants.DEBUG) {
                    var14.printStackTrace();
                }
            }

            this.terminated = true;
            if (Constants.VERBOSE) {
                Constants.out("Stop AudioCapture push thread");
            }

        }

        public synchronized void terminate() {
            this.doTerminate = true;
            this.notifyAll();
        }

        public synchronized boolean isTerminating() {
            return this.doTerminate || this.terminated;
        }

        public synchronized void waitFor() {
            if (!this.terminated) {
                try {
                    this.join();
                } catch (InterruptedException var2) {
                    if (Constants.DEBUG) {
                        var2.printStackTrace();
                    }
                }
            }

        }
    }
}
