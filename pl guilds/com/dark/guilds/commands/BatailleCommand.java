package com.dark.guilds.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.dark.guilds.Guilds;
import com.dark.guilds.events.GainExperience;

public class BatailleCommand implements CommandExecutor {

	Guilds guilds;

	public BatailleCommand(Guilds guilds) {
		this.guilds = guilds;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("bataille") && sender instanceof Player) {
			Player player = (Player) sender;

			if (args.length == 0) {
				player.sendMessage("§9§m-----§r§9\u00bb§6Guildes§9\u00ab§m-----");
				if (player.isOp()) {
					player.sendMessage(
							"§6/bataille win <nom de guilde> §3Annonce la victoire d'une bataille de guilde");
					player.sendMessage(
							"§6/bataille battle <nom de la guilde> <nom de la guilde> §3Démarre une bataille de guilde entre deux guildes");
					player.sendMessage(
							"§6/bataille assaut a <nom de guilde attaquant> d <nom de guilde défenseur> §3Démarre une guerre de guilde entre deux guildes");
					player.sendMessage(
							"§6/bataille assaut win <nom de guilde vainqueur> §3Annonce la victoire d'une guerre de guilde");
				}
				player.sendMessage("§9§m-----§r§9\u00bb§6Guildes§9\u00ab§m-----");
			}
			if (args.length == 1) {
				if (player.isOp()) {
					if (args[0].equalsIgnoreCase("win")) {
						player.sendMessage("§4Il manque le nom de la guilde qui a gagné !");
					}
				} else {
					player.sendMessage("§4Tu n'as pas le droit d'utiliser ça !");
				}
			}
			if (args.length == 2) {
				if (player.isOp()) {
					if (args[0].equalsIgnoreCase("win")) {
						Bukkit.broadcastMessage((String) ("§9§m-----§r§9\u00bb§6Guildes§9\u00ab§m-----\n"
								+ "§2La guilde §6§l" + args[1] + "§r§2 remporte la bataille de guilde !§r\n"
								+ "§9§m-----§r§9\u00bb§6Guildes§9\u00ab§m-----"));

						guilds.getGuildByName(args[1]).addXp(GainExperience.WIN_GUILD_BATTLE.getGain());
					}
				} else {
					player.sendMessage("§4Tu n'as pas le droit d'utiliser ça !");
				}
			}

			if (args.length == 3) {
				if (player.isOp()) {
					if (args[0].equalsIgnoreCase("assaut") && args[1].equalsIgnoreCase("win")) {
						player.sendMessage("Tu ne peux pas encore utiliser ça :/");

						if (!guilds.nameExist(args[2])) {
							player.sendMessage("§4La guilde n'existe pas !");
							return false;
						}

						if (!guilds.isInFight(args[2])) {
							player.sendMessage("§4La guilde en question n'est pas en combat !");
							return false;
						}

						String loseGuild = guilds.getGuildNameByWarFile(args[2]);

						if (loseGuild.equals("")) {
							player.sendMessage("§La guilde perdante n'a pas été trouvée !");
							return false;
						}

						if (guilds.endWar(args[2])) {
							Bukkit.broadcastMessage(
									(String) ("§9§m-----§r§9\u00bb§6Guildes§9\u00ab§m-----\n" + "§2La guilde §6§l"
											+ args[2] + "§r§2 a gagné la guerre de guilde contre " + loseGuild + "!\n"
											+ "Bravo à eux !!§r\n" + "§9§m-----§r§9\u00bb§6Guildes§9\u00ab§m-----"));

							guilds.getGuildByName(args[2]).addXp(GainExperience.WIN_GUILD_WAR.getGain());
							guilds.getGuildByName(loseGuild).removeXp(GainExperience.LOSE_GUILD_WAR.getGain());
						} else {
							player.sendMessage("§4Un problème est survenu dans l'arrêt de la guerre !");
						}
					}

					if (args[0].equalsIgnoreCase("battle")) {
						if (guilds.startBattle(args[1], args[2], player)) {
							Bukkit.broadcastMessage((String) ("§9§m-----§r§9\u00bb§6Guildes§9\u00ab§m-----\n"
									+ "§2Les guildes §6§l" + args[1] + "§r§2 et §r§6§l" + args[2]
									+ "§r§2 vont s'affronter dans l'arène !\n"
									+ "N'hésitez pas à venir en tant que spectateurs !§r\n"
									+ "§9§m-----§r§9\u00bb§6Guildes§9\u00ab§m-----"));
						}
					}
				} else {
					player.sendMessage("§4Tu n'as pas le droit d'utiliser ça !");
				}
			}

			if (args.length == 5) {
				if (args[0].equalsIgnoreCase("assaut")) {
					if (!args[1].equalsIgnoreCase("a") || !args[3].equalsIgnoreCase("d")) {
						player.sendMessage("§6Il y a une erreur dans l'écriture de la commande, veuillez recommencer");
						return false;
					}
					
					if (guilds.startWar(args[2], args[4], player)) {
						Bukkit.broadcastMessage(
								(String) ("§9§m-----§r§9\u00bb§6Guildes§9\u00ab§m-----\n" + "§2Les guildes §6§l"
										+ args[2] + "§r§2 et §r§6§l" + args[4] + "§r§2 Entrent en guerre !\n"
										+ "Un petit conseil, ne restez pas dans les parrages !§r\n"
										+ "§9§m-----§r§9\u00bb§6Guildes§9\u00ab§m-----"));
					}
				}
			}
		}
		return false;
	}
}
