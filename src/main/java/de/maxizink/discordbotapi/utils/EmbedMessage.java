package de.maxizink.discordbotapi.utils;

import net.dv8tion.jda.api.entities.MessageEmbed;

public abstract class EmbedMessage {

  public abstract EmbedMessage addField(final String name, final String text);

  public abstract MessageEmbed build();

}
