package com.yufengandbabaozhou.partiesloader.Server.ULPacket;;
import com.yufengandbabaozhou.partiesloader.Server.GroupServerHelper;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class JoinGroupPacket {

    private final String groupId;

    public JoinGroupPacket(String groupId) {
        this.groupId = groupId;
    }

    public static void encode(JoinGroupPacket msg, FriendlyByteBuf buf) {
        buf.writeUtf(msg.groupId);
    }

    public static JoinGroupPacket decode(FriendlyByteBuf buf) {
        return new JoinGroupPacket(buf.readUtf());
    }

    public static void handle(JoinGroupPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player == null) return;

            GroupServerHelper.joinGroup(player, msg.groupId);
        });
        ctx.get().setPacketHandled(true);
    }
}