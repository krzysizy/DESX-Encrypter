package pl.krypto.graficControllers;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import pl.krypto.cast.BitOperations;
import pl.krypto.cast.tabTransformation;
import pl.krypto.desx.Des;
import pl.krypto.desx.DesX;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Random;

public class Controller {

    @FXML
    private TextField Key1Text;

    @FXML
    private TextField Key2Text;

    @FXML
    private TextField Key3Text;

    @FXML
    private TextArea TextToEncrypt;

    @FXML
    private TextArea EncryptedText;

    @FXML
    private Button EncryptTextButton;

    @FXML
    private Button DecryptTextButton;

    @FXML
    private Button EncryptFileButton;

    @FXML
    private Button DecryptFileButton;

    @FXML
    private Button ExampleKeyButton;

    @FXML
    private TextField FileNameToEncrypt;

    @FXML
    private Label FileEncryptionSuccessLabel;

    @FXML
    private ProgressBar ProgressBar;
    private double progress = 0;
    private double tmpProgress;
    Random rand = new Random();
    private File inputFle;
    private File outputFile;
    private DesX desXControler = new DesX();


    public String randomKey () {
        String key = "";
        char [] hexTable = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        for(int i = 0; i < 16; i++){
            key += hexTable[rand.nextInt(16)];
        }
        return key;
    }
    public void loadExampleKey() {
        Key1Text.setText(randomKey());
        Key2Text.setText(randomKey());
        Key3Text.setText(randomKey());
    }

    public void fileEncoding() throws UnsupportedEncodingException {
        FileEncryptionSuccessLabel.setVisible(true);
        ProgressBar.setProgress(0.5);
        for(int i = 0; i < 8; i++){
            System.out.println(Arrays.toString(tabTransformation.StringToByteArray(TextToEncrypt.getText())));
        }
    }

    public void textEncrypt() throws UnsupportedEncodingException {
        byte [] test = tabTransformation.StringToByteArray(TextToEncrypt.getText());
        String str = tabTransformation.bytesToHex(test);
        System.out.println(str);
        test = tabTransformation.hexToBytes(str);
        for(int i = 0; i < test.length*8;i++)
            System.out.print(BitOperations.getBit(test,i));
        System.out.print("\n");
//        byte [] key1 = tabTransformation.hexToBytes(Key1Text.getText());
//        byte [] key2 = tabTransformation.hexToBytes(Key2Text.getText());
//        byte [] key3 = tabTransformation.hexToBytes(Key3Text.getText());
//        byte [] test2 = desXControler.encodeDESX(test,key1,key2,key3,true);
//        EncryptedText.setText(tabTransformation.bytesToHex(test2));
//        for(int i = 0; i < test2.length*8;i++)
//            System.out.print(BitOperations.getBit(test2,i));
//        System.out.print("\n");
//        test2 = desXControler.encodeDESX(test2,key3,key2,key1,false);
//        for(int i = 0; i < test2.length*8;i++)
//            System.out.print(BitOperations.getBit(test2,i));
//        System.out.print("\n");
    }

    public void textDecrypt() throws UnsupportedEncodingException {
        byte [] test2 = tabTransformation.hexToBytes(EncryptedText.getText());
        byte [] key1 = tabTransformation.hexToBytes(Key1Text.getText());
        byte [] key2 = tabTransformation.hexToBytes(Key2Text.getText());
        byte [] key3 = tabTransformation.hexToBytes(Key3Text.getText());
        test2 = desXControler.encodeDESX(test2,key3,key2,key1,false);
        EncryptedText.setText(tabTransformation.ByteArrayToString(test2));
    }



}
