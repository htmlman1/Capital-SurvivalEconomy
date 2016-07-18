package com.htmlman1.capitaleconomy.commands.executor;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.htmlman1.capitaleconomy.api.CapitalUserFactory;
import com.htmlman1.capitaleconomy.perms.CapitalPermission;
import com.htmlman1.capitaleconomy.user.CapitalUser;
import com.htmlman1.capitaleconomy.util.ValidationUtils;

public class MoneyTakeCommand {

	public static void execute(CommandSender sender, String[] args) throws IllegalArgumentException {
		if(sender instanceof Player) {
			CapitalUser user = CapitalUserFactory.getUser((Player) sender);
			if(!sender.isOp() && !user.hasPermission(CapitalPermission.GIVE_MONEY)) {
				sender.sendMessage(CapitalMessages.NO_PERMS);
				return;
			}
		} else {
			if(!sender.isOp()) {
				sender.sendMessage(CapitalMessages.NO_PERMS);
				return;
			}
		}
		
		if(CapitalUserFactory.isUser(args[0])) {
			CapitalUser target = CapitalUserFactory.getUser(args[0]);
			if(ValidationUtils.isDouble(args[1])) {
				double amount = Double.parseDouble(args[1]);
				target.decreaseDebit(amount);
				sender.sendMessage("§6$" + amount + " of §b" + target.getDisplayName() + " §c's money disappeared down a black hole.");
				target.sendMessage("§6$" + amount + " §cof your money disappeared down a black hole.");
			} else {
				sender.sendMessage(CapitalMessages.USE_NUMBER);
			}
		} else {
			sender.sendMessage(CapitalMessages.DOES_NOT_EXIST);
		}
	}
	
}
