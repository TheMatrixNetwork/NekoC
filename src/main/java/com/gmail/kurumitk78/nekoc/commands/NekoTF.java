package com.gmail.kurumitk78.nekoc.commands;

import com.gmail.kurumitk78.nekoc.Config;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import com.gmail.kurumitk78.nekoc.NekoC;
import org.bukkit.*;

import static com.gmail.kurumitk78.nekoc.Config.NekoList;

public class NekoTF implements CommandExecutor {
    public boolean onCommand(final CommandSender commandSender, final Command command, final String s, final String[] args) {
        if (!commandSender.hasPermission("NekoC.NekoTF")) {
            return false;
        }
        if (args.length == 0) {
            final Player commandTarget = ((Player) commandSender).getPlayer();
            if (!NekoC.isNeko(commandTarget)) {
                NekoC.addNeko(commandTarget);
                commandSender.sendMessage(Config.PluginPrefix + ChatColor.LIGHT_PURPLE + " Made you a neko!");
            } else {
                NekoC.removeNeko(commandTarget);
                commandSender.sendMessage(Config.PluginPrefix + ChatColor.LIGHT_PURPLE + " You are no longer a neko!");
            }
        } else if (commandSender.hasPermission("nekoC.Admin")) {
            final Player commandTarget = Bukkit.getPlayer(args[0]);
            if (commandTarget.isValid()) {
                if (!NekoC.isNeko(commandTarget)) {
                    NekoC.addNeko(commandTarget);
                    commandSender.sendMessage(Config.PluginPrefix + ChatColor.LIGHT_PURPLE + " Made " + ChatColor.YELLOW + commandTarget.getDisplayName() + ChatColor.LIGHT_PURPLE + " a neko!");
                } else {
                    NekoC.removeNeko(commandTarget);
                    commandSender.sendMessage(Config.PluginPrefix + ChatColor.LIGHT_PURPLE + " " + ChatColor.YELLOW + commandTarget.getDisplayName() + " is no longer a neko!");
                }
            } else {
                commandSender.sendMessage(Config.PluginPrefix + ChatColor.LIGHT_PURPLE + " Player does not exist!");
            }
        }
        return true;
    }
}
