//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.commun.vue.jedit;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.text.Segment;
import javax.swing.text.TabExpander;
import javax.swing.text.Utilities;

public class SyntaxUtilities {
    static Color maroon = new Color(11546720);
    static Color darkBlue = new Color(128);
    static Color darkGreen;
    static Color vertFonce;
    static Color darkPurple;

    public static boolean regionMatches(boolean ignoreCase, Segment text, int offset, String match) {
        int length = offset + match.length();
        char[] textArray = text.array;
        if (length > text.offset + text.count) {
            return false;
        } else {
            int i = offset;

            for(int j = 0; i < length; ++j) {
                char c1 = textArray[i];
                char c2 = match.charAt(j);
                if (ignoreCase) {
                    c1 = Character.toUpperCase(c1);
                    c2 = Character.toUpperCase(c2);
                }

                if (c1 != c2) {
                    return false;
                }

                ++i;
            }

            return true;
        }
    }

    public static boolean regionMatches(boolean ignoreCase, Segment text, int offset, char[] match) {
        int length = offset + match.length;
        char[] textArray = text.array;
        if (length > text.offset + text.count) {
            return false;
        } else {
            int i = offset;

            for(int j = 0; i < length; ++j) {
                char c1 = textArray[i];
                char c2 = match[j];
                if (ignoreCase) {
                    c1 = Character.toUpperCase(c1);
                    c2 = Character.toUpperCase(c2);
                }

                if (c1 != c2) {
                    return false;
                }

                ++i;
            }

            return true;
        }
    }

    public static SyntaxStyle[] getDefaultSyntaxStyles() {
        SyntaxStyle[] styles = new SyntaxStyle[]{null, new SyntaxStyle(vertFonce, false, false), new SyntaxStyle(vertFonce, false, false), new SyntaxStyle(vertFonce, false, false), new SyntaxStyle(vertFonce, false, true), new SyntaxStyle(maroon, false, true), new SyntaxStyle(Color.blue, false, false), new SyntaxStyle(Color.blue, false, true), new SyntaxStyle(Color.blue, false, false), new SyntaxStyle(darkBlue, false, true), new SyntaxStyle(Color.red, false, true)};
        return styles;
    }

    public static int paintSyntaxLine(Segment line, Token tokens, SyntaxStyle[] styles, TabExpander expander, Graphics gfx, int x, int y) {
        Font defaultFont = gfx.getFont();
        Color defaultColor = gfx.getColor();
        int offset = 0;

        while(true) {
            byte id = tokens.id;
            if (id == 127) {
                return x;
            }

            int length = tokens.length;
            if (id == 0) {
                if (!defaultColor.equals(gfx.getColor())) {
                    gfx.setColor(defaultColor);
                }

                if (!defaultFont.equals(gfx.getFont())) {
                    gfx.setFont(defaultFont);
                }
            } else {
                styles[id].setGraphicsFlags(gfx, defaultFont);
            }

            line.count = length;
            x = Utilities.drawTabbedText(line, x, y, gfx, expander, 0);
            line.offset += length;
            offset += length;
            tokens = tokens.next;
        }
    }

    private SyntaxUtilities() {
    }

    static {
        darkGreen = Color.GREEN.darker();
        vertFonce = new Color(0, 102, 0);
        darkPurple = (new Color(10494192)).darker();
    }
}
