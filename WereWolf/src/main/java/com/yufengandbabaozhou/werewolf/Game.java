package com.yufengandbabaozhou.werewolf;

import com.yufengandbabaozhou.partiesloader.gameinterfaces.IGame;

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
}
