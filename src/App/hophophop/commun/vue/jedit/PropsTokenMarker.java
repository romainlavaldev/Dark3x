//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.commun.vue.jedit;

import javax.swing.text.Segment;

public class PropsTokenMarker extends TokenMarker {
    public static final byte VALUE = 100;

    public PropsTokenMarker() {
    }

    public byte markTokensImpl(byte token, Segment line, int lineIndex) {
        char[] array = line.array;
        int offset = line.offset;
        int lastOffset = offset;
        int length = line.count + offset;

        label38:
        for(int i = offset; i < length; ++i) {
            int i1 = i + 1;
            switch(token) {
                case 0:
                    switch(array[i]) {
                        case '#':
                        case ';':
                            if (i == offset) {
                                this.addToken(line.count, (byte)1);
                                lastOffset = length;
                                break label38;
                            }
                            continue;
                        case '=':
                            this.addToken(i - lastOffset, (byte)6);
                            token = 100;
                            lastOffset = i;
                            continue;
                        case '[':
                            if (i == offset) {
                                this.addToken(i - lastOffset, token);
                                token = 7;
                                lastOffset = i;
                            }
                        default:
                            continue;
                    }
                case 7:
                    if (array[i] == ']') {
                        this.addToken(i1 - lastOffset, token);
                        token = 0;
                        lastOffset = i1;
                    }
                case 100:
                    break;
                default:
                    throw new InternalError("Invalid state: " + token);
            }
        }

        if (lastOffset != length) {
            this.addToken(length - lastOffset, (byte)0);
        }

        return 0;
    }

    public boolean supportsMultilineTokens() {
        return false;
    }
}
