//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.commun.vue.jedit;

import javax.swing.text.Segment;

public abstract class TokenMarker {
    protected Token firstToken;
    protected Token lastToken;
    protected TokenMarker.LineInfo[] lineInfo;
    protected int length;
    protected int lastLine = -1;
    protected boolean nextLineRequested;

    public Token markTokens(Segment line, int lineIndex) {
        if (lineIndex >= this.length) {
            throw new IllegalArgumentException("Tokenizing invalid line: " + lineIndex);
        } else {
            this.lastToken = null;
            TokenMarker.LineInfo info = this.lineInfo[lineIndex];
            TokenMarker.LineInfo prev;
            if (lineIndex == 0) {
                prev = null;
            } else {
                prev = this.lineInfo[lineIndex - 1];
            }

            byte oldToken = info.token;
            byte token = this.markTokensImpl(prev == null ? 0 : prev.token, line, lineIndex);
            info.token = token;
            if (this.lastLine != lineIndex || !this.nextLineRequested) {
                this.nextLineRequested = oldToken != token;
            }

            this.lastLine = lineIndex;
            this.addToken(0, (byte)127);
            return this.firstToken;
        }
    }

    protected abstract byte markTokensImpl(byte var1, Segment var2, int var3);

    public boolean supportsMultilineTokens() {
        return true;
    }

    public void insertLines(int index, int lines) {
        if (lines > 0) {
            this.length += lines;
            this.ensureCapacity(this.length);
            int len = index + lines;
            System.arraycopy(this.lineInfo, index, this.lineInfo, len, this.lineInfo.length - len);

            for(int i = index + lines - 1; i >= index; --i) {
                this.lineInfo[i] = new TokenMarker.LineInfo();
            }

        }
    }

    public void deleteLines(int index, int lines) {
        if (lines > 0) {
            int len = index + lines;
            this.length -= lines;
            System.arraycopy(this.lineInfo, len, this.lineInfo, index, this.lineInfo.length - len);
        }
    }

    public int getLineCount() {
        return this.length;
    }

    public boolean isNextLineRequested() {
        return this.nextLineRequested;
    }

    protected TokenMarker() {
    }

    protected void ensureCapacity(int index) {
        if (this.lineInfo == null) {
            this.lineInfo = new TokenMarker.LineInfo[index + 1];
        } else if (this.lineInfo.length <= index) {
            TokenMarker.LineInfo[] lineInfoN = new TokenMarker.LineInfo[(index + 1) * 2];
            System.arraycopy(this.lineInfo, 0, lineInfoN, 0, this.lineInfo.length);
            this.lineInfo = lineInfoN;
        }

    }

    protected void addToken(int length, byte id) {
        if (id >= 100 && id <= 126) {
            throw new InternalError("Invalid id: " + id);
        } else if (length != 0 || id == 127) {
            if (this.firstToken == null) {
                this.firstToken = new Token(length, id);
                this.lastToken = this.firstToken;
            } else if (this.lastToken == null) {
                this.lastToken = this.firstToken;
                this.firstToken.length = length;
                this.firstToken.id = id;
            } else if (this.lastToken.next == null) {
                this.lastToken.next = new Token(length, id);
                this.lastToken = this.lastToken.next;
            } else {
                this.lastToken = this.lastToken.next;
                this.lastToken.length = length;
                this.lastToken.id = id;
            }

        }
    }

    public class LineInfo {
        public byte token;
        public Object obj;

        public LineInfo() {
        }

        public LineInfo(byte token, Object obj) {
            this.token = token;
            this.obj = obj;
        }
    }
}
