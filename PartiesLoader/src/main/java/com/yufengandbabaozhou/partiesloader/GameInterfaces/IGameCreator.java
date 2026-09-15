package com.yufengandbabaozhou.partiesloader.GameInterfaces;

import net.minecraft.client.gui.screens.Screen;

public interface IGameCreator {
    String getGameName();
    IGame CreateNewGameByConfig(IGameConfig config);
    Screen createUIScreen();

}
