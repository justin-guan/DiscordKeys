package app.discordkeys.controller;

import app.discordkeys.GlobalKeyListener;
import app.discordkeys.JDAInstance;
import app.discordkeys.Keybind;
import app.discordkeys.Shortcut;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.TextChannel;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by justin on 6/28/17.
 */
public class DiscordKeysHotkeyBinder implements Initializable {

    @FXML
    private TableView<Keybind> commandTable;
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
        globalKeyListener = new GlobalKeyListener();

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
        ObservableList<Keybind> data = commandTable.getItems();
        Shortcut s = new Shortcut(newShortcut.getText(), command.getText(), servers.getValue(), channels.getValue());
        Keybind k = new Keybind(s.getShortcut(), s.getCommand(), s.getServer(), s.getChannel());

        data.add(k);
        globalKeyListener.addNewShortcut(s);
    }
}
