package com.sec.resourceparse;

/**
 *  struct ResChunk_header header;
 *
 *  // The number of ResTable_package structures.
 *  uint32_t packageCount;
 */
public class ResTableHeader {

    public ResChunkHeader chunkHeader;

    public int packageCount;

    public ResTableHeader() {
        chunkHeader = new ResChunkHeader();
    }

    public int getHeaderSize() {
        return chunkHeader.getHeaderSize() + 4;
    }

    @Override
    public String toString() {
        return "ResTableHeader{" +
                "chunkHeader=" + chunkHeader.toString() +
                ", packageCount=" + packageCount +
                '}';
    }
}
