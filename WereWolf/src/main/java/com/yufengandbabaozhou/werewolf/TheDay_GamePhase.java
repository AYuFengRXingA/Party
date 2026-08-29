package com.yufengandbabaozhou.werewolf;

import com.yufengandbabaozhou.partiesloader.GameInterfaces.IGamePhase;

public class TheDay_GamePhase implements IGamePhase {
    private static final int GAME_PHASE_ID=0;
    private boolean _isGamePhaseActive=false;
    public TheDay_GamePhase(GameConfig config){

    }
    @Override
    public int getGamePhaseID() {
        return GAME_PHASE_ID;
    }

    @Override
    public boolean getIsGamePhaseActive() {
        return _isGamePhaseActive;
    }

    @Override
    public void setIsGamePhaseActive(boolean active) {
        _isGamePhaseActive=active;
    }

    @Override
    public void StartThePhase() {
        setIsGamePhaseActive(true);
    }

    @Override
    public void EndThePhase() {
        setIsGamePhaseActive(false);
    }
}
