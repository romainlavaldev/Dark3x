//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.commun.vue.jedit;

import javax.swing.text.Segment;

public class RubyTokenMarker extends TokenMarker {
    private static KeywordMap cKeywords;
    private boolean cpp;
    private KeywordMap keywords;
    private int lastOffset;
    private int lastKeyword;
    private static KeywordMap rubyKeywords;

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

    public RubyTokenMarker() {
        this(true, getKeywords());
    }

    public RubyTokenMarker(boolean cpp, KeywordMap keywords) {
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

        label70:
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
                                backslash = false;
                                this.doKeyword(line, i, c);
                                this.addToken(i - this.lastOffset, token);
                                this.addToken(length - i, (byte)1);
                                this.lastOffset = this.lastKeyword = length;
                                break label70;
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
        if (rubyKeywords == null) {
            rubyKeywords = new KeywordMap(false);
            rubyKeywords.add("require", (byte)7);
            rubyKeywords.add("load", (byte)7);
            rubyKeywords.add("import", (byte)7);
            rubyKeywords.add("def", (byte)8);
            rubyKeywords.add("end", (byte)8);
            rubyKeywords.add("new", (byte)8);
            rubyKeywords.add("class", (byte)8);
            rubyKeywords.add("private_class_method", (byte)6);
            rubyKeywords.add("public_class_method", (byte)6);
            rubyKeywords.add("public", (byte)6);
            rubyKeywords.add("private", (byte)6);
            rubyKeywords.add("do", (byte)8);
            rubyKeywords.add("else", (byte)6);
            rubyKeywords.add("for", (byte)6);
            rubyKeywords.add("if", (byte)6);
            rubyKeywords.add("each", (byte)6);
            rubyKeywords.add("return", (byte)6);
            rubyKeywords.add("while", (byte)6);
            rubyKeywords.add("print", (byte)6);
            rubyKeywords.add("puts", (byte)6);
            rubyKeywords.add("self", (byte)4);
            rubyKeywords.add("nil", (byte)4);
            rubyKeywords.add("super", (byte)4);
            rubyKeywords.add("true", (byte)4);
            rubyKeywords.add("false", (byte)4);
        }

        return rubyKeywords;
    }
}
