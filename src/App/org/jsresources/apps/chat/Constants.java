//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jsresources.apps.chat;

class Constants {
    public static final int DIR_MIC = 0;
    public static final int DIR_SPK = 1;
    public static boolean DEBUG = true;
    public static boolean VERBOSE = true;
    public static final int FORMAT_CODE_CD = 1;
    public static final int FORMAT_CODE_FM = 2;
    public static final int FORMAT_CODE_TELEPHONE = 3;
    public static final int FORMAT_CODE_GSM = 4;
    public static final String[] FORMAT_NAMES = new String[]{"Cell phone GSM (13.2KBit/s - Modem)", "Telephone ulaw (64KBit/s - ISDN)", "FM quality mono (352.8KBit/s - ADSL)", "CD quality mono (705.6KBit/s - LAN)"};
    public static final int[] FORMAT_CODES = new int[]{4, 3, 2, 1};
    public static final int[] BUFFER_SIZE_MILLIS = new int[]{30, 40, 50, 70, 85, 100, 130, 150, 180, 220, 400};
    public static final String[] BUFFER_SIZE_MILLIS_STR = new String[]{"30", "40", "50", "70", "85", "100", "130", "150", "180", "220", "400"};
    public static final int BUFFER_SIZE_INDEX_DEFAULT = 2;
    public static final String CONNECTION_PROPERTY = "CONNECTION";
    public static final String AUDIO_PROPERTY = "AUDIO";
    public static final int PROTOCOL_MAGIC = 1128808788;
    public static final int PROTOCOL_VERSION = 1;
    public static final int PROTOCOL_ACK = 1001;
    public static final int PROTOCOL_ERROR = 1002;
    public static final boolean TCP_NODELAY = false;
    public static final int TCP_RECEIVE_BUFFER_SIZE = 1024;
    public static final int TCP_SEND_BUFFER_SIZE = 1024;
    public static final int CONNECTION_TYPE_TCP = 1;
    public static final int CONNECTION_TYPE_UDP = 2;
    public static final int[] CONNECTION_TYPES = new int[]{1, 2};
    public static final String[] CONNECTION_TYPE_NAMES = new String[]{"TCP (for LAN)", "UDP (for WAN)"};
    public static final int DEFAULT_PORT = 8765;
    public static final int DEFAULT_CONNECTION_TYPE = 1;
    public static final int DEFAULT_FORMAT_CODE = 3;

    Constants() {
    }

    public static void out(String s) {
        System.out.println(s);
    }
}
