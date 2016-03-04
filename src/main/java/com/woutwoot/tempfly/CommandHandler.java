package com.woutwoot.tempfly;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by WoutP on 3/03/2016.
 */
public class CommandHandler implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(command.getName().equalsIgnoreCase("tfly")){
            if(args.length == 0){
                Player p;
                if (!(sender instanceof Player)) {
                    sender.sendMessage(Vars.tag + "This command is player only.");
                    return true;
                }
                p = (Player) sender;
                Main.instance.handleFlightCommand(p);
            } else if (args.length == 1){
                if(args[0].equalsIgnoreCase("reload") && sender.hasPermission("temporaryflight.reload")){
                    sender.sendMessage(Vars.tag + "Reloading config...");
                    Main.instance.loadConfig();
                    Messages.load();
                    sender.sendMessage(Vars.tag + "Reload finished!");
                }
            }
            return true;
        }
        return false;
    }

}
