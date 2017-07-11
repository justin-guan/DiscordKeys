package app.discordkeys;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.dv8tion.jda.core.JDA;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

/**
 * Created by justin on 6/22/17.
 */
public class DiscordKeys extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/app/discordkeys/view/DiscordKeysLogin.fxml"));
        primaryStage.setTitle("DiscordKeys");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() {
        System.exit(0);
    }
}
