package com.yufengandbabaozhou.partiesloader;

import com.mojang.logging.LogUtils;
import com.yufengandbabaozhou.partiesloader.Server.DLPacket.GroupListResponsePacket;
import com.yufengandbabaozhou.partiesloader.Server.DLPacket.RefreshListPacket;
import com.yufengandbabaozhou.partiesloader.Server.ULPacket.CreateGroupPacket;
import com.yufengandbabaozhou.partiesloader.Server.DLPacket.CreateGroupResponsePacket;
import com.yufengandbabaozhou.partiesloader.Server.ULPacket.GetGroupListPacket;
import com.yufengandbabaozhou.partiesloader.Server.ULPacket.JoinGroupPacket;
import com.yufengandbabaozhou.partiesloader.Server.ULPacket.LeaveGroupPacket;
import com.yufengandbabaozhou.partiesloader.GameInterfaces.IGameConfig;
import com.yufengandbabaozhou.partiesloader.GameInterfaces.IGameCreator;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(PartiesLoader.MODID)
public class PartiesLoader {
    public static PartiesLoader INSTANCE;
    // Define mod id in a common place for everything to reference
    public static final String MODID = "partiesloader";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    private static final String PROTOCOL_VERSION = "1.0";
    //使用字典来存放游戏配置，一个小游戏对象可以使用多张地图和多项配置游玩，每个小游戏对象配置都有游戏起始点，终止点等信息。
    public static HashMap<IGameCreator, IGameConfig[]> Games=new HashMap<>();



    public static final SimpleChannel NETWORK = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(MODID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );
    public static List<IGameCreator> getAllGames() {
        return new ArrayList<>(Games.keySet());}
    // PartiesLoader.java

    //注册方法
    private static int nextPacketId = 0;

    public static <T> void registerPacket(
            Class<T> packetClass,
            BiConsumer<T, FriendlyByteBuf> encoder,
            Function<FriendlyByteBuf, T> decoder,
            BiConsumer<T, Supplier<NetworkEvent.Context>> handler
    ) {
        NETWORK.registerMessage(nextPacketId++, packetClass, encoder, decoder, handler);
    }


    public PartiesLoader() {
        INSTANCE = this;


        registerPacket(
                CreateGroupPacket.class,
                CreateGroupPacket::encode,
                CreateGroupPacket::decode,
                CreateGroupPacket::handle);

        registerPacket(
                JoinGroupPacket.class,
                JoinGroupPacket::encode,
                JoinGroupPacket::decode,
                JoinGroupPacket::handle);

        registerPacket(
                LeaveGroupPacket.class,
                LeaveGroupPacket::encode,
                LeaveGroupPacket::decode,
                LeaveGroupPacket::handle);

        registerPacket(
                CreateGroupResponsePacket.class,
                CreateGroupResponsePacket::encode,
                CreateGroupResponsePacket::decode,
                CreateGroupResponsePacket::handle);

        registerPacket(
                RefreshListPacket.class,
                RefreshListPacket::encode,
                RefreshListPacket::decode,
                RefreshListPacket::handle);

        registerPacket(
                GetGroupListPacket.class,
                GetGroupListPacket::encode,
                GetGroupListPacket::decode,
                GetGroupListPacket::handle);

        registerPacket(
                GroupListResponsePacket.class,
                GroupListResponsePacket::encode,
                GroupListResponsePacket::decode,
                GroupListResponsePacket::handle);

        System.out.println("群组网络包已注册");

        // Register the commonSetup method for modloading
        //modEventBus.addListener(this::commonSetup);


        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        //modEventBus.addListener(this::addCreative);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        //ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);

    }

    public static void registerGame(IGameCreator creator, IGameConfig[] configs){
        Games.put(creator,configs);
        LOGGER.info("已注册小游戏: [{}]", creator.getGameName());
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
