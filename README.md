# DiscordKeys
DiscordKeys is a hotkey application for Discord bots. It's purpose is to allow hotkey combinations to send messages to a channel. These messages can invoke commands or simply send prerecorded messages with a hotkey combination.

## Running DiscordKeys
### Finding the User Token
1. Go to <a href=https://discordapp.com/channels/@me>the discord web client</a>
2. On Chrome, press `Ctrl + Shift + i` to open the developer tools
3. Navigate to the Application tab
4. Select Storage on the left side, and click on Discord
5. The User Token is listed as "Token"

## Building DiscordKeys
DiscordKeys can be built using Gradle. Simply type `gradle jfxJar` to build a Jar. `gradle jfxNative` will build a native installer. The system that this command is being run from must meet the requirements listed <a href=https://github.com/FibreFoX/javafx-gradle-plugin#os-specific-requirements>here</a>.