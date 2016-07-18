package com.htmlman1.capitaleconomy.commands.executor;

import org.bukkit.command.CommandSender;

import com.htmlman1.capitaleconomy.api.CapitalUserFactory;
import com.htmlman1.capitaleconomy.perms.CapitalPermission;
import com.htmlman1.capitaleconomy.user.ServerCapitalUser.AccountType;

public class LotteryCheckCommand {

	public static void execute(CommandSender sender) throws IllegalArgumentException {
		if(sender.hasPermission(CapitalPermission.CHECK_LOTTERY.getApplicablePermission())) {
			sender.sendMessage("§8Current lottery pot: §6$" + CapitalUserFactory.getServerUser().getBalance(AccountType.LOTTERY));
		} else {
			throw new IllegalArgumentException(CapitalMessages.NO_PERMS);
		}
	}
	
}
