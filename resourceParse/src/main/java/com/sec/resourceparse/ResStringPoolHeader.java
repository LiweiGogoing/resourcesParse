package com.sec.resourceparse;

/**
 * struct ResStringPool_header
 * {
 *     struct ResChunk_header header;
 *
 *     // Number of strings in this pool (number of uint32_t indices that follow
 *     // in the data).
 *     uint32_t stringCount;
 *
 *     // Number of style span arrays in the pool (number of uint32_t indices
 *     // follow the string indices).
 *     uint32_t styleCount;
 *
 *     // Flags.
 *     enum {
 *         // If set, the string index is sorted by the string values (based
 *         // on strcmp16()).
 *         SORTED_FLAG = 1<<0,
 *
 *         // String pool is encoded in UTF-8
 *         UTF8_FLAG = 1<<8
 *     };
 *     uint32_t flags;
 *
 *     // Index from header of the string data.
 *     uint32_t stringsStart;
 *
 *     // Index from header of the style data.
 *     uint32_t stylesStart;
 * };
 */
public class ResStringPoolHeader {

    public ResChunkHeader chunkHeader;

    public int stringCount;

    public int styleCount;

    public final static int SORTED_FLAG = 1;
    public final static int UTF8_FLAG = (1<<8);

    public int flags;

    // current chunk index
    public int stringsStart;

    public int stylesStart;

    public ResStringPoolHeader() {
        chunkHeader = new ResChunkHeader();
    }

    public int getHeaderSize() {
        return chunkHeader.getHeaderSize() + 4 + 4 + 4 + 4 + 4;
    }

    public boolean isUtf8Encoding() {
        if ((flags & UTF8_FLAG) != 0) {
            return true;
        }
        return false;
    }

    public int decodeLength(byte[] data) {
        int len = data[0];
        if (flags == UTF8_FLAG) {
            if ((data[0] & 0x80) != 0) {
                len  = ((data[0] & 0x7F) << 8) | data[1];
            }
        } else {
            if ((len & 0x8000) != 0) {
                len  = ((len & 0x7FFF) << 16) | data[1];
            }
            len = len * 2;
        }
        return len;
    }


    @Override
    public String toString() {
        return "ResStringPoolHeader{" +
                "chunkHeader=" + chunkHeader +
                ", stringCount=" + stringCount +
                ", styleCount=" + styleCount +
                ", flags=" + flags +
                ", stringsStart=" + stringsStart +
                ", stylesStart=" + stylesStart +
                '}';
    }
}
