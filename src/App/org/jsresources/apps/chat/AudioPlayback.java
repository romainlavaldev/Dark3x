//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jsresources.apps.chat;

import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.DataLine.Info;

public class AudioPlayback extends AudioBase {
    private static final boolean DEBUG_TRANSPORT = false;
    protected AudioInputStream ais;
    private AudioPlayback.PlayThread thread;

    public AudioPlayback(int formatCode, Mixer mixer, int bufferSizeMillis) {
        super("Speaker", formatCode, mixer, bufferSizeMillis);
    }

    protected void createLineImpl() throws Exception {
        Info info = new Info(SourceDataLine.class, this.lineFormat);
        if (this.mixer != null) {
            this.line = (SourceDataLine)this.mixer.getLine(info);
        } else {
            this.line = AudioSystem.getSourceDataLine(this.lineFormat);
        }

    }

    protected void openLineImpl() throws Exception {
        SourceDataLine sdl = (SourceDataLine)this.line;
        sdl.open(this.lineFormat, this.bufferSize);
    }

    public synchronized void start() throws Exception {
        boolean needStartThread = false;
        if (this.thread != null && this.thread.isTerminating()) {
            this.thread.terminate();
            needStartThread = true;
        }

        if (this.thread == null || needStartThread) {
            this.thread = new AudioPlayback.PlayThread();
            this.thread.start();
        }

        super.start();
    }

    protected void closeLine(boolean willReopen) {
        AudioPlayback.PlayThread oldThread = null;
        synchronized(this) {
            if (!willReopen && this.thread != null) {
                this.thread.terminate();
            }

            super.closeLine(willReopen);
            if (!willReopen && this.thread != null) {
                oldThread = this.thread;
                this.thread = null;
            }
        }

        if (oldThread != null) {
            if (Constants.VERBOSE) {
                Constants.out("AudioPlayback.closeLine(): closing thread, waiting for it to die");
            }

            oldThread.waitFor();
            if (Constants.VERBOSE) {
                Constants.out("AudioPlayback.closeLine(): thread closed");
            }
        }

    }

    public void setAudioInputStream(AudioInputStream ais) {
        this.ais = AudioSystem.getAudioInputStream(this.lineFormat, ais);
    }

    class PlayThread extends Thread {
        private boolean doTerminate = false;
        private boolean terminated = false;
        private boolean printedBytes = false;

        PlayThread() {
        }

        public void run() {
            if (Constants.VERBOSE) {
                Constants.out("Start AudioPlayback pull thread");
            }

            byte[] buffer = new byte[AudioPlayback.this.getBufferSize()];

            try {
                while(!this.doTerminate) {
                    SourceDataLine sdl = (SourceDataLine)AudioPlayback.this.line;
                    if (AudioPlayback.this.ais != null) {
                        int r = AudioPlayback.this.ais.read(buffer, 0, buffer.length);
                        if (r > 50) {
                        }

                        if (r > 0) {
                            if (AudioPlayback.this.isMuted()) {
                                AudioPlayback.this.muteBuffer(buffer, 0, r);
                            }

                            AudioPlayback.this.calcCurrVol(buffer, 0, r);
                            if (sdl != null) {
                                sdl.write(buffer, 0, r);
                            }
                        } else if (r == 0) {
                            synchronized(this) {
                                this.wait(40L);
                            }
                        }
                    } else {
                        synchronized(this) {
                            this.wait(50L);
                        }
                    }
                }
            } catch (IOException var9) {
            } catch (InterruptedException var10) {
                if (Constants.DEBUG) {
                    var10.printStackTrace();
                }
            }

            if (Constants.VERBOSE) {
                Constants.out("Stop AudioPlayback pull thread");
            }

            this.terminated = true;
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
