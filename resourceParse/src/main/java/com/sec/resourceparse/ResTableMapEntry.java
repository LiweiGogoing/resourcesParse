package com.sec.resourceparse;

import java.util.ArrayList;

/**
 * struct ResTable_map_entry : public ResTable_entry
 * {
 *     // Resource identifier of the parent mapping, or 0 if there is none.
 *     // This is always treated as a TYPE_DYNAMIC_REFERENCE.
 *     ResTable_ref parent;
 *     // Number of name/value pairs that follow for FLAG_COMPLEX.
 *     uint32_t count;
 * };
 */
public class ResTableMapEntry extends ResTableEntry {

    public ResTableRef parent;

    public int count;

    public ArrayList<ResValue> resValues;

    public ResTableMapEntry() {
        parent = new ResTableRef();
        resValues = new ArrayList<>();
    }

    public int getSize() {
        return super.getSize() + parent.getSize() + 4;
    }

    @Override
    public String toString() {
        return "ResTableMapEntry{" +
                "parent=" + parent +
                ", count=" + count +
                ", resValues=" + resValues +
                ", size=" + size +
                ", flags=" + flags +
                ", key=" + key +
                ", resValue=" + resValue +
                '}';
    }
}
