package com.sec.resourceparse;

public class ResTableRef {

    int index;

    public int getSize() {
        return 4;
    }

    @Override
    public String toString() {
        return "ResTableRef{" +
                "index=" + Integer.toHexString(index) +
                '}';
    }
}
