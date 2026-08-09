package com.yufengandbabaozhou.werewolf;

import com.yufengandbabaozhou.partiesloader.GameInterfaces.IGame;
import com.yufengandbabaozhou.partiesloader.GameInterfaces.IGamePhase;

public class Game implements IGame {
    public GameConfig Config;
    public Game(GameConfig config){
        Config=config;
    }
    @Override
    public String Name() {
        return "狼人杀";
    }

    @Override
    public String Description() {
        return "";
    }

    @Override
    public String Rule() {
        return "";
    }

    @Override
    public IGamePhase[] getGamePhases() {
        return new IGamePhase[0];
    }
}
