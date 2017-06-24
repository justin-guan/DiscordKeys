package app.discordkeys.controller;

import app.discordkeys.GlobalKeyListener;
import app.discordkeys.JDAInstance;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import javax.security.auth.login.LoginException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by justin on 6/22/17.
 */
public class DiscordKeysController implements Initializable {

    @FXML
    private TextField token;

    @FXML
    private Button login;

    @FXML
    private Label status;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void login(ActionEvent actionEvent) {
        try {
            token.setDisable(true);
            login.setDisable(true);
            JDAInstance.login(token.getText());
            status.setText("Connected to Discord");
            status.setTextFill(Color.GREEN);
            GlobalScreen.registerNativeHook();
            GlobalScreen.addNativeKeyListener(new GlobalKeyListener());
        } catch (LoginException | InterruptedException | RateLimitedException e) {
            System.out.println("Failed to login");
            token.setDisable(false);
            login.setDisable(false);
        } catch (NativeHookException e) {
            System.out.println("Failed to register native hook");
        }
    }
}
