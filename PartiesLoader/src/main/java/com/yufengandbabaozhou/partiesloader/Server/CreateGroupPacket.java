package com.yufengandbabaozhou.partiesloader.Server;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class CreateGroupPacket {
    private final String groupId;
    public CreateGroupPacket(String groupId) {this.groupId = groupId;}

    public static void encode(CreateGroupPacket msg, FriendlyByteBuf buf) {
        buf.writeUtf(msg.groupId);
    }


    public static CreateGroupPacket decode(FriendlyByteBuf buf) {
        return new CreateGroupPacket(buf.readUtf());
    }


    public static void handle(CreateGroupPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {

            ServerPlayer player = ctx.get().getSender();
            if (player == null) return;
            GroupServerHelper.CreateGroup(player, msg.groupId);
        });
        ctx.get().setPacketHandled(true);
    }



}
