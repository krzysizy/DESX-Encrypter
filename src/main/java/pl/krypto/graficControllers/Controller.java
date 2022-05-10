package pl.krypto.graficControllers;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import pl.krypto.cast.BitOperations;
import pl.krypto.cast.tabTransformation;
import pl.krypto.desx.Des;
import pl.krypto.desx.DesX;

import java.io.*;
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
    private File inputFile;
    private File outputFile;
    private String fileExtension;
    private DesX desXControler = new DesX();

    public void getFileExtension() {
        String extension = null;

        int index = FileNameToEncrypt.getText().lastIndexOf('.');
        if(index > 0) {
            extension = FileNameToEncrypt.getText().substring(index + 1);
        }

        fileExtension = extension;
    }

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

    public void fileEncoding() throws IOException {
        byte [] fileByteArray;
        fileByteArray = new byte[(int) inputFile.getTotalSpace()];
        inputFile = new File(FileNameToEncrypt.getText());
        FileInputStream fileStream = new FileInputStream(inputFile);
        getFileExtension();
        fileStream.read(fileByteArray);
        for(int i =0; i < fileByteArray.length*8; i++)
        System.out.println(BitOperations.getBit(fileByteArray,i));
        FileEncryptionSuccessLabel.setVisible(true);
    }

    public void FileDecoding(){

    }

    public void textEncrypt() throws UnsupportedEncodingException {
        byte [] test = tabTransformation.StringToByteArray(TextToEncrypt.getText());
        byte [] key1 = tabTransformation.hexToBytes(Key1Text.getText());
        byte [] key2 = tabTransformation.hexToBytes(Key2Text.getText());
        byte [] key3 = tabTransformation.hexToBytes(Key3Text.getText());
        byte [] test2 = desXControler.DESX(test,key1,key2,key3,true);
        EncryptedText.setText(tabTransformation.bytesToHex(test2));
    }

    public void textDecrypt() throws UnsupportedEncodingException {
        byte [] test2 = tabTransformation.hexToBytes(EncryptedText.getText());
        byte [] key1 = tabTransformation.hexToBytes(Key1Text.getText());
        byte [] key2 = tabTransformation.hexToBytes(Key2Text.getText());
        byte [] key3 = tabTransformation.hexToBytes(Key3Text.getText());
        test2 = desXControler.DESX(test2,key3,key2,key1,false);
        EncryptedText.setText(tabTransformation.ByteArrayToString(test2));
    }





}
