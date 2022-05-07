package pl.krypto.cast;

public class XOR {
    //xoruje dwie tablice bajtów
    public static byte[] XORBytes(byte[] a, byte[] b)
    {
        byte[] result = new byte[a.length];
        for (int i = 0; i < a.length; i++)
        {
            result[i] = (byte) (a[i] ^ b[i]);
        }
        return result;
    }
}
