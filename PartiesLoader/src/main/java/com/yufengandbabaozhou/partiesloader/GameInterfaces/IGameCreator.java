package com.yufengandbabaozhou.partiesloader.GameInterfaces;

public interface IGameCreator {
    String getGameName();
    IGame CreateNewGameByConfig(IGameConfig config);
}
