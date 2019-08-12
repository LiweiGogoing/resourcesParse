package com.sec.resourceparse;



public class ByteBuffer {

    private java.nio.ByteBuffer mByteBuffer;

    public ByteBuffer (java.nio.ByteBuffer byteBuffer) {
        this.mByteBuffer = byteBuffer;
    }

    public void position(int position) {
        mByteBuffer.position(position);
    }

    public int position() {
        return mByteBuffer.position();
    }


    public int getInt() {
        byte [] data = new byte[4];
        mByteBuffer.get(data);
        return Utils.byteArrayToInt(data);
    }


    public short getShort() {
        byte[] data = new byte[2];
        mByteBuffer.get(data);
        return Utils.byte2Short(data);
    }

    public void get(byte [] data)  {
        mByteBuffer.get(data);
    }

    public byte get() {
        return mByteBuffer.get();
    }

    public int capacity() {
        return mByteBuffer.capacity();
    }

    public char getChar() {
        return mByteBuffer.getChar();
    }


}
