package com.yufengandbabaozhou.partiesloader.Server;

import com.yufengandbabaozhou.partiesloader.PartiesLoader;
import com.yufengandbabaozhou.partiesloader.PlayerTag;
import com.yufengandbabaozhou.partiesloader.Server.DLPacket.CreateGroupResponsePacket;
import com.yufengandbabaozhou.partiesloader.Server.DLPacket.RefreshListPacket;
import com.yufengandbabaozhou.partiesloader.Group.Group;
import com.yufengandbabaozhou.partiesloader.Group.GroupManager;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;

import java.util.List;

import static com.yufengandbabaozhou.partiesloader.PartiesLoader.NETWORK;

public class GroupServerHelper {


    public static boolean CreateGroup(ServerPlayer player, String groupId) {
        String playerName = player.getName().getString();

        if (GroupManager.getInstance().isInGroup(playerName)) {
            sendResponse(player, false, "你已在群组中，不能创建！", groupId, "");
            return false;
        }
        if (GroupManager.getInstance().isGroupIdUsed(groupId)) {
            sendResponse(player, false, "该群组ID已被占用！", groupId, "");
            return false;
        }

        Group group = GroupManager.getInstance().createGroup(playerName, groupId);
        if (group == null) {
            sendResponse(player, false, "创建群组失败！", groupId, "");
            return false;
        }

        PlayerTag.setGroup(player, groupId, group.getGroupName());


        sendResponse(player, true, "已创建群组：" + group.getGroupName(), groupId, group.getGroupName());

        return true;
    }


    private static void sendResponse(ServerPlayer player, boolean success, String message, String groupId, String groupName) {
        NETWORK.sendTo(
                new CreateGroupResponsePacket(success, message, groupId, groupName),
                player.connection.connection,
                NetworkDirection.PLAY_TO_CLIENT
        );
        System.out.println(" 发送响应包: success=" + success + ", groupId=" + groupId);
    }


    public static boolean joinGroup(ServerPlayer player, String groupId) {
        String playerName = player.getName().getString();

        if (GroupManager.getInstance().isInGroup(playerName)) {
            player.sendSystemMessage(Component.literal("§c你已在群组中！"));
            return false;
        }

        Group group = GroupManager.getInstance().getGroup(groupId);
        if (group == null) {
            player.sendSystemMessage(Component.literal("§c群组不存在！"));
            return false;
        }

        boolean success = GroupManager.getInstance().joinGroup(groupId, playerName);
        if (!success) {
            player.sendSystemMessage(Component.literal("§c加入群组失败！"));
            return false;
        }

        PlayerTag.setGroup(player, groupId, group.getGroupName());
        player.sendSystemMessage(Component.literal("§a 已加入群组：" + group.getGroupName()));
        return true;
    }


    public static boolean leaveGroup(ServerPlayer player) {
        String playerName = player.getName().getString();

        Group group = GroupManager.getInstance().getPlayerGroup(playerName);
        if (group == null) {
            player.sendSystemMessage(Component.literal("§c你不在任何群组中！"));
            return false;
        }

        boolean success = GroupManager.getInstance().leaveGroup(playerName);
        if (!success) {
            player.sendSystemMessage(Component.literal("§c离开群组失败！"));
            return false;
        }
        PlayerTag.removeGroup(player);



        Group updatedGroup = GroupManager.getInstance().getGroup(group.getGroupId());


        if (updatedGroup == null || updatedGroup.getMemberCount() == 0) {
            GroupManager.getInstance().removeGroup(playerName);
            System.out.println("群组已为空，自动删除: " + playerName);
        }

        // ===== 通知客户端刷新列表 =====
        sendRefreshResponse(player);

        return true;
    }

    // ===== 发送刷新通知 =====
    private static void sendRefreshResponse(ServerPlayer player) {
        PartiesLoader.NETWORK.sendTo(
                new RefreshListPacket(),
                player.connection.connection,
                NetworkDirection.PLAY_TO_CLIENT
        );
    }


    public static Group getPlayerGroup(ServerPlayer player) {
        return GroupManager.getInstance().getPlayerGroup(player.getName().getString());
    }

    public static List<Group> getAllGroups() {
        return GroupManager.getInstance().getAllGroups();
    }

    public static boolean isInGroup(ServerPlayer player) {
        return GroupManager.getInstance().isInGroup(player.getName().getString());
    }

    public static int getGroupCount() {
        return GroupManager.getInstance().getGroupCount();
    }

    public static Group getGroup(String groupId) {
        return GroupManager.getInstance().getGroup(groupId);
    }

    public static boolean isGroupIdUsed(String groupId) {
        return GroupManager.getInstance().isGroupIdUsed(groupId);
    }
}