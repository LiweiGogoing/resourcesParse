package com.sec.resourceparse;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;

public class Manager {

    public static void main(String [] args) {
        //resources.arsc file path
        String resPath = "";
        FileInputStream ins = null;
        ByteArrayOutputStream ous = null;
        ByteBuffer byteBuffer = null;
        try {
            ins = new FileInputStream(new File(resPath));
            int length = ins.available();
            byteBuffer = ByteBuffer.allocateDirect(length);
            byte [] data = new byte[length];
            ins.read(data);
            byteBuffer.put(data);
            byteBuffer.position(0);
            com.sec.resourceparse.ByteBuffer nByteBuffer = new com.sec.resourceparse.ByteBuffer(byteBuffer);
            ParseUtils.parseRes(nByteBuffer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
