package com.htmlman1.capitaleconomy.listeners;

import com.htmlman1.capitaleconomy.shop.CapitalShop;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import com.htmlman1.capitaleconomy.shop.ShopSign;

public class SignShopListener implements Listener {
    @EventHandler
    public void onShopCreate(SignChangeEvent event){
        if(ShopSign.isValidSign(event.getBlock()) && ShopSign.isNameRegistered(event.getBlock()) && ShopSign.parseMaterialAndQuantity(event.getBlock())){
            ShopSign shop = new ShopSign(event.getBlock());

        }
    }
    @EventHandler
    public void onShopBreak(BlockBreakEvent event){

    }
    @EventHandler
    public void onShopInteract(PlayerInteractEvent event){

    }
}
