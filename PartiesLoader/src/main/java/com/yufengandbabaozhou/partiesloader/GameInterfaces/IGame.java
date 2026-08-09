package com.yufengandbabaozhou.partiesloader.GameInterfaces;

public interface IGame {
     String Name();
     String Description();//应支持富文本，但我不会搞
     String Rule();
     IGamePhase[] getGamePhases();
}
