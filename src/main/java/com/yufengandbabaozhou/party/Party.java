package com.yufengandbabaozhou.party;

import com.mojang.logging.LogUtils;
import com.yufengandbabaozhou.party.Server.CreateGroupPacket;
import com.yufengandbabaozhou.party.Server.CreateGroupResponsePacket;
import com.yufengandbabaozhou.party.Server.JoinGroupPacket;
import com.yufengandbabaozhou.party.Server.LeaveGroupPacket;
import com.yufengandbabaozhou.party.games.IGame;
import com.yufengandbabaozhou.party.games.IGameConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

import java.util.HashMap;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Party.MODID)
public class Party {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "party";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    private static final String PROTOCOL_VERSION = "1.0";
    //使用字典来存放游戏配置，一个小游戏对象可以使用多张地图和多项配置游玩，每个小游戏对象配置都有游戏起始点，终止点等信息。
    public static HashMap<IGame, IGameConfig> Games=new HashMap<>();

    public static final SimpleChannel NETWORK = NetworkRegistry.newSimpleChannel(
            ResourceLocation.fromNamespaceAndPath(MODID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );




    public Party() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        int id = 0;
        NETWORK.registerMessage(id++, CreateGroupPacket.class,
                CreateGroupPacket::encode,
                CreateGroupPacket::decode,
                CreateGroupPacket::handle
        );
        NETWORK.registerMessage(id++, JoinGroupPacket.class,
                JoinGroupPacket::encode,
                JoinGroupPacket::decode,
                JoinGroupPacket::handle
        );
        NETWORK.registerMessage(id++, LeaveGroupPacket.class,
                LeaveGroupPacket::encode,
                LeaveGroupPacket::decode,
                LeaveGroupPacket::handle
        );
        NETWORK.registerMessage(id++, CreateGroupResponsePacket.class,
                CreateGroupResponsePacket::encode,
                CreateGroupResponsePacket::decode,
                CreateGroupResponsePacket::handle
        );
        System.out.println("群组网络包已注册");

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);


        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");
        /*LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));

        if (Config.logDirtBlock)
            LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));

        LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);

        Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));*/
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}
