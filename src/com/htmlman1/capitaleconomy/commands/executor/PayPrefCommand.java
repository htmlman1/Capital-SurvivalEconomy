package com.htmlman1.capitaleconomy.commands.executor;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.htmlman1.capitaleconomy.api.CapitalUserFactory;
import com.htmlman1.capitaleconomy.money.util.PaymentType;
import com.htmlman1.capitaleconomy.perms.CapitalPermission;
import com.htmlman1.capitaleconomy.user.CapitalUser;

public class PayPrefCommand {

	public static void execute(CommandSender sender, String[] args) throws IllegalArgumentException {
		boolean isPlayer = (sender instanceof Player);
		
		switch(args.length) {
		case 1:
			if(isPlayer) {
				CapitalUser user = CapitalUserFactory.getUser((Player) sender);
				if(user.hasPermission(CapitalPermission.SET_PAY_PREF)) {
					String type = args[0].toUpperCase();
					if(PaymentType.isPaymentType(type)) {
						user.setPaymentType(PaymentType.getType(type));
						sender.sendMessage("§aYour preferred payment method was set to §6§l" + type + "§a.");
					} else {
						throw new IllegalArgumentException("§cPayment type '" + type + "' not recognized.");
					}
				} else {
					throw new IllegalArgumentException(CapitalMessages.NO_PERMS);
				}
			} else {
				throw new IllegalArgumentException(CapitalMessages.BE_PLAYER);
			}
			break;
		}
	}
	
}
