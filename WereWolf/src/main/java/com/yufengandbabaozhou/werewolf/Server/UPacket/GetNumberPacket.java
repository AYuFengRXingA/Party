package com.yufengandbabaozhou.werewolf.Server.UPacket;


import com.yufengandbabaozhou.partiesloader.PartiesLoader;
import com.yufengandbabaozhou.werewolf.Server.DPacket.NumberPacket;
import com.yufengandbabaozhou.werewolf.Server.WereWolfManager;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class GetNumberPacket {

    public static void encode(GetNumberPacket msg, FriendlyByteBuf buf) {
    }

    public static GetNumberPacket decode(FriendlyByteBuf buf) {
        return new GetNumberPacket();
    }

    public static void handle(GetNumberPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ServerPlayer player = ctx.get().getSender();
        ctx.get().enqueueWork(() -> {
            int wolf =  WereWolfManager.getInstance().getwolf();
            int witch = WereWolfManager.getInstance().getwitch();
            int hunter = WereWolfManager.getInstance().gethunter();
            PartiesLoader.NETWORK.sendTo(
            new NumberPacket(wolf, witch, hunter),
                    player.connection.connection,
                    NetworkDirection.PLAY_TO_CLIENT
            );

        });
        ctx.get().setPacketHandled(true);
    }
}
