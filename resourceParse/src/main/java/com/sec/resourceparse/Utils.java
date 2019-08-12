package com.sec.resourceparse;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

public class Utils {

    public static int byteArrayToInt(byte[] bytes) {
       return  (0xff & bytes[0]) | (0xff00 & (bytes[1] << 8)) | (0xff0000 & (bytes[2] << 16)) | (0xff000000 & (bytes[3] << 24));
    }

    public static short byte2Short(byte[] bytes) {
        return (short) ((0xff & bytes[0]) | (0xff00 & (bytes[1] << 8)));
    }


    public static byte[] copyByte(byte[] data, int start, int length) {
        byte [] newData = new byte[length];
        System.arraycopy(data, start, newData, 0, length);
        return newData;
    }

    public static char[] getChars (byte[] bytes) {
        Charset cs = Charset.forName ("UTF-8");
        ByteBuffer bb = ByteBuffer.allocate (bytes.length);
        bb.put (bytes);
        bb.flip ();
        CharBuffer cb = cs.decode (bb);
        return cb.array();
    }

}
