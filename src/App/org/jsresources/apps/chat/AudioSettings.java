//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jsresources.apps.chat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.CompoundControl;
import javax.sound.sampled.Control;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.Port;
import javax.sound.sampled.FloatControl.Type;
import javax.sound.sampled.Mixer.Info;

class AudioSettings {
    private Port[] port = new Port[2];
    private FloatControl[] portVolume = new FloatControl[2];
    private BooleanControl[] portSelect = new BooleanControl[2];
    private List<String>[] portNames = new List[2];
    private List<Port>[] ports = new List[2];
    private List<Integer>[] controlIndex = new List[2];
    private Mixer[] mixer = new Mixer[2];
    private List<Mixer>[] mixers = new List[2];
    private int[] bufferSizeIndex = new int[2];
    private boolean inited = false;
    private MasterModel master;

    public AudioSettings(MasterModel master) {
        this.portNames[0] = new ArrayList();
        this.portNames[1] = new ArrayList();
        this.ports[0] = new ArrayList();
        this.ports[1] = new ArrayList();
        this.controlIndex[0] = new ArrayList();
        this.controlIndex[1] = new ArrayList();
        this.mixers[0] = new ArrayList();
        this.mixers[1] = new ArrayList();
        this.bufferSizeIndex[0] = 2;
        this.bufferSizeIndex[1] = 2;
        this.master = master;
    }

    public void init() {
        for(int d = 0; d < 2; ++d) {
            this.portNames[d].clear();
            this.ports[d].clear();
            this.controlIndex[d].clear();
            this.mixers[d].clear();
        }

        Info[] infos = AudioSystem.getMixerInfo();

        int d;
        for(d = 0; d < infos.length; ++d) {
            try {
                Mixer mixer = AudioSystem.getMixer(infos[d]);
                this.addMixer(mixer, mixer.getSourceLineInfo(), 1);
                this.addMixer(mixer, mixer.getTargetLineInfo(), 0);
            } catch (Exception var4) {
                if (Constants.DEBUG) {
                    var4.printStackTrace();
                }
            }
        }

        for(d = 0; d < 2; ++d) {
            if (this.mixers[d].size() > 1) {
                this.mixers[d].add(0, (Mixer) null);
            }
        }

        this.inited = true;
        if (Constants.VERBOSE) {
            Constants.out("Microphone Ports:  " + this.ports[0].size());
            Constants.out("Microphone Mixers: " + this.mixers[0].size());
            Constants.out("Speaker Ports:  " + this.ports[1].size());
            Constants.out("Speaker Mixers: " + this.mixers[1].size());
        }

    }

    public void exit() {
        for(int d = 0; d < 2; ++d) {
            this.closePort(d);
            this.portNames[d].clear();
            this.controlIndex[d].clear();
            this.ports[d].clear();
            this.mixers[d].clear();
        }

    }

    private void addMixer(Mixer mixer, javax.sound.sampled.Line.Info[] infos, int dirDataLine) {
        for(int i = 0; i < infos.length; ++i) {
            try {
                if (infos[i] instanceof javax.sound.sampled.Port.Info) {
                    javax.sound.sampled.Port.Info info = (javax.sound.sampled.Port.Info)infos[i];
                    byte d;
                    if (info.isSource()) {
                        d = 0;
                    } else {
                        d = 1;
                    }

                    Port p = (Port)mixer.getLine(info);
                    p.open();

                    try {
                        Control[] cs = p.getControls();

                        for(int c = 0; c < cs.length; ++c) {
                            if (cs[c] instanceof CompoundControl) {
                                this.ports[d].add(p);
                                this.portNames[d].add(mixer.getMixerInfo().getName() + ": " + cs[c].getType().toString());
                                this.controlIndex[d].add(c);
                            }
                        }
                    } finally {
                        p.close();
                    }
                }

                if (infos[i] instanceof javax.sound.sampled.DataLine.Info && !this.mixers[dirDataLine].contains(mixer)) {
                    this.mixers[dirDataLine].add(mixer);
                }
            } catch (Exception var14) {
                if (Constants.DEBUG) {
                    var14.printStackTrace();
                }
            }
        }

    }

    public List<String> getMixers(int d) {
        if (!this.inited) {
            this.init();
        }

        List<String> res = new ArrayList();
        Iterator var3 = this.mixers[d].iterator();

        while(var3.hasNext()) {
            Mixer m = (Mixer)var3.next();
            if (m == null) {
                res.add("(Default)");
            } else {
                res.add(m.getMixerInfo().getName());
            }
        }

        return res;
    }

    public Mixer getSelMixer(int d) {
        return this.mixer[d];
    }

    public void setSelMixer(int d, int index) {
        if (index >= 0 && index < this.mixers[d].size()) {
            this.mixer[d] = (Mixer)this.mixers[d].get(index);
        } else {
            if (Constants.DEBUG) {
                Constants.out("setSelMixer out of range: iondex=" + index);
            }

        }
    }

    public List<String> getPorts(int d) {
        if (!this.inited) {
            this.init();
        }

        return this.portNames[d];
    }

    public Port getSelPort(int d) {
        return this.port[d];
    }

    public FloatControl getSelVolControl(int d) {
        return this.portVolume[d];
    }

    public void setSelPort(int d, int index) {
        this.setSelPort(d, index, true);
    }

    public void setSelPort(int d, int index, boolean doSelect) {
        if (index >= 0 && index < this.ports[d].size()) {
            this.setupPort(d, (Port)this.ports[d].get(index), (Integer)this.controlIndex[d].get(index));
            if (doSelect && d == 0 && this.portSelect[d] != null) {
                this.portSelect[d].setValue(true);
            }

            if (Constants.DEBUG) {
                Constants.out("Selected " + (String)this.portNames[d].get(index));
            }

        } else {
            if (Constants.DEBUG) {
                Constants.out("setSelPort out of range: iondex=" + index);
            }

            this.closePort(d);
        }
    }

    private void closePort(int d) {
        if (this.port[d] != null) {
            this.port[d].close();
            this.port[d] = null;
        }

        this.portVolume[d] = null;
        this.portSelect[d] = null;
    }

    private void setupPort(int d, Port p, int controlIndex) {
        if (p != this.port[d] && this.port[d] != null) {
            this.port[d].close();
        }

        this.portVolume[d] = null;
        this.portSelect[d] = null;
        this.port[d] = p;

        try {
            p.open();
            Control[] cs = p.getControls();
            if (controlIndex >= cs.length) {
                throw new Exception("control not found!");
            }

            if (!(cs[controlIndex] instanceof CompoundControl)) {
                throw new Exception("control not found!");
            }

            CompoundControl cc = (CompoundControl)cs[controlIndex];
            cs = cc.getMemberControls();
            Control[] var6 = cs;
            int var7 = cs.length;

            for(int var8 = 0; var8 < var7; ++var8) {
                Control c = var6[var8];
                if (c instanceof FloatControl && c.getType().equals(Type.VOLUME) && this.portVolume[d] == null) {
                    this.portVolume[d] = (FloatControl)c;
                }

                if (c instanceof BooleanControl && c.getType().toString().contains("elect") && this.portSelect[d] == null) {
                    this.portSelect[d] = (BooleanControl)c;
                }
            }
        } catch (Exception var10) {
            if (Constants.DEBUG) {
                var10.printStackTrace();
            }

            this.closePort(d);
        }

    }

    public int getBufferSizeMillis(int d) {
        return Constants.BUFFER_SIZE_MILLIS[this.bufferSizeIndex[d]];
    }

    public int getBufferSizeIndex(int d) {
        return this.bufferSizeIndex[d];
    }

    public void setBufferSizeIndex(int d, int bufferSizeIndex) {
        this.bufferSizeIndex[d] = bufferSizeIndex;
    }
}
