package de.maxizink.discordbotapi.command;

import de.maxizink.discordbotapi.DiscordBot;
import de.maxizink.discordbotapi.command.core.Command;
import de.maxizink.discordbotapi.utils.embedbuilders.models.PagedEmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExampleEmbedPageCommand extends Command {

  private final DiscordBot discordBot;

  public ExampleEmbedPageCommand(List<String> permissions, final DiscordBot discordBot) {
    super(permissions);
    this.discordBot = discordBot;
  }

  @Override
  public String getName() {
    return "embedpage";
  }

  @Override
  public String getDescription() {
    return "Sends you and example message";
  }

  @Override
  public List<String> getListeningChannels() {
    return new ArrayList<>();
  }

  @Override
  public boolean onCommandSend(final GuildMessageReceivedEvent event, final String[] args) {
    PagedEmbedBuilder pagedEmbedBuilder = new PagedEmbedBuilder(Arrays.asList(event.getAuthor().getId()),event.getChannel(), discordBot);
    pagedEmbedBuilder.addPage("Ich bin ein Titel", "Ich bin ein UnterTitel", "Ich bin ein Text");
    pagedEmbedBuilder.addPage("Hallo", "Ich bin eine Seite", "Cool oder");
    pagedEmbedBuilder.addPage("Ich bin auch eine Seite", "Moin", "Servus");
    pagedEmbedBuilder.sendFirstPage();
    return false;
  }
}
