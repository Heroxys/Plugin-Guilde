package com.dark.guilds.events;

public enum GainExperience {

	KILL_BOSS_DIFF_GUILD(1), WIN_GUILD_BATTLE(50), WIN_GUILD_WAR(300),
	// A utiliser avec removeXp();
	LOSE_GUILD_WAR(50);

	private int gain = 0;

	GainExperience(int gain) {
		this.gain = gain;
	}

	public int getGain() {
		return gain;
	}
}
