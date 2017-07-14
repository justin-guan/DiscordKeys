package app.discordkeys.controller;

import app.discordkeys.JDAInstance;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

import javax.crypto.SecretKey;
import javax.security.auth.login.LoginException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

/**
 * Created by justin on 6/22/17.
 */
public class DiscordKeysLoginController implements Initializable {

    @FXML
    private TextField textFieldToken;

    @FXML
    private Label labelStatus;

    @FXML
    private Button buttonLogin;

    private Preferences preferences;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        preferences = Preferences.userRoot().node("DiscordKeys");
    }

    public void login(ActionEvent actionEvent) {
        try {
            attemptLogin(textFieldToken.getText());
            preferences.put("Discord Key", textFieldToken.getText());
            loadKeyBinder();

            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        } catch (LoginException e) {
            labelStatus.setText("Failed to Login: Login Failure");
            setDisconnectedStatus();
        } catch (InterruptedException e) {
            labelStatus.setText("Failed to Login: Login Interrupted");
            setDisconnectedStatus();
        } catch (RateLimitedException e) {
            labelStatus.setText("Failed to Login: Rate Limited");
            setDisconnectedStatus();
        } catch (IOException e) {
            labelStatus.setText("Unknown error");
        }
    }

    private void attemptLogin(String token) throws LoginException, InterruptedException, RateLimitedException {
        JDAInstance.login(token);
        setConnectedStatus();
    }

    private void setConnectedStatus() {
        labelStatus.setText("Connected to Discord");
        labelStatus.setTextFill(Color.GREEN);
        textFieldToken.setDisable(true);
        buttonLogin.setDisable(true);
    }

    private void setDisconnectedStatus() {
        labelStatus.setText("Disconnected from Discord");
        labelStatus.setTextFill(Color.CRIMSON);
        textFieldToken.setDisable(false);
        buttonLogin.setDisable(false);
    }

    private void loadKeyBinder() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/app/discordkeys/view/DiscordKeysHotkeyBinder.fxml"));
        Stage stage = new Stage();
        stage.setTitle("DiscordKeys Hotkey Binder");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
