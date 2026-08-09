package com.yufengandbabaozhou.partiesloader.Server.DLPacket;

import com.yufengandbabaozhou.partiesloader.PartiesLoader;
import com.yufengandbabaozhou.partiesloader.Server.ULPacket.GetGroupListPacket;
import com.yufengandbabaozhou.partiesloader.UI.ListSet;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class RefreshListPacket {
    public RefreshListPacket() {}

    public static void encode(RefreshListPacket msg, FriendlyByteBuf buf) {}

    public static RefreshListPacket decode(FriendlyByteBuf buf) {
        return new RefreshListPacket();
    }

    public static void handle(RefreshListPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            if (ListSet.groupListWidget != null) {
                // 客户端主动请求最新列表
                PartiesLoader.NETWORK.sendToServer(new GetGroupListPacket());
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
