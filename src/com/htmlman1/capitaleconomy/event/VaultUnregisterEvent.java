package com.htmlman1.capitaleconomy.event;

import java.util.UUID;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public final class VaultUnregisterEvent extends Event implements Cancellable {
	
    private static final HandlerList handlers = new HandlerList();
    private UUID owner;
	private RemovalMethod method;
	private boolean cancelled;

    public VaultUnregisterEvent(UUID owner, RemovalMethod method) {
        this.owner = owner;
        this.method = method;
    }

    public UUID getOwner() {
		return owner;
	}
    
    public RemovalMethod getRemovalMethod() {
    	return this.method;
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
    
    public enum RemovalMethod {
    	BREAK, COMMAND
    }
    
}