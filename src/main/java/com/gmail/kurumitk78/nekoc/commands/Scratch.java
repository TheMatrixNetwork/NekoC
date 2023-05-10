package com.gmail.kurumitk78.nekoc.commands;

import com.gmail.kurumitk78.nekoc.Config;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import com.gmail.kurumitk78.nekoc.NekoC;
import org.bukkit.*;

import static com.gmail.kurumitk78.nekoc.Config.NekoList;

public class Scratch implements CommandExecutor {
    public boolean onCommand(final CommandSender sender, final Command command, final String s, final String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }
        if (args.length == 0 || Bukkit.getPlayer(args[0]) == null) {
            sender.sendMessage(Config.PluginPrefix + ChatColor.LIGHT_PURPLE + " Invalid Input");
            return false;
        }
        final Player player = (Player) sender;
        if (NekoC.isNeko(player)) {
            final Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                sender.sendMessage(Config.PluginPrefix + ChatColor.LIGHT_PURPLE + " Invalid Input");
                return false;
            }
            if ((target.getWorld().equals(player.getWorld()) && player.getLocation().distance(target.getLocation()) < Config.ScratchRange)) {
                if (Config.GlobalCommandMessages) {
                    Bukkit.broadcastMessage(Config.PluginPrefix + ChatColor.YELLOW + player.getDisplayName() + ChatColor.LIGHT_PURPLE + " has scratched " + ChatColor.YELLOW + target.getName() + ChatColor.LIGHT_PURPLE + " with sharp claws!");
                } else {
                    player.sendMessage(Config.PluginPrefix + ChatColor.LIGHT_PURPLE + " You have scratched " + ChatColor.YELLOW + target.getDisplayName() + ChatColor.LIGHT_PURPLE + "!");
                    target.sendMessage(Config.PluginPrefix + ChatColor.LIGHT_PURPLE + " You feel " + ChatColor.YELLOW + player.getDisplayName() + ChatColor.LIGHT_PURPLE + "'s claws scratch you!");
                }
                int randomchance = (int) (Math.random() * (100 - 1) + 1);
                if(randomchance <= Config.ScratchInfectChance){
                    NekoC.addNeko(player);
                    target.sendMessage(ChatColor.LIGHT_PURPLE + " You have been infected by " + player.getDisplayName() + "!");
                    player.sendMessage(ChatColor.LIGHT_PURPLE + " You have infected " + target.getDisplayName() + "!");
                }
            } else {
                sender.sendMessage(Config.PluginPrefix + ChatColor.LIGHT_PURPLE + " You are too far away to scratch " + ChatColor.YELLOW + target.getName());
                return false;
            }
        }
        return true;
    }
}
