package de.maxizink.discordbotapi;

import de.maxizink.discordbotapi.utils.Logger;
import lombok.Getter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DiscordBot {

  @Getter
  private JDA jda = null;

  public DiscordBot(final String token) {
    try {
      this.jda = JDABuilder.create(token, getGatewayIntents()).enableCache(getCacheFlags())
          .setActivity(Activity.playing("here"))
          .setStatus(OnlineStatus.ONLINE).setAutoReconnect(true).build().awaitReady();
    } catch (LoginException | InterruptedException e) {
      Logger.ERROR("Error while starting the Bot");
      e.printStackTrace();
    }
  }

  private Collection<GatewayIntent> getGatewayIntents() {
    List<GatewayIntent> intents = new ArrayList<>();
    intents.add(GatewayIntent.GUILD_MEMBERS);
    intents.add(GatewayIntent.GUILD_MESSAGES);
    intents.add(GatewayIntent.GUILD_MESSAGE_TYPING);
    intents.add(GatewayIntent.DIRECT_MESSAGES);
    intents.add(GatewayIntent.DIRECT_MESSAGE_TYPING);
    intents.add(GatewayIntent.GUILD_PRESENCES);
    intents.add(GatewayIntent.GUILD_VOICE_STATES);
    intents.add(GatewayIntent.GUILD_EMOJIS);
    intents.add(GatewayIntent.GUILD_MESSAGE_REACTIONS);
    return intents;
  }

  private Collection<CacheFlag> getCacheFlags() {
    List<CacheFlag> cacheFlags = new ArrayList<>();
    cacheFlags.add(CacheFlag.EMOTE);
    cacheFlags.add(CacheFlag.VOICE_STATE);
    return cacheFlags;
  }

  public void setActivity(final Activity activity) {
    jda.getPresence().setActivity(activity);
  }

  public void setStatus(final OnlineStatus onlineStatus) {
    jda.getPresence().setStatus(onlineStatus);
  }

}
