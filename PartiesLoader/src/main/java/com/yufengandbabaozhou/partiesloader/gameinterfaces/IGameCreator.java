package com.yufengandbabaozhou.partiesloader.gameinterfaces;

public interface IGameCreator {
    String getGameName();
    IGame CreateNewGameByConfig(IGameConfig config);
}
