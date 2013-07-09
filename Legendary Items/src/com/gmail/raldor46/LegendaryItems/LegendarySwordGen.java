package com.gmail.raldor46.LegendaryItems;

import java.sql.SQLException;
import java.util.ArrayList;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.gmail.raldor46.Items.Sword;

public class LegendarySwordGen implements CommandExecutor{
	
	LegendarySwords swords = new LegendarySwords();
	ArrayList<SwordGen> SwordGens = new ArrayList<SwordGen>();
	
	 
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    }
	    // only got here if we didn't return false
	    return true;
	}
	
	public boolean onCommand(CommandSender sender, Command command,String label, String[] args) {
		if (sender instanceof Player){
			if (command.getName().equalsIgnoreCase("LegendarySword")){
				if(args.length > 0){
						if (args[0].equalsIgnoreCase("Get")){
							try {
								if(swords.validName(args[1])){
									ItemStack sword = swords.genSword(args[1]);
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
						else if (args[0].equalsIgnoreCase("Create")){
							boolean creating = false;
							if(SwordGens.size()>0){
								for(SwordGen gen : SwordGens){
									if (gen.player.getName().equalsIgnoreCase(sender.getName()))
										creating = true;break;
								}
							}
							if(creating == false){
								try {
									if(swords.validName(args[1]) == false)
										if(args.length >= 4){
											Sword s;
											if(args[1].length()>13){
												sender.sendMessage("Name too Long");
												return false;
											}
											String N = args[1];
											String mat = args[2];
											String lore = "";
//											List<String> LoreList = new ArrayList<String>();
											for(int i = 3; i<args.length; i++){
												if(i > 3)
													lore += " ";
												lore += args[i];
//												if(lore.length()>20){
//													LoreList.add(lore);
//													lore = "";
//												}
											}
											s = new Sword(N,mat,lore);
											SwordGen gen = new SwordGen((Player)sender,s);
											SwordGens.add(gen);
											gen.Scoreboard();
											return true;
										}
										else{
											sender.sendMessage("Not enough arguments to create.");
											return false;
									}
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							else {
								sender.sendMessage("You are already creating a sword");
								return false;
							}
						}
						
						else if (args[0].equalsIgnoreCase("Enchantments")){
							sender.sendMessage("Valid enchantments are: Sharpness, Smite, Bane,"
									+ "Knockback, FireAspect, Looting, Unbreaking.");
							return true;
						}
						
						else if (args[0].equalsIgnoreCase("Enchant")){
							if (args.length>1){
								boolean creating =false;
								SwordGen sg = null;
								if(SwordGens.size()>0){
									for(SwordGen gen : SwordGens){
										if (gen.player.getName().equalsIgnoreCase(sender.getName()))
											creating = true; sg = gen; break;
									}
								}
								if(creating){
									if(isInteger(args[2])){
										int i = Integer.parseInt(args[2]);
										
										if(args[1].equalsIgnoreCase("Sharpness")){
											sg.sword.setSharpness_Level(i);
											sg.Scoreboard();
											return true;
										}
										else if(args[1].equalsIgnoreCase("Smite")){
											sg.sword.setSmite_Level(i);
											sg.Scoreboard();
											return true;
										}
										else if(args[1].equalsIgnoreCase("Bane")){
											sg.sword.setBane_Level(i);
											sg.Scoreboard();
											return true;
										}
										else if(args[1].equalsIgnoreCase("Knockback")){
											sg.sword.setKnockback_Level(i);
											sg.Scoreboard();
											return true;
										}
										else if(args[1].equalsIgnoreCase("FireAspect")){
											sg.sword.setFireAspect_Level(i);
											sg.Scoreboard();
											return true;
										}
										else if(args[1].equalsIgnoreCase("Looting")){
											sg.sword.setLooting_Level(i);
											sg.Scoreboard();
											return true;
										}
										else if(args[1].equalsIgnoreCase("Unbreaking")){
											sg.sword.setUnbreaking_Level(i);
											sg.Scoreboard();
											return true;
										}
										else{
											sender.sendMessage(args[1]+" is not a valid enchantment.");
											return false;
										}
									}
									else{
										sender.sendMessage(args[1] + " is not a valid enchantment level.");
										return false;
									}
								}
								else{
									sender.sendMessage("You are not creating a sword.");
									return false;
								}
							}
							else{
								sender.sendMessage("Not enough args.");
								return false;
							}
						}
						
						else if (args[0].equalsIgnoreCase("Save")){
							boolean creating =false;
							SwordGen sg = null;
							if(SwordGens.size()>0){
								for(SwordGen gen : SwordGens){
									if (gen.player.getName().equalsIgnoreCase(sender.getName()))
										creating = true; sg = gen; break;
								}
							}
							if(creating){
								swords.SaveSword(sg.sword);
								for(SwordGen gen : SwordGens){
									if (gen.player.getName().equalsIgnoreCase(sender.getName()))
										SwordGens.remove(gen); break;
								}
								sender.sendMessage("The sword has been created.");
								sg.player.setScoreboard(sg.manager.getNewScoreboard());
								return true;
							}
							else{
								sender.sendMessage("you are not making a sword");
								return false;
							}
							
						}
						
						else if (args[0].equalsIgnoreCase("Cancel")){
							boolean creating =false;
							SwordGen sg = null;
							if(SwordGens.size()>0){
								for(SwordGen gen : SwordGens){
									if (gen.player.getName().equalsIgnoreCase(sender.getName()))
										creating = true; sg = gen; break;
								}
							}
							if(creating){;
								for(SwordGen gen : SwordGens){
									if (gen.player.getName().equalsIgnoreCase(sender.getName()))
										SwordGens.remove(gen); break;
								}
								sender.sendMessage("You have canceled creation.");
								sg.player.setScoreboard(sg.manager.getNewScoreboard());
								return true;
							}
							else{
								sender.sendMessage("You are not making a sword.");
								return false;
							}
						}
						
						else{
							sender.sendMessage(args[0]+" not implemented.");
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
