package app.discordkeys;

import javafx.beans.property.SimpleStringProperty;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.TextChannel;

/**
 * Created by justin on 7/10/17.
 */
public class Keybind {

    private final SimpleStringProperty keybind = new SimpleStringProperty("");
    private final SimpleStringProperty command = new SimpleStringProperty("");
    private final SimpleStringProperty server = new SimpleStringProperty("");
    private final SimpleStringProperty channel = new SimpleStringProperty("");

    public Keybind(String shortcut, String command, Guild server, TextChannel channel) {
        this.keybind.set(shortcut);
        this.command.set(command);
        this.server.set(server.getName());
        this.channel.set(channel.getName());
    }

    public String getKeybind() {
        return keybind.get();
    }

    public void setKeybind(String fKeybind) {
        this.keybind.set(fKeybind);
    }

    public String getCommand() {
        return command.get();
    }

    public void setCommand(String fCommand) {
        this.command.set(fCommand);
    }

    public String getServer() {
        return server.get();
    }

    public void setServer(String fServer) {
        this.server.set(fServer);
    }

    public String getChannel() {
        return channel.get();
    }

    public void setChannel(String fChannel) {
        this.channel.set(fChannel);
    }

}
