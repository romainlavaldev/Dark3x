//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jsresources.apps.chat;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioFormat.Encoding;
import org.tritonus.share.sampled.TConversionTool;

public class MixingAudioInputStream extends AudioInputStream {
    private static final boolean DEBUG = false;
    private List m_audioInputStreamList;

    public MixingAudioInputStream(AudioFormat audioFormat, Collection audioInputStreams) {
        super(new ByteArrayInputStream(new byte[0]), audioFormat, -1L);
        this.m_audioInputStreamList = new ArrayList(audioInputStreams);
    }

    public long getFrameLength() {
        long lLengthInFrames = 0L;

        long lLength;
        for(Iterator streamIterator = this.m_audioInputStreamList.iterator(); streamIterator.hasNext(); lLengthInFrames = Math.max(lLengthInFrames, lLength)) {
            AudioInputStream stream = (AudioInputStream)streamIterator.next();
            lLength = stream.getFrameLength();
            if (lLength == -1L) {
                return -1L;
            }
        }

        return lLengthInFrames;
    }

    public int read() throws IOException {
        int nSample = 0;
        Iterator streamIterator = this.m_audioInputStreamList.iterator();

        while(streamIterator.hasNext()) {
            AudioInputStream stream = (AudioInputStream)streamIterator.next();
            int nByte = stream.read();
            if (nByte == -1) {
                streamIterator.remove();
            } else {
                nSample += nByte;
            }
        }

        return (byte)(nSample & 255);
    }

    public int read(byte[] abData, int nOffset, int nLength) throws IOException {
        int nChannels = this.getFormat().getChannels();
        int nFrameSize = this.getFormat().getFrameSize();
        int nSampleSize = nFrameSize / nChannels;
        boolean bBigEndian = this.getFormat().isBigEndian();
        Encoding encoding = this.getFormat().getEncoding();
        byte[] abBuffer = new byte[nFrameSize];
        int[] anMixedSamples = new int[nChannels];

        for(int nFrameBoundry = 0; nFrameBoundry < nLength; nFrameBoundry += nFrameSize) {
            for(int i = 0; i < nChannels; ++i) {
                anMixedSamples[i] = 0;
            }

            Iterator streamIterator = this.m_audioInputStreamList.iterator();

            while(true) {
                int nBufferOffset;
                while(streamIterator.hasNext()) {
                    AudioInputStream stream = (AudioInputStream)streamIterator.next();
                    nBufferOffset = stream.read(abBuffer, 0, nFrameSize);
                    if (nBufferOffset == -1) {
                        streamIterator.remove();
                    } else {
                        for(int nChannel = 0; nChannel < nChannels; ++nChannel) {
                            nBufferOffset = nChannel * nSampleSize;
                            int nSampleToAdd = 0;
                            if (encoding.equals(Encoding.PCM_SIGNED)) {
                                switch(nSampleSize) {
                                    case 1:
                                        nSampleToAdd = abBuffer[nBufferOffset];
                                        break;
                                    case 2:
                                        nSampleToAdd = TConversionTool.bytesToInt16(abBuffer, nBufferOffset, bBigEndian);
                                        break;
                                    case 3:
                                        nSampleToAdd = TConversionTool.bytesToInt24(abBuffer, nBufferOffset, bBigEndian);
                                        break;
                                    case 4:
                                        nSampleToAdd = TConversionTool.bytesToInt32(abBuffer, nBufferOffset, bBigEndian);
                                }
                            } else if (encoding.equals(Encoding.ALAW)) {
                                nSampleToAdd = TConversionTool.alaw2linear(abBuffer[nBufferOffset]);
                            } else if (encoding.equals(Encoding.ULAW)) {
                                nSampleToAdd = TConversionTool.ulaw2linear(abBuffer[nBufferOffset]);
                            }

                            anMixedSamples[nChannel] += nSampleToAdd;
                        }
                    }
                }

                for(int nChannel = 0; nChannel < nChannels; ++nChannel) {
                    nBufferOffset = nOffset + nFrameBoundry + nChannel * nSampleSize;
                    if (encoding.equals(Encoding.PCM_SIGNED)) {
                        switch(nSampleSize) {
                            case 1:
                                abData[nBufferOffset] = (byte)anMixedSamples[nChannel];
                                break;
                            case 2:
                                TConversionTool.intToBytes16(anMixedSamples[nChannel], abData, nBufferOffset, bBigEndian);
                                break;
                            case 3:
                                TConversionTool.intToBytes24(anMixedSamples[nChannel], abData, nBufferOffset, bBigEndian);
                                break;
                            case 4:
                                TConversionTool.intToBytes32(anMixedSamples[nChannel], abData, nBufferOffset, bBigEndian);
                        }
                    } else if (encoding.equals(Encoding.ALAW)) {
                        abData[nBufferOffset] = TConversionTool.linear2alaw((short)anMixedSamples[nChannel]);
                    } else if (encoding.equals(Encoding.ULAW)) {
                        abData[nBufferOffset] = TConversionTool.linear2ulaw(anMixedSamples[nChannel]);
                    }
                }
                break;
            }
        }

        return nLength;
    }

    public long skip(long lLength) throws IOException {
        Iterator streamIterator = this.m_audioInputStreamList.iterator();

        while(streamIterator.hasNext()) {
            AudioInputStream stream = (AudioInputStream)streamIterator.next();
            stream.skip(lLength);
        }

        return lLength;
    }

    public int available() throws IOException {
        int nAvailable = 0;

        AudioInputStream stream;
        for(Iterator streamIterator = this.m_audioInputStreamList.iterator(); streamIterator.hasNext(); nAvailable = Math.min(nAvailable, stream.available())) {
            stream = (AudioInputStream)streamIterator.next();
        }

        return nAvailable;
    }

    public void close() throws IOException {
    }

    public void mark(int nReadLimit) {
        Iterator streamIterator = this.m_audioInputStreamList.iterator();

        while(streamIterator.hasNext()) {
            AudioInputStream stream = (AudioInputStream)streamIterator.next();
            stream.mark(nReadLimit);
        }

    }

    public void reset() throws IOException {
        Iterator streamIterator = this.m_audioInputStreamList.iterator();

        while(streamIterator.hasNext()) {
            AudioInputStream stream = (AudioInputStream)streamIterator.next();
            stream.reset();
        }

    }

    public boolean markSupported() {
        Iterator streamIterator = this.m_audioInputStreamList.iterator();

        AudioInputStream stream;
        do {
            if (!streamIterator.hasNext()) {
                return true;
            }

            stream = (AudioInputStream)streamIterator.next();
        } while(stream.markSupported());

        return false;
    }

    private static void out(String strMessage) {
        System.out.println(strMessage);
    }
}
