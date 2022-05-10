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
import java.util.Objects;
import java.util.Random;

public class Controller {

    @FXML
    private TextField Key1Text;

    @FXML
    private TextField Key2Text;

    @FXML
    private TextField Key3Text;

    @FXML
    private TextField choosenFile;

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

    Random rand = new Random();
    private File inputFile;
    private File outputFile;
    private String fileExtension;
    private DesX desXControler = new DesX();
    private byte [] key1;
    private byte [] key2;
    private byte [] key3;
    private Stage stage;
    private DialogueBoxPopUp exceptionMessage = new DialogueBoxPopUp();
    private final char [] hexTable = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
    private final String hexTableString = "0123456789ABCDEF";


    public void openFile() throws Exception {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Wybierz plik do zaszyfrowania");
        inputFile = fileChooser.showOpenDialog(stage);
        choosenFile.setText(inputFile.getName());
        if(inputFile == null){
            throw new Exception("Nie wybrano pliku lub plik nie otworzyl sie poprawnie.");
        }
    }

    public void keyToByteKey  () throws Exception {
        if(Key1Text.getText().length() != 16 && Key1Text.getText().length() != 16 && Key1Text.getText().length() != 16) {
            throw new Exception("Wpisane klucze sa niepoprawne. Klucz musi sie skladac z 16 znakow z zakresu 0-9 i A-F");
        }
        for(int i = 0; i<16;i++) {
            if (!(hexTableString.contains(String.valueOf(Key1Text.getText().charAt(i)).toUpperCase()) && hexTableString.contains(String.valueOf(Key2Text.getText().charAt(i)).toUpperCase()) && hexTableString.contains(String.valueOf(Key3Text.getText().charAt(i)).toUpperCase()))) {
                throw new Exception("Wpisane klucze sa niepoprawne. Klucz musi sie skladac z 16 znakow z zakresu 0-9 i A-F");
            }
        }
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

    public void fileEncoding()  {
        byte [] fileByteArray;
        try {
            if(inputFile == null){
                throw new Exception("Nie wybrano pliku lub plik nie otworzyl sie poprawnie.");
            }
            fileByteArray = new byte[(int) inputFile.length()];
            FileInputStream fileStream = new FileInputStream(inputFile);
            getFileExtension();
            fileStream.read(fileByteArray);
            keyToByteKey();
            fileByteArray = desXControler.DESX(fileByteArray, key1, key2, key3, true);
            outputFile = new File("Zaszyfrowane." + fileExtension);
            FileOutputStream fileOutStream = new FileOutputStream(outputFile);
            fileOutStream.write(fileByteArray);
            FileEncryptionSuccessLabel.setText("Zaszyfrowano pomyslnie do pliku: " + outputFile.getName());
            FileEncryptionSuccessLabel.setVisible(true);
        }catch (Exception e) {
            exceptionMessage.messsageBox(e.getMessage());
        }
    }

    public void FileDecoding()  {
        byte [] fileByteArray;
        try {
            if(inputFile == null){
                throw new Exception("Nie wybrano pliku lub plik nie otworzyl sie poprawnie.");
            }
            fileByteArray = new byte[(int) inputFile.length()];
            FileInputStream fileStream = new FileInputStream(inputFile);
            getFileExtension();
            fileStream.read(fileByteArray);
            keyToByteKey();
            fileByteArray = desXControler.DESX(fileByteArray, key3, key2, key1, false);
            outputFile = new File("Odszyfrowane." + fileExtension);
            FileOutputStream fileOutStream = new FileOutputStream(outputFile);
            fileOutStream.write(fileByteArray);
            FileEncryptionSuccessLabel.setText("Odszyfrowano pomyslnie do pliku: " + outputFile.getName());
        }catch(Exception e){
            exceptionMessage.messsageBox(e.getMessage());
        }
    }

    public void textEncrypt() {
        try {
            byte[] test = tabTransformation.StringToByteArray(TextToEncrypt.getText());
            keyToByteKey();
            byte[] test2 = desXControler.DESX(test, key1, key2, key3, true);
            EncryptedText.setText(tabTransformation.bytesToHex(test2));
            DecryptTextButton.setDisable(false);
        }catch (Exception e){
            exceptionMessage.messsageBox(e.getMessage());
        }
    }

    public void textDecrypt() {
        try {
            if(Objects.equals(EncryptedText.getText(), "")) {
                throw new Exception("Nie ma tekstu do odszyfrowania");
            }
            byte[] test2 = tabTransformation.hexToBytes(EncryptedText.getText());
            keyToByteKey();
            test2 = desXControler.DESX(test2, key3, key2, key1, false);
            EncryptedText.setText(tabTransformation.ByteArrayToString(test2));
            DecryptTextButton.setDisable(true);
        } catch (Exception e) {
            exceptionMessage.messsageBox(e.getMessage());
        }
    }

}
