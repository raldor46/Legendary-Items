package com.gmail.raldor46.LegendaryItems;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import org.bukkit.inventory.ItemStack;

import com.gmail.raldor46.Items.Sword;

import lib.PatPeter.SQLibrary.MySQL;

public class LegendarySwords{
	static MySQL sql = new MySQL(Logger.getLogger("Minecraft"), 
            "minecraft", 
            "localhost", 
            3306, 
            "minecraft", 
            "minecraft", 
            "minecraft");
	
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
}
