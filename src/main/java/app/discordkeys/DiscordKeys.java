package app.discordkeys;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import javax.security.auth.login.LoginException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;

/**
 * Created by justin on 6/22/17.
 */
public class DiscordKeys extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Preferences preferences = Preferences.userRoot().node("DiscordKeys");

        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);

        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            System.exit(1);
        }

        try {
            JDAInstance.login(preferences.get("Discord Key", null));
            Parent root = FXMLLoader.load(getClass().getResource("/app/discordkeys/view/DiscordKeysHotkeyBinder.fxml"));
            primaryStage.setTitle("DiscordKeys Hotkey Binder");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (LoginException | RateLimitedException | InterruptedException e) {
            Parent root = FXMLLoader.load(getClass().getResource("/app/discordkeys/view/DiscordKeysLogin.fxml"));
            primaryStage.setTitle("DiscordKeys");
            primaryStage.setScene(new Scene(root));
            primaryStage.setResizable(false);
            primaryStage.show();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() {
        JDA jda = JDAInstance.getJda();
        if (jda != null) {
            JDAInstance.getJda().shutdownNow();
        }

        System.exit(0); // Force unregister native hook
    }
}
