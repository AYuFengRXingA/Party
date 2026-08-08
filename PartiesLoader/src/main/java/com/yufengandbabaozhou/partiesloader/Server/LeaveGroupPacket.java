package com.yufengandbabaozhou.partiesloader.Server;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class LeaveGroupPacket {

    public LeaveGroupPacket() {}

    public static void encode(LeaveGroupPacket msg, FriendlyByteBuf buf) {
        // 没有数据需要发送
    }

    public static LeaveGroupPacket decode(FriendlyByteBuf buf) {
        return new LeaveGroupPacket();
    }

    public static void handle(LeaveGroupPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player == null) return;

            GroupServerHelper.leaveGroup(player);
        });
        ctx.get().setPacketHandled(true);
    }
}
