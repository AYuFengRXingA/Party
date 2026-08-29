package com.yufengandbabaozhou.werewolf;

import com.yufengandbabaozhou.partiesloader.GameInterfaces.IGameConfig;
import jdk.jfr.Timespan;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

import java.time.Duration;
import java.util.List;

@Mod.EventBusSubscriber(modid = WereWolf.MODID,bus = Mod.EventBusSubscriber.Bus.MOD)
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

    public int getDayTicks(){
        return DayTicks.get();
    }
    public int getNightTicks(){
        return 180*20;
    }

    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

//    private static final ForgeConfigSpec.BooleanValue LOG_DIRT_BLOCK = BUILDER
//            .comment("Whether to log the dirt block on common setup")
//            .define("logDirtBlock", true);

    private static final ForgeConfigSpec.IntValue DayTicks = BUILDER
            .comment("白天的游戏刻数")
            .defineInRange("DayTicks", 20*180, 0, Integer.MAX_VALUE);

    private static final ForgeConfigSpec.IntValue NightTicks = BUILDER
            .comment("黑夜的游戏刻数")
            .defineInRange("NightTicks", 20*180, 0, Integer.MAX_VALUE);


    // a list of strings that are treated as resource locations for items
    private static final ForgeConfigSpec.ConfigValue<List<? extends String>> ITEM_STRINGS = BUILDER
            .comment("A list of items to log on common setup.")
            .defineListAllowEmpty("items", List.of("minecraft:iron_ingot"), Config::validateItemName);

    static final ForgeConfigSpec SPEC = BUILDER.build();
}
