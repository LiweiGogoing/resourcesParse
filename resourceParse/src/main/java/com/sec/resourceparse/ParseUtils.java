package com.sec.resourceparse;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ParseUtils {


    public static void parseRes(ByteBuffer byteBuffer) {
        if (byteBuffer == null || byteBuffer.capacity() <= 0) {
            return;
        }

        ResTableHeader resTableHeader = new ResTableHeader();
        parseResTable(byteBuffer, resTableHeader);
        ResStringPoolHeader resStringPoolHeader = new ResStringPoolHeader();
        int position = byteBuffer.position();
        parseStringPool(byteBuffer, resStringPoolHeader);
        ResTablePackage resTablePackage  = new ResTablePackage();
        position = position + resStringPoolHeader.chunkHeader.size;
        byteBuffer.position(position);
        parseResTablePackage(byteBuffer, resTablePackage);
    }


    public static void parseResTable(ByteBuffer byteBuffer, ResTableHeader resTableHeader) {
        resTableHeader.chunkHeader = parseResChunkHeader(byteBuffer);
        resTableHeader.packageCount = byteBuffer.getInt();
        System.out.println(resTableHeader.toString());
    }

    public static void parseStringPool(ByteBuffer buffer, ResStringPoolHeader resStringPoolHeader)  {
        int position = buffer.position();
        resStringPoolHeader.chunkHeader = parseResChunkHeader(buffer);
        resStringPoolHeader.stringCount = buffer.getInt();
        resStringPoolHeader.styleCount = buffer.getInt();
        resStringPoolHeader.flags = buffer.getInt();
        resStringPoolHeader.stringsStart = buffer.getInt();
        resStringPoolHeader.stylesStart = buffer.getInt();
        System.out.println(resStringPoolHeader.toString());

        /******************parse string index pool***************************/
        int[] stringIndexAry = new int[resStringPoolHeader.stringCount];
        for (int i=0; i<stringIndexAry.length; i++) {
            stringIndexAry[i] = buffer.getInt();
        }

        int[] styleIndexAry = new int[resStringPoolHeader.styleCount];
        for (int i=0; i<styleIndexAry.length; i++) {
            styleIndexAry[i] = buffer.getInt();
        }

        /******************parse string pool*****************************/
        int index = 0;
        ArrayList<String> strings = new ArrayList<>();
        int stringPoolOffset = position + resStringPoolHeader.stringsStart;
        while (index < resStringPoolHeader.stringCount) {
            int currentStringOffset = stringPoolOffset + stringIndexAry[index];
            buffer.position(currentStringOffset);
            byte [] lengthByte = new byte[2];
            buffer.get(lengthByte);
            int stringSize = resStringPoolHeader.decodeLength(lengthByte);
            if (stringSize > 0) {
                byte [] data = new byte[stringSize];
                buffer.get(data);
                String value = "";
                if (resStringPoolHeader.isUtf8Encoding()) {
                    value = new String(data, StandardCharsets.UTF_8);
                } else {
                    value = new String(data, StandardCharsets.UTF_16LE);
                }
                strings.add(value);
                System.out.println("---------value ==" + value + "---index:" + index);
            }
            index++;
        }

        /********************parse string style****************************/
        System.out.println("start parse string style");
        int stringPoolStyleOffset = position + resStringPoolHeader.stylesStart;
        ArrayList<ResStringPoolStyle> stringPoolStyles = new ArrayList<>();
        for (int i=0; i<styleIndexAry.length; i++) {
            buffer.position(stringPoolStyleOffset);
            ResStringPoolStyle resStringPoolStyle = new ResStringPoolStyle();
            resStringPoolStyle.parseResStringPoolStyle(buffer);
            stringPoolStyles.add(resStringPoolStyle);
        }
    }

    public static void parseResTablePackage(ByteBuffer byteBuffer, ResTablePackage resTablePackage) {
        int position = byteBuffer.position();
        resTablePackage.chunkHeader = parseResChunkHeader(byteBuffer);
        resTablePackage.id = byteBuffer.getInt();
        byte[] nameBytes = new byte[128 * 2];
        byteBuffer.get(nameBytes);
        resTablePackage.name = Utils.getChars(nameBytes);
        resTablePackage.typeStrings = byteBuffer.getInt();
        resTablePackage.lastPublicType = byteBuffer.getInt();
        resTablePackage.keyStrings = byteBuffer.getInt();
        resTablePackage.lastPublicKey = byteBuffer.getInt();
        resTablePackage.typeIdOffset = byteBuffer.getInt();
        System.out.println(resTablePackage.toString());

        ResStringPoolHeader typeStringPool  = new ResStringPoolHeader();
        byteBuffer.position(position + resTablePackage.typeStrings);
        parseStringPool(byteBuffer, typeStringPool);

        ResStringPoolHeader keyStringPool  = new ResStringPoolHeader();
        byteBuffer.position(position + resTablePackage.keyStrings);
        parseStringPool(byteBuffer, keyStringPool);

        int resTableTypeSpecOffset = position + resTablePackage.keyStrings + keyStringPool.chunkHeader.size;
        byteBuffer.position(resTableTypeSpecOffset);
        parseResTableTypeSpecOrTableType(byteBuffer);
    }




    public static void parseResTableTypeSpecOrTableType(ByteBuffer byteBuffer) {
        int position = byteBuffer.position();
        while (position < byteBuffer.capacity()) {
            byteBuffer.position(position);
            ResChunkHeader chunkHeader = parseResChunkHeader(byteBuffer);
            if (chunkHeader.type == ResChunkHeader.RES_TABLE_TYPE_SPEC_TYPE) {
                parseResTableTypeSpec(byteBuffer, chunkHeader);
            } else {
                ResTableType resTableType = parseResTableType(byteBuffer, chunkHeader);
                System.out.println(resTableType.toString());
            }
            position = position + chunkHeader.size;
        }
    }

    private static void parseResTableTypeSpec(ByteBuffer byteBuffer, ResChunkHeader chunkHeader) {
        ResTableTypeSpec resTableTypeSpec = new ResTableTypeSpec();
        resTableTypeSpec.chunkHeader = chunkHeader;
        resTableTypeSpec.id = byteBuffer.get();
        resTableTypeSpec.res0 = byteBuffer.get();
        resTableTypeSpec.res1 = byteBuffer.getShort();
        resTableTypeSpec.entryCount = byteBuffer.getInt();
        resTableTypeSpec.parseSpecArr(byteBuffer);
        System.out.println(resTableTypeSpec.toString());
    }


    public static ResTableType parseResTableType(ByteBuffer byteBuffer, ResChunkHeader chunkHeader) {
        ResTableType resTableType  = new ResTableType();
        int position = byteBuffer.position();
        resTableType.chunkHeader = chunkHeader;
        resTableType.id = byteBuffer.get();
        resTableType.flags = byteBuffer.get();
        resTableType.reserved = byteBuffer.getShort();
        resTableType.entryCount = byteBuffer.getInt();
        resTableType.entriesStart = byteBuffer.getInt();
        resTableType.parseConfig(byteBuffer);
        resTableType.parseEntrys(byteBuffer, position  - chunkHeader.getHeaderSize());
        return resTableType;
    }

    public static ResChunkHeader parseResChunkHeader(ByteBuffer byteBuffer) {
        ResChunkHeader resChunkHeader = new ResChunkHeader();
        resChunkHeader.type = byteBuffer.getShort();
        resChunkHeader.headerSize = byteBuffer.getShort();
        resChunkHeader.size = byteBuffer.getInt();
        return resChunkHeader;
    }

}
