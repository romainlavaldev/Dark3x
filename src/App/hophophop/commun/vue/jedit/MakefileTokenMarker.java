//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.commun.vue.jedit;

import javax.swing.text.Segment;

public class MakefileTokenMarker extends TokenMarker {
    public MakefileTokenMarker() {
    }

    public byte markTokensImpl(byte token, Segment line, int lineIndex) {
        char[] array = line.array;
        int offset = line.offset;
        int lastOffset = offset;
        int length = line.count + offset;
        boolean backslash = false;

        label87:
        for(int i = offset; i < length; ++i) {
            int i1 = i + 1;
            char c = array[i];
            if (c == '\\') {
                backslash = !backslash;
            } else {
                switch(token) {
                    case 0:
                        switch(c) {
                            case '\t':
                            case ' ':
                            case ':':
                            case '=':
                                backslash = false;
                                if (lastOffset == offset) {
                                    this.addToken(i1 - lastOffset, (byte)6);
                                    lastOffset = i1;
                                }
                                break;
                            case '"':
                                if (backslash) {
                                    backslash = false;
                                } else {
                                    this.addToken(i - lastOffset, token);
                                    token = 3;
                                    lastOffset = i;
                                }
                                break;
                            case '#':
                                if (!backslash) {
                                    this.addToken(i - lastOffset, token);
                                    this.addToken(length - i, (byte)1);
                                    lastOffset = length;
                                    break label87;
                                }

                                backslash = false;
                                break;
                            case '$':
                                if (backslash) {
                                    backslash = false;
                                } else if (lastOffset != offset) {
                                    this.addToken(i - lastOffset, token);
                                    lastOffset = i;
                                    if (length - i > 1) {
                                        char c1 = array[i1];
                                        if (c1 != '(' && c1 != '{') {
                                            this.addToken(2, (byte)7);
                                            lastOffset = i + 2;
                                            ++i;
                                        } else {
                                            token = 7;
                                        }
                                    }
                                }
                                break;
                            case '\'':
                                if (backslash) {
                                    backslash = false;
                                } else {
                                    this.addToken(i - lastOffset, token);
                                    token = 4;
                                    lastOffset = i;
                                }
                                break;
                            default:
                                backslash = false;
                        }
                    case 7:
                        backslash = false;
                        if (c == ')' || c == '}') {
                            this.addToken(i1 - lastOffset, token);
                            token = 0;
                            lastOffset = i1;
                        }
                    case 1:
                    case 2:
                    case 5:
                    case 6:
                    default:
                        break;
                    case 3:
                        if (backslash) {
                            backslash = false;
                        } else if (c == '"') {
                            this.addToken(i1 - lastOffset, token);
                            token = 0;
                            lastOffset = i1;
                        } else {
                            backslash = false;
                        }
                        break;
                    case 4:
                        if (backslash) {
                            backslash = false;
                        } else if (c == '\'') {
                            this.addToken(i1 - lastOffset, (byte)3);
                            token = 0;
                            lastOffset = i1;
                        } else {
                            backslash = false;
                        }
                }
            }
        }

        switch(token) {
            case 4:
                this.addToken(length - lastOffset, (byte)3);
                break;
            case 7:
                this.addToken(length - lastOffset, (byte)10);
                token = 0;
                break;
            default:
                this.addToken(length - lastOffset, token);
        }

        return token;
    }
}
