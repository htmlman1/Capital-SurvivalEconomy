package com.htmlman1.capitaleconomy.commands.executor;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.htmlman1.capitaleconomy.api.CapitalUserFactory;
import com.htmlman1.capitaleconomy.perms.CapitalPermission;
import com.htmlman1.capitaleconomy.user.CapitalUser;
import com.htmlman1.capitaleconomy.util.ValidationUtils;

public class MoneyGiveCommand {

	public static void execute(CommandSender sender, String[] args) throws IllegalArgumentException {
		if(sender instanceof Player) {
			if(!sender.isOp() && !sender.hasPermission(CapitalPermission.GIVE_MONEY.getApplicablePermission())) {
				sender.sendMessage(CapitalMessages.NO_PERMS);
				return;
			}
		} else {
			if(!sender.isOp()) {
				sender.sendMessage(CapitalMessages.NO_PERMS);
				return;
			}
		}
		
		if(CapitalUserFactory.isUser(args[0]) || CapitalUserFactory.isOnline(args[0])) {
			CapitalUser target = CapitalUserFactory.getUser(args[0]);
			if(ValidationUtils.isDouble(args[1])) {
				double amount = Double.parseDouble(args[1]);
				target.increaseDebit(amount);
				sender.sendMessage("§b" + target.getDisplayName() + " §amagically received §6$" + amount + "§a.");
				target.sendMessage("§aYou magically received §6$" + amount + "§a from the heavens.");
			} else {
				sender.sendMessage(CapitalMessages.USE_NUMBER);
			}
		} else {
			sender.sendMessage(CapitalMessages.DOES_NOT_EXIST);
		}
	}
	
}
