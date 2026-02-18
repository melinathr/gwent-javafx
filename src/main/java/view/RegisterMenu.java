package view;

import controller.RegisterController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Player;


import java.net.URL;
import java.util.Objects;


public class RegisterMenu extends Application {

    public TextField username;
    public PasswordField passwordField;
    public PasswordField passConfirmField;
    public TextField email;
    public TextField nickname;
    public ChoiceBox<String> securityQuestion;
    public TextField securityAnswer;

    private String generatedPassword;

    RegisterController controller = new RegisterController();

    @Override
    public void start(Stage stage) throws Exception {
        URL url = RegisterMenu.class.getResource("/FXML/Register.fxml");
        BorderPane borderPane = FXMLLoader.load(Objects.requireNonNull(url));
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void register(MouseEvent mouseEvent) {

        String username = this.username.getText();
        String password = this.passwordField.getText();
        String passConfirm = this.passConfirmField.getText();
        String email = this.email.getText();
        String nickname = this.nickname.getText();
        String securityQuestion = this.securityQuestion.getValue();
        String securityAnswer = this.securityAnswer.getText();

        String result = controller.register(username, password, passConfirm, email, nickname, securityQuestion, securityAnswer);
        if (result.equals("successful")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Registration Successful");
            alert.showAndWait();
            try {
                (new StartMenu()).start(Main.stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (result.equals("generateRandomPassword")){

            generatedPassword = controller.generateRandomPassword();
            Alert alert  = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Random Password Generated");
            alert.setContentText("Generated Password: " + generatedPassword + "\nDo you want to use this password?");
            ButtonType yesButton = new ButtonType("Yes");
            ButtonType noButton = new ButtonType("No");
            alert.getButtonTypes().setAll(yesButton, noButton);
            alert.showAndWait().ifPresent(response -> {
                if (response == yesButton) {
                    passwordField.setText(generatedPassword);
                    passConfirmField.setText(generatedPassword);
                } else {
                    passwordField.setText("");
                    passConfirmField.setText("");
                }
            });

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Registration Failed");
            alert.setContentText(result);
            alert.showAndWait();
        }
    }

    public void back(MouseEvent mouseEvent) throws Exception {
        new StartMenu().start(Main.stage);
    }
}