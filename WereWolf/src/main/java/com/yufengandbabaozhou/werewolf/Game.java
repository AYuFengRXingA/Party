package com.yufengandbabaozhou.werewolf;

import com.yufengandbabaozhou.partiesloader.GameInterfaces.IGame;
import com.yufengandbabaozhou.partiesloader.GameInterfaces.IGamePhase;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import java.util.Arrays;
@Mod.EventBusSubscriber(modid = WereWolf.MODID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class Game implements IGame {
    public GameConfig Config;
    @Nullable
    public IGamePhase CurrentGamePhase=null;
    public Game(GameConfig config){
        Config=config;
    }
    @Override
    public String Name() {
        return "狼人杀";
    }

    @Override
    public String Description() {
        return "";
    }

    @Override
    public String Rule() {
        return "";
    }

    @Override
    public IGamePhase[] getGamePhases() {
        return new IGamePhase[]{new TheDay_GamePhase(Config)};
    }

    @Override
    public IGamePhase getFirstGamePhase() {
        return getGamePhases()[0];
    }

    @Override
    public @org.jetbrains.annotations.Nullable IGamePhase getCurrentGamePhase() {
        return CurrentGamePhase;
    }

    @Override
    public void EnterNextGamePhase(int gamePhaseID) {

        if (getCurrentGamePhase() == null) return;
        getCurrentGamePhase().EndThePhase();

        IGamePhase findResult= Arrays.stream(getGamePhases()).filter(x->x.getGamePhaseID()==gamePhaseID).findFirst().orElse(null);
        if (findResult == null) return;
        findResult.StartThePhase();
    }

    @Override
    public void EndGame() {
        if (getCurrentGamePhase() != null) {
            getCurrentGamePhase().EndThePhase();
        }
    }
}
