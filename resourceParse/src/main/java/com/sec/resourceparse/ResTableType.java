package com.sec.resourceparse;


import java.util.ArrayList;
import java.util.Arrays;

/**
 *     struct ResChunk_header header;
 *
 *     enum {
 *         NO_ENTRY = 0xFFFFFFFF
 *     };
 *
 *     // The type identifier this chunk is holding.  Type IDs start
 *     // at 1 (corresponding to the value of the type bits in a
 *     // resource identifier).  0 is invalid.
 *     uint8_t id;
 *
 *     enum {
 *         // If set, the entry is sparse, and encodes both the entry ID and offset into each entry,
 *         // and a binary search is used to find the key. Only available on platforms >= O.
 *         // Mark any types that use this with a v26 qualifier to prevent runtime issues on older
 *         // platforms.
 *         FLAG_SPARSE = 0x01,
 *     };
 *     uint8_t flags;
 *
 *     // Must be 0.
 *     uint16_t reserved;
 *
 *     // Number of uint32_t entry indices that follow.
 *     uint32_t entryCount;
 *
 *     // Offset from header where ResTable_entry data starts.
 *     uint32_t entriesStart;
 *
 *     // Configuration this collection of entries is designed for. This must always be last.
 *     ResTable_config config;
 */

public class ResTableType {

    public ResChunkHeader chunkHeader;

    public byte id;

    public byte flags;

    public short reserved;

    public int entryCount;

    public int entriesStart;

    public ResTableConfig resConfig;

    public int [] entrys;

    public ArrayList<ResTableEntry> resTableEntries;

    public ResTableType(){
        chunkHeader = new ResChunkHeader();
        resConfig = new ResTableConfig();
    }

    public void parseConfig(ByteBuffer byteBuffer) {
        resConfig.parse(byteBuffer);
    }


    public void parseEntrys(ByteBuffer byteBuffer, int offset) {
        int chunkOffset = offset + chunkHeader.headerSize;
        if (entryCount <= 0) {
            return;
        }

        byteBuffer.position(chunkOffset);
        resTableEntries = new ArrayList<>();
        entrys = new int[entryCount];
        for (int i=0; i<entryCount; i++) {
            entrys[i] = byteBuffer.getInt();
        }

        chunkOffset = offset + entriesStart;
        for (int i=0; i<entryCount; i++) {
            if (entrys[i] == 0xFFFFFFFF) {
                resTableEntries.add(null);
            } else {
                byteBuffer.position(chunkOffset + entrys[i]);
                resTableEntries.add(parseEntry(byteBuffer));
            }
        }
    }

    public ResTableEntry parseEntry(ByteBuffer byteBuffer) {
        short size = byteBuffer.getShort();
        short flags = byteBuffer.getShort();
        int index = byteBuffer.getInt();
        if ((flags & ResTableEntry.FLAG_COMPLEX) != 0 ) {
            ResTableMapEntry resTableMapEntry = new ResTableMapEntry();
            resTableMapEntry.size = size;
            resTableMapEntry.flags = flags;
            resTableMapEntry.key.index = index;
            resTableMapEntry.parent.index = byteBuffer.getInt();
            resTableMapEntry.count = byteBuffer.getInt();
            int position = byteBuffer.position();
            for (int i=0; i<resTableMapEntry.count; i++) {
                byteBuffer.position(position + i * ResValue.getSize());
                resTableMapEntry.resValues.add(parseResValue(byteBuffer));
            }
            return resTableMapEntry;
        } else {
            ResTableEntry resTableEntry = new ResTableEntry();
            resTableEntry.size = size;
            resTableEntry.flags = flags;
            resTableEntry.key.index = index;
            resTableEntry.resValue = parseResValue(byteBuffer);
            return resTableEntry;
        }
    }

    private ResValue parseResValue(ByteBuffer byteBuffer) {
        ResValue resValue = new ResValue();
        resValue.size = byteBuffer.getShort();
        resValue.res0 = byteBuffer.get();
        resValue.dataType = byteBuffer.get();
        resValue.data = byteBuffer.getInt();
        return resValue;
    }

    private String[] getHexValue() {
        String [] strings = new String[entryCount];
        for (int i=0; i<strings.length; i++) {
            strings[i] = Integer.toHexString(entrys[i]);
        }
        return strings;
    }

    @Override
    public String toString() {
        return "ResTableType{" +
                "chunkHeader=" + chunkHeader +
                ", id=" + Integer.toHexString(id & 0xFF) +
                ", flags=" + flags +
                ", reserved=" + reserved +
                ", entryCount=" + entryCount +
                ", entriesStart=" + entriesStart +
//                ", resConfig=" + resConfig.toString() +
                ", entrys=" + Arrays.toString(getHexValue()) +
                ", resTableEntries=" + resTableEntries +
                '}';
    }
}
