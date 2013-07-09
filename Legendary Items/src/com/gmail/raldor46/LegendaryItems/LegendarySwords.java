package com.gmail.raldor46.LegendaryItems;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

//import org.bukkit.command.Command;
//import org.bukkit.command.CommandExecutor;
//import org.bukkit.command.CommandSender;
//import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.gmail.raldor46.Items.Sword;

import lib.PatPeter.SQLibrary.MySQL;

public class LegendarySwords {
	static MySQL sql = new MySQL(Logger.getLogger("Minecraft"), 
            "minecraft", 
            "localhost", 
            3306, 
            "minecraft", 
            "minecraft", 
            "minecraft");
	
	public boolean validName(String Name) throws SQLException{
		if (sql.open()) {
			ResultSet results = sql.query("SELECT Name FROM `minecraft`.`legendary_swords`WHERE Name = '"+ Name + "'");
			if(results.first()){
				return true;
			}
			else{
				return false;
			}
		}
		return false;
	}
	
	public ItemStack genSword(String name) throws SQLException{
		if (sql.open()) {
			ResultSet results = sql.query("SELECT * FROM `minecraft`.`legendary_swords`WHERE Name = '"+ name + "'");
			if(results.first()){
				Sword s = new Sword(results.getString("Name"),results.getString("Material"),results.getString("Lore"));
					
					s.setSharpness_Level(results.getInt("Sharpness_Level"));
					s.setSmite_Level(results.getInt("Smite_Level"));
					s.setBane_Level(results.getInt("Bane_Level"));
					s.setKnockback_Level(results.getInt("Knockback_Level"));
					s.setFireAspect_Level(results.getInt("FireAspect_Level"));
					s.setLooting_Level(results.getInt("Looting_Level"));
					s.setUnbreaking_Level(results.getInt("Unbreaking_Level"));
				
				return s.createSword();
			}
			else{
				return null;
			}
		}
		else{
			return null;
		}
	}
	
	public boolean SaveSword(Sword s){
		if (sql.open()) {
			try {
				sql.query("INSERT INTO `minecraft`.`legendary_swords`"+
					"(`Name`,`Material`,`Lore`,`Sharpness_Level`,`Smite_Level`,"+
					"`Bane_Level`,`Knockback_Level`,`FireAspect_Level`,`Looting_Level`,`Unbreaking_Level`)"+
						"VALUES"+
						"("+
						"'"+s.getName()+"',"+
						"'"+s.getMaterialStr()+"',"+
						"'"+s.getLore()+"',"+
						""+s.getSharpness_Level()+","+
						""+s.getSmite_Level()+","+
						""+s.getBane_Level()+","+
						""+s.getKnockback_Level()+","+
						""+s.getFireAspect_Level()+","+
						""+s.getLooting_Level()+","+
						""+s.getUnbreaking_Level()+""+
						")"
							);
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}
	
//	public boolean onCommand(CommandSender sender, Command command,String label, String[] args) {
//		if (sender instanceof Player){
//			if (command.getName().equalsIgnoreCase("LegendarySword")){
//				if(args.length > 1 && args.length < 3){
//						if (args[1].equalsIgnoreCase("Get")){
//							try {
//								if(validName(args[2])){
//									ItemStack sword = genSword(args[2]);
//										if (sword != null){
//											((Player) sender).getInventory().addItem(sword);
//											return true;
//										}
//										else{
//											sender.sendMessage("There was an error creating the sword.");
//											return false;
//										}
//									}
//								else{
//									sender.sendMessage("Invalid Name");
//									return false;
//								}
//							} catch (IllegalArgumentException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							} catch (SQLException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//						}
//						
//						else{
//							sender.sendMessage(args[1]+" not implemented.");
//							return false;
//						}
//					}
//				else{
//					sender.sendMessage("Not enough Arguments");
//					return false;
//				}
//			}
//		}
//		
//		return false;
//	}
	
}
