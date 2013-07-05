package com.gmail.raldor46.Items;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.enchantments.*;

public class Sword {
	//info
	private String Name;
	private Material Mat;
	private List<String> Lore = new ArrayList<String>();
	//Enchantment
	private int Sharpness_Level = 0;
	private int Smite_Level = 0;
	private int Bane_Level = 0;
	private int Knockback_Level = 0;
	private int FireAspect_Level = 0;
	private int Looting_Level = 0;
	private int Unbreaking_Level = 0;
	private ArrayList<Integer> enchantmentLevels = new ArrayList<Integer>();
	private int Sharpness_Id = 16;
	private int Smite_Id = 17;
	private int Bane_Id = 18;
	private int Knockback_Id = 19;
	private int FireAspect_Id = 20;
	private int Looting_Id = 21;
	private int Unbreaking_Id = 34;
	private ArrayList<Integer> enchantmentIds = new ArrayList<Integer>();
	
	public Sword(String name, String material, String lore) {
		Name = name;
		if (material.equalsIgnoreCase("Diamond"))
			Mat = Material.DIAMOND_SWORD;
		else if (material.equalsIgnoreCase("Gold"))
			Mat = Material.GOLD_SWORD;
		else if (material.equalsIgnoreCase("Iron"))
			Mat = Material.IRON_SWORD;
		else if (material.equalsIgnoreCase("Stone"))
			Mat = Material.STONE_SWORD;
		else if (material.equalsIgnoreCase("Wood"))
			Mat = Material.WOOD_SWORD;
		else
			Mat = Material.DIAMOND_SWORD;
		Lore.add(lore);
	}
	
	public ItemStack createSword(){
		ItemStack sword = new ItemStack(Mat);
		ItemMeta sword_meta = sword.getItemMeta();
		sword_meta.setDisplayName(Name);
		sword_meta.setLore(Lore);
		sword.setItemMeta(sword_meta);
		
		genEnchantments();
		
		for (int i = 0 ; i<enchantmentLevels.size() ; i++){
			Enchantment myEnchantment = new EnchantmentWrapper(enchantmentIds.get(i));  //new enchantment of effect id
			sword.addUnsafeEnchantment(myEnchantment, enchantmentLevels.get(i));  //enchant the item
		}

		return sword;
	}
	
	public void genEnchantments(){
		if (Sharpness_Level > 0){
			enchantmentLevels.add(Sharpness_Level);
			enchantmentIds.add(Sharpness_Id);
		}
		if (Smite_Level > 0){
			enchantmentLevels.add(Smite_Level);
			enchantmentIds.add(Smite_Id);
		}
		if (Bane_Level > 0){
			enchantmentLevels.add(Bane_Level);
			enchantmentIds.add(Bane_Id);
		}
		if (Knockback_Level > 0){
			enchantmentLevels.add(Knockback_Level);
			enchantmentIds.add(Knockback_Id);
		}
		if (FireAspect_Level > 0){
			enchantmentLevels.add(FireAspect_Level);
			enchantmentIds.add(FireAspect_Id);
		}
		if (Looting_Level > 0){
			enchantmentLevels.add(Looting_Level);
			enchantmentIds.add(Looting_Id);
		}
		if (Unbreaking_Level > 0){
			enchantmentLevels.add(Unbreaking_Level);
			enchantmentIds.add(Unbreaking_Id);
		}
	}
	
	//INFO
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	
	public Material getMaterial() {
		return Mat;
	}
	public void setMaterial(Material material) {
		Mat = material;
	}
	
	public String getLore() {
		return Lore.get(0);
	}
	public void setLore(String lore) {
		Lore.clear();
		Lore.add(lore);
	}
	
	//ENCHANTMENTS
	public void setSharpness_Level(int sharpness_Level) {
		Sharpness_Level = sharpness_Level;
	}
	public int getSharpness_Level() {
		return Sharpness_Level;
	}
	
	public void setSmite_Level(int smite_Level) {
		Smite_Level = smite_Level;
	}
	public int getSmite_Level() {
		return Smite_Level;
	}
	
	public void setBane_Level(int bane_Level) {
		Bane_Level = bane_Level;
	}
	public int getBane_Level() {
		return Bane_Level;
	}
	
	public void setKnockback_Level(int knockback_Level) {
		Knockback_Level = knockback_Level;
	}
	public int getKnockback_Level() {
		return Knockback_Level;
	}
	
	public void setFireAspect_Level(int fireAspect_Level) {
		FireAspect_Level = fireAspect_Level;
	}
	public int getFireAspect_Level() {
		return FireAspect_Level;
	}
	
	public void setLooting_Level(int looting_Level) {
		Looting_Level = looting_Level;
	}
	public int getLooting_Level() {
		return Looting_Level;
	}
	
	public void setUnbreaking_Level(int unbreaking_Level) {
		Unbreaking_Level = unbreaking_Level;
	}
	public int getUnbreaking_Level() {
		return Unbreaking_Level;
	}
}
