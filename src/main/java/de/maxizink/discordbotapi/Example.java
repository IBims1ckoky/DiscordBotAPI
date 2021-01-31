package de.maxizink.discordbotapi;

import de.maxizink.discordbotapi.command.ExampleEmbedPageCommand;
import de.maxizink.discordbotapi.command.core.CommandManager;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import java.util.ArrayList;

@RequiredArgsConstructor
public class Example {

  public static final String GUILD_ID = "696818081724498040";

  public static void main(final String[] args) {
    System.out.println("DiscordBotAPI - by @IBims1ckoky - Maxi Zink");

    DiscordBot discordBot = new DiscordBot("ODA0NDg1NzMzOTA1NDY1Mzk0.YBNBuQ.WP9QDxzFGScRk2ZosngdAJUlmUc");
    discordBot.setActivity(Activity.playing("hier"));
    discordBot.setStatus(OnlineStatus.DO_NOT_DISTURB);

    CommandManager commandManager = new CommandManager(discordBot.getJda());
    commandManager.registerCommand(new ExampleEmbedPageCommand(new ArrayList<>(), discordBot));
  }
}
