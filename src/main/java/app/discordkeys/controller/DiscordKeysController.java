package app.discordkeys.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

import javax.security.auth.login.LoginException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by justin on 6/22/17.
 */
public class DiscordKeysController implements Initializable {

    @FXML
    TextField token;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void login(ActionEvent actionEvent) {
        JDA jda;
        try {
            jda = new JDABuilder(AccountType.CLIENT)
                    .setToken(token.getText())
                    .setAutoReconnect(true)
                    .buildBlocking();
            System.out.println(jda.asClient().getFriends());
        } catch (LoginException | InterruptedException | RateLimitedException e) {
            e.printStackTrace();
        }
    }
}
