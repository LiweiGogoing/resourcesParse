package com.sec.resourceparse;



import java.util.Arrays;

/**
 * struct ResChunk_header header;
 *
 *     // If this is a base package, its ID.  Package IDs start
 *     // at 1 (corresponding to the value of the package bits in a
 *     // resource identifier).  0 means this is not a base package.
 *     uint32_t id;
 *
 *     // Actual name of this package, \0-terminated.
 *     uint16_t name[128];
 *
 *     // Offset to a ResStringPool_header defining the resource
 *     // type symbol table.  If zero, this package is inheriting from
 *     // another base package (overriding specific values in it).
 *     uint32_t typeStrings;
 *
 *     // Last index into typeStrings that is for public use by others.
 *     uint32_t lastPublicType;
 *
 *     // Offset to a ResStringPool_header defining the resource
 *     // key symbol table.  If zero, this package is inheriting from
 *     // another base package (overriding specific values in it).
 *     uint32_t keyStrings;
 *
 *     // Last index into keyStrings that is for public use by others.
 *     uint32_t lastPublicKey;
 *
 *     uint32_t typeIdOffset;
 */
public class ResTablePackage {

    public ResChunkHeader chunkHeader;

    public int id;

    public char[] name = new char[128];

    public int typeStrings;

    public int lastPublicType;

    public int keyStrings;

    public int lastPublicKey;

    public int typeIdOffset;

    public ResTablePackage() {
        chunkHeader = new ResChunkHeader();
    }

    @Override
    public String toString() {
        return "ResTablePackage{" +
                "chunkHeader=" + chunkHeader +
                ", id=" + Integer.toHexString(id) +
                ", name=" + Arrays.toString(name) +
                ", typeStrings=" + typeStrings +
                ", lastPublicType=" + lastPublicType +
                ", keyStrings=" + keyStrings +
                ", lastPublicKey=" + lastPublicKey +
                ", typeIdOffset=" + typeIdOffset +
                '}';
    }


}
