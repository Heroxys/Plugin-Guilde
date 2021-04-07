/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  org.bukkit.OfflinePlayer
 *  org.bukkit.entity.Player
 */
package com.dark.guilds.api;

import com.dark.guilds.Guild;
import com.dark.guilds.Guilds;
import com.dark.guilds.players.GuildPlayer;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Stream;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class GuildAPI {
    public List<Guild> getGuilds() {
        return Guilds.getInstance().getGuilds();
    }

    public Guild getGuild(Player player) {
        return Guilds.getInstance().getGuild(player);
    }

    public Guild getGuild(String guildName) {
        return Guilds.getInstance().getGuildByName(guildName);
    }

    public boolean hasGuild(Player player) {
        return Guilds.getInstance().hasGuild(player);
    }

    public Guild getGuild(UUID uuid) {
        return Guilds.getInstance().getGuilds().stream().filter(g -> g.getUniqueId().equals(uuid)).findAny().orElse(null);
    }

    public GuildPlayer getGuildPlayer(OfflinePlayer player) {
        return Guilds.getInstance().getGuildPlayer(player);
    }
}

