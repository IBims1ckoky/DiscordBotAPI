package de.maxizink.discordbotapi;

import de.maxizink.discordbotapi.command.core.Command;
import de.maxizink.discordbotapi.command.core.CommandManager;
import de.maxizink.discordbotapi.listener.core.EventListener;
import de.maxizink.discordbotapi.listener.core.EventManager;
import de.maxizink.discordbotapi.schedulars.core.SchedulerManager;
import de.maxizink.discordbotapi.schedulars.core.TimeScheduler;
import lombok.Getter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DiscordBot {

  @Getter
  @Deprecated
  private JDA JDA;

  private CommandManager commandManager;
  private EventManager eventManager;
  private SchedulerManager schedulerManager;

  public DiscordBot(final String token) throws LoginException, InterruptedException {
    discordBotAwake(token);

    commandManager = new CommandManager(this);
    eventManager = new EventManager(this);
    schedulerManager = new SchedulerManager();
  }

  public void discordBotAwake(final String token) throws LoginException, InterruptedException {
    this.JDA = JDABuilder.create(token, getGatewayIntents())
        .enableCache(getCacheFlags())
        .setActivity(Activity.playing("here"))
        .setStatus(OnlineStatus.DO_NOT_DISTURB)
        .setAutoReconnect(true)
        .build().awaitReady();

    Runtime.getRuntime().addShutdownHook(new ShutDownHook());
  }

  /**
   * Presence of the Bot.
   *
   * @Method setActivity sets the Activity{@link Activity} of the Bot
   * @Method setStatus sets the Status{@link OnlineStatus} of the Bot
   */
  public void setStatus(final OnlineStatus onlineStatus) {
    JDA.getPresence().setStatus(onlineStatus);
  }

  public void setActivity(final Activity activity) {
    JDA.getPresence().setActivity(activity);
  }

  /**
   * Registering Stuff
   *
   * @Method registerCommand register a {@link Command}
   * @Method registerEvent register a {@link EventListener}
   * @Method registerTimeScheduler register a {@link TimeScheduler}
   */
  public void registerCommand(final Command command) {
    commandManager.registerCommand(command);
  }

  public void registerEvent(final EventListener eventListener) {
    eventManager.registerEvent(eventListener);
  }

  public void registerTimeScheduler(final TimeScheduler timeScheduler) {
    schedulerManager.registerTimeSchedular(timeScheduler);
  }

  public void startTimeSchedulers() {
    schedulerManager.startAll();
  }

  /**
   * JDA Api Methods
   *
   * @Methods replacing jda.getXXX
   */
  public TextChannel getTextChannelById(final String id) {
    return getJDA().getTextChannelById(id);
  }

  public VoiceChannel getVoiceChannelById(final String id) {
    return getJDA().getVoiceChannelById(id);
  }

  public Guild getGuildById(final String id) {
    return getJDA().getGuildById(id);
  }

  public Role getRoleById(final String id) {
    return getJDA().getRoleById(id);
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
    cacheFlags.add(CacheFlag.ACTIVITY);
    cacheFlags.add(CacheFlag.CLIENT_STATUS);
    cacheFlags.add(CacheFlag.MEMBER_OVERRIDES);
    return cacheFlags;
  }

  private class ShutDownHook extends Thread {

    @Override
    public void run() {
      shutdownAll();
    }

    public synchronized void shutdownAll() {
      schedulerManager.stopAll();

      getJDA().shutdown();
    }
  }
}