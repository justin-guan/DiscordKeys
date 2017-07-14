package app.discordkeys.controller;

import app.discordkeys.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.TextChannel;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import java.io.*;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

/**
 * Created by justin on 6/28/17.
 */
public class DiscordKeysHotkeyBinder implements Initializable {

    @FXML
    private TableView<Shortcut> commandTable;
    @FXML
    private ToggleButton newShortcut;
    @FXML
    private TextField command;
    @FXML
    public ComboBox<Guild> servers;
    @FXML
    public ComboBox<TextChannel> channels;

    private HashSet<KeyCode> pressedKeys;
    private GlobalKeyListener globalKeyListener;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pressedKeys = new HashSet<>();

        List<Guild> guilds = JDAInstance.getJda().getGuilds();
        servers.setItems(FXCollections.observableArrayList(guilds));
        servers.setCellFactory(param -> new ListCell<Guild>() {
            @Override
            protected void updateItem(Guild guild, boolean empty) {
                super.updateItem(guild, empty);
                if (guild != null) {
                    setText(guild.getName());
                }
            }
        });
        servers.valueProperty().addListener((observable, oldValue, newValue) -> {
            updateChannels();
        });

        try {
            globalKeyListener = State.loadKeybinds();
        } catch (IOException e) {
            globalKeyListener = new GlobalKeyListener();
        }

        commandTable.getItems().addAll(globalKeyListener.getShortcuts());

        GlobalScreen.addNativeKeyListener(globalKeyListener);
    }

    public void registerHotkey(ActionEvent actionEvent) {
        newShortcut.setOnKeyPressed(event -> pressedKeys.add(event.getCode()));

        newShortcut.setOnKeyReleased(event -> {
            if (pressedKeys.contains(event.getCode())) {
                StringBuilder shortcut = new StringBuilder();
                for (KeyCode k : pressedKeys) {
                    shortcut.append(k.toString()).append(" + ");
                }
                shortcut.setLength(shortcut.length() - 3);

                pressedKeys.clear();
                newShortcut.setSelected(false);
                newShortcut.setText(shortcut.toString());
                newShortcut.setOnKeyPressed(null);
                newShortcut.setOnKeyReleased(null);
            }
        });
    }

    private void updateChannels() {
        List<TextChannel> textChannels = servers.getValue().getTextChannels();
        channels.setItems(FXCollections.observableArrayList(textChannels));
        channels.setCellFactory(param -> new ListCell<TextChannel>() {
            @Override
            protected void updateItem(TextChannel channel, boolean empty) {
                super.updateItem(channel, empty);
                if (channel != null) {
                    setText(channel.getName());
                }
            }
        });
    }

    public void addNewShortcut(ActionEvent actionEvent) {
        ObservableList<Shortcut> data = commandTable.getItems();
        Shortcut s = new Shortcut(newShortcut.getText(), command.getText(), servers.getValue(), channels.getValue());

        data.add(s);
        globalKeyListener.addShortcut(s);

        try {
            State.saveKeybinds(globalKeyListener);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeShortcut(ActionEvent actionEvent) {
        Shortcut s = commandTable.getSelectionModel().getSelectedItem();
        commandTable.getItems().remove(s);
        globalKeyListener.removeShortcut(s);

        try {
            State.saveKeybinds(globalKeyListener);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logout(ActionEvent actionEvent) {
        JDA jda = JDAInstance.getJda();
        if (jda != null) {
            JDAInstance.getJda().shutdownNow();
        }

        GlobalScreen.removeNativeKeyListener(globalKeyListener);
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            System.exit(1);
        }
        Preferences preferences = Preferences.userRoot().node("DiscordKeys");
        preferences.remove("Discord Key");

        commandTable.getScene().getWindow().hide();

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/app/discordkeys/view/DiscordKeysLogin.fxml"));
            Stage stage = new Stage();
            stage.setTitle("DiscordKeys");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.exit(1);
        }
    }
}
