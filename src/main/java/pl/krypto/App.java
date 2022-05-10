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
        Scene scene = new Scene(root, 659, 579);
        primaryStage.setScene(scene);
        primaryStage.show();

//        String stringToConvert = "F123456789ABCDEF";
//        byte [] data = tabTransformation.hexToBytes(stringToConvert);
//        for(int i = 0; i < 8; i++) {
//            System.out.println(BitOperations.getBit(data, i));
//        }
//        byte [] tab1 = {1};
//        byte [] tab2 = {2};
//        byte [] result;
//        result = BitOperations.joinTabBytes(tab1,tab2);
//        result = BitOperations.shiftLeft(result,1);
//        for(int i = 0; i < 16; i++) {
//            System.out.print(BitOperations.getBit(result, i));
//        }
//
//        System.out.println(Permutation.getBit(data,14));
//        Permutation.setBit(data,13,1);
//        Permutation.setBit(data,14,0);
//        System.out.println(Permutation.getBit(data,13));
//        System.out.println(Permutation.getBit(data,14));
//        System.out.println(Permutation.getBit(Permutation.permutation(data,patern),1));
    }
}
