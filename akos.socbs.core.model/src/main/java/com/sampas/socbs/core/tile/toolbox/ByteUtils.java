package com.sampas.socbs.core.tile.toolbox;

public class ByteUtils {
    
    /**
     * Converts an unsigned 32 bit integer, stored in a long, into an array of bytes.
     *
     * @param j a long
     * @return byte[4] representing the unsigned integer, most significant bit first.
     */
    public static byte[] uIntLongToByteWord(long j) {
        int i = (int) j;
        byte[] byteWord = new byte[4];
        byteWord[0] = (byte) ((i >>> 24) & 0x000000FF);
        byteWord[1] = (byte) ((i >> 16) & 0x000000FF);
        byteWord[2] = (byte) ((i >> 8) & 0x000000FF);
        byteWord[3] = (byte) (i & 0x00FF);
        return byteWord;
    }
    
    /**
     * Combines four bytes (most significant bit first) into a 32 bit unsigned integer.
     *
     * @param bytes
     * @param index of most significant byte
     * @return long with the 32 bit unsigned integer
     */
    public static long bytesToUIntLong(byte[] bytes, int index) {
        long accum = 0;
        int i = 3;
        for (int shiftBy = 0; shiftBy < 32; shiftBy += 8 ) {
            accum |= ( (long)( bytes[index + i] & 0xff ) ) << shiftBy;
            i--;
        }
        return accum;
    }
}
