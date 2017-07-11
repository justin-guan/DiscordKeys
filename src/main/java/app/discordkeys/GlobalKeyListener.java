package app.discordkeys;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.TextChannel;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.awt.*;
import java.util.HashMap;
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
        pressedKeys.add(nativeKeyEvent.getKeyCode());
//        for (Shortcut s : shortcuts) {
//            for (KeyCode k : s.getShortcut()) {
//                if (pressedKeys.contains(k)) {
//
//                }
//            }
//        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
        pressedKeys.remove(nativeKeyEvent.getKeyCode());
//        boolean isCtrlPressed = (nativeKeyEvent.getModifiers() & NativeKeyEvent.CTRL_MASK) != 0;
//
//        if (nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_F1 && isCtrlPressed) {
//            sendDiscordMessage("!waow");
//        } else if (nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_F2 && isCtrlPressed) {
//            sendDiscordMessage("!ld");
//        } else if (nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_F3 && isCtrlPressed) {
//            sendDiscordMessage("!tucker");
//        } else if (nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_F4 && isCtrlPressed) {
//            sendDiscordMessage("!noone");
//        } else if (nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_F5 && isCtrlPressed) {
//            sendDiscordMessage("!cyka");
//        } else if (nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_F6 && isCtrlPressed) {
//            sendDiscordMessage("!free");
//        } else if (nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_F7 && isCtrlPressed) {
//            sendDiscordMessage("!price");
//        } else if (nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_F8 && isCtrlPressed) {
//            sendDiscordMessage("!maple");
//        }
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {
        //System.out.println("Key Typed: " + nativeKeyEvent.getKeyText(nativeKeyEvent.getKeyCode()));
    }

    private void sendDiscordMessage(String msg) {
        JDA jda = JDAInstance.getJda();
        for (Guild g : jda.getGuilds()) {
            if (g.toString().equals("G:Niggaz In The House(178372543986663425)")) {
                for (TextChannel t : g.getTextChannels()) {
                    if (t.toString().equals("TC:general(178372543986663425)")) {
                        t.sendMessage(msg).queue();
                    }
                }
            }
        }
    }

    public void addNewShortcut(Shortcut shortcut) {
        shortcuts.add(shortcut);
    }
}
