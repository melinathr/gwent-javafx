package view;

import controller.RegisterController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Player;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class ProfileMenu extends Application {

    public TextField usernameField;
    public TextField newEmailField;
    public TextField newNickNameField;
    public TextField oldPasswordField;
    public TextField newPasswordField;
    RegisterController controller = new RegisterController();

    public void start(Stage stage) throws IOException {
        URL url = ProfileMenu.class.getResource("/FXML/Profile.fxml");
        BorderPane pane = FXMLLoader.load(Objects.requireNonNull(url));
        stage.setScene(new Scene(pane));
        stage.show();
    }

    public void handleChangeUsername(MouseEvent mouseEvent) {
        if (usernameField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("username is empty");
            alert.showAndWait();

        } else if (Player.getLoggedInUser().getUsername().equals(usernameField.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("new username is the same as old username");
            alert.showAndWait();

        } else if (!controller.isUsernameValid(usernameField.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("username is not valid");
            alert.showAndWait();
        }else {
            Player.getLoggedInUser().setUsername(usernameField.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("username changed successfully");
            alert.showAndWait();
        }

    }

    public void handleChangeEmail(MouseEvent mouseEvent) {
        if (newEmailField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("email is empty");
            alert.showAndWait();
        } else if (!controller.isEmailValid(newEmailField.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("email is not valid");
            alert.showAndWait();
        } else if (Player.getLoggedInUser().getEmail().equals(newEmailField.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("new email is the same as old email");
            alert.showAndWait();
        } else {
            Player.getLoggedInUser().setEmail(newEmailField.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("email changed successfully");
            alert.showAndWait();
        }
    }

    public void handleChangeNickName(MouseEvent mouseEvent) {
        if (newNickNameField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("nickname is empty");
            alert.showAndWait();
        } else if (!controller.isNicknameValid(newNickNameField.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("nickname is not valid");
            alert.showAndWait();
        } else if (Player.getLoggedInUser().getNickname().equals(newNickNameField.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("new nickname is the same as old nickname");
            alert.showAndWait();
        } else {
            Player.getLoggedInUser().setNickname(newNickNameField.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("nickname changed successfully");
            alert.showAndWait();
        }
    }

    public void handleChangePassword(MouseEvent mouseEvent) {
        if (oldPasswordField.getText().isEmpty() || newPasswordField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("password is empty");
            alert.showAndWait();
        } else if (!Player.getLoggedInUser().getPassword().equals(oldPasswordField.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("old password is not correct");
            alert.showAndWait();
        } else if (newPasswordField.getText().equals(oldPasswordField.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("new password is the same as old password");
            alert.showAndWait();

        } else if (!controller.isPasswordValid(newPasswordField.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("password is not valid");
            alert.showAndWait();
        } else {
            Player.getLoggedInUser().setPassword(newPasswordField.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("password changed successfully");
            alert.showAndWait();
        }
    }

    public void showUsersInfo(MouseEvent mouseEvent) throws Exception {
        new UsersInfo().start(Main.stage);
    }

    public void backtoMainMenu(MouseEvent mouseEvent) throws Exception {
        new MainMenu().start(Main.stage);
    }

    public void gamehistory(MouseEvent mouseEvent) throws Exception{
        new GameHistory().start(Main.stage);
    }
}
