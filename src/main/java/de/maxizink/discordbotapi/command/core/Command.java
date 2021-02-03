package de.maxizink.discordbotapi.command.core;

import lombok.Getter;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.List;

@Getter
public abstract class Command {

  public abstract String getName();

  public abstract String getDescription();

  public abstract List<String> getPermissionIds();

  public abstract List<String> getListeningChannels();

  public abstract boolean onCommandSend(final GuildMessageReceivedEvent event, final String[] args);

  public boolean hasPermission(final Member member) {
    if (getPermissionIds() == null || getPermissionIds().isEmpty()) {
      return true;
    }

    for (String string : getPermissionIds()) {
      Role needRole = member.getGuild().getRoleById(string);
      if (member.getRoles().contains(needRole)) {
        return true;
      }
    }
    return false;
  }

}