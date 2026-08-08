package com.yufengandbabaozhou.werewolf;

import com.yufengandbabaozhou.partiesloader.gameinterfaces.IGame;
import com.yufengandbabaozhou.partiesloader.gameinterfaces.IGameConfig;
import com.yufengandbabaozhou.partiesloader.gameinterfaces.IGameCreator;

public class GameCreator implements IGameCreator {
    @Override
    public String getGameName() {
        return "狼人杀";
    }

    @Override
    public IGame CreateNewGameByConfig(IGameConfig config) {
        if (config.getClass()==GameConfig.class){
            GameConfig verifiedConfig=(GameConfig)config;
            return new Game(verifiedConfig);
        }
        //log
        return null;
    }
}
