//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.commun.vue.jedit;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentEvent.ElementChange;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.PlainDocument;
import javax.swing.text.Segment;
import javax.swing.undo.UndoableEdit;

public class SyntaxDocument extends PlainDocument {
    protected TokenMarker tokenMarker;

    public SyntaxDocument() {
        this.putProperty("tabSize", 4);
    }

    public TokenMarker getTokenMarker() {
        return this.tokenMarker;
    }

    public void setTokenMarker(TokenMarker tm) {
        this.tokenMarker = tm;
        if (tm != null) {
            this.tokenMarker.insertLines(0, this.getDefaultRootElement().getElementCount());
            this.tokenizeLines();
        }
    }

    public void tokenizeLines() {
        this.tokenizeLines(0, this.getDefaultRootElement().getElementCount());
    }

    public void tokenizeLines(int start, int len) {
        if (this.tokenMarker != null && this.tokenMarker.supportsMultilineTokens()) {
            Segment lineSegment = new Segment();
            Element map = this.getDefaultRootElement();
            len += start;

            try {
                for(int i = start; i < len; ++i) {
                    Element lineElement = map.getElement(i);
                    int lineStart = lineElement.getStartOffset();
                    this.getText(lineStart, lineElement.getEndOffset() - lineStart - 1, lineSegment);
                    this.tokenMarker.markTokens(lineSegment, i);
                }
            } catch (BadLocationException var8) {
                var8.printStackTrace();
            }

        }
    }

    public void beginCompoundEdit() {
    }

    public void endCompoundEdit() {
    }

    public void addUndoableEdit(UndoableEdit edit) {
    }

    protected void fireInsertUpdate(DocumentEvent evt) {
        if (this.tokenMarker != null) {
            ElementChange ch = evt.getChange(this.getDefaultRootElement());
            if (ch != null) {
                this.tokenMarker.insertLines(ch.getIndex() + 1, ch.getChildrenAdded().length - ch.getChildrenRemoved().length);
            }
        }

        super.fireInsertUpdate(evt);
    }

    protected void fireRemoveUpdate(DocumentEvent evt) {
        if (this.tokenMarker != null) {
            ElementChange ch = evt.getChange(this.getDefaultRootElement());
            if (ch != null) {
                this.tokenMarker.deleteLines(ch.getIndex() + 1, ch.getChildrenRemoved().length - ch.getChildrenAdded().length);
            }
        }

        super.fireRemoveUpdate(evt);
    }
}
