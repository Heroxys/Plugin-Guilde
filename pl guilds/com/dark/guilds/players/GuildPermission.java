package com.dark.guilds.players;

import java.util.HashMap;
import java.util.Map;

public enum GuildPermission {

    ALL("§8Toutes les permissions"),
    ADMIN("§8Accéder à l'administration de la guilde"),
    CHANGE_SPAWN("§8Modifier le spawn de la guilde"),
    MANAGE_PERMS("§8Gérer les permissions"),
    CHANGE_CLAIM("§8Modifier le claim"),
    CHANGE_CDC("§8Modifier le Cahier des Charges"),
    RENAME("§8Renommer la guilde"),
    MANAGE("§8Gérer la guilde"),
    CHANGE_DESC("§8Changer la description de la guilde"),
    CHANGE_STATUS("§8Changer le status de la guilde"),
    NOTHING("§8Aucune permission");

    private String perm = "";
    private static Map<String, GuildPermission> perms  = new HashMap<>();

    static {
        for (GuildPermission r : GuildPermission.values()) {
            perms.put(r.getPerm(), r);
        }
    }

    GuildPermission(String s) {
        this.perm = s;
    }

    public String getPerm(){
        return perm;
    }

    static public GuildPermission getPermFromString(String s){
        return perms.get(s);
    }
}
