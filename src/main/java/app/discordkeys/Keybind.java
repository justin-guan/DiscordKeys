package app.discordkeys;

/**
 * Created by justin on 7/13/17.
 */
public class Keybind {

    private String keyCombination;
    private String command;
    private String serverID;
    private String channelID;

    public Keybind() {}

    public Keybind (Shortcut shortcut) {
        this.keyCombination = shortcut.getShortcut();
        this.command = shortcut.getCommand();
        this.serverID = shortcut.getServer().getId();
        this.channelID = shortcut.getChannel().getId();
    }

    public Keybind(String keyCombination, String command, String serverID, String channelID) {
        this.keyCombination = keyCombination;
        this.command = command;
        this.serverID = serverID;
        this.channelID = channelID;
    }

    public String getKeyCombination() {
        return keyCombination;
    }

    public void setKeyCombination(String keyCombination) {
        this.keyCombination = keyCombination;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getServerID() {
        return serverID;
    }

    public void setServerID(String serverID) {
        this.serverID = serverID;
    }

    public String getChannelID() {
        return channelID;
    }

    public void setChannelID(String channelID) {
        this.channelID = channelID;
    }
}
