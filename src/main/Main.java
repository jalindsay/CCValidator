package main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    private boolean validate(String str){
        int[] numbers = new int[str.length()];
        char[] stringToCharArray = str.toCharArray();
        int sum = 0;

        //copy from char array to int array, going in backwards
        for(int i=0; i<str.length(); i++){
            numbers[str.length()-i-1] = stringToCharArray[i] - '0';
        }

        //calculate sum part of check digits
        for(int i=1; i<numbers.length; i++){
            if(i%2 == 0) {
                sum += numbers[i];
            } else {
                int doubled = 2 * numbers[i];
                if(doubled > 9)
                    doubled -= 9;
                sum += doubled;
            }
        }

        //final part of calculation of check digit
        int checkDigit = (sum*9)%10;

        System.out.println("Check Digit: "+checkDigit);

        //comparing calculated check digit with first digit of card

        return numbers[0] == checkDigit;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Credit Card Validator");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label label = new Label();
        grid.add(label, 1, 1);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 2);

        Button validateButton = new Button("Validate");
        grid.add(validateButton, 1, 3);

        Scene scene = new Scene(grid, 300, 275);
        primaryStage.setScene(scene);

        primaryStage.show();

        validateButton.setOnAction(event -> {
            String str = userTextField.getText();
            boolean result;
            result = validate(str);
            if(result)
                label.setText("VALID");
            else
                label.setText("INVALID");
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
