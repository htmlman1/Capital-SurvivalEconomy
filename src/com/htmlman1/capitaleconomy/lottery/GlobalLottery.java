package com.htmlman1.capitaleconomy.lottery;

public class GlobalLottery {
	
	private static boolean started;
	private static long countdown;
	private static long elapsed;
	
	public static long getCountdown() {
		return countdown;
	}

	public static void setCountdown(long countdown) {
		GlobalLottery.countdown = countdown;
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

	public static void start() {
		GlobalLottery.started = true;
	}
	
	public static void stop() {
		GlobalLottery.started = false;
	}
	
	private class GlobalLotteryUpdateTask implements Runnable {
		public void run() {
			
		}
	}
	
}
