package de.maxizink.discordbotapi.utils;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.Color;


/**
 * A Default EmbedBuilder with a Title and Fields
 */
public class BotEmbedBuilder extends EmbedMessage {

  private EmbedBuilder embedBuilder;

  public BotEmbedBuilder(String title) {
    embedBuilder = new EmbedBuilder();
    embedBuilder.setTitle(title);
  }

  public BotEmbedBuilder setTitle(final String title) {
    embedBuilder.setTitle(title);
    return this;
  }

  public BotEmbedBuilder setColor(final Color color) {
    embedBuilder.setColor(color);
    return this;
  }

  public BotEmbedBuilder setThumbnail(final String url) {
    embedBuilder.setThumbnail(url);
    return this;
  }

  @Override
  public BotEmbedBuilder addField(final String underTitle, final String text) {
    embedBuilder.addField(underTitle, text, false);
    return this;
  }

  @Override
  public MessageEmbed build() {
    return embedBuilder.build();
  }
}
