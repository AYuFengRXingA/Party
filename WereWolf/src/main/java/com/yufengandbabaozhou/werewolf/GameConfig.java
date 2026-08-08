package com.yufengandbabaozhou.werewolf;

import com.yufengandbabaozhou.partiesloader.gameinterfaces.IGameConfig;

public class GameConfig implements IGameConfig {
    public double StartXPos;
    public double StartYPos;
    public double StartZPos;
    public GameConfig(double startXPos,double startYPos,double startZPos){
        StartXPos =startXPos;
        StartYPos =startYPos;
        StartZPos =startZPos;
    }
    @Override
    public double getStartXPos() {
        return StartXPos;
    }

    @Override
    public double getStartYPos() {
        return StartYPos;
    }

    @Override
    public double getStartZPos() {
        return StartZPos;
    }
}
