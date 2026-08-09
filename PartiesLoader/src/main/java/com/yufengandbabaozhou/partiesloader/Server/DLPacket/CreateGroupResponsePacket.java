package com.yufengandbabaozhou.partiesloader.Server.DLPacket;

import com.yufengandbabaozhou.partiesloader.UI.ListSet;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class CreateGroupResponsePacket {
    private final boolean success;
    private final String message;
    private final String groupId;
    private final String groupName;


    public CreateGroupResponsePacket(boolean success, String message, String groupId, String groupName) {
        this.success = success;
        this.message = message;
        this.groupId = groupId;
        this.groupName = groupName;
    }


    public static void encode(CreateGroupResponsePacket msg, FriendlyByteBuf buf) {
        buf.writeBoolean(msg.success);
        buf.writeUtf(msg.message);
        buf.writeUtf(msg.groupId);
        buf.writeUtf(msg.groupName);
    }


    public static CreateGroupResponsePacket decode(FriendlyByteBuf buf) {
        return new CreateGroupResponsePacket(
                buf.readBoolean(),
                buf.readUtf(),
                buf.readUtf(),
                buf.readUtf()
        );
    }


    public static void handle(CreateGroupResponsePacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {

            if (msg.success) {
                if(ListSet.groupListWidget != null) {
                    ListSet.groupListWidget.addEntry(msg.groupName + " (ID: " + msg.groupId + ")");
                    System.out.println("已添加群组到列表: " + msg.groupName);
                } else{
                    System.out.println("列表还没创建，群组数据已存在，打开列表时会显示");
                }


                Minecraft.getInstance().player.sendSystemMessage(
                        Component.literal("§a" + msg.message)
                );
            } else {
                Minecraft.getInstance().player.sendSystemMessage(
                        Component.literal("§c" + msg.message)
                );
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
