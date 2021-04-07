package com.dark.guilds;

public enum GuildStatus {

    CLOSED("§cFermée"),
    OPENED("§aOuverte"),
    INVITATION("§6Sur Invitation");

    private String rank = "";

    GuildStatus(String s) {
        this.rank = s;
    }

    public String getStatus(){
        return rank;
    }

}
