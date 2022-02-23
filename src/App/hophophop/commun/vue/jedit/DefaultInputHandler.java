//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.commun.vue.jedit;

import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Hashtable;
import java.util.StringTokenizer;
import javax.swing.KeyStroke;

public class DefaultInputHandler extends InputHandler {
    private Hashtable bindings;
    private Hashtable currentBindings;

    public DefaultInputHandler() {
        this.bindings = this.currentBindings = new Hashtable();
    }

    public void addDefaultKeyBindings() {
        this.addKeyBinding("C+c", COPY);
        this.addKeyBinding("M+c", COPY);
        this.addKeyBinding("C+x", CUT);
        this.addKeyBinding("M+x", CUT);
        this.addKeyBinding("C+v", PASTE);
        this.addKeyBinding("M+v", PASTE);
        this.addKeyBinding("M+z", UNDO);
        this.addKeyBinding("C+z", UNDO);
        this.addKeyBinding("M+y", REDO);
        this.addKeyBinding("C+y", REDO);
        this.addKeyBinding("M+a", SELECTALL);
        this.addKeyBinding("C+a", SELECTALL);
        this.addKeyBinding("BACK_SPACE", BACKSPACE);
        this.addKeyBinding("C+BACK_SPACE", BACKSPACE_WORD);
        this.addKeyBinding("DELETE", DELETE);
        this.addKeyBinding("C+DELETE", DELETE_WORD);
        this.addKeyBinding("ENTER", INSERT_BREAK);
        this.addKeyBinding("TAB", INSERT_TAB);
        this.addKeyBinding("INSERT", OVERWRITE);
        this.addKeyBinding("C+\\", TOGGLE_RECT);
        this.addKeyBinding("HOME", HOME);
        this.addKeyBinding("END", END);
        this.addKeyBinding("S+HOME", SELECT_HOME);
        this.addKeyBinding("S+END", SELECT_END);
        this.addKeyBinding("C+HOME", DOCUMENT_HOME);
        this.addKeyBinding("C+END", DOCUMENT_END);
        this.addKeyBinding("CS+HOME", SELECT_DOC_HOME);
        this.addKeyBinding("CS+END", SELECT_DOC_END);
        this.addKeyBinding("PAGE_UP", PREV_PAGE);
        this.addKeyBinding("PAGE_DOWN", NEXT_PAGE);
        this.addKeyBinding("S+PAGE_UP", SELECT_PREV_PAGE);
        this.addKeyBinding("S+PAGE_DOWN", SELECT_NEXT_PAGE);
        this.addKeyBinding("LEFT", PREV_CHAR);
        this.addKeyBinding("S+LEFT", SELECT_PREV_CHAR);
        this.addKeyBinding("C+LEFT", PREV_WORD);
        this.addKeyBinding("CS+LEFT", SELECT_PREV_WORD);
        this.addKeyBinding("RIGHT", NEXT_CHAR);
        this.addKeyBinding("S+RIGHT", SELECT_NEXT_CHAR);
        this.addKeyBinding("C+RIGHT", NEXT_WORD);
        this.addKeyBinding("CS+RIGHT", SELECT_NEXT_WORD);
        this.addKeyBinding("UP", PREV_LINE);
        this.addKeyBinding("S+UP", SELECT_PREV_LINE);
        this.addKeyBinding("DOWN", NEXT_LINE);
        this.addKeyBinding("S+DOWN", SELECT_NEXT_LINE);
        this.addKeyBinding("C+ENTER", REPEAT);
    }

    public void addKeyBinding(String keyBinding, ActionListener action) {
        Hashtable current = this.bindings;
        StringTokenizer st = new StringTokenizer(keyBinding);

        while(st.hasMoreTokens()) {
            KeyStroke keyStroke = parseKeyStroke(st.nextToken());
            if (keyStroke == null) {
                return;
            }

            if (st.hasMoreTokens()) {
                Object o = current.get(keyStroke);
                if (o instanceof Hashtable) {
                    current = (Hashtable)o;
                } else {
                    o = new Hashtable();
                    current.put(keyStroke, o);
                    current = (Hashtable)o;
                }
            } else {
                current.put(keyStroke, action);
            }
        }

    }

    public void removeKeyBinding(String keyBinding) {
        throw new InternalError("Not yet implemented");
    }

    public void removeAllKeyBindings() {
        this.bindings.clear();
    }

    public InputHandler copy() {
        return new DefaultInputHandler(this);
    }

    public void keyPressed(KeyEvent evt) {
        int keyCode = evt.getKeyCode();
        int modifiers = evt.getModifiers();
        if (keyCode != 17 && keyCode != 16 && keyCode != 18 && keyCode != 157) {
            if ((modifiers & -2) != 0 || evt.isActionKey() || keyCode == 8 || keyCode == 127 || keyCode == 10 || keyCode == 9 || keyCode == 27) {
                if (this.grabAction != null) {
                    this.handleGrabAction(evt);
                    return;
                }

                KeyStroke keyStroke = KeyStroke.getKeyStroke(keyCode, modifiers);
                Object o = this.currentBindings.get(keyStroke);
                if (o == null) {
                    if (this.currentBindings != this.bindings) {
                        Toolkit.getDefaultToolkit().beep();
                        this.repeatCount = 0;
                        this.repeat = false;
                        evt.consume();
                    }

                    this.currentBindings = this.bindings;
                    return;
                }

                if (o instanceof ActionListener) {
                    this.currentBindings = this.bindings;
                    this.executeAction((ActionListener)o, evt.getSource(), (String)null);
                    evt.consume();
                    return;
                }

                if (o instanceof Hashtable) {
                    this.currentBindings = (Hashtable)o;
                    evt.consume();
                    return;
                }
            }

        }
    }

    public void keyTyped(KeyEvent evt) {
        int modifiers = evt.getModifiers();
        char c = evt.getKeyChar();
        if (c != '\uffff' && (modifiers & 4) == 0 && c >= ' ' && c != 127) {
            KeyStroke keyStroke = KeyStroke.getKeyStroke(Character.toUpperCase(c));
            Object o = this.currentBindings.get(keyStroke);
            if (o instanceof Hashtable) {
                this.currentBindings = (Hashtable)o;
                return;
            }

            if (o instanceof ActionListener) {
                this.currentBindings = this.bindings;
                this.executeAction((ActionListener)o, evt.getSource(), String.valueOf(c));
                return;
            }

            this.currentBindings = this.bindings;
            if (this.grabAction != null) {
                this.handleGrabAction(evt);
                return;
            }

            if (this.repeat && Character.isDigit(c)) {
                this.repeatCount *= 10;
                this.repeatCount += c - 48;
                return;
            }

            this.executeAction(INSERT_CHAR, evt.getSource(), String.valueOf(evt.getKeyChar()));
            this.repeatCount = 0;
            this.repeat = false;
        }

    }

    public static KeyStroke parseKeyStroke(String keyStroke) {
        if (keyStroke == null) {
            return null;
        } else {
            int modifiers = 0;
            int index = keyStroke.indexOf(43);
            if (index != -1) {
                for(int i = 0; i < index; ++i) {
                    switch(Character.toUpperCase(keyStroke.charAt(i))) {
                        case 'A':
                            modifiers |= 8;
                            break;
                        case 'C':
                            modifiers |= 2;
                            break;
                        case 'M':
                            modifiers |= 4;
                            break;
                        case 'S':
                            modifiers |= 1;
                    }
                }
            }

            String key = keyStroke.substring(index + 1);
            if (key.length() == 1) {
                char ch = Character.toUpperCase(key.charAt(0));
                return modifiers == 0 ? KeyStroke.getKeyStroke(ch) : KeyStroke.getKeyStroke(ch, modifiers);
            } else if (key.length() == 0) {
                System.err.println("Invalid key stroke: " + keyStroke);
                return null;
            } else {
                int ch;
                try {
                    ch = KeyEvent.class.getField("VK_".concat(key)).getInt((Object)null);
                } catch (Exception var6) {
                    System.err.println("Invalid key stroke: " + keyStroke);
                    return null;
                }

                return KeyStroke.getKeyStroke(ch, modifiers);
            }
        }
    }

    private DefaultInputHandler(DefaultInputHandler copy) {
        this.bindings = this.currentBindings = copy.bindings;
    }
}
