//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.commun.vue.jedit;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import javax.swing.ToolTipManager;
import javax.swing.text.Segment;
import javax.swing.text.TabExpander;
import javax.swing.text.Utilities;

public class TextAreaPainter extends JComponent implements TabExpander {
    int currentLineIndex;
    Token currentLineTokens;
    Segment currentLine;
    protected JEditTextArea textArea;
    protected SyntaxStyle[] styles;
    protected Color caretColor;
    protected Color selectionColor;
    protected Color lineHighlightColor;
    protected Color bracketHighlightColor;
    protected Color eolMarkerColor;
    protected boolean blockCaret;
    protected boolean lineHighlight;
    protected boolean bracketHighlight;
    protected boolean paintInvalid;
    protected boolean eolMarkers;
    protected int cols;
    protected int rows;
    protected int tabSize;
    protected FontMetrics fm;
    protected TextAreaPainter.Highlight highlights;

    public TextAreaPainter(JEditTextArea textArea, TextAreaDefaults defaults) {
        this.textArea = textArea;
        this.setAutoscrolls(true);
        this.setDoubleBuffered(true);
        this.setOpaque(true);
        ToolTipManager.sharedInstance().registerComponent(this);
        this.currentLine = new Segment();
        this.currentLineIndex = -1;
        this.setCursor(Cursor.getPredefinedCursor(2));
        this.setForeground(Color.black);
        this.setBackground(Color.white);
        this.blockCaret = defaults.blockCaret;
        this.styles = defaults.styles;
        this.cols = defaults.cols;
        this.rows = defaults.rows;
        this.caretColor = defaults.caretColor;
        this.selectionColor = defaults.selectionColor;
        this.lineHighlightColor = defaults.lineHighlightColor;
        this.lineHighlight = defaults.lineHighlight;
        this.bracketHighlightColor = defaults.bracketHighlightColor;
        this.bracketHighlight = defaults.bracketHighlight;
        this.paintInvalid = defaults.paintInvalid;
        this.eolMarkerColor = defaults.eolMarkerColor;
        this.eolMarkers = defaults.eolMarkers;
    }

    public final boolean isManagingFocus() {
        return false;
    }

    public final SyntaxStyle[] getStyles() {
        return this.styles;
    }

    public final void setStyles(SyntaxStyle[] styles) {
        this.styles = styles;
        this.repaint();
    }

    public final Color getCaretColor() {
        return this.caretColor;
    }

    public final void setCaretColor(Color caretColor) {
        this.caretColor = caretColor;
        this.invalidateSelectedLines();
    }

    public final Color getSelectionColor() {
        return this.selectionColor;
    }

    public final void setSelectionColor(Color selectionColor) {
        this.selectionColor = selectionColor;
        this.invalidateSelectedLines();
    }

    public final Color getLineHighlightColor() {
        return this.lineHighlightColor;
    }

    public final void setLineHighlightColor(Color lineHighlightColor) {
        this.lineHighlightColor = lineHighlightColor;
        this.invalidateSelectedLines();
    }

    public final boolean isLineHighlightEnabled() {
        return this.lineHighlight;
    }

    public final void setLineHighlightEnabled(boolean lineHighlight) {
        this.lineHighlight = lineHighlight;
        this.invalidateSelectedLines();
    }

    public final Color getBracketHighlightColor() {
        return this.bracketHighlightColor;
    }

    public final void setBracketHighlightColor(Color bracketHighlightColor) {
        this.bracketHighlightColor = bracketHighlightColor;
        this.invalidateLine(this.textArea.getBracketLine());
    }

    public final boolean isBracketHighlightEnabled() {
        return this.bracketHighlight;
    }

    public final void setBracketHighlightEnabled(boolean bracketHighlight) {
        this.bracketHighlight = bracketHighlight;
        this.invalidateLine(this.textArea.getBracketLine());
    }

    public final boolean isBlockCaretEnabled() {
        return this.blockCaret;
    }

    public final void setBlockCaretEnabled(boolean blockCaret) {
        this.blockCaret = blockCaret;
        this.invalidateSelectedLines();
    }

    public final Color getEOLMarkerColor() {
        return this.eolMarkerColor;
    }

    public final void setEOLMarkerColor(Color eolMarkerColor) {
        this.eolMarkerColor = eolMarkerColor;
        this.repaint();
    }

    public final boolean getEOLMarkersPainted() {
        return this.eolMarkers;
    }

    public final void setEOLMarkersPainted(boolean eolMarkers) {
        this.eolMarkers = eolMarkers;
        this.repaint();
    }

    public boolean getInvalidLinesPainted() {
        return this.paintInvalid;
    }

    public void setInvalidLinesPainted(boolean paintInvalid) {
        this.paintInvalid = paintInvalid;
    }

    public void addCustomHighlight(TextAreaPainter.Highlight highlight) {
        highlight.init(this.textArea, this.highlights);
        this.highlights = highlight;
    }

    public String getToolTipText(MouseEvent evt) {
        return this.highlights != null ? this.highlights.getToolTipText(evt) : null;
    }

    public FontMetrics getFontMetrics() {
        return this.fm;
    }

    public void setFont(Font font) {
        super.setFont(font);
        this.fm = this.getFontMetrics(font);
        this.textArea.recalculateVisibleLines();
    }

    public void paint(Graphics gfx) {
        ((Graphics2D)gfx).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        this.tabSize = this.fm.charWidth(' ') * (Integer)this.textArea.getDocument().getProperty("tabSize");
        Rectangle clipRect = gfx.getClipBounds();
        gfx.setColor(this.getBackground());
        gfx.fillRect(clipRect.x, clipRect.y, clipRect.width, clipRect.height);
        int height = this.fm.getHeight();
        int firstLine = this.textArea.getFirstLine();
        int firstInvalid = firstLine + clipRect.y / height;
        int lastInvalid = firstLine + (clipRect.y + clipRect.height - 1) / height;

        try {
            TokenMarker tokenMarker = this.textArea.getDocument().getTokenMarker();
            int x = this.textArea.getHorizontalOffset();

            int h;
            for(h = firstInvalid; h <= lastInvalid; ++h) {
                this.paintLine(gfx, tokenMarker, h, x);
            }

            if (tokenMarker != null && tokenMarker.isNextLineRequested()) {
                h = clipRect.y + clipRect.height;
                this.repaint(0, h, this.getWidth(), this.getHeight() - h);
            }
        } catch (Exception var10) {
            System.err.println("Error repainting line range {" + firstInvalid + "," + lastInvalid + "}:");
            var10.printStackTrace();
        }

    }

    public final void invalidateLine(int line) {
        this.repaint(0, this.textArea.lineToY(line) + this.fm.getMaxDescent() + this.fm.getLeading(), this.getWidth(), this.fm.getHeight());
    }

    public final void invalidateLineRange(int firstLine, int lastLine) {
        this.repaint(0, this.textArea.lineToY(firstLine) + this.fm.getMaxDescent() + this.fm.getLeading(), this.getWidth(), (lastLine - firstLine + 1) * this.fm.getHeight());
    }

    public final void invalidateSelectedLines() {
        this.invalidateLineRange(this.textArea.getSelectionStartLine(), this.textArea.getSelectionEndLine());
    }

    public float nextTabStop(float x, int tabOffset) {
        int offset = this.textArea.getHorizontalOffset();
        int ntabs = ((int)x - offset) / this.tabSize;
        return (float)((ntabs + 1) * this.tabSize + offset);
    }

    public Dimension getPreferredSize() {
        Dimension dim = new Dimension();
        dim.width = this.fm.charWidth('w') * this.cols;
        dim.height = this.fm.getHeight() * this.rows;
        return dim;
    }

    public Dimension getMinimumSize() {
        return this.getPreferredSize();
    }

    protected void paintLine(Graphics gfx, TokenMarker tokenMarker, int line, int x) {
        Font defaultFont = this.getFont();
        Color defaultColor = this.getForeground();
        this.currentLineIndex = line;
        int y = this.textArea.lineToY(line);
        if (line >= 0 && line < this.textArea.getLineCount()) {
            if (tokenMarker == null) {
                this.paintPlainLine(gfx, line, defaultFont, defaultColor, x, y);
            } else {
                this.paintSyntaxLine(gfx, tokenMarker, line, defaultFont, defaultColor, x, y);
            }
        } else if (this.paintInvalid) {
            this.paintHighlight(gfx, line, y);
            this.styles[10].setGraphicsFlags(gfx, defaultFont);
            gfx.drawString("~", 0, y + this.fm.getHeight());
        }

    }

    protected void paintPlainLine(Graphics gfx, int line, Font defaultFont, Color defaultColor, int x, int y) {
        this.paintHighlight(gfx, line, y);
        this.textArea.getLineText(line, this.currentLine);
        gfx.setFont(defaultFont);
        gfx.setColor(defaultColor);
        y += this.fm.getHeight();
        x = Utilities.drawTabbedText(this.currentLine, x, y, gfx, this, 0);
        if (this.eolMarkers) {
            gfx.setColor(this.eolMarkerColor);
            gfx.drawString(".", x, y);
        }

    }

    protected void paintSyntaxLine(Graphics gfx, TokenMarker tokenMarker, int line, Font defaultFont, Color defaultColor, int x, int y) {
        this.textArea.getLineText(this.currentLineIndex, this.currentLine);
        this.currentLineTokens = tokenMarker.markTokens(this.currentLine, this.currentLineIndex);
        this.paintHighlight(gfx, line, y);
        gfx.setFont(defaultFont);
        gfx.setColor(defaultColor);
        y += this.fm.getHeight();
        x = SyntaxUtilities.paintSyntaxLine(this.currentLine, this.currentLineTokens, this.styles, this, gfx, x, y);
        if (this.eolMarkers) {
            gfx.setColor(this.eolMarkerColor);
            gfx.drawString(".", x, y);
        }

    }

    protected void paintHighlight(Graphics gfx, int line, int y) {
        if (line >= this.textArea.getSelectionStartLine() && line <= this.textArea.getSelectionEndLine()) {
            this.paintLineHighlight(gfx, line, y);
        }

        if (this.highlights != null) {
            this.highlights.paintHighlight(gfx, line, y);
        }

        if (this.bracketHighlight && line == this.textArea.getBracketLine()) {
            this.paintBracketHighlight(gfx, line, y);
        }

        if (line == this.textArea.getCaretLine()) {
            this.paintCaret(gfx, line, y);
        }

    }

    protected void paintLineHighlight(Graphics gfx, int line, int y) {
        int height = this.fm.getHeight();
        y += this.fm.getLeading() + this.fm.getMaxDescent();
        int selectionStart = this.textArea.getSelectionStart();
        int selectionEnd = this.textArea.getSelectionEnd();
        if (selectionStart == selectionEnd) {
            if (this.lineHighlight) {
                gfx.setColor(this.lineHighlightColor);
                gfx.fillRect(0, y, this.getWidth(), height);
            }
        } else {
            gfx.setColor(this.selectionColor);
            int selectionStartLine = this.textArea.getSelectionStartLine();
            int selectionEndLine = this.textArea.getSelectionEndLine();
            int lineStart = this.textArea.getLineStartOffset(line);
            int x1;
            int x2;
            if (this.textArea.isSelectionRectangular()) {
                int lineLen = this.textArea.getLineLength(line);
                x1 = this.textArea._offsetToX(line, Math.min(lineLen, selectionStart - this.textArea.getLineStartOffset(selectionStartLine)));
                x2 = this.textArea._offsetToX(line, Math.min(lineLen, selectionEnd - this.textArea.getLineStartOffset(selectionEndLine)));
                if (x1 == x2) {
                    ++x2;
                }
            } else if (selectionStartLine == selectionEndLine) {
                x1 = this.textArea._offsetToX(line, selectionStart - lineStart);
                x2 = this.textArea._offsetToX(line, selectionEnd - lineStart);
            } else if (line == selectionStartLine) {
                x1 = this.textArea._offsetToX(line, selectionStart - lineStart);
                x2 = this.getWidth();
            } else if (line == selectionEndLine) {
                x1 = 0;
                x2 = this.textArea._offsetToX(line, selectionEnd - lineStart);
            } else {
                x1 = 0;
                x2 = this.getWidth();
            }

            gfx.fillRect(x1 > x2 ? x2 : x1, y, x1 > x2 ? x1 - x2 : x2 - x1, height);
        }

    }

    protected void paintBracketHighlight(Graphics gfx, int line, int y) {
        int position = this.textArea.getBracketPosition();
        if (position != -1) {
            y += this.fm.getLeading() + this.fm.getMaxDescent();
            int x = this.textArea._offsetToX(line, position);
            gfx.setColor(this.bracketHighlightColor);
            gfx.drawRect(x, y, this.fm.charWidth('(') - 1, this.fm.getHeight() - 1);
        }
    }

    protected void paintCaret(Graphics gfx, int line, int y) {
        if (this.textArea.isCaretVisible()) {
            int offset = this.textArea.getCaretPosition() - this.textArea.getLineStartOffset(line);
            int caretX = this.textArea._offsetToX(line, offset);
            int caretWidth = !this.blockCaret && !this.textArea.isOverwriteEnabled() ? 1 : this.fm.charWidth('w');
            y += this.fm.getLeading() + this.fm.getMaxDescent();
            int height = this.fm.getHeight();
            gfx.setColor(this.caretColor);
            if (this.textArea.isOverwriteEnabled()) {
                gfx.fillRect(caretX, y + height - 1, caretWidth, 1);
            } else {
                gfx.drawRect(caretX, y, caretWidth - 1, height - 1);
            }
        }

    }

    public interface Highlight {
        void init(JEditTextArea var1, TextAreaPainter.Highlight var2);

        void paintHighlight(Graphics var1, int var2, int var3);

        String getToolTipText(MouseEvent var1);
    }
}
