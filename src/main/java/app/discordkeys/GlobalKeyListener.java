package app.discordkeys;

import javafx.application.Platform;
import net.dv8tion.jda.core.entities.TextChannel;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.util.HashSet;

/**
 * Created by justin on 6/23/17.
 */
public class GlobalKeyListener implements NativeKeyListener {

    private HashSet<Shortcut> shortcuts;
    private HashSet<Integer> pressedKeys;

    public GlobalKeyListener() {
        shortcuts = new HashSet<>();
        pressedKeys = new HashSet<>();
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
        Platform.runLater(() -> {
            pressedKeys.add(nativeKeyEvent.getKeyCode());
        });
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
        Platform.runLater(() -> {
            System.out.println("Pressed keys: " + pressedKeys.toString());
            for (Shortcut s : shortcuts) {
                System.out.println("Shortcuts: " + s.getShortcutAsNativeKeyEventList());
                if (pressedKeys.containsAll(s.getShortcutAsNativeKeyEventList())) {
                    sendDiscordMessage(s.getChannel(), s.getCommand());
                }
            }
            pressedKeys.remove(nativeKeyEvent.getKeyCode());
        });
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {
        //System.out.println("Key Typed: " + nativeKeyEvent.getKeyText(nativeKeyEvent.getKeyCode()));
    }

    private void sendDiscordMessage(TextChannel t, String msg) {
        t.sendMessage(msg).queue();
    }

    public void addNewShortcut(Shortcut shortcut) {
        shortcuts.add(shortcut);
    }
}
