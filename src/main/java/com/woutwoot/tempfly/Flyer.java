package com.woutwoot.tempfly;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Flyer {

    private UUID player;
    private int secondsCooldown;
    private int secondsDuration;
    private int maxDuration;
    private int maxCooldown;
    private boolean isFlying = false;

    public Flyer(UUID player) {
        this.player = player;
        reset();
        start();
    }

    public void tick(){
        if(secondsDuration > 0) {
            secondsDuration--;
        }else{
            if(secondsCooldown > 0) {
                secondsCooldown--;
            }
            if(secondsDuration == 0 && isFlying){
                stop();
            }
        }
    }

    private void reset() {
        maxDuration = (Main.instance.getConfig().getInt("FlightDuration"));
        maxCooldown = (Main.instance.getConfig().getInt("FlightCooldown"));
        secondsCooldown = 0;
        secondsDuration = maxDuration;
    }

    public void stop() {
        Player p = Bukkit.getPlayer(player);
        if( p != null){
            p.setAllowFlight(false);
            p.setFlying(false);
            p.sendMessage(Vars.tag + Messages.get("flight_worn_off"));
            secondsCooldown = maxCooldown;
            isFlying = false;
        }
    }

    public void start() {
        Player p = Bukkit.getPlayer(player);
        if(p != null){
            p.setAllowFlight(true);
            p.sendMessage(Vars.tag + Messages.get("can_fly_for_time").replace("{time}",Tools.secondsToTime(secondsDuration)));
            secondsDuration = maxDuration;
            isFlying = true;
        }
    }

    public void handleCommand() {
        Player p = Bukkit.getPlayer(player);
        if( p != null) {
            if (secondsCooldown == 0) {
                if (!isFlying) {
                    start();
                } else {
                    p.sendMessage(Vars.tag + Messages.get("already_have_flight_for_time").replace("{time}", Tools.secondsToTime(secondsDuration)));
                }
            } else {
                p.sendMessage(Vars.tag + Messages.get("wait_for_time_cooldown").replace("{time}", Tools.secondsToTime(secondsCooldown)));
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flyer flyer = (Flyer) o;
        return player.equals(flyer.player);
    }

    @Override
    public int hashCode() {
        return player.hashCode();
    }
}
