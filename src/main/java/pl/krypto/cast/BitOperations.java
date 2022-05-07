package pl.krypto.cast;

import javax.swing.*;

public class BitOperations {

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

    //zwraca tablicę z podaną ilością bitów od podanej pozycji z tablicy bajtów
    public static byte[] selectBits(byte[] in, int pos, int len)
    {
        int numOfBytes = (len - 1) / 8 + 1;
        byte[] out = new byte[numOfBytes];
        for (int i = 0; i < len; i++) {
            setBit(out, i, getBit(in, pos + i));
        }
        return out;
    }

    //cykliczne przesunięcie bitów w lewo o zadana ilość pozycji
    public static byte[] shiftLeft(byte[] in, int step)
    {
        byte[] out = new byte[(in.length - 1) / 8 + 1];
        for (int i = 0; i < in.length; i++)
        {
            setBit(out, i, getBit(in, (i + step) % in.length));
        }
        return out;
    }

    //łączenie dwóch ciągów bajtów w jeden
    public static byte[] joinTabBytes(byte[] a, byte[] b)
    {
        int numOfBytes = (a.length + b.length - 1) / 8 + 1;
        byte[] out = new byte[numOfBytes];
        int j = 0;
        for (int i = 0; i < a.length; i++)
        {
            setBit(out, j, getBit(a, i));
            j++;
        }
        for (int i = 0; i < b.length; i++)
        {
            setBit(out, j, getBit(b, i));
            j++;
        }
        return out;
    }


}