package pl.krypto.desx;

import pl.krypto.cast.KeysGenerator;
import pl.krypto.cast.XOR;

public class DesX {
    public DesX()
    {
    }

    public byte[] encodeDESX(byte[] message, byte [] key1, byte [] key2, byte [] key3, boolean ifE)
    {
        Des des = new Des();
        //xorowanie z kluczem I
        byte[] step1= XOR.XORBytes(message, key1);
        //szyfrowanie z kluczem II
        KeysGenerator subkeysGen = new KeysGenerator(key2);
        byte[][] subkeys = subkeysGen.getSubkeys();
        byte[] step2= des.crypt(step1, subkeys, ifE);
        //xorowanie z kluczem II
        byte[] result=XOR.XORBytes(step2, key3);
        return result;
    }
}
