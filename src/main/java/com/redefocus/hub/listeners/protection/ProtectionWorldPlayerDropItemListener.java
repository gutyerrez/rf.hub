package com.redefocus.hub.listeners.protection;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class ProtectionWorldPlayerDropItemListener implements Listener {
    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();

        if (!player.hasPermission("redefocus.build")) event.setCancelled(true);
    }
}
