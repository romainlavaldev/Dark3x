//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jsresources.apps.chat;

import java.io.PrintStream;
import java.security.AccessControlException;
import java.util.StringTokenizer;

public class Debug {
    public static boolean SHOW_ACCESS_CONTROL_EXCEPTIONS = false;
    private static final String PROPERTY_PREFIX = "jsinfo.";
    private static PrintStream sm_printStream;
    private static String indent;
    private static boolean sm_bTraceAllExceptions;
    private static boolean sm_bTraceMixerPanel;
    private static boolean sm_bTraceControlsPanel;
    private static boolean sm_bTraceControlPropertiesPanel;
    private static boolean sm_bTraceLineTableModel;
    private static boolean sm_bTraceServiceProviderTableModel;
    private static boolean sm_bTraceConfigurationFilesTableModel;

    public Debug() {
    }

    public static final boolean getTraceAllExceptions() {
        return sm_bTraceAllExceptions;
    }

    public static final boolean getTraceMixerPanel() {
        return sm_bTraceMixerPanel;
    }

    public static final boolean getTraceControlsPanel() {
        return sm_bTraceControlsPanel;
    }

    public static final boolean getTraceControlPropertiesPanel() {
        return sm_bTraceControlPropertiesPanel;
    }

    public static final boolean getTraceLineTableModel() {
        return sm_bTraceLineTableModel;
    }

    public static final boolean getTraceServiceProviderTableModel() {
        return sm_bTraceServiceProviderTableModel;
    }

    public static final boolean getTraceConfigurationFilesTableModel() {
        return sm_bTraceConfigurationFilesTableModel;
    }

    public static void out(String strMessage) {
        if (strMessage.length() > 0 && strMessage.charAt(0) == '<') {
            if (indent.length() > 2) {
                indent = indent.substring(2);
            } else {
                indent = "";
            }
        }

        String newMsg = null;
        if (indent != "" && strMessage.indexOf("\n") >= 0) {
            newMsg = "";

            for(StringTokenizer tokenizer = new StringTokenizer(strMessage, "\n"); tokenizer.hasMoreTokens(); newMsg = newMsg + indent + tokenizer.nextToken() + "\n") {
            }
        } else {
            newMsg = indent + strMessage;
        }

        sm_printStream.println(newMsg);
        if (strMessage.length() > 0 && strMessage.charAt(0) == '>') {
            indent = indent + "  ";
        }

    }

    public static void out(Throwable throwable) {
        throwable.printStackTrace(sm_printStream);
    }

    private static boolean getBooleanProperty(String strName) {
        String strPropertyName = "jsinfo." + strName;
        String strValue = "false";

        try {
            strValue = System.getProperty(strPropertyName, "false");
        } catch (AccessControlException var4) {
            if (SHOW_ACCESS_CONTROL_EXCEPTIONS) {
                out((Throwable)var4);
            }
        }

        boolean bValue = strValue.toLowerCase().equals("true");
        return bValue;
    }

    static {
        sm_printStream = System.out;
        indent = "";
        sm_bTraceAllExceptions = getBooleanProperty("TraceAllExceptions");
        sm_bTraceMixerPanel = getBooleanProperty("TraceMixerPanel");
        sm_bTraceControlsPanel = getBooleanProperty("TraceControlsPanel");
        sm_bTraceControlPropertiesPanel = getBooleanProperty("TraceControlPropertiesPanel");
        sm_bTraceLineTableModel = getBooleanProperty("TraceLineTableModel");
        sm_bTraceServiceProviderTableModel = getBooleanProperty("TraceServiceProviderTableModel");
        sm_bTraceConfigurationFilesTableModel = getBooleanProperty("TraceConfigurationFilesTableModel");
    }
}
