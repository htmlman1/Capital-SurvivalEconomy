package com.htmlman1.capitaleconomy.lottery;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitTask;

import com.htmlman1.capitaleconomy.CapitalEconomy;

public class GlobalLottery {
	
	private static boolean started;
	private static long countdown;
	private static long elapsed;
	private static BukkitTask task;
	
	public static long getCountdown() {
		return countdown;
	}

	public static void setCountdown(long countdown) {
		GlobalLottery.countdown = countdown;
	}
	
	public static void addElapsed(long amount) {
		setElapsed(getElapsed() + amount);
	}

	public static long getElapsed() {
		return elapsed;
	}

	public static void setElapsed(long elapsed) {
		GlobalLottery.elapsed = elapsed;
	}

	public static boolean isActive() {
		return started;
	}
	
	public static long timeLeft() {
		return countdown - elapsed;
	}

	public static void start() {
		started = true;
		task = Bukkit.getScheduler().runTaskTimer(CapitalEconomy.getPlugin(), new GlobalLotteryUpdateTask(), 0, 20);
	}
	
	public static void stop(boolean silent) {
		if(!silent) Bukkit.broadcastMessage(ChatColor.GREEN + "The lottery has ended!");
		started = false;
		task.cancel();
	}
	
	private static class GlobalLotteryUpdateTask implements Runnable {
		public void run() {
			if(started) {
				GlobalLottery.addElapsed(1);
				Bukkit.broadcastMessage(ChatColor.BLUE + "There are " + ChatColor.GOLD + timeLeft() + ChatColor.BLUE + " seconds left before the lottery ends!");
			}
			else {
				GlobalLottery.setElapsed(0);
				GlobalLottery.stop(false);
			}
		}
	}
	
}
