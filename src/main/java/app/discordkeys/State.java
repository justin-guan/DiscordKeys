package app.discordkeys;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

/**
 * Created by justin on 7/13/17.
 */
public class State {

    public static void save(GlobalKeyListener globalKeyListener, String saveFile) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(saveFile), globalKeyListener.getShortcuts());
    }

}
