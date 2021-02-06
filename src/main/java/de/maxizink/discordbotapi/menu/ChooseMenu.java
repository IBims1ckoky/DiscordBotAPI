package de.maxizink.discordbotapi.menu;

import de.maxizink.discordbotapi.DiscordBot;
import de.maxizink.discordbotapi.menu.utils.NumberEmojis;
import de.maxizink.discordbotapi.utils.BotEmbedBuilder;
import lombok.Getter;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * An Embed Message with Reactions to Choose something
 *
 * @param <T> ist the Type which you can Choose (BackEnd)
 */
public class ChooseMenu<T> extends ListenerAdapter {

  private final List<T> chooseOptions;
  private final HashMap<T, String> displayMessages = new LinkedHashMap<>();
  private final String[] allowedUsers;
  private final DiscordBot discordBot;

  @Getter
  /**
   * @param choosed use it only in the runnable in the Method addActionAfterChoose because its null before.
   */
  private T choosed;
  @Getter
  /**
   * @param choosedTranslation is the String/Text from the ChooseOption
   */
  private String choosedTranslation;
  private Runnable afterChoosingRunnable;
  private Message message;

  public ChooseMenu(final DiscordBot discordBot, final String... allowedUserIds) {
    this.chooseOptions = new LinkedList<>();
    this.allowedUsers = allowedUserIds;
    this.discordBot = discordBot;
    discordBot.getJDA().addEventListener(this);
  }

  public void addChooseOption(final T t, final String displayMessage) {
    chooseOptions.add(t);
    displayMessages.put(t, displayMessage);
  }

  public void send(final TextChannel textChannel, final String title, final String underTitle) {
    BotEmbedBuilder defaultEmbedBuilder = new BotEmbedBuilder(title);
    StringBuilder stringBuilder = new StringBuilder();
    int i = 0;
    for (final T t : chooseOptions) {
      i++;
      stringBuilder.append(NumberEmojis.getEmojiByNumber(i).getName())
          .append(" ")
          .append(displayMessages.get(t))
          .append("\n");
    }
    defaultEmbedBuilder.addField(underTitle, stringBuilder.toString());
    textChannel.sendMessage(defaultEmbedBuilder.build()).queue(message1 -> {
      message = message1;
      for (int j = 1; j < chooseOptions.size() + 1; j++) {
        message1.addReaction(NumberEmojis.getEmojiByNumber(j).getUniCode()).queue();
      }
    });
  }

  public void addActionAfterChoose(final Runnable runnable) {
    this.afterChoosingRunnable = runnable;
  }

  private void run() {
    afterChoosingRunnable.run();
  }

  @Override
  public void onGuildMessageReactionAdd(@NotNull GuildMessageReactionAddEvent event) {
    if (!event.getMessageId().equals(message.getId())) {
      return;
    }

    if (event.getUser().isBot()) {
      return;
    }

    if (Arrays.stream(allowedUsers).noneMatch(s -> s.equals(event.getUser().getId()))) {
      event.getReaction().removeReaction(event.getUser()).queue();
      return;
    }

    NumberEmojis numberEmojis = NumberEmojis.getEmojiByUniCode(event.getReactionEmote().getName());
    choosed = chooseOptions.get(numberEmojis.getNumber() - 1);
    choosedTranslation = displayMessages.get(choosed);
    message.delete().queue();
    run();
  }
}