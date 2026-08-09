package com.yufengandbabaozhou.partiesloader.Server.ULPacket;

import com.yufengandbabaozhou.partiesloader.PartiesLoader;
import com.yufengandbabaozhou.partiesloader.Server.DLPacket.GroupListResponsePacket;
import com.yufengandbabaozhou.partiesloader.group.Group;
import com.yufengandbabaozhou.partiesloader.group.GroupManager;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;


import java.util.List;
import java.util.function.Supplier;

public class GetGroupListPacket {
    public GetGroupListPacket() {}

    public static void encode(GetGroupListPacket msg, FriendlyByteBuf buf) {
        // 没有数据需要写入
    }

    public static GetGroupListPacket decode(FriendlyByteBuf buf) {
        return new GetGroupListPacket();
    }

    public static void handle(GetGroupListPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player == null) return;

            // ===== 从 GroupManager 获取所有群组 =====
            List<Group> groups = GroupManager.getInstance().getAllGroups();

            // ===== 发送响应包给客户端 =====
            PartiesLoader.NETWORK.sendTo(
                    new GroupListResponsePacket(groups),
                    player.connection.connection,
                    NetworkDirection.PLAY_TO_CLIENT
            );
            System.out.println("📤 发送群组列表，共 " + groups.size() + " 个群组");
        });
        ctx.get().setPacketHandled(true);
    }
}
