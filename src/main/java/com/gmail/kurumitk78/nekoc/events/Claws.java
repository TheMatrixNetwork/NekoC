package com.gmail.kurumitk78.nekoc.events;

import com.gmail.kurumitk78.nekoc.Config;
import com.gmail.kurumitk78.nekoc.NekoC;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import static com.gmail.kurumitk78.nekoc.Config.NekoList;

public class Claws implements Listener{

    @EventHandler
    public void OnPlayerAttack(final EntityDamageByEntityEvent event){

        if(event.getDamager() instanceof Player && event.getEntity() instanceof LivingEntity){
            Player damager = ((Player) event.getDamager()).getPlayer();
            LivingEntity damagee = (LivingEntity) event.getEntity();
            if (NekoC.isNeko(damager)) {
                if (damagee instanceof Player) {
                    Player damageePlayer = ((Player) damagee).getPlayer();
                    if (NekoC.isNeko(damageePlayer)) {
                        event.setCancelled(true);
                    } else {
                        int randomchance = (int) (Math.random() * (100 - 1) + 1);
                        if(randomchance <= Config.ClawInfectChance){
                            NekoC.addNeko(damageePlayer);
                            damageePlayer.sendMessage(ChatColor.LIGHT_PURPLE + " You have been infected by " + damager.getDisplayName() + "!");
                            damager.sendMessage(ChatColor.LIGHT_PURPLE + " You have infected " + damageePlayer.getDisplayName() + "!");
                        }
                    }
                }
                if(Config.ClawDamageAdditive){
                    int randomchance = (int) (Math.random() * (100 - 1) + 1);
                    if(randomchance <= Config.ClawPoisonChance){
                        damagee.addPotionEffect(new PotionEffect(PotionEffectType.POISON, Config.ClawPoisonDuration, Config.ClawPoisonLevel-1));
                    }
                    event.setDamage(event.getDamage() + Config.ClawDamage);
                }
                else if(damager.getInventory().getItemInMainHand().getType().isAir()){
                    int randomchance = (int) (Math.random() * (100 - 1) + 1);
                    if(randomchance <= Config.ClawPoisonChance){
                        damagee.addPotionEffect(new PotionEffect(PotionEffectType.POISON, Config.ClawPoisonDuration, Config.ClawPoisonLevel-1));
                    }
                    event.setDamage(Config.ClawDamage);
                }
            }
        }


    }


}
