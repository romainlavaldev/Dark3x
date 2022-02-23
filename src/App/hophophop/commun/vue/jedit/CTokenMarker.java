//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.commun.vue.jedit;

import javax.swing.text.Segment;

public class CTokenMarker extends TokenMarker {
    private static KeywordMap cKeywords;
    private boolean cpp;
    private KeywordMap keywords;
    private int lastOffset;
    private int lastKeyword;

    public CTokenMarker() {
        this(true, getKeywords());
    }

    public CTokenMarker(boolean cpp, KeywordMap keywords) {
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
            cKeywords.add("true", (byte)4);
            cKeywords.add("false", (byte)4);
            cKeywords.add("NULL", (byte)4);
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
