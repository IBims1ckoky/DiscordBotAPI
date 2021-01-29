package de.maxizink.discordbotapi.command;

import de.maxizink.discordbotapi.command.models.Command;
import de.maxizink.discordbotapi.utils.embedbuilders.models.DefaultEmbedMessageBuilder;
import lombok.Setter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CommandManager extends ListenerAdapter {

  private final List<Command> commands = new ArrayList<>();

  @Setter
  private String commandPrefix = "!";
  @Setter
  private String noPermissionTitle = "Keine Rechte!";

  public CommandManager(final JDA jda) {
    jda.addEventListener(this);
  }

  public void registerCommand(final Command command) {
    commands.add(command);
  }

  public void handleMessageSent(final GuildMessageReceivedEvent guildMessageReceivedEvent, final String line,
                                final Member sender, final TextChannel channel) {
    if (!line.isEmpty() && Character.toString(line.charAt(0)).equals(commandPrefix)) {

      String[] message = line.split(" ");
      String command = message[0];
      final String commandName = command.replace(commandPrefix, "");
      String[] args = new String[] {};

      if (!line.replace(command, "").isEmpty()) {
        args = line.replace(command, "").substring(1).split(" ");
      }

      for (Command commands : commands) {
        if (command.startsWith(commandPrefix) && commands.getName().equalsIgnoreCase(commandName)) {

          if (!commands.hasPermission(sender)) {
            if (commands.getListeningChannels() == null) {
              sendNoPermission(sender, channel);
              return;
            }
            if (commands.getListeningChannels() != null &&
                commands.getListeningChannels().contains(channel.getId())) {
              sendNoPermission(sender, channel);
              return;
            }
            sendNoPermission(sender, channel);
            return;
          }

          if (commands.getListeningChannels().isEmpty()) {
            commands.onCommandSend(guildMessageReceivedEvent, args);
            return;
          }

          if (commands.getListeningChannels() != null &&
              commands.getListeningChannels().contains(channel.getId())) {
            commands.onCommandSend(guildMessageReceivedEvent, args);
            return;
          }
          return;
        }
      }
    }
  }

  @Override
  public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
    if (event.getAuthor().isBot()) {
      return;
    }

    handleMessageSent(event, event.getMessage().getContentRaw().trim(), event.getMember(), event.getChannel());
  }

  private void sendNoPermission(final Member member, final TextChannel textChannel) {
    final DefaultEmbedMessageBuilder defaultEmbedMessageBuilder = new DefaultEmbedMessageBuilder(noPermissionTitle);
    defaultEmbedMessageBuilder.setColor(Color.RED);
    defaultEmbedMessageBuilder.setThumbnail(member.getUser().getAvatarUrl());
    textChannel.sendMessage(defaultEmbedMessageBuilder.build())
        .complete().delete().queueAfter(10, TimeUnit.SECONDS);
  }

}
