package com.redefocus.hub.combat.data;

import com.redefocus.hub.combat.manager.CombatPlayerManager;
import com.redefocus.hub.items.LobbyItem;
import com.redefocus.hub.managers.SpawnManager;
import com.redefocus.hub.util.inventory.Item;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

public class CombatPlayer {
    public void start() {
        Player player = CombatPlayerManager.getPlayer(this);

        if (player == null) return;

        PlayerInventory playerInventory = player.getInventory();

        playerInventory.clear();

        playerInventory.setHelmet(
                new Item(Material.DIAMOND_HELMET)
                .build()
        );

        playerInventory.setChestplate(
                new Item(Material.DIAMOND_CHESTPLATE)
                        .build()
        );

        playerInventory.setLeggings(
                new Item(Material.DIAMOND_LEGGINGS)
                        .build()
        );

        playerInventory.setBoots(
                new Item(Material.DIAMOND_BOOTS)
                        .build()
        );

        playerInventory.setItem(
                0,
                new Item(Material.DIAMOND_SWORD)
                .build()
        );

        playerInventory.setItem(
                1,
                new Item(Material.GOLDEN_APPLE)
                        .amount(4)
                        .build()
        );

        player.updateInventory();
    }

    public void end() {
        Player player = CombatPlayerManager.getPlayer(this);

        if (player == null) return;

        PlayerInventory playerInventory = player.getInventory();

        CombatPlayerManager.remove(player.getUniqueId());

        playerInventory.setHelmet(null);
        playerInventory.setChestplate(null);
        playerInventory.setLeggings(null);
        playerInventory.setBoots(null);

        playerInventory.clear();

        LobbyItem.giveItems(player);

        Location location = SpawnManager.getSpawn();

        player.teleport(location);

        Double health = player.getMaxHealth();

        player.setHealth(health);
    }
}
