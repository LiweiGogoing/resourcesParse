package com.sec.resourceparse;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * struct ResTable_typeSpec
 * {
 *     struct ResChunk_header header;
 *
 *     // The type identifier this chunk is holding.  Type IDs start
 *     // at 1 (corresponding to the value of the type bits in a
 *     // resource identifier).  0 is invalid.
 *     uint8_t id;
 *
 *     // Must be 0.
 *     uint8_t res0;
 *     // Must be 0.
 *     uint16_t res1;
 *
 *     // Number of uint32_t entry configuration masks that follow.
 *     uint32_t entryCount;
 *
 *     enum : uint32_t {
 *         // Additional flag indicating an entry is public.
 *         SPEC_PUBLIC = 0x40000000u,
 *
 *         // Additional flag indicating an entry is overlayable at runtime.
 *         // Added in Android-P.
 *         SPEC_OVERLAYABLE = 0x80000000u,
 *     };
 * };
 */
public class ResTableTypeSpec {

    public final static int SPEC_PUBLIC = 0x40000000;
    public final static int SPEC_OVERLAYABLE = 0x80000000;

    public ResChunkHeader chunkHeader;

    public byte id;

    public byte res0;

    public short res1;

    public int entryCount;

    public ArrayList<ResTableType> resTableTypes;

    public int [] spec;

    public ResTableTypeSpec() {
        chunkHeader = new ResChunkHeader();
        resTableTypes = new ArrayList<>();
    }

    public void parseSpecArr(ByteBuffer byteBuffer) {
        if (entryCount > 0) {
            spec = new int[entryCount];
            for (int i=0; i<entryCount; i++) {
                spec[i] = byteBuffer.getInt();
            }
        }
    }

    @Override
    public String toString() {
        return "ResTableTypeSpec{" +
                "chunkHeader=" + chunkHeader +
                ", id=" +  Integer.toHexString(id & 0xFF) +
                ", res0=" + res0 +
                ", res1=" + res1 +
                ", entryCount=" + entryCount +
                ", resTableTypes=" + resTableTypes +
                ", spec=" + Arrays.toString(spec) +
                '}';
    }
}
