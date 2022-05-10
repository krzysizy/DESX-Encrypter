package pl.krypto;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.krypto.cast.BitOperations;
import pl.krypto.cast.Permutation;
import pl.krypto.cast.tabTransformation;

import java.util.Objects;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/javafx.fxml")));
        Scene scene = new Scene(root, 659, 540);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
