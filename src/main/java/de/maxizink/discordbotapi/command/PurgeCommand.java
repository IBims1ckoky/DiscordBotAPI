package de.maxizink.discordbotapi.command;

import de.maxizink.discordbotapi.command.core.Command;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.ArrayList;
import java.util.List;

public class PurgeCommand extends Command {

  @Override
  public String getName() {
    return "purge";
  }

  @Override
  public String getDescription() {
    return "Delete Messages";
  }

  @Override
  public List<String> getPermissionIds() {
    return new ArrayList<>();
  }

  @Override
  public List<String> getListeningChannels() {
    return new ArrayList<>();
  }

  @Override
  public boolean onCommandSend(final GuildMessageReceivedEvent event, final String[] args) {
    int amount = Integer.parseInt(args[0]);

    event.getChannel().getHistory().retrievePast(amount)
        .queue(messages -> messages.forEach(eachMessage -> eachMessage.delete().queue()));
    event.getMessage().delete().queue();

    return false;
  }
}
