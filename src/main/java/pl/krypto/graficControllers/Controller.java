package pl.krypto.graficControllers;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Controller {
    @FXML
    private Label buttonLabel1;

    public void sayHello() {
        buttonLabel1.setText("Hello FXML!");
    }
}
