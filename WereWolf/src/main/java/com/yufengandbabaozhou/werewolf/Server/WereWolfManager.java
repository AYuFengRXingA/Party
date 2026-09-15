package com.yufengandbabaozhou.werewolf.Server;

import com.yufengandbabaozhou.werewolf.Identity.NumberMan;
import com.yufengandbabaozhou.werewolf.Server.WereWolfGame;

import java.util.HashMap;
import java.util.Map;

public class WereWolfManager {


    private static final WereWolfManager INSTANCE = new WereWolfManager();
    public static WereWolfManager getInstance() { return INSTANCE; }
    private static WereWolfGame wereWolfGame;
    private final Map<String,String> GameOne = new HashMap<>();


    public int getwolf(){
        return wereWolfGame.getWolfCount();
    }
    public int getwitch(){
        return wereWolfGame.getWitchCount();
    }
    public int gethunter(){
        return wereWolfGame.getHungerCount();
    }
    public void addWolf(){
        wereWolfGame.addWolf();
    }












    public void clearALL(){

    }

}
