package app.discordkeys;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

import javax.security.auth.login.LoginException;

/**
 * Created by justin on 6/23/17.
 */
public class JDAInstance {

    private static JDA jda;

    public static void login(String token) throws LoginException, InterruptedException, RateLimitedException {
        jda = new JDABuilder(AccountType.CLIENT)
                .setToken(token)
                .setAutoReconnect(true)
                .buildBlocking();
    }

    static JDA getJda() {
        return jda;
    }

}
