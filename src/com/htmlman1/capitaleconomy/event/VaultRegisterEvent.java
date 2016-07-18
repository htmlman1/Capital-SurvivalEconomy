package com.htmlman1.capitaleconomy.event;

import java.util.UUID;

import org.bukkit.block.DoubleChest;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public final class VaultRegisterEvent extends Event implements Cancellable {
	
    private static final HandlerList handlers = new HandlerList();
    private UUID owner;
    private DoubleChest vault;
    private boolean cancelled;

    public VaultRegisterEvent(UUID owner, DoubleChest vault) {
        this.owner = owner;
        this.vault = vault;
    }

    public UUID getOwner() {
		return owner;
	}

	public DoubleChest getVault() {
		return vault;
	}
	
	public boolean isCancelled() {
		return cancelled;
	}
	
	public void setCancelled(boolean cancel) {
		this.cancelled = cancel;
	}

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
    
}