package com.yufengandbabaozhou.partiesloader.GameInterfaces;

public interface IGamePhase {
    int getGamePhaseID();
    boolean getIsGamePhaseActive();
    void setIsGamePhaseActive(boolean active);
    void StartThePhase();
    void EndThePhase();
}
