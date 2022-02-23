//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.commun.vue.jedit;

import javax.swing.text.Segment;

public class NxcTokenMarker extends TokenMarker {
    private static KeywordMap cKeywords;
    private boolean cpp;
    private KeywordMap keywords;
    private int lastOffset;
    private int lastKeyword;

    public NxcTokenMarker() {
        this(true, getKeywords());
    }

    public NxcTokenMarker(boolean cpp, KeywordMap keywords) {
        this.cpp = cpp;
        this.keywords = keywords;
    }

    public byte markTokensImpl(byte token, Segment line, int lineIndex) {
        char[] array = line.array;
        int offset = line.offset;
        this.lastOffset = offset;
        this.lastKeyword = offset;
        int length = line.count + offset;
        boolean backslash = false;

        label107:
        for(int i = offset; i < length; ++i) {
            int i1 = i + 1;
            char c = array[i];
            if (c == '\\') {
                backslash = !backslash;
            } else {
                switch(token) {
                    case 0:
                        switch(c) {
                            case '"':
                                this.doKeyword(line, i, c);
                                if (backslash) {
                                    backslash = false;
                                } else {
                                    this.addToken(i - this.lastOffset, token);
                                    token = 3;
                                    this.lastOffset = this.lastKeyword = i;
                                }
                                continue;
                            case '#':
                                if (backslash) {
                                    backslash = false;
                                } else if (this.cpp && !this.doKeyword(line, i, c)) {
                                    this.addToken(i - this.lastOffset, token);
                                    this.addToken(length - i, (byte)7);
                                    this.lastOffset = this.lastKeyword = length;
                                    break label107;
                                }
                                continue;
                            case '\'':
                                this.doKeyword(line, i, c);
                                if (backslash) {
                                    backslash = false;
                                } else {
                                    this.addToken(i - this.lastOffset, token);
                                    token = 4;
                                    this.lastOffset = this.lastKeyword = i;
                                }
                                continue;
                            case '/':
                                backslash = false;
                                this.doKeyword(line, i, c);
                                if (length - i > 1) {
                                    switch(array[i1]) {
                                        case '*':
                                            this.addToken(i - this.lastOffset, token);
                                            this.lastOffset = this.lastKeyword = i;
                                            if (length - i > 2 && array[i + 2] == '*') {
                                                token = 2;
                                            } else {
                                                token = 1;
                                            }
                                            continue;
                                        case '/':
                                            this.addToken(i - this.lastOffset, token);
                                            this.addToken(length - i, (byte)1);
                                            this.lastOffset = this.lastKeyword = length;
                                            break label107;
                                    }
                                }
                                continue;
                            case ':':
                                if (this.lastKeyword == offset) {
                                    if (!this.doKeyword(line, i, c)) {
                                        backslash = false;
                                        this.addToken(i1 - this.lastOffset, (byte)5);
                                        this.lastOffset = this.lastKeyword = i1;
                                    }
                                } else if (this.doKeyword(line, i, c)) {
                                }
                                continue;
                            default:
                                backslash = false;
                                if (!Character.isLetterOrDigit(c) && c != '_') {
                                    this.doKeyword(line, i, c);
                                }
                                continue;
                        }
                    case 1:
                    case 2:
                        backslash = false;
                        if (c == '*' && length - i > 1 && array[i1] == '/') {
                            ++i;
                            this.addToken(i + 1 - this.lastOffset, token);
                            token = 0;
                            this.lastOffset = this.lastKeyword = i + 1;
                        }
                        break;
                    case 3:
                        if (backslash) {
                            backslash = false;
                        } else if (c == '"') {
                            this.addToken(i1 - this.lastOffset, token);
                            token = 0;
                            this.lastOffset = this.lastKeyword = i1;
                        }
                        break;
                    case 4:
                        if (backslash) {
                            backslash = false;
                        } else if (c == '\'') {
                            this.addToken(i1 - this.lastOffset, (byte)3);
                            token = 0;
                            this.lastOffset = this.lastKeyword = i1;
                        }
                        break;
                    default:
                        throw new InternalError("Invalid state: " + token);
                }
            }
        }

        if (token == 0) {
            this.doKeyword(line, length, '\u0000');
        }

        switch(token) {
            case 3:
            case 4:
                this.addToken(length - this.lastOffset, (byte)10);
                token = 0;
                break;
            case 7:
                this.addToken(length - this.lastOffset, token);
                if (!backslash) {
                    token = 0;
                }
            case 5:
            case 6:
            default:
                this.addToken(length - this.lastOffset, token);
        }

        return token;
    }

    public static KeywordMap getKeywords() {
        if (cKeywords == null) {
            cKeywords = new KeywordMap(false);
            cKeywords.add("char", (byte)8);
            cKeywords.add("double", (byte)8);
            cKeywords.add("enum", (byte)8);
            cKeywords.add("float", (byte)8);
            cKeywords.add("int", (byte)8);
            cKeywords.add("long", (byte)8);
            cKeywords.add("short", (byte)8);
            cKeywords.add("signed", (byte)8);
            cKeywords.add("struct", (byte)8);
            cKeywords.add("typedef", (byte)8);
            cKeywords.add("union", (byte)8);
            cKeywords.add("unsigned", (byte)8);
            cKeywords.add("void", (byte)8);
            cKeywords.add("auto", (byte)6);
            cKeywords.add("const", (byte)6);
            cKeywords.add("extern", (byte)6);
            cKeywords.add("register", (byte)6);
            cKeywords.add("static", (byte)6);
            cKeywords.add("volatile", (byte)6);
            cKeywords.add("break", (byte)6);
            cKeywords.add("case", (byte)6);
            cKeywords.add("continue", (byte)6);
            cKeywords.add("default", (byte)6);
            cKeywords.add("do", (byte)6);
            cKeywords.add("else", (byte)6);
            cKeywords.add("for", (byte)6);
            cKeywords.add("goto", (byte)6);
            cKeywords.add("if", (byte)6);
            cKeywords.add("return", (byte)6);
            cKeywords.add("sizeof", (byte)6);
            cKeywords.add("switch", (byte)6);
            cKeywords.add("while", (byte)6);
            cKeywords.add("asm", (byte)7);
            cKeywords.add("asmlinkage", (byte)7);
            cKeywords.add("far", (byte)7);
            cKeywords.add("huge", (byte)7);
            cKeywords.add("inline", (byte)7);
            cKeywords.add("near", (byte)7);
            cKeywords.add("pascal", (byte)7);
            cKeywords.add("sub", (byte)7);
            cKeywords.add("SetDebug", (byte)6);
            cKeywords.add("PrintError", (byte)6);
            cKeywords.add("PlayTone", (byte)6);
            cKeywords.add("ToneOut", (byte)6);
            cKeywords.add("LCD_LINE1", (byte)5);
            cKeywords.add("LCD_LINE2", (byte)5);
            cKeywords.add("LCD_LINE3", (byte)5);
            cKeywords.add("LCD_LINE4", (byte)5);
            cKeywords.add("LCD_LINE5", (byte)5);
            cKeywords.add("LCD_LINE6", (byte)5);
            cKeywords.add("LCD_LINE7", (byte)5);
            cKeywords.add("LCD_LINE8", (byte)5);
            cKeywords.add("ClearScreen", (byte)6);
            cKeywords.add("ClearLine", (byte)6);
            cKeywords.add("NumOut", (byte)6);
            cKeywords.add("TextOut", (byte)6);
            cKeywords.add("LineOut", (byte)6);
            cKeywords.add("RectOut", (byte)6);
            cKeywords.add("CircleOut", (byte)6);
            cKeywords.add("BTN_RIGHT", (byte)5);
            cKeywords.add("BTN_LEFT", (byte)5);
            cKeywords.add("BTN_CENTER", (byte)5);
            cKeywords.add("BTN_EXIT", (byte)5);
            cKeywords.add("IsButtonPressed", (byte)6);
            cKeywords.add("WaitEnterKey", (byte)6);
            cKeywords.add("CurrentTick", (byte)6);
            cKeywords.add("Wait", (byte)6);
            cKeywords.add("OUT_A", (byte)5);
            cKeywords.add("OUT_B", (byte)5);
            cKeywords.add("OUT_C", (byte)5);
            cKeywords.add("OUT_AB", (byte)5);
            cKeywords.add("OUT_AC", (byte)5);
            cKeywords.add("OUT_BC", (byte)5);
            cKeywords.add("OUT_ABC", (byte)5);
            cKeywords.add("MotorOff", (byte)6);
            cKeywords.add("MotorOn", (byte)6);
            cKeywords.add("ResetMotorRotationCount", (byte)6);
            cKeywords.add("MotorRotationCount", (byte)6);
            cKeywords.add("IN_1", (byte)5);
            cKeywords.add("IN_2", (byte)5);
            cKeywords.add("IN_3", (byte)5);
            cKeywords.add("IN_4", (byte)5);
            cKeywords.add("NONE", (byte)5);
            cKeywords.add("SONIC", (byte)5);
            cKeywords.add("COLOR", (byte)5);
            cKeywords.add("SOUND", (byte)5);
            cKeywords.add("TOUCH", (byte)5);
            cKeywords.add("ARRAY", (byte)5);
            cKeywords.add("KEYPAD", (byte)5);
            cKeywords.add("DefineSensors", (byte)6);
            cKeywords.add("GetSensorValue", (byte)6);
            cKeywords.add("COLOR_BLACK", (byte)5);
            cKeywords.add("COLOR_BLUE", (byte)5);
            cKeywords.add("COLOR_GREEN", (byte)5);
            cKeywords.add("COLOR_YELLOW", (byte)5);
            cKeywords.add("COLOR_RED", (byte)5);
            cKeywords.add("COLOR_WHITE", (byte)5);
            cKeywords.add("GetSensorArray", (byte)6);
            cKeywords.add("LineCalibration", (byte)6);
            cKeywords.add("LineViewRaw", (byte)6);
            cKeywords.add("Random", (byte)6);
            cKeywords.add("true", (byte)3);
            cKeywords.add("false", (byte)3);
            cKeywords.add("NULL", (byte)3);
        }

        return cKeywords;
    }

    private boolean doKeyword(Segment line, int i, char c) {
        int i1 = i + 1;
        int len = i - this.lastKeyword;
        byte id = this.keywords.lookup(line, this.lastKeyword, len);
        if (id != 0) {
            if (this.lastKeyword != this.lastOffset) {
                this.addToken(this.lastKeyword - this.lastOffset, (byte)0);
            }

            this.addToken(len, id);
            this.lastOffset = i;
        }

        this.lastKeyword = i1;
        return false;
    }
}
