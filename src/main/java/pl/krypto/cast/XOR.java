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

    public static byte[] xorEncode(byte[] message, byte[] key)
    {
        //Obliczanie długości szyfrogramu
        int len;
        if (message.length % 8 != 0)
            len = (message.length / 8 + 1) * 8;
        else
            len = message.length;

        byte[] result = new byte[len];
        byte[] tempBlock;
        System.arraycopy(message, 0, result, 0, message.length);
        // Pętla tnąca i kodowanie bloków
        for (int i = 0; i < (result.length / 8); i++)
        {
            tempBlock = XORBytes(BitOperations.oneBlock(result, i * 8),key);
            System.arraycopy(tempBlock, 0, result, i * 8, 8);
        }
        return result;
    }

    public static byte[] xorDecode(byte[] encrypted, byte[] key)
    {
        byte[] tmpResult = new byte[encrypted.length];
        byte[] tempBlock = new byte[8];
        byte[] rawData = null;
        try {
            rawData = encrypted;
            //Pętla tnąca i kodowanie bloków
            for (int i = 0; i < (rawData.length / 8); i++)
            {
                tempBlock = XORBytes(BitOperations.oneBlock(rawData, i * 8),key);
                System.arraycopy(tempBlock, 0, tmpResult, i * 8, tempBlock.length);
            }
            // Usuwanie dopełnionych zer
            int cnt = 0;
            for (int i = 1; i < 9; i += 2)
            {
                if (tmpResult[tmpResult.length - i] == 0 && tmpResult[tmpResult.length - i - 1] == 0)
                    cnt += 2;
                else
                    break;
            }
            byte[] result = new byte[tmpResult.length - cnt];
            System.arraycopy(tmpResult, 0, result, 0, tmpResult.length - cnt);
            return result;
        } catch (Exception ex) { };
        return null;
    }
}
