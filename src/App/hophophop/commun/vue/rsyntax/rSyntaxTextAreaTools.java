//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.commun.vue.rsyntax;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.io.InputStreamReader;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxScheme;
import org.fife.ui.rsyntaxtextarea.Theme;
import org.fife.ui.rtextarea.RTextScrollPane;

public class rSyntaxTextAreaTools {
    public rSyntaxTextAreaTools() {
    }

    public static void setColorRSyntaxTextArea(RTextScrollPane sp, RSyntaxTextArea textArea) {
        sp.setFoldIndicatorEnabled(true);
        sp.getGutter().setBorderColor(new Color(221, 221, 221));
        sp.getGutter().setLineNumberColor(new Color(120, 120, 120));
        sp.getGutter().setFoldIndicatorForeground(new Color(128, 128, 128));
        sp.getGutter().setFoldBackground(new Color(255, 255, 255));
        sp.getGutter().setActiveLineRangeColor(new Color(51, 153, 255));
        textArea.setCodeFoldingEnabled(true);
        textArea.setAntiAliasingEnabled(true);
        textArea.setFont(new Font("MonoLisa", 0, 14));
        //textArea.setBackground(new Color(33, 33, 33));
        //textArea.setCaretColor(new Color(0, 0, 0));
        //textArea.setSelectionColor(new Color(200, 200, 255));
        //textArea.setCurrentLineHighlightColor(new Color(41, 41, 41));
        //textArea.setFadeCurrentLineHighlight(false);
        //textArea.setMarginLineColor(new Color(176, 180, 185));
        //textArea.setMarkAllHighlightColor(new Color(107, 129, 137));
        //textArea.setMarkOccurrencesColor(new Color(212, 212, 212));
        //textArea.setHyperlinkForeground(new Color(0, 0, 255));
        /*SyntaxScheme scheme = textArea.getSyntaxScheme();
        scheme.getStyle(20).foreground = new Color(0, 0, 0);
        scheme.getStyle(6).foreground = new Color(127, 0, 85);
        scheme.getStyle(6).font = new Font("Courier", 1, 14);
        scheme.getStyle(7).foreground = new Color(63, 95, 191);
        scheme.getStyle(7).font = new Font("Courier", 1, 14);
        scheme.getStyle(19).foreground = new Color(128, 128, 128);
        scheme.getStyle(3).foreground = new Color(63, 95, 191);
        scheme.getStyle(1).foreground = new Color(63, 127, 95);
        scheme.getStyle(2).foreground = new Color(63, 127, 95);
        scheme.getStyle(4).foreground = new Color(127, 159, 191);
        scheme.getStyle(4).font = new Font("Courier", 1, 14);
        scheme.getStyle(5).foreground = new Color(127, 127, 159);
        scheme.getStyle(16).foreground = new Color(127, 0, 85);
        scheme.getStyle(16).font = new Font("Courier", 1, 14);
        scheme.getStyle(8).foreground = new Color(127, 0, 85);
        scheme.getStyle(8).font = new Font("Courier", 0, 14);
        scheme.getStyle(9).foreground = new Color(127, 0, 85);
        scheme.getStyle(9).font = new Font("Courier", 1, 14);
        scheme.getStyle(10).foreground = new Color(0, 0, 0);
        scheme.getStyle(11).foreground = new Color(0, 0, 0);
        scheme.getStyle(12).foreground = new Color(0, 0, 0);
        scheme.getStyle(13).foreground = new Color(41, 0, 255);
        scheme.getStyle(14).foreground = new Color(41, 0, 255);
        scheme.getStyle(15).foreground = new Color(41, 0, 255);
        scheme.getStyle(25).foreground = new Color(128, 128, 128);
        scheme.getStyle(26).foreground = new Color(63, 127, 127);
        scheme.getStyle(27).foreground = new Color(127, 127, 127);
        scheme.getStyle(28).foreground = new Color(41, 0, 255);
        scheme.getStyle(31).foreground = new Color(128, 128, 128);
        scheme.getStyle(33).foreground = new Color(204, 102, 0);
        scheme.getStyle(23).foreground = new Color(0, 0, 0);
        scheme.getStyle(24).foreground = new Color(127, 0, 85);
        scheme.getStyle(18).foreground = new Color(0, 128, 64);
        scheme.getStyle(22).foreground = new Color(0, 0, 0);
        scheme.getStyle(17).foreground = new Color(255, 153, 0);
        scheme.getStyle(17).font = new Font("Courier", 1, 14);
        scheme.getStyle(21).foreground = new Color(0, 0, 0);
        scheme.getStyle(35).foreground = new Color(0, 0, 0);
        scheme.getStyle(35).background = new Color(255, 204, 204);
        scheme.getStyle(36).foreground = new Color(128, 128, 128);
        scheme.getStyle(36).background = new Color(255, 204, 204);
        scheme.getStyle(37).foreground = new Color(0, 128, 64);
        scheme.getStyle(37).background = new Color(255, 204, 204);
        scheme.getStyle(38).foreground = new Color(0, 0, 0);
        scheme.getStyle(38).background = new Color(255, 204, 204);
        */

        try {
            Theme theme = Theme.load(Theme.class.getClassLoader().getResourceAsStream("org/fife/ui/rsyntaxtextarea/themes/dark.xml"));
            theme.apply(textArea);
        } catch (IOException e) {
            e.printStackTrace();
        }


        textArea.setAnimateBracketMatching(true);
        textArea.setMatchedBracketBGColor(new Color(54, 199, 56));

        sp.revalidate();
    }
}
