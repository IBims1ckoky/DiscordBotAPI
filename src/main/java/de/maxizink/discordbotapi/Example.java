package de.maxizink.discordbotapi;

import de.maxizink.discordbotapi.command.ExampleChooseEmbedCommand;
import de.maxizink.discordbotapi.command.ExampleEmbedPageCommand;
import de.maxizink.discordbotapi.command.PurgeCommand;
import de.maxizink.discordbotapi.listener.ChatMessageEvent;
import de.maxizink.discordbotapi.schedulars.ExampleAsyncTimeScheduler;
import de.maxizink.discordbotapi.schedulars.ExampleSyncTimeScheduler;
import de.maxizink.discordbotapi.utils.Logger;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

@RequiredArgsConstructor
public class Example {

  /*
  public static void main(final String[] args) {
    System.out.println("DiscordBotAPI Example - by @IBims1ckoky | Maxi Zink");

    DiscordBot discordBot = null;
    try {
      discordBot = new DiscordBot("token");
    } catch (LoginException | InterruptedException e) {
      Logger.ERROR("Error while starting the Service!");
      e.printStackTrace();
    } finally {
      discordBot.setActivity(Activity.playing("here"));
      discordBot.setStatus(OnlineStatus.DO_NOT_DISTURB);

      discordBot.registerCommand(new ExampleEmbedPageCommand(discordBot));
      discordBot.registerCommand(new ExampleChooseEmbedCommand(discordBot));
      discordBot.registerCommand(new PurgeCommand());

      discordBot.registerEvent(new ChatMessageEvent());

      discordBot.registerTimeScheduler(new ExampleAsyncTimeScheduler());
      discordBot.registerTimeScheduler(new ExampleSyncTimeScheduler());

      discordBot.startTimeSchedulers();
    }
  }

   */
}
