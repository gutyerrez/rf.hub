package com.redefocus.hub.tags.packets;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PacketWrapper {

    public String error;
    private Object packet;

    public PacketWrapper(String name, int param, List<String> members) {
        this.packet = PacketAccessor.createPacket();
        if (param != 3 && param != 4) {
            throw new IllegalArgumentException("Method must be join or leave for player constructor");
        }
        this.setupDefaults(name, param);
        this.setupMembers(members);
    }

    public PacketWrapper(String name, String prefix, String suffix, int param, Collection<?> players) {
        this.packet = PacketAccessor.createPacket();
        this.setupDefaults(name, param);
        if (param != 0) {
            if (param != 2) {
                return;
            }
        }
        try {
            PacketAccessor.DISPLAY_NAME.set(this.packet, name);
            PacketAccessor.PREFIX.set(this.packet, prefix);
            PacketAccessor.SUFFIX.set(this.packet, suffix);
            PacketAccessor.PACK_OPTION.set(this.packet, 1);
            if (PacketAccessor.VISIBILITY != null) {
                PacketAccessor.VISIBILITY.set(this.packet, "always");
            }
            if (param == 0) ((Collection) PacketAccessor.MEMBERS.get(this.packet)).addAll(players);
        } catch (Exception e) {
            this.error = e.getMessage();
        }
    }

    private void setupMembers(Collection<?> players) {
        try {
            players = ((players == null || players.isEmpty()) ? new ArrayList<>() : players);
            ((Collection) PacketAccessor.MEMBERS.get(this.packet)).addAll(players);
        } catch (Exception e) {
            this.error = e.getMessage();
        }
    }

    private void setupDefaults(String name, int param) {
        try {
            PacketAccessor.TEAM_NAME.set(this.packet, name);
            PacketAccessor.PARAM_INT.set(this.packet, param);
        } catch (Exception e) {
            this.error = e.getMessage();
        }
    }

    public void send() {
        PacketAccessor.sendPacket(Bukkit.getOnlinePlayers(), this.packet);
    }

    public void send(Player player) {
        PacketAccessor.sendPacket(player, this.packet);
    }
}
