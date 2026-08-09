package com.yufengandbabaozhou.partiesloader.Server.DLPacket;

import com.yufengandbabaozhou.partiesloader.UI.ListSet;
import com.yufengandbabaozhou.partiesloader.group.Group;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class GroupListResponsePacket {
    private final List<GroupData> groups;

    // ===== 构造方法1：从 Group 列表 =====
    public GroupListResponsePacket(List<Group> groups) {
        this.groups = new ArrayList<>();
        for (Group g : groups) {
            this.groups.add(new GroupData(g.getGroupId(), g.getGroupName()));
        }
    }

    // ===== 构造方法2：直接从 GroupData 列表 =====
    public GroupListResponsePacket(List<GroupData> groups, boolean dummy) {
        this.groups = groups;
    }

    public static void encode(GroupListResponsePacket msg, FriendlyByteBuf buf) {
        buf.writeInt(msg.groups.size());
        for (GroupData data : msg.groups) {
            buf.writeUtf(data.groupId);
            buf.writeUtf(data.groupName);
        }
    }

    public static GroupListResponsePacket decode(FriendlyByteBuf buf) {
        int size = buf.readInt();
        List<GroupData> groups = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            groups.add(new GroupData(buf.readUtf(), buf.readUtf()));
        }
        return new GroupListResponsePacket(groups, true);
    }

    public static void handle(GroupListResponsePacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            if (ListSet.groupListWidget != null) {
                ListSet.groupListWidget.clearAll();
                for (GroupData data : msg.groups) {
                    ListSet.groupListWidget.addEntry(data.groupName + " (ID: " + data.groupId + ")");
                }
                System.out.println("📋 收到群组列表，共 " + msg.groups.size() + " 个群组");
            }
        });
        ctx.get().setPacketHandled(true);
    }

    public static class GroupData {
        public final String groupId;
        public final String groupName;

        public GroupData(String groupId, String groupName) {
            this.groupId = groupId;
            this.groupName = groupName;
        }
    }
}