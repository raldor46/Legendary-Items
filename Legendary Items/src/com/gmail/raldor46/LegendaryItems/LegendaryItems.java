package com.gmail.raldor46.LegendaryItems;

import java.sql.SQLException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class LegendaryItems extends JavaPlugin {
	LegendarySwords swords;
	public void onEnable() {
		swords = new LegendarySwords();
	}
	public void onDisable() {
		
	}
	public boolean onCommand(CommandSender sender, Command command,String label, String[] args) {
		if (sender instanceof Player){
			if (command.getName().equalsIgnoreCase("Legendary")){
				if(args.length > 1 && args.length < 4){
					if(args[0].equalsIgnoreCase("Sword")){
						if (args[1].equalsIgnoreCase("Get")){
							try {
								if(swords.validName(args[2])){
									ItemStack sword = swords.genSword(args[2]);
										if (sword != null){
											((Player) sender).getInventory().addItem(sword);
											return true;
										}
										else{
											sender.sendMessage("There was an error creating the sword.");
											return false;
										}
									}
								else{
									sender.sendMessage("Invalid Name");
									return false;
								}
							} catch (IllegalArgumentException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						else{
							sender.sendMessage(args[1]+" not implemented.");
							return false;
						}
					}
					else{
						sender.sendMessage(args[0]+ " is not a valid item");
						return false;
					}
				}
				else{
					sender.sendMessage("Not enough Arguments");
					return false;
				}
			}
		}
		
		return false;
	}
}
