package de.maxizink.discordbotapi.command.core;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.List;

public abstract class Command {

  private final List<String> permissions;

  public Command(final List<String> permissions) {
    this.permissions = permissions;
  }

  public abstract String getName();

  public abstract String getDescription();

  public abstract List<String> getListeningChannels();

  public abstract boolean onCommandSend(final GuildMessageReceivedEvent event, final String[] args);

  public boolean hasPermission(final Member member) {
    if (permissions == null || permissions.isEmpty()) {
      return true;
    }

    for (String string : permissions) {
      Role needRole = member.getGuild().getRoleById(string);
      if (member.getRoles().contains(needRole)) {
        return true;
      }
    }
    return false;
  }
}