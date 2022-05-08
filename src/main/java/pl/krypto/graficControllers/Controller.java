package pl.krypto.graficControllers;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import pl.krypto.cast.tabTransformation;

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



}
