package com.yufengandbabaozhou.werewolf;

import com.yufengandbabaozhou.partiesloader.GameInterfaces.IGameConfig;
import jdk.jfr.Timespan;

import java.time.Duration;

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

    public Duration getDayLastTime(){
        return Duration.ofSeconds(180);
    }
    public Duration getNightLastTime(){
        return Duration.ofSeconds(180);
    }
}
