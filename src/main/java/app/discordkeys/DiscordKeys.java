package app.discordkeys;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jdk.nashorn.internal.objects.Global;
import net.dv8tion.jda.core.JDA;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by justin on 6/22/17.
 */
public class DiscordKeys extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/app/discordkeys/view/DiscordKeysLogin.fxml"));
        primaryStage.setTitle("DiscordKeys");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();

        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);

        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() {
        System.exit(0); // Force unregister native hook
    }
}
