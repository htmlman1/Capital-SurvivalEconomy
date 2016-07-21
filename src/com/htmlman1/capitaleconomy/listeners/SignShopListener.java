package com.htmlman1.capitaleconomy.listeners;

import com.htmlman1.capitaleconomy.CapitalEconomy;
import com.htmlman1.capitaleconomy.shop.CapitalShop;
import com.htmlman1.capitaleconomy.shop.ShopSign;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class SignShopListener implements Listener {
    CapitalEconomy plugin;
    public SignShopListener( CapitalEconomy plugin){
        this.plugin = plugin;
    }
    @EventHandler
    public void onShopCreate(SignChangeEvent event){
        if(ShopSign.isValidSign(event.getBlock()) && ShopSign.parseMaterialAndQuantity(event.getBlock())){
            ShopSign shop = new ShopSign(event.getBlock());
            CapitalShop cShop = new CapitalShop(plugin)

        }
    }
    @EventHandler
    public void onShopBreak(BlockBreakEvent event){

    }
    @EventHandler
    public void onShopInteract(PlayerInteractEvent event){

    }
}
