//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.etudiant.vue;

import java.io.IOException;

public class BrowserControl {
    private static final String WIN_ID = "Windows";
    private static final String MAC_ID = "Mac OS X";
    private static final String WIN_PATH = "rundll32";
    private static final String WIN_FLAG = "url.dll,FileProtocolHandler";
    private static final String UNIX_PATH = "firefox";
    private static final String MAC_PATH = "open";

    public BrowserControl() {
    }

    public static void displayURL(String url) {
        boolean windows = isWindowsPlatform();
        String cmd = null;

        try {
            Process var3;
            if (windows) {
                cmd = "rundll32 url.dll,FileProtocolHandler " + url;
                var3 = Runtime.getRuntime().exec(cmd);
            } else {
                if (isMacPlatform()) {
                    cmd = "open " + url;
                } else {
                    cmd = "firefox " + url;
                }

                var3 = Runtime.getRuntime().exec(cmd);
            }
        } catch (IOException var4) {
            System.err.println("Could not invoke browser, command=" + cmd);
            System.err.println("Caught: " + var4);
        }

    }

    public static boolean isWindowsPlatform() {
        String os = System.getProperty("os.name");
        return os != null && os.startsWith("Windows");
    }

    public static boolean isMacPlatform() {
        String os = System.getProperty("os.name");
        return os != null && os.startsWith("Mac OS X");
    }

    public static void main(String[] args) {
        displayURL("http://www.javaworld.com");
    }
}
