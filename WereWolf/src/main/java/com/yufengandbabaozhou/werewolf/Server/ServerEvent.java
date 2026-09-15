package com.yufengandbabaozhou.werewolf.Server;

import net.minecraft.server.MinecraftServer;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.event.server.ServerStoppingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "werewolf", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ServerEvent {
    @SubscribeEvent
    public static void onServerStarting(ServerStartingEvent event) {
        MinecraftServer server = event.getServer();

    }

    // 服务器停止
    @SubscribeEvent
    public static void onServerStopping(ServerStoppingEvent event) {

    }



}
