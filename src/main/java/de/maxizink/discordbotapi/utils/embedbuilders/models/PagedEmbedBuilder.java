package de.maxizink.discordbotapi.utils.embedbuilders.models;

import de.maxizink.discordbotapi.DiscordBot;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;

/**
 * An Embed Message with selectable Pages per Reactions
 */
public class PagedEmbedBuilder extends ListenerAdapter {

  public PagedEmbedBuilder(final List<String> allowedUsers, final TextChannel textChannel,
                           final DiscordBot discordBot) {
    this.textChannel = textChannel;
    discordBot.getJDA().addEventListener(this);
    this.allowedUsers = allowedUsers;
  }

  private final List<String> allowedUsers;
  private final TextChannel textChannel;
  private LinkedList<DefaultEmbedBuilder> embedPages = new LinkedList<>();
  private int current;

  private Message currentMessage;

  public void sendFirstPage() {
    current = 0;
    textChannel.sendMessage(embedPages.get(0).build()).queue(message -> {
      message.editMessage(message).queue();
      currentMessage = message;
      message.addReaction("U+25C0").queue();
      message.addReaction("U+25B6").queue();
    });
  }

  public void addPage(final DefaultEmbedBuilder defaultEmbedBuilder) {
    embedPages.add(defaultEmbedBuilder);
  }

  public void addPage(final String title, final String underTitle, final String text) {
    DefaultEmbedBuilder defaultEmbedBuilder = new DefaultEmbedBuilder(title);
    defaultEmbedBuilder.addField(underTitle, text);
    embedPages.add(defaultEmbedBuilder);
  }

  public void sendBefore() {
    if (current == 0) {
      current = embedPages.size() - 1;
    } else {
      current--;
    }
    currentMessage.editMessage(embedPages.get(current).build()).queue(message -> currentMessage = message);
  }

  public void sendNext() {
    if (embedPages.size() == current + 1) {
      current = 0;
    } else {
      current++;
    }
    currentMessage.editMessage(embedPages.get(current).build()).queue(message -> {
      currentMessage = message;
    });
  }


  @Override
  public void onGuildMessageReactionAdd(@NotNull GuildMessageReactionAddEvent event) {
    if (!event.getMessageId().equals(currentMessage.getId())) {
      return;
    }

    if (event.getMember().getUser().isBot()) {
      return;
    }

    if (!allowedUsers.contains(event.getUser().getId())) {
      event.getReaction().removeReaction(event.getUser()).queue();
      return;
    }

    if (event.getReactionEmote().getName().equals("\u25C0")) {
      sendBefore();
      event.getReaction().removeReaction(event.getUser()).queue();
      return;
    }

    if (event.getReactionEmote().getName().equals("\u25B6")) {
      sendNext();
      event.getReaction().removeReaction(event.getUser()).queue();
    }
  }
}