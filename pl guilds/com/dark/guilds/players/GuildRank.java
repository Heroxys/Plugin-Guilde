package com.dark.guilds.players;

public enum GuildRank {

    BOSS ("§6[Chef]"),
    MODERATOR ("§e[Adjoint]"),
    MEMBER ("§7[Membre]");

    private String rank = "";

    GuildRank(String s) {
        this.rank = s;
    }

    public String getPrefix(){
        return rank;
    }
}
