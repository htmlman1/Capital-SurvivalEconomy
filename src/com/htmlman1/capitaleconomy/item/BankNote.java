package com.htmlman1.capitaleconomy.item;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class BankNote {

    public static ItemStack create(double value){
        ItemStack is = new ItemStack(Material.PAPER);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA+"Bank Note");
        meta.getLore().clear();
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.WHITE+"$"+value);
        meta.setLore(lore);
        is.setItemMeta(meta);
        return is;
    }
}
