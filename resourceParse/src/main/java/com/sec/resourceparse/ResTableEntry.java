package com.sec.resourceparse;

/**
 * struct ResTable_entry
 * {
 *     // Number of bytes in this structure.
 *     uint16_t size;
 *
 *     enum {
 *         // If set, this is a complex entry, holding a set of name/value
 *         // mappings.  It is followed by an array of ResTable_map structures.
 *         FLAG_COMPLEX = 0x0001,
 *         // If set, this resource has been declared public, so libraries
 *         // are allowed to reference it.
 *         FLAG_PUBLIC = 0x0002,
 *         // If set, this is a weak resource and may be overriden by strong
 *         // resources of the same name/type. This is only useful during
 *         // linking with other resource tables.
 *         FLAG_WEAK = 0x0004
 *     };
 *     uint16_t flags;
 *
 *     // Reference into ResTable_package::keyStrings identifying this entry.
 *     struct ResStringPool_ref key;
 * };
 */
public class ResTableEntry {

    public short size;

    public static final int FLAG_COMPLEX = 0x0001;

    public static final int FLAG_PUBLIC = 0x0002;

    public static final int FLAG_WEAK = 0x0004;

    public short flags;

    public ResStringPoolRef key;

    public ResValue resValue;

    public ResTableEntry() {
        key = new ResStringPoolRef();
        resValue = new ResValue();
    }

    public int getSize() {
        return key.getSize() + 2 + 2;
    }

    @Override
    public String toString() {
        return "ResTableEntry{" +
                "size=" + size +
                ", flags=" + flags +
                ", key=" + key +
                ", resValue=" + resValue +
                '}';
    }
}
