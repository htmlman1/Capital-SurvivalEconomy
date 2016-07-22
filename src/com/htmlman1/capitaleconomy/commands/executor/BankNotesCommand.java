package com.htmlman1.capitaleconomy.commands.executor;

import com.htmlman1.capitaleconomy.api.CapitalUserFactory;
import com.htmlman1.capitaleconomy.item.BankNote;
import com.htmlman1.capitaleconomy.user.CapitalUser;
import com.htmlman1.capitaleconomy.util.ValidationUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BankNotesCommand {

    public static void execute(CommandSender sender, String[] args) throws IllegalArgumentException{
        if(sender instanceof Player) {
            Player player = (Player) sender;
            switch (args[0].toLowerCase()){
                case "withdraw":
                    CapitalUser user = CapitalUserFactory.getUser(player);
                    if(ValidationUtils.isDouble(args[1])) {
                       Double amount = Double.valueOf(args[1]);
                        if (amount < user.getDebit()){
                            player.getInventory().addItem(BankNote.create(amount));
                        }
                    }
                case "deposit":
                    break;
                default:
                    sender.sendMessage(ChatColor.RED+"Invalid command!");
            }
        }
    }
}
