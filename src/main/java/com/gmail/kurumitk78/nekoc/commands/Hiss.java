package com.gmail.kurumitk78.nekoc.commands;

import com.gmail.kurumitk78.nekoc.Config;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import com.gmail.kurumitk78.nekoc.NekoC;
import org.bukkit.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Hiss implements CommandExecutor {
    public boolean onCommand(final CommandSender sender, final Command command, final String s, final String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }
        if (args.length == 0 || Bukkit.getPlayer(args[0]) == null) {
            sender.sendMessage(Config.PluginPrefix + ChatColor.LIGHT_PURPLE + " Invalid Input");
            return false;
        }

        final Player target = Bukkit.getPlayer(args[0]);
        final Player player = ((Player) sender).getPlayer();
        if (target == null) {
            sender.sendMessage(Config.PluginPrefix + ChatColor.LIGHT_PURPLE + " Invalid Input");
            return false;
        }
        if (NekoC.isNeko(player)) {
            if ((target.getWorld().equals(player.getWorld()) && player.getLocation().distance(target.getLocation()) < Config.HissRange)) {
                if (Config.GlobalCommandMessages) {
                    Bukkit.broadcastMessage(Config.PluginPrefix + ChatColor.LIGHT_PURPLE + ChatColor.YELLOW + player.getDisplayName() + ChatColor.LIGHT_PURPLE + " is hissing at " + target.getName());
                } else {
                    player.sendMessage(Config.PluginPrefix + ChatColor.LIGHT_PURPLE + " You have hissed at " + ChatColor.YELLOW + target.getDisplayName() + "!");
                    target.sendMessage(Config.PluginPrefix + ChatColor.LIGHT_PURPLE + " You hear " + ChatColor.YELLOW + player.getDisplayName() + ChatColor.LIGHT_PURPLE + " hiss at you!");
                }
                player.getWorld().playSound(player.getLocation(), Sound.ENTITY_CAT_HISS, 0.3F, 1);
                target.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 200, 10));
            } else {
                sender.sendMessage(Config.PluginPrefix + ChatColor.LIGHT_PURPLE + " You are too far away to hiss at " + ChatColor.YELLOW + target.getName());
                return false;
            }

        }
        else{player.sendMessage(Config.PluginPrefix + ChatColor.LIGHT_PURPLE + " Only Nekos can hiss" + "!");}

        return true;
    }
}
