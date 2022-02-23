//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.commun.vue.jedit;

import java.awt.Color;
import javax.swing.JPopupMenu;

public class TextAreaDefaults {
    private static TextAreaDefaults DEFAULTS;
    public InputHandler inputHandler;
    public SyntaxDocument document;
    public boolean editable;
    public boolean caretVisible;
    public boolean caretBlinks;
    public boolean blockCaret;
    public int electricScroll;
    public int cols;
    public int rows;
    public SyntaxStyle[] styles;
    public Color caretColor;
    public Color selectionColor;
    public Color lineHighlightColor;
    public boolean lineHighlight;
    public Color bracketHighlightColor;
    public boolean bracketHighlight;
    public Color eolMarkerColor;
    public boolean eolMarkers;
    public boolean paintInvalid;
    public JPopupMenu popup;

    public TextAreaDefaults() {
    }

    public static TextAreaDefaults getDefaults() {
        if (DEFAULTS == null) {
            DEFAULTS = new TextAreaDefaults();
            DEFAULTS.inputHandler = new DefaultInputHandler();
            DEFAULTS.inputHandler.addDefaultKeyBindings();
            DEFAULTS.document = new SyntaxDocument();
            DEFAULTS.editable = true;
            DEFAULTS.caretVisible = true;
            DEFAULTS.caretBlinks = true;
            DEFAULTS.electricScroll = 3;
            DEFAULTS.cols = 80;
            DEFAULTS.rows = 25;
            DEFAULTS.styles = SyntaxUtilities.getDefaultSyntaxStyles();
            DEFAULTS.caretColor = Color.black;
            DEFAULTS.selectionColor = new Color(13421823);
            DEFAULTS.lineHighlightColor = new Color(14737632);
            DEFAULTS.lineHighlight = true;
            DEFAULTS.bracketHighlightColor = Color.black;
            DEFAULTS.bracketHighlight = true;
            DEFAULTS.eolMarkerColor = new Color(39321);
            DEFAULTS.eolMarkers = false;
            DEFAULTS.paintInvalid = false;
        }

        return DEFAULTS;
    }
}
