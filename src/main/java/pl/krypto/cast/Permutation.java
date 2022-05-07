package pl.krypto.cast;

public class Permutation {

    public static byte[] permutation(byte[] block, byte[] pattern) {
        byte[] resultBlock = new byte[pattern.length / 8];
        for (int i = 0; i < pattern.length; i++) {
            setBit(resultBlock, i, getBit(block, pattern[i] - 1));
        }
        return resultBlock;
    }

    //pobiera bit z podanej pozycji w tablicy bajtów
    public static int getBit(byte[] data, int pos)
    {
        return  data[pos / 8] >> (7 - (pos % 8)) & 1;
    }

    //ustawia lub kasuje bit na podanej pozycji w tablicy bajtow
    public static void setBit(byte[] data, int pos, int val)
    {
        byte oldByte = data[pos / 8];
        oldByte = (byte) (((0xFF7F >> (pos % 8)) & oldByte) & 0x00FF);
        byte newByte = (byte) ((val << (7 - (pos % 8))) | oldByte);
        data[pos / 8] = newByte;
    }
}
