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

    public static byte[] xorBlock(byte[] key, byte[] data, int beginIndex) throws Exception
    {
        byte[] msg = new byte[8];
        System.arraycopy(data, beginIndex, msg, 0, 8);
        msg = XORBytes(msg, key);
        return msg;
    }
    public static byte[] xorEncode(byte[] message, byte[] key)
    {
        //Obliczanie długości szyfrogramu
        int len;
        if ((message.length / 2 % 4) != 0)
            len = (message.length / 8 + 1) * 8;
        else
            len = message.length;

        byte[] result = new byte[len];
        byte[] tempBlock = new byte[8];
        byte[] rawData = null;
        try {
            rawData = message;
            // Pętla tnąca i kodowanie bloków
            for (int i = 0; i < (rawData.length / 8); i++)
            {
                tempBlock = xorBlock(key, rawData, i * 8);
                System.arraycopy(tempBlock, 0, result, i * 8, 8);
            }

            // Dopełnianie zerami i kodowanie ostatniego bloku
            if (message.length / 2 % 4 != 0)
            {
                for (int i = 0; i < 8; i++)
                {
                    if (i + (rawData.length / 8) * 8 < rawData.length)
                        tempBlock[i] = rawData[i + (rawData.length / 8) * 8];
                    else
                        tempBlock[i] = 0;
                }
                tempBlock = xorBlock(key, tempBlock, 0);
                System.arraycopy(tempBlock, 0, result, (rawData.length / 8) * 8, 8);
            }
            return result;
        } catch (Exception ex) {};
        return null;
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
                tempBlock = xorBlock(key, rawData, i * 8);
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
