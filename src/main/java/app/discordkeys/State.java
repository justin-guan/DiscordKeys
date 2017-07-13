package app.discordkeys;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by justin on 7/13/17.
 */
public class State {

    public static void saveKeybinds(GlobalKeyListener globalKeyListener) throws IOException {
        File settingsDirectory = new File("settings");
        settingsDirectory.mkdirs();

        ObjectMapper mapper = new ObjectMapper();
        String userID = JDAInstance.getJda().getSelfUser().getId();
        List<Keybind> keybinds = new ArrayList<>();

        for (Shortcut s : globalKeyListener.getShortcuts()) {
            Keybind k = new Keybind(s);
            keybinds.add(k);
        }

        mapper.writeValue(new File("settings/" + userID + ".json"), keybinds);
    }

    public static GlobalKeyListener loadKeybinds() throws IOException {
        File settingsDirectory = new File("settings");
        settingsDirectory.mkdirs();

        ObjectMapper mapper = new ObjectMapper();
        String userID = JDAInstance.getJda().getSelfUser().getId();

        Keybind[] keybinds = mapper.readValue(new File("settings/" + userID + ".json"), Keybind[].class);

        GlobalKeyListener globalKeyListener = new GlobalKeyListener();
        for (Keybind k : keybinds) {
            Shortcut s = new Shortcut(k);
            globalKeyListener.addShortcut(s);
        }

        return globalKeyListener;
    }

}
