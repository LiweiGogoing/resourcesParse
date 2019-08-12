package com.sec.resourceparse;


import java.util.ArrayList;

public class ResStringPoolStyle {

    public static final int END = 0xFFFFFFFF;

    public ArrayList<ResStringPoolSpan> spans;

    public ResStringPoolStyle () {
        spans = new ArrayList<>();
    }

    public void parseResStringPoolStyle(ByteBuffer byteBuffer) {
        int index = byteBuffer.getInt();
        while (index != END) {
            ResStringPoolSpan resStringPoolSpan = new ResStringPoolSpan();
            resStringPoolSpan.name.index = index;
            resStringPoolSpan.firstChar = byteBuffer.getInt();
            resStringPoolSpan.lastChar = byteBuffer.getInt();
            spans.add(resStringPoolSpan);
            index = byteBuffer.getInt();
        }
    }

    @Override
    public String toString() {
        return "ResStringPoolStyle{" +
                "spans=" + spans +
                '}';
    }
}
