package com.yufengandbabaozhou.party.Server;

import com.yufengandbabaozhou.party.Party;
import com.yufengandbabaozhou.party.PlayerTag;
import com.yufengandbabaozhou.party.group.Group;
import com.yufengandbabaozhou.party.group.GroupManager;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;

import java.util.List;

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
        Party.NETWORK.sendTo(
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
        player.sendSystemMessage(Component.literal("§a已离开群组：" + group.getGroupName()));
        return true;
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