package com.gmail.raldor46.LegendaryItems;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import com.gmail.raldor46.Items.Sword;

public class SwordGen {
	public Player player;
	public Sword sword;
	public ScoreboardManager manager = Bukkit.getScoreboardManager();
	public Scoreboard swordBoard = manager.getNewScoreboard();
	public Objective objective;

	
	public SwordGen(Player p, Sword s) {
		player = p;
		sword = s;
		objective = swordBoard.registerNewObjective(sword.getName()+(int)(100*Math.random()), "dummy");
		objective.setDisplayName(sword.getName());
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
	}
	public void Scoreboard(){


	    Score sharpnessScore = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN + "Sharpness:"));
	    sharpnessScore.setScore(sword.getSharpness_Level());
	    
	    Score smiteScore = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN + "Smite:"));
	    smiteScore.setScore(sword.getSmite_Level());
	    
	    Score baneScore = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN + "Bane:"));
	    baneScore.setScore(sword.getBane_Level());
	    
	    Score knockbackScore = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN + "Knockback:"));
	    knockbackScore.setScore(sword.getKnockback_Level());
	    
	    Score fireAspectScore = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN + "Fire Aspect:"));
	    fireAspectScore.setScore(sword.getFireAspect_Level());
	    
	    Score lootingScore = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN + "Looting:"));
	    lootingScore.setScore(sword.getLooting_Level());
	    
	    Score unbreakingScore = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN + "Unbreaking:"));
	    unbreakingScore.setScore(sword.getUnbreaking_Level());

		player.setScoreboard(swordBoard);
	}
	
}
