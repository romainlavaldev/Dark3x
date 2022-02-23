//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.commun.vue.jedit;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Enumeration;
import java.util.EventObject;
import java.util.Hashtable;
import javax.swing.JPopupMenu;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

public abstract class InputHandler extends KeyAdapter {
    public static final String SMART_HOME_END_PROPERTY = "InputHandler.homeEnd";
    public static final ActionListener COPY = new InputHandler.copy();
    public static final ActionListener CUT = new InputHandler.cut();
    public static final ActionListener PASTE = new InputHandler.paste();
    public static final ActionListener UNDO = new InputHandler.undo();
    public static final ActionListener REDO = new InputHandler.redo();
    public static final ActionListener SELECTALL = new InputHandler.selectall();
    public static final ActionListener BACKSPACE = new InputHandler.backspace();
    public static final ActionListener BACKSPACE_WORD = new InputHandler.backspace_word();
    public static final ActionListener DELETE = new InputHandler.delete();
    public static final ActionListener DELETE_WORD = new InputHandler.delete_word();
    public static final ActionListener END = new InputHandler.end(false);
    public static final ActionListener DOCUMENT_END = new InputHandler.document_end(false);
    public static final ActionListener SELECT_END = new InputHandler.end(true);
    public static final ActionListener SELECT_DOC_END = new InputHandler.document_end(true);
    public static final ActionListener INSERT_BREAK = new InputHandler.insert_break();
    public static final ActionListener INSERT_TAB = new InputHandler.insert_tab();
    public static final ActionListener HOME = new InputHandler.home(false);
    public static final ActionListener DOCUMENT_HOME = new InputHandler.document_home(false);
    public static final ActionListener SELECT_HOME = new InputHandler.home(true);
    public static final ActionListener SELECT_DOC_HOME = new InputHandler.document_home(true);
    public static final ActionListener NEXT_CHAR = new InputHandler.next_char(false);
    public static final ActionListener NEXT_LINE = new InputHandler.next_line(false);
    public static final ActionListener NEXT_PAGE = new InputHandler.next_page(false);
    public static final ActionListener NEXT_WORD = new InputHandler.next_word(false);
    public static final ActionListener SELECT_NEXT_CHAR = new InputHandler.next_char(true);
    public static final ActionListener SELECT_NEXT_LINE = new InputHandler.next_line(true);
    public static final ActionListener SELECT_NEXT_PAGE = new InputHandler.next_page(true);
    public static final ActionListener SELECT_NEXT_WORD = new InputHandler.next_word(true);
    public static final ActionListener OVERWRITE = new InputHandler.overwrite();
    public static final ActionListener PREV_CHAR = new InputHandler.prev_char(false);
    public static final ActionListener PREV_LINE = new InputHandler.prev_line(false);
    public static final ActionListener PREV_PAGE = new InputHandler.prev_page(false);
    public static final ActionListener PREV_WORD = new InputHandler.prev_word(false);
    public static final ActionListener SELECT_PREV_CHAR = new InputHandler.prev_char(true);
    public static final ActionListener SELECT_PREV_LINE = new InputHandler.prev_line(true);
    public static final ActionListener SELECT_PREV_PAGE = new InputHandler.prev_page(true);
    public static final ActionListener SELECT_PREV_WORD = new InputHandler.prev_word(true);
    public static final ActionListener REPEAT = new InputHandler.repeat();
    public static final ActionListener TOGGLE_RECT = new InputHandler.toggle_rect();
    public static final ActionListener INSERT_CHAR = new InputHandler.insert_char();
    private static Hashtable<String, ActionListener> actions = new Hashtable();
    protected ActionListener grabAction;
    protected boolean repeat;
    protected int repeatCount;
    protected InputHandler.MacroRecorder recorder;

    public InputHandler() {
    }

    public static ActionListener getAction(String name) {
        return (ActionListener)actions.get(name);
    }

    public static String getActionName(ActionListener listener) {
        Enumeration enuem = getActions();

        String name;
        ActionListener _listener;
        do {
            if (!enuem.hasMoreElements()) {
                return null;
            }

            name = (String)enuem.nextElement();
            _listener = getAction(name);
        } while(_listener != listener);

        return name;
    }

    public static Enumeration<String> getActions() {
        return actions.keys();
    }

    public abstract void addDefaultKeyBindings();

    public abstract void addKeyBinding(String var1, ActionListener var2);

    public abstract void removeKeyBinding(String var1);

    public abstract void removeAllKeyBindings();

    public void grabNextKeyStroke(ActionListener listener) {
        this.grabAction = listener;
    }

    public boolean isRepeatEnabled() {
        return this.repeat;
    }

    public void setRepeatEnabled(boolean repeat) {
        this.repeat = repeat;
    }

    public int getRepeatCount() {
        return this.repeat ? Math.max(1, this.repeatCount) : 1;
    }

    public void setRepeatCount(int repeatCount) {
        this.repeatCount = repeatCount;
    }

    public InputHandler.MacroRecorder getMacroRecorder() {
        return this.recorder;
    }

    public void setMacroRecorder(InputHandler.MacroRecorder recorder) {
        this.recorder = recorder;
    }

    public abstract InputHandler copy();

    public void executeAction(ActionListener listener, Object source, String actionCommand) {
        ActionEvent evt = new ActionEvent(source, 1001, actionCommand);
        if (listener instanceof InputHandler.Wrapper) {
            listener.actionPerformed(evt);
        } else {
            boolean _repeat = this.repeat;
            int _repeatCount = this.getRepeatCount();
            if (listener instanceof InputHandler.NonRepeatable) {
                listener.actionPerformed(evt);
            } else {
                for(int i = 0; i < Math.max(1, this.repeatCount); ++i) {
                    listener.actionPerformed(evt);
                }
            }

            if (this.grabAction == null) {
                if (this.recorder != null && !(listener instanceof InputHandler.NonRecordable)) {
                    if (_repeatCount != 1) {
                        this.recorder.actionPerformed(REPEAT, String.valueOf(_repeatCount));
                    }

                    this.recorder.actionPerformed(listener, actionCommand);
                }

                if (_repeat) {
                    this.repeat = false;
                    this.repeatCount = 0;
                }
            }

        }
    }

    public static JEditTextArea getTextArea(EventObject evt) {
        if (evt != null) {
            Object o = evt.getSource();
            if (o instanceof Component) {
                Object c = (Component)o;

                while(true) {
                    if (c instanceof JEditTextArea) {
                        return (JEditTextArea)c;
                    }

                    if (c == null) {
                        break;
                    }

                    if (c instanceof JPopupMenu) {
                        c = ((JPopupMenu)c).getInvoker();
                    } else {
                        c = ((Component)c).getParent();
                    }
                }
            }
        }

        System.err.println("BUG: getTextArea() returning null");
        System.err.println("Report this to Slava Pestov <sp@gjt.org>");
        return null;
    }

    protected void handleGrabAction(KeyEvent evt) {
        ActionListener _grabAction = this.grabAction;
        this.grabAction = null;
        this.executeAction(_grabAction, evt.getSource(), String.valueOf(evt.getKeyChar()));
    }

    static {
        actions.put("copy", COPY);
        actions.put("cut", CUT);
        actions.put("paste", PASTE);
        actions.put("undo", UNDO);
        actions.put("redo", REDO);
        actions.put("selectall", SELECTALL);
        actions.put("backspace", BACKSPACE);
        actions.put("backspace-word", BACKSPACE_WORD);
        actions.put("delete", DELETE);
        actions.put("delete-word", DELETE_WORD);
        actions.put("end", END);
        actions.put("select-end", SELECT_END);
        actions.put("document-end", DOCUMENT_END);
        actions.put("select-doc-end", SELECT_DOC_END);
        actions.put("insert-break", INSERT_BREAK);
        actions.put("insert-tab", INSERT_TAB);
        actions.put("home", HOME);
        actions.put("select-home", SELECT_HOME);
        actions.put("document-home", DOCUMENT_HOME);
        actions.put("select-doc-home", SELECT_DOC_HOME);
        actions.put("next-char", NEXT_CHAR);
        actions.put("next-line", NEXT_LINE);
        actions.put("next-page", NEXT_PAGE);
        actions.put("next-word", NEXT_WORD);
        actions.put("select-next-char", SELECT_NEXT_CHAR);
        actions.put("select-next-line", SELECT_NEXT_LINE);
        actions.put("select-next-page", SELECT_NEXT_PAGE);
        actions.put("select-next-word", SELECT_NEXT_WORD);
        actions.put("overwrite", OVERWRITE);
        actions.put("prev-char", PREV_CHAR);
        actions.put("prev-line", PREV_LINE);
        actions.put("prev-page", PREV_PAGE);
        actions.put("prev-word", PREV_WORD);
        actions.put("select-prev-char", SELECT_PREV_CHAR);
        actions.put("select-prev-line", SELECT_PREV_LINE);
        actions.put("select-prev-page", SELECT_PREV_PAGE);
        actions.put("select-prev-word", SELECT_PREV_WORD);
        actions.put("repeat", REPEAT);
        actions.put("toggle-rect", TOGGLE_RECT);
        actions.put("insert-char", INSERT_CHAR);
    }

    public interface MacroRecorder {
        void actionPerformed(ActionListener var1, String var2);
    }

    public interface Wrapper {
    }

    public interface NonRepeatable {
    }

    public interface NonRecordable {
    }

    public static class copy implements ActionListener {
        public copy() {
        }

        public void actionPerformed(ActionEvent e) {
            JEditTextArea textArea = InputHandler.getTextArea(e);
            textArea.copy();
        }
    }

    public static class cut implements ActionListener {
        public cut() {
        }

        public void actionPerformed(ActionEvent e) {
            JEditTextArea textArea = InputHandler.getTextArea(e);
            textArea.cut();
        }
    }

    public static class paste implements ActionListener {
        public paste() {
        }

        public void actionPerformed(ActionEvent e) {
            JEditTextArea textArea = InputHandler.getTextArea(e);
            textArea.paste();
        }
    }

    public static class undo implements ActionListener {
        public undo() {
        }

        public void actionPerformed(ActionEvent e) {
            JEditTextArea textArea = InputHandler.getTextArea(e);
            if (textArea.getUndoManager().canUndo()) {
                textArea.getUndoManager().undo();
            }

        }
    }

    public static class redo implements ActionListener {
        public redo() {
        }

        public void actionPerformed(ActionEvent e) {
            JEditTextArea textArea = InputHandler.getTextArea(e);
            System.out.println("REDO");
            if (textArea.getUndoManager().canRedo()) {
                textArea.getUndoManager().redo();
            }

        }
    }

    public static class selectall implements ActionListener {
        public selectall() {
        }

        public void actionPerformed(ActionEvent e) {
            JEditTextArea textArea = InputHandler.getTextArea(e);
            textArea.selectAll();
        }
    }

    public static class backspace implements ActionListener {
        public backspace() {
        }

        public void actionPerformed(ActionEvent evt) {
            JEditTextArea textArea = InputHandler.getTextArea(evt);
            if (!textArea.isEditable()) {
                textArea.getToolkit().beep();
            } else {
                if (textArea.getSelectionStart() != textArea.getSelectionEnd()) {
                    textArea.setSelectedText("");
                } else {
                    int caret = textArea.getCaretPosition();
                    if (caret == 0) {
                        textArea.getToolkit().beep();
                        return;
                    }

                    try {
                        String aSupprimer = textArea.getDocument().getText(caret - 1, 1);
                        int ind = "\"'([".indexOf(aSupprimer);
                        if (ind != -1) {
                            String carEquiv = "\"')]".substring(ind, ind + 1);
                            if (caret != textArea.getDocumentLength() && textArea.getDocument().getText(caret, 1).equals(carEquiv)) {
                                textArea.getDocument().remove(caret - 1, 2);
                            } else {
                                textArea.getDocument().remove(caret - 1, 1);
                            }
                        } else {
                            textArea.getDocument().remove(caret - 1, 1);
                        }
                    } catch (BadLocationException var7) {
                        var7.printStackTrace();
                    }
                }

            }
        }
    }

    public static class backspace_word implements ActionListener {
        public backspace_word() {
        }

        public void actionPerformed(ActionEvent evt) {
            JEditTextArea textArea = InputHandler.getTextArea(evt);
            int start = textArea.getSelectionStart();
            if (start != textArea.getSelectionEnd()) {
                textArea.setSelectedText("");
            }

            int line = textArea.getCaretLine();
            int lineStart = textArea.getLineStartOffset(line);
            int caret = start - lineStart;
            String lineText = textArea.getLineText(textArea.getCaretLine());
            if (caret == 0) {
                if (lineStart == 0) {
                    textArea.getToolkit().beep();
                    return;
                }

                --caret;
            } else {
                String noWordSep = (String)textArea.getDocument().getProperty("noWordSep");
                caret = TextUtilities.findWordStart(lineText, caret, noWordSep);
            }

            try {
                textArea.getDocument().remove(caret + lineStart, start - (caret + lineStart));
            } catch (BadLocationException var9) {
                var9.printStackTrace();
            }

        }
    }

    public static class delete implements ActionListener {
        public delete() {
        }

        public void actionPerformed(ActionEvent evt) {
            JEditTextArea textArea = InputHandler.getTextArea(evt);
            if (!textArea.isEditable()) {
                textArea.getToolkit().beep();
            } else {
                if (textArea.getSelectionStart() != textArea.getSelectionEnd()) {
                    textArea.setSelectedText("");
                } else {
                    int caret = textArea.getCaretPosition();
                    if (caret == textArea.getDocumentLength()) {
                        textArea.getToolkit().beep();
                        return;
                    }

                    try {
                        String aSupprimer = textArea.getDocument().getText(caret, 1);
                        int ind = "\"'([".indexOf(aSupprimer);
                        if (ind != -1) {
                            String carEquiv = "\"')]".substring(ind, ind + 1);
                            if (caret + 1 != textArea.getDocumentLength() && textArea.getDocument().getText(caret + 1, 1).equals(carEquiv)) {
                                textArea.getDocument().remove(caret, 2);
                            } else {
                                textArea.getDocument().remove(caret, 1);
                            }
                        } else {
                            textArea.getDocument().remove(caret, 1);
                        }
                    } catch (BadLocationException var7) {
                        var7.printStackTrace();
                    }
                }

            }
        }
    }

    public static class delete_word implements ActionListener {
        public delete_word() {
        }

        public void actionPerformed(ActionEvent evt) {
            JEditTextArea textArea = InputHandler.getTextArea(evt);
            int start = textArea.getSelectionStart();
            if (start != textArea.getSelectionEnd()) {
                textArea.setSelectedText("");
            }

            int line = textArea.getCaretLine();
            int lineStart = textArea.getLineStartOffset(line);
            int caret = start - lineStart;
            String lineText = textArea.getLineText(textArea.getCaretLine());
            if (caret == lineText.length()) {
                if (lineStart + caret == textArea.getDocumentLength()) {
                    textArea.getToolkit().beep();
                    return;
                }

                ++caret;
            } else {
                String noWordSep = (String)textArea.getDocument().getProperty("noWordSep");
                caret = TextUtilities.findWordEnd(lineText, caret, noWordSep);
            }

            try {
                textArea.getDocument().remove(start, caret + lineStart - start);
            } catch (BadLocationException var9) {
                var9.printStackTrace();
            }

        }
    }

    public static class end implements ActionListener {
        private boolean select;

        public end(boolean select) {
            this.select = select;
        }

        public void actionPerformed(ActionEvent evt) {
            JEditTextArea textArea = InputHandler.getTextArea(evt);
            int caret = textArea.getCaretPosition();
            int lastOfLine = textArea.getLineEndOffset(textArea.getCaretLine()) - 1;
            int lastVisibleLine = textArea.getFirstLine() + textArea.getVisibleLines();
            if (lastVisibleLine >= textArea.getLineCount()) {
                lastVisibleLine = Math.min(textArea.getLineCount() - 1, lastVisibleLine);
            } else {
                lastVisibleLine -= textArea.getElectricScroll() + 1;
            }

            int lastVisible = textArea.getLineEndOffset(lastVisibleLine) - 1;
            int lastDocument = textArea.getDocumentLength();
            if (caret == lastDocument) {
                textArea.getToolkit().beep();
            } else {
                if (!Boolean.TRUE.equals(textArea.getClientProperty("InputHandler.homeEnd"))) {
                    caret = lastOfLine;
                } else if (caret == lastVisible) {
                    caret = lastDocument;
                } else if (caret == lastOfLine) {
                    caret = lastVisible;
                } else {
                    caret = lastOfLine;
                }

                if (this.select) {
                    textArea.select(textArea.getMarkPosition(), caret);
                } else {
                    textArea.setCaretPosition(caret);
                }

            }
        }
    }

    public static class document_end implements ActionListener {
        private boolean select;

        public document_end(boolean select) {
            this.select = select;
        }

        public void actionPerformed(ActionEvent evt) {
            JEditTextArea textArea = InputHandler.getTextArea(evt);
            if (this.select) {
                textArea.select(textArea.getMarkPosition(), textArea.getDocumentLength());
            } else {
                textArea.setCaretPosition(textArea.getDocumentLength());
            }

        }
    }

    public static class insert_break implements ActionListener {
        public insert_break() {
        }

        public void actionPerformed(ActionEvent evt) {
            JEditTextArea textArea = InputHandler.getTextArea(evt);
            if (!textArea.isEditable()) {
                textArea.getToolkit().beep();
            } else {
                textArea.ajouterLigne();
            }
        }
    }

    public static class insert_tab implements ActionListener {
        public insert_tab() {
        }

        public void actionPerformed(ActionEvent evt) {
            JEditTextArea textArea = InputHandler.getTextArea(evt);
            if (!textArea.isEditable()) {
                textArea.getToolkit().beep();
            } else {
                int debut = textArea.getSelectionStart();
                int fin = textArea.getSelectionEnd();
                if (debut == fin) {
                    textArea.overwriteSetSelectedText("\t");
                } else {
                    try {
                        while(debut != 0 && !textArea.getDocument().getText(debut, 1).equals("\n")) {
                            --debut;
                        }

                        textArea.setSelectionStart(debut);
                        textArea.setSelectionEnd(fin);
                        textArea.setSelectedText("\t" + textArea.getSelectedText().replaceAll("\n", "\n\t"));
                    } catch (BadLocationException var6) {
                        var6.printStackTrace();
                    }
                }

            }
        }
    }

    public static class home implements ActionListener {
        private boolean select;

        public home(boolean select) {
            this.select = select;
        }

        public void actionPerformed(ActionEvent evt) {
            JEditTextArea textArea = InputHandler.getTextArea(evt);
            int caret = textArea.getCaretPosition();
            int firstLine = textArea.getFirstLine();
            int firstOfLine = textArea.getLineStartOffset(textArea.getCaretLine());
            int firstVisibleLine = firstLine == 0 ? 0 : firstLine + textArea.getElectricScroll();
            int firstVisible = textArea.getLineStartOffset(firstVisibleLine);
            if (caret == 0) {
                textArea.getToolkit().beep();
            } else {
                if (!Boolean.TRUE.equals(textArea.getClientProperty("InputHandler.homeEnd"))) {
                    caret = firstOfLine;
                } else if (caret == firstVisible) {
                    caret = 0;
                } else if (caret == firstOfLine) {
                    caret = firstVisible;
                } else {
                    caret = firstOfLine;
                }

                if (this.select) {
                    textArea.select(textArea.getMarkPosition(), caret);
                } else {
                    textArea.setCaretPosition(caret);
                }

            }
        }
    }

    public static class document_home implements ActionListener {
        private boolean select;

        public document_home(boolean select) {
            this.select = select;
        }

        public void actionPerformed(ActionEvent evt) {
            JEditTextArea textArea = InputHandler.getTextArea(evt);
            if (this.select) {
                textArea.select(textArea.getMarkPosition(), 0);
            } else {
                textArea.setCaretPosition(0);
            }

        }
    }

    public static class next_char implements ActionListener {
        private boolean select;

        public next_char(boolean select) {
            this.select = select;
        }

        public void actionPerformed(ActionEvent evt) {
            JEditTextArea textArea = InputHandler.getTextArea(evt);
            int caret = textArea.getCaretPosition();
            if (caret == textArea.getDocumentLength()) {
                textArea.getToolkit().beep();
            } else {
                if (this.select) {
                    textArea.select(textArea.getMarkPosition(), caret + 1);
                } else {
                    textArea.setCaretPosition(caret + 1);
                }

            }
        }
    }

    public static class next_line implements ActionListener {
        private boolean select;

        public next_line(boolean select) {
            this.select = select;
        }

        public void actionPerformed(ActionEvent evt) {
            JEditTextArea textArea = InputHandler.getTextArea(evt);
            int caret = textArea.getCaretPosition();
            int line = textArea.getCaretLine();
            if (line == textArea.getLineCount() - 1) {
                textArea.getToolkit().beep();
            } else {
                int magic = textArea.getMagicCaretPosition();
                if (magic == -1) {
                    magic = textArea.offsetToX(line, caret - textArea.getLineStartOffset(line));
                }

                caret = textArea.getLineStartOffset(line + 1) + textArea.xToOffset(line + 1, magic);
                if (this.select) {
                    textArea.select(textArea.getMarkPosition(), caret);
                } else {
                    textArea.setCaretPosition(caret);
                }

                textArea.setMagicCaretPosition(magic);
            }
        }
    }

    public static class next_page implements ActionListener {
        private boolean select;

        public next_page(boolean select) {
            this.select = select;
        }

        public void actionPerformed(ActionEvent evt) {
            JEditTextArea textArea = InputHandler.getTextArea(evt);
            int lineCount = textArea.getLineCount();
            int firstLine = textArea.getFirstLine();
            int visibleLines = textArea.getVisibleLines();
            int line = textArea.getCaretLine();
            firstLine += visibleLines;
            if (firstLine + visibleLines >= lineCount - 1) {
                firstLine = lineCount - visibleLines;
            }

            textArea.setFirstLine(firstLine);
            int caret = textArea.getLineStartOffset(Math.min(textArea.getLineCount() - 1, line + visibleLines));
            if (this.select) {
                textArea.select(textArea.getMarkPosition(), caret);
            } else {
                textArea.setCaretPosition(caret);
            }

        }
    }

    public static class next_word implements ActionListener {
        private boolean select;

        public next_word(boolean select) {
            this.select = select;
        }

        public void actionPerformed(ActionEvent evt) {
            JEditTextArea textArea = InputHandler.getTextArea(evt);
            int caret = textArea.getCaretPosition();
            int line = textArea.getCaretLine();
            int lineStart = textArea.getLineStartOffset(line);
            caret -= lineStart;
            String lineText = textArea.getLineText(textArea.getCaretLine());
            if (caret == lineText.length()) {
                if (lineStart + caret == textArea.getDocumentLength()) {
                    textArea.getToolkit().beep();
                    return;
                }

                ++caret;
            } else {
                String noWordSep = (String)textArea.getDocument().getProperty("noWordSep");
                caret = TextUtilities.findWordEnd(lineText, caret, noWordSep);
            }

            if (this.select) {
                textArea.select(textArea.getMarkPosition(), lineStart + caret);
            } else {
                textArea.setCaretPosition(lineStart + caret);
            }

        }
    }

    public static class overwrite implements ActionListener {
        public overwrite() {
        }

        public void actionPerformed(ActionEvent evt) {
            JEditTextArea textArea = InputHandler.getTextArea(evt);
            textArea.setOverwriteEnabled(!textArea.isOverwriteEnabled());
        }
    }

    public static class prev_char implements ActionListener {
        private boolean select;

        public prev_char(boolean select) {
            this.select = select;
        }

        public void actionPerformed(ActionEvent evt) {
            JEditTextArea textArea = InputHandler.getTextArea(evt);
            int caret = textArea.getCaretPosition();
            if (caret == 0) {
                textArea.getToolkit().beep();
            } else {
                if (this.select) {
                    textArea.select(textArea.getMarkPosition(), caret - 1);
                } else {
                    textArea.setCaretPosition(caret - 1);
                }

            }
        }
    }

    public static class prev_line implements ActionListener {
        private boolean select;

        public prev_line(boolean select) {
            this.select = select;
        }

        public void actionPerformed(ActionEvent evt) {
            JEditTextArea textArea = InputHandler.getTextArea(evt);
            int caret = textArea.getCaretPosition();
            int line = textArea.getCaretLine();
            if (line == 0) {
                textArea.getToolkit().beep();
            } else {
                int magic = textArea.getMagicCaretPosition();
                if (magic == -1) {
                    magic = textArea.offsetToX(line, caret - textArea.getLineStartOffset(line));
                }

                caret = textArea.getLineStartOffset(line - 1) + textArea.xToOffset(line - 1, magic);
                if (this.select) {
                    textArea.select(textArea.getMarkPosition(), caret);
                } else {
                    textArea.setCaretPosition(caret);
                }

                textArea.setMagicCaretPosition(magic);
            }
        }
    }

    public static class prev_page implements ActionListener {
        private boolean select;

        public prev_page(boolean select) {
            this.select = select;
        }

        public void actionPerformed(ActionEvent evt) {
            JEditTextArea textArea = InputHandler.getTextArea(evt);
            int firstLine = textArea.getFirstLine();
            int visibleLines = textArea.getVisibleLines();
            int line = textArea.getCaretLine();
            if (firstLine < visibleLines) {
                firstLine = visibleLines;
            }

            textArea.setFirstLine(firstLine - visibleLines);
            int caret = textArea.getLineStartOffset(Math.max(0, line - visibleLines));
            if (this.select) {
                textArea.select(textArea.getMarkPosition(), caret);
            } else {
                textArea.setCaretPosition(caret);
            }

        }
    }

    public static class prev_word implements ActionListener {
        private boolean select;

        public prev_word(boolean select) {
            this.select = select;
        }

        public void actionPerformed(ActionEvent evt) {
            JEditTextArea textArea = InputHandler.getTextArea(evt);
            int caret = textArea.getCaretPosition();
            int line = textArea.getCaretLine();
            int lineStart = textArea.getLineStartOffset(line);
            caret -= lineStart;
            String lineText = textArea.getLineText(textArea.getCaretLine());
            if (caret == 0) {
                if (lineStart == 0) {
                    textArea.getToolkit().beep();
                    return;
                }

                --caret;
            } else {
                String noWordSep = (String)textArea.getDocument().getProperty("noWordSep");
                caret = TextUtilities.findWordStart(lineText, caret, noWordSep);
            }

            if (this.select) {
                textArea.select(textArea.getMarkPosition(), lineStart + caret);
            } else {
                textArea.setCaretPosition(lineStart + caret);
            }

        }
    }

    public static class repeat implements ActionListener, InputHandler.NonRecordable {
        public repeat() {
        }

        public void actionPerformed(ActionEvent evt) {
            JEditTextArea textArea = InputHandler.getTextArea(evt);
            textArea.getInputHandler().setRepeatEnabled(true);
            String actionCommand = evt.getActionCommand();
            if (actionCommand != null) {
                textArea.getInputHandler().setRepeatCount(Integer.parseInt(actionCommand));
            }

        }
    }

    public static class toggle_rect implements ActionListener {
        public toggle_rect() {
        }

        public void actionPerformed(ActionEvent evt) {
            JEditTextArea textArea = InputHandler.getTextArea(evt);
            textArea.setSelectionRectangular(!textArea.isSelectionRectangular());
        }
    }

    public static class insert_char implements ActionListener, InputHandler.NonRepeatable {
        public insert_char() {
        }

        public void actionPerformed(ActionEvent evt) {
            JEditTextArea textArea = InputHandler.getTextArea(evt);
            String str = evt.getActionCommand();
            int repeatCount = textArea.getInputHandler().getRepeatCount();
            if (textArea.isEditable()) {
                String selection = textArea.getSelectedText();
                StringBuffer buf = new StringBuffer();

                for(int i = 0; i < repeatCount; ++i) {
                    buf.append(str);
                }

                textArea.overwriteSetSelectedText(buf.toString());
                char touche = str.charAt(0);
                int positionCourante = textArea.getCaretPosition();

                try {
                    String caractereSuivant = textArea.getDocument().getText(positionCourante, 1);
                    int index1 = "\"'".indexOf(touche);
                    if (index1 != -1 && touche != caractereSuivant.charAt(0)) {
                        textArea.getDocument().insertString(positionCourante, "\"'".substring(index1, index1 + 1), (AttributeSet)null);
                        textArea.setCaretPosition(positionCourante);
                    } else {
                        int index2 = "([".indexOf(touche);
                        if (index2 != -1) {
                            if (selection != null) {
                                selection = "([".substring(index2, index2 + 1) + selection + ")]".substring(index2, index2 + 1);
                                textArea.getDocument().remove(positionCourante - 1, 1);
                                textArea.setSelectedText(selection);
                            } else {
                                textArea.getDocument().insertString(positionCourante, ")]".substring(index2, index2 + 1), (AttributeSet)null);
                            }

                            textArea.setCaretPosition(positionCourante);
                        } else if (")]}\"'".indexOf(touche) != -1 && touche == caractereSuivant.charAt(0)) {
                            textArea.getDocument().remove(positionCourante, 1);
                        }
                    }
                } catch (BadLocationException var12) {
                    var12.printStackTrace();
                }
            } else {
                textArea.getToolkit().beep();
            }

        }
    }
}
