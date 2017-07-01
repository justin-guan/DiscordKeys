package app.discordkeys.controller;

import app.discordkeys.GlobalKeyListener;
import app.discordkeys.JDAInstance;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.stage.WindowEvent;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by justin on 6/22/17.
 */
public class DiscordKeysLoginController implements Initializable {

    @FXML
    private TextField token;

    @FXML
    private Label status;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void login(ActionEvent actionEvent) {
        try {
            JDAInstance.login(token.getText());
            status.setText("Connected to Discord");
            status.setTextFill(Color.GREEN);

            Parent root = FXMLLoader.load(getClass().getResource("/app/discordkeys/view/DiscordKeysHotkeyBinder.fxml"));
            Stage stage = new Stage();
            stage.setTitle("DiscordKeys Hotkey Binder");
            stage.setScene(new Scene(root));
            stage.setOnCloseRequest(event -> Platform.runLater(this::cleanUp));
            stage.show();
        } catch (LoginException e) {
            status.setText("Failed to Login: Login Failure");
        } catch (InterruptedException e) {
            status.setText("Failed to Login: Login Interrupted");
        } catch (RateLimitedException e) {
            status.setText("Failed to Login: Rate Limited");
        } catch (IOException e) {
            e.printStackTrace();
        }

        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
    }

    private void cleanUp() {
        JDA jda = JDAInstance.getJda();
        if (jda != null) {
            JDAInstance.getJda().shutdown(true);
        }

//        try {
//            GlobalScreen.unregisterNativeHook();
//        } catch (NativeHookException e) {
//            System.exit(1);
//        }
    }
}
