package com.yufengandbabaozhou.werewolf;

import com.mojang.logging.LogUtils;
import com.yufengandbabaozhou.partiesloader.PartiesLoader;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;
import com.yufengandbabaozhou.partiesloader.gameinterfaces.IGame;

@Mod(WereWolf.MODID)
public class WereWolf{

    // Define mod id in a common place for everything to reference
    public static final String MODID = "werewolf";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    public int GameCount;
    public GameConfig[] WereWolfConfigs;
    public WereWolf(){

        //加载配置文件
        GameCount=0;
        WereWolfConfigs=new GameConfig[GameCount];
        for (int i=0;i<GameCount;i++){
            //将读出的配置存入
            WereWolfConfigs[i]=new GameConfig(0,0,0);
        }
        PartiesLoader.registerGame(new GameCreator(),WereWolfConfigs);
    }
}
