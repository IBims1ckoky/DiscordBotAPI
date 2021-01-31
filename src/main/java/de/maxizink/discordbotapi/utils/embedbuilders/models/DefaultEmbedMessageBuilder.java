package de.maxizink.discordbotapi.utils.embedbuilders.models;

import de.maxizink.discordbotapi.utils.embedbuilders.EmbedMessage;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.Color;

public class DefaultEmbedMessageBuilder extends EmbedMessage {

  private EmbedBuilder embedBuilder;

  public DefaultEmbedMessageBuilder(String title) {
    embedBuilder = new EmbedBuilder();
    embedBuilder.setTitle(title);
  }

  public DefaultEmbedMessageBuilder setTitle(final String title) {
    embedBuilder.setTitle(title);
    return this;
  }

  public DefaultEmbedMessageBuilder setColor(final Color color) {
    embedBuilder.setColor(color);
    return this;
  }

  public DefaultEmbedMessageBuilder setThumbnail(final String url) {
    embedBuilder.setThumbnail(url);
    return this;
  }

  @Override
  public DefaultEmbedMessageBuilder addField(final String underTitle, final String text) {
    embedBuilder.addField(underTitle, text, false);
    return this;
  }

  @Override
  public MessageEmbed build() {
    return embedBuilder.build();
  }
}
