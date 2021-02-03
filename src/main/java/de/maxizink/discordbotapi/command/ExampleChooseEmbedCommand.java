package de.maxizink.discordbotapi.command;

import de.maxizink.discordbotapi.DiscordBot;
import de.maxizink.discordbotapi.command.core.Command;
import de.maxizink.discordbotapi.utils.embedbuilders.models.ChooseEmbedBuilder;
import de.maxizink.discordbotapi.utils.embedbuilders.models.DefaultEmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class ExampleChooseEmbedCommand extends Command {

  private final DiscordBot discordBot;

  public ExampleChooseEmbedCommand(final DiscordBot discordBot) {
    this.discordBot = discordBot;
  }

  @Override
  public String getName() {
    return "embedchoose";
  }

  @Override
  public String getDescription() {
    return "An Example for the ChooseEmbedBuilder";
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
    ChooseEmbedBuilder<Integer> chooseEmbedBuilder = new ChooseEmbedBuilder<>(discordBot, event.getAuthor().getId());
    chooseEmbedBuilder.addChooseOption(5, "Ich bin die Zahl 5 und bin cool!");
    chooseEmbedBuilder.addChooseOption(10, "Ich bin die Zahl 10 und bin cooler!");
    chooseEmbedBuilder.addChooseOption(15, "Ich bin die Zahl 15 und bin am coolesten!");
    chooseEmbedBuilder.send(event.getChannel(), "Ich bin ein Titel", "Ich bin der Unter-Titel");

    chooseEmbedBuilder.addActionAfterChoose(() -> {
      DefaultEmbedBuilder defaultEmbedBuilder = new DefaultEmbedBuilder("Es wurde eine entscheidung getroffen");
      defaultEmbedBuilder.addField("Du hast ausgewählt:", chooseEmbedBuilder.getChoosedTranslation());
      defaultEmbedBuilder.setColor(Color.GREEN);
      event.getChannel().sendMessage(defaultEmbedBuilder.build()).queue();

      System.out.println("Your Object to Code is " + chooseEmbedBuilder.getChoosed());
    });
    return false;
  }
}
