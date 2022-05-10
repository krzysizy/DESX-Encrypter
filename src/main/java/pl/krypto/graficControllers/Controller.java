package pl.krypto.graficControllers;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
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
    private byte [] key1;
    private byte [] key2;
    private byte [] key3;
    private Stage stage;

    public void openFile() {
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Wybierz plik do zaszyfrowania");

        inputFile = fileChooser.showOpenDialog(stage);
    }

    public void keyToByteKey  (){
        key1 = tabTransformation.hexToBytes(Key1Text.getText());
        key2 = tabTransformation.hexToBytes(Key2Text.getText());
        key3 = tabTransformation.hexToBytes(Key3Text.getText());
    }
    public void getFileExtension() {
        String extension = null;

        int index = inputFile.getName().lastIndexOf('.');
        if(index > 0) {
            extension = inputFile.getName().substring(index + 1);
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

        fileByteArray = new byte[(int)inputFile.length()];
        FileInputStream fileStream = new FileInputStream(inputFile);
        getFileExtension();
        fileStream.read(fileByteArray);
        keyToByteKey();
        fileByteArray = desXControler.DESX(fileByteArray,key1,key2,key3,true);
        outputFile = new File("Zaszyfrowane." + fileExtension);
        FileOutputStream fileOutStream = new FileOutputStream(outputFile);
        fileOutStream.write(fileByteArray);
        FileEncryptionSuccessLabel.setText("Zaszyfrowano pomyslnie do pliku: " + outputFile.getName());
        FileEncryptionSuccessLabel.setVisible(true);
    }

    public void FileDecoding() throws IOException {
        byte [] fileByteArray;
        inputFile = new File("Zaszyfrowane." + fileExtension);
        fileByteArray = new byte[(int)inputFile.length()];
        FileInputStream fileStream = new FileInputStream(inputFile);
        getFileExtension();
        fileStream.read(fileByteArray);
        keyToByteKey();
        fileByteArray = desXControler.DESX(fileByteArray,key3,key2,key1,false);
        outputFile = new File("Odszyfrowane." + fileExtension);
        FileOutputStream fileOutStream = new FileOutputStream(outputFile);
        fileOutStream.write(fileByteArray);
        FileEncryptionSuccessLabel.setText("Odszyfrowano pomyslnie do pliku: " + outputFile.getName());
    }

    public void textEncrypt() throws UnsupportedEncodingException {
        byte [] test = tabTransformation.StringToByteArray(TextToEncrypt.getText());
        keyToByteKey();
        byte [] test2 = desXControler.DESX(test,key1,key2,key3,true);
        EncryptedText.setText(tabTransformation.bytesToHex(test2));
    }

    public void textDecrypt() throws UnsupportedEncodingException {
        byte [] test2 = tabTransformation.hexToBytes(EncryptedText.getText());
        keyToByteKey();
        test2 = desXControler.DESX(test2,key3,key2,key1,false);
        EncryptedText.setText(tabTransformation.ByteArrayToString(test2));
    }

    public static void fillProgressBar() {

    }
}
