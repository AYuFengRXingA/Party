package com.yufengandbabaozhou.werewolf;

import com.yufengandbabaozhou.partiesloader.GameInterfaces.IGame;
import com.yufengandbabaozhou.partiesloader.GameInterfaces.IGameConfig;
import com.yufengandbabaozhou.partiesloader.GameInterfaces.IGameCreator;
import net.minecraft.client.gui.screens.Screen;

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

        return null;
    }
    @Override
    public Screen createUIScreen() {
        return new UIConfig();
    }
}
