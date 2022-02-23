//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.commun.vue.jedit;

import javax.swing.text.Segment;

public class PatchTokenMarker extends TokenMarker {
    public PatchTokenMarker() {
    }

    public byte markTokensImpl(byte token, Segment line, int lineIndex) {
        if (line.count == 0) {
            return 0;
        } else {
            switch(line.array[line.offset]) {
                case '*':
                case '@':
                    this.addToken(line.count, (byte)8);
                    break;
                case '+':
                case '>':
                    this.addToken(line.count, (byte)6);
                    break;
                case '-':
                case '<':
                    this.addToken(line.count, (byte)7);
                    break;
                default:
                    this.addToken(line.count, (byte)0);
            }

            return 0;
        }
    }

    public boolean supportsMultilineTokens() {
        return false;
    }
}
