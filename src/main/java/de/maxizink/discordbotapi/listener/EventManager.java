package de.maxizink.discordbotapi.listener;

import de.maxizink.discordbotapi.DiscordBot;

public class EventManager {

  private final DiscordBot discordBot;

  public EventManager(DiscordBot discordBot) {
    this.discordBot = discordBot;
  }

  public void registerEvent(final EventListener eventListener) {
    discordBot.getJDA().addEventListener(eventListener);
  }


}