package com.yufengandbabaozhou.werewolf;

import com.mojang.logging.LogUtils;
import com.yufengandbabaozhou.partiesloader.GameInterfaces.IGameConfig;
import com.yufengandbabaozhou.partiesloader.PartiesLoader;
import com.yufengandbabaozhou.werewolf.Server.DPacket.NumberPacket;
import com.yufengandbabaozhou.werewolf.Server.UPacket.AddWolfPacket;
import com.yufengandbabaozhou.werewolf.Server.UPacket.GetNumberPacket;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

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

        PartiesLoader.registerPacket(
                GetNumberPacket.class,
                GetNumberPacket::encode,
                GetNumberPacket::decode,
                GetNumberPacket::handle
        );
        PartiesLoader.registerPacket(
                AddWolfPacket.class,
                AddWolfPacket::encode,
                AddWolfPacket::decode,
                AddWolfPacket::handle
        );
        PartiesLoader.registerPacket(
                NumberPacket.class,
                NumberPacket::encode,
                NumberPacket::decode,
                NumberPacket::handle
        );


    }
}
