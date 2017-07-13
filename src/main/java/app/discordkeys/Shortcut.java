package app.discordkeys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javafx.beans.property.SimpleStringProperty;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.TextChannel;
import org.jnativehook.keyboard.NativeKeyEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by justin on 7/2/17.
 */
public class Shortcut {

    @JsonIgnore
    private List<Integer> shortcutList;
    @JsonIgnore
    private String shortcut;
    @JsonIgnore
    private String command;
    @JsonIgnore
    private Guild server;
    @JsonIgnore
    private TextChannel channel;

    private final SimpleStringProperty keybindProperty = new SimpleStringProperty("");
    private final SimpleStringProperty commandProperty = new SimpleStringProperty("");
    private final SimpleStringProperty serverProperty = new SimpleStringProperty("");
    private final SimpleStringProperty channelProperty = new SimpleStringProperty("");

    public Shortcut(String shortcut, String command, Guild server, TextChannel channel) {
        this.keybindProperty.set(shortcut);
        this.commandProperty.set(command);
        this.serverProperty.set(server.getName());
        this.channelProperty.set(channel.getName());

        this.shortcut = shortcut;
        this.command = command;
        this.server = server;
        this.channel = channel;
        this.shortcutList = toNativeKeyList(shortcut);
    }

    public String getKeybindProperty() {
        return keybindProperty.get();
    }

    public void setKeybindProperty(String fKeybind) {
        this.keybindProperty.set(fKeybind);
    }

    public String getCommandProperty() {
        return commandProperty.get();
    }

    public void setCommandProperty(String fCommand) {
        this.commandProperty.set(fCommand);
    }

    public String getServerProperty() {
        return serverProperty.get();
    }

    public void setServerProperty(String fServer) {
        this.serverProperty.set(fServer);
    }

    public String getChannelProperty() {
        return channelProperty.get();
    }

    public void setChannelProperty(String fChannel) {
        this.channelProperty.set(fChannel);
    }

    public String getShortcut() {
        return shortcut;
    }

    @JsonIgnore
    public List<Integer> getShortcutAsNativeKeyEventList() {
        return shortcutList;
    }

    public void setShortcut(String shortcut) {
        this.shortcut = shortcut;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Guild getServer() {
        return server;
    }

    public void setServer(Guild server) {
        this.server = server;
    }

    public TextChannel getChannel() {
        return channel;
    }

    public void setChannel(TextChannel channel) {
        this.channel = channel;
    }

    private List<Integer> toNativeKeyList(String shortcut) {
        List<Integer> list = new ArrayList<>();
        String[] s = shortcut.split(" \\+ ");
        for (String key : s) {
            switch (key) {
                case "CONTROL":
                    list.add(NativeKeyEvent.VC_CONTROL);
                    break;
                case "SHIFT":
                    list.add(NativeKeyEvent.VC_SHIFT);
                    break;
                case "ALT":
                    list.add(NativeKeyEvent.VC_ALT);
                    break;
                case "F1":
                    list.add(NativeKeyEvent.VC_F1);
                    break;
                case "F2":
                    list.add(NativeKeyEvent.VC_F2);
                    break;
                case "F3":
                    list.add(NativeKeyEvent.VC_F3);
                    break;
                case "F4":
                    list.add(NativeKeyEvent.VC_F4);
                    break;
                case "F5":
                    list.add(NativeKeyEvent.VC_F5);
                    break;
                case "F6":
                    list.add(NativeKeyEvent.VC_F6);
                    break;
                case "F7":
                    list.add(NativeKeyEvent.VC_F7);
                    break;
                case "F8":
                    list.add(NativeKeyEvent.VC_F8);
                    break;
                case "F9":
                    list.add(NativeKeyEvent.VC_F9);
                    break;
                case "F10":
                    list.add(NativeKeyEvent.VC_F10);
                    break;
                case "F11":
                    list.add(NativeKeyEvent.VC_F11);
                    break;
                case "F12":
                    list.add(NativeKeyEvent.VC_F12);
                    break;
                case "A":
                    list.add(NativeKeyEvent.VC_A);
                    break;
                case "B":
                    list.add(NativeKeyEvent.VC_B);
                    break;
                case "C":
                    list.add(NativeKeyEvent.VC_C);
                    break;
                case "D":
                    list.add(NativeKeyEvent.VC_D);
                    break;
                case "E":
                    list.add(NativeKeyEvent.VC_E);
                    break;
                case "F":
                    list.add(NativeKeyEvent.VC_F);
                    break;
                case "G":
                    list.add(NativeKeyEvent.VC_G);
                    break;
                case "H":
                    list.add(NativeKeyEvent.VC_H);
                    break;
                case "I":
                    list.add(NativeKeyEvent.VC_I);
                    break;
                case "J":
                    list.add(NativeKeyEvent.VC_J);
                    break;
                case "K":
                    list.add(NativeKeyEvent.VC_K);
                    break;
                case "L":
                    list.add(NativeKeyEvent.VC_L);
                    break;
                case "M":
                    list.add(NativeKeyEvent.VC_M);
                    break;
                case "N":
                    list.add(NativeKeyEvent.VC_N);
                    break;
                case "O":
                    list.add(NativeKeyEvent.VC_O);
                    break;
                case "P":
                    list.add(NativeKeyEvent.VC_P);
                    break;
                case "Q":
                    list.add(NativeKeyEvent.VC_Q);
                    break;
                case "R":
                    list.add(NativeKeyEvent.VC_R);
                    break;
                case "S":
                    list.add(NativeKeyEvent.VC_S);
                    break;
                case "T":
                    list.add(NativeKeyEvent.VC_T);
                    break;
                case "U":
                    list.add(NativeKeyEvent.VC_U);
                    break;
                case "V":
                    list.add(NativeKeyEvent.VC_V);
                    break;
                case "W":
                    list.add(NativeKeyEvent.VC_W);
                    break;
                case "X":
                    list.add(NativeKeyEvent.VC_X);
                    break;
                case "Y":
                    list.add(NativeKeyEvent.VC_Y);
                    break;
                case "Z":
                    list.add(NativeKeyEvent.VC_Z);
                    break;
                case "INSERT":
                    list.add(NativeKeyEvent.VC_INSERT);
                    break;
                case "DELETE":
                    list.add(NativeKeyEvent.VC_DELETE);
                    break;
                case "HOME":
                    list.add(NativeKeyEvent.VC_HOME);
                    break;
                case "END":
                    list.add(NativeKeyEvent.VC_END);
                    break;
                case "PAGE_UP":
                    list.add(NativeKeyEvent.VC_PAGE_UP);
                    break;
                case "PAGE_DOWN":
                    list.add(NativeKeyEvent.VC_PAGE_DOWN);
                    break;
                case "EQUALS":
                    list.add(NativeKeyEvent.VC_EQUALS);
                    break;
                case "MINUS":
                    list.add(NativeKeyEvent.VC_MINUS);
                    break;
                case "ESCAPE":
                    list.add(NativeKeyEvent.VC_ESCAPE);
                    break;
                case "COMMA":
                    list.add(NativeKeyEvent.VC_COMMA);
                    break;
                case "PERIOD":
                    list.add(NativeKeyEvent.VC_PERIOD);
                    break;
                case "SEMICOLON":
                    list.add(NativeKeyEvent.VC_SEMICOLON);
                    break;
                case "QUOTE":
                    list.add(NativeKeyEvent.VC_QUOTE);
                    break;
                case "SLASH":
                    list.add(NativeKeyEvent.VC_SLASH);
                    break;
                case "BACK_SLASH":
                    list.add(NativeKeyEvent.VC_BACK_SLASH);
                    break;
                case "BACK_QUOTE":
                    list.add(NativeKeyEvent.VC_BACKQUOTE);
                    break;
                case "OPEN_BRACKET":
                    list.add(NativeKeyEvent.VC_OPEN_BRACKET);
                    break;
                case "CLOSE_BRACKET":
                    list.add(NativeKeyEvent.VC_CLOSE_BRACKET);
                    break;
                case "SPACE":
                    list.add(NativeKeyEvent.VC_SPACE);
                    break;
                case "WINDOWS":
                    list.add(NativeKeyEvent.VC_META);
                    break;
                case "CAPS":
                    list.add(NativeKeyEvent.VC_CAPS_LOCK);
                    break;
                case "TAB":
                    list.add(NativeKeyEvent.VC_TAB);
                    break;
                case "BACK_SPACE":
                    list.add(NativeKeyEvent.VC_BACKSPACE);
                    break;
                case "UP":
                    list.add(NativeKeyEvent.VC_UP);
                    break;
                case "DOWN":
                    list.add(NativeKeyEvent.VC_DOWN);
                    break;
                case "LEFT":
                    list.add(NativeKeyEvent.VC_LEFT);
                    break;
                case "RIGHT":
                    list.add(NativeKeyEvent.VC_RIGHT);
                    break;
                case "SCROLL_LOCK":
                    list.add(NativeKeyEvent.VC_SCROLL_LOCK);
                    break;
                case "PAUSE":
                    list.add(NativeKeyEvent.VC_PAUSE);
                    break;
                case "CONTEXT_MENU":
                    list.add(NativeKeyEvent.VC_CONTEXT_MENU);
                    break;
            }
        }
        return list;
    }
}
