//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.commun.outils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterOutputStream;

public class CompressionUtil {
    public CompressionUtil() {
    }

    public static String compressB64(String text) throws IOException {
        return new String(Base64.getEncoder().encode(compress(text)));
    }

    public static String decompressB64(String b64Compressed) throws IOException {
        byte[] decompressedBArray = decompress(Base64.getDecoder().decode(b64Compressed));
        return new String(decompressedBArray, StandardCharsets.UTF_8);
    }

    public static byte[] compress(String text) throws IOException {
        return compress(text.getBytes());
    }

    public static byte[] compress(byte[] bArray) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        DeflaterOutputStream dos = new DeflaterOutputStream(os);

        try {
            dos.write(bArray);
        } catch (Throwable var6) {
            try {
                dos.close();
            } catch (Throwable var5) {
                var6.addSuppressed(var5);
            }

            throw var6;
        }

        dos.close();
        return os.toByteArray();
    }

    public static byte[] decompress(byte[] compressedTxt) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        InflaterOutputStream ios = new InflaterOutputStream(os);

        try {
            ios.write(compressedTxt);
        } catch (Throwable var6) {
            try {
                ios.close();
            } catch (Throwable var5) {
                var6.addSuppressed(var5);
            }

            throw var6;
        }

        ios.close();
        return os.toByteArray();
    }
}
