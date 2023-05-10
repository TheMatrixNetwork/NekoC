package com.gmail.kurumitk78.nekoc.events;

import com.gmail.kurumitk78.nekoc.NekoC;
import org.bukkit.ChatColor;
import org.bukkit.event.player.*;
import org.bukkit.event.*;

import java.util.Random;

public class NekoChat implements Listener
{
    private final Random random = new Random();

    private String translateToUwu(String originalMessage) {
        String[] words = originalMessage.split(" ");
        StringBuilder translatedMessage = new StringBuilder();
        String[] var4 = words;
        int var5 = words.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            String word = var4[var6];
            if (word.length() > 0) {
                String uwuWord = word.replaceAll("[lrLR]", "w");
                String var10000;
                if (uwuWord.endsWith(".") || uwuWord.endsWith("?") || uwuWord.endsWith("!")) {
                    var10000 = uwuWord.substring(0, uwuWord.length() - 1);
                    uwuWord = var10000 + " uwu" + uwuWord.charAt(uwuWord.length() - 1);
                }

                if (Character.isUpperCase(word.charAt(0))) {
                    var10000 = uwuWord.substring(0, 1).toUpperCase();
                    uwuWord = var10000 + uwuWord.substring(1);
                }

                translatedMessage.append(uwuWord).append(" ");
            }
        }

        return translatedMessage.toString().trim();
    }
    @EventHandler
    public void onPlayerChat(final AsyncPlayerChatEvent event) {
        if (NekoC.isNeko(event.getPlayer())) {
            String msg = event.getMessage(); //to-do: Use fucking regex instead of this garbage
            msg = msg.replace("na", "nya");
            msg = msg.replace("nu", "ny");
            msg = msg.replace("ni", "nyi");
            msg = msg.replace("ne", "nye");
            msg = msg.replace("no", "nyo");
            msg = msg.replace("mo", "myo");
            msg = msg.replace("Na", "Nya");
            msg = msg.replace("Nu", "Ny");
            msg = msg.replace("Ni", "Nyi");
            msg = msg.replace("Ne", "Nye");
            msg = msg.replace("No", "Nyo");
            msg = msg.replace("Mo", "Myo");
            msg = msg.replace("please", "pwease");
            msg = msg.replace("yeah", "nya");
            msg = msg.replace("huh", "nya");
            msg = msg.replace("meow", "nya");
            msg = msg.replace("Meow", "Nya");
            msg = msg.replace("honyey", "honey");
            msg = msg.replace("evernyunye", "everyonye");
            msg = msg.replace("Honyey", "Honey");
            msg = msg.replace("Evernyunye", "Everyonye");
            if (msg.equalsIgnoreCase("yay") || msg.equalsIgnoreCase("yay!")) {
                msg = "Nya!";
            }
            if (shouldTranslate()) {
                msg += "-nya";
            }
            msg = msg.replace("nya-nya", "nya");
            msg = msg.replace("Nya-nya", "Nya");
            if (shouldTranslate())
                event.setMessage(translateToUwu(msg));
            else
                event.setMessage(msg);
        }
    }
    private boolean shouldTranslate() {
        int randomInt = this.random.nextInt(100) + 1;
        return randomInt <= 25;
    }
}
