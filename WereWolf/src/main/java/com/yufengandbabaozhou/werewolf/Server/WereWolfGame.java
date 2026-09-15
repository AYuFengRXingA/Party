package com.yufengandbabaozhou.werewolf.Server;

import com.yufengandbabaozhou.werewolf.Identity.NumberMan;
import net.minecraft.client.Minecraft;
import net.minecraft.server.level.ServerPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class WereWolfGame {
    private final String gameId;
    private final List<ServerPlayer> players;
    private final NumberMan numberMan;

    public WereWolfGame(List<ServerPlayer> players) {
        this.gameId = Minecraft.getInstance().player.getName().getString();
        this.players = new ArrayList<>(players);
        this.numberMan = new NumberMan();
    }

    public String getGameId() { return gameId; }
    public List<ServerPlayer> getPlayers() { return players; }
    public NumberMan getNumberMan() { return numberMan; }


    public int getWolfCount() {
        return numberMan.getWolfnumber();
    }

    public int getWitchCount() {
        return numberMan.getwitchnumber();
    }
    public int getHungerCount() {
        return numberMan.gethunternumber();
    }
    public void addWolf() {
        numberMan.addWolfnumber();
    }
    public void downWolf() {
        numberMan.downWolfnumber();
    }
}
