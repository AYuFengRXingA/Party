package com.yufengandbabaozhou.party.Server;
import com.yufengandbabaozhou.party.PlayerTag;
import com.yufengandbabaozhou.party.UI.ListSet;
import com.yufengandbabaozhou.party.group.Group;
import com.yufengandbabaozhou.party.group.GroupManager;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import java.util.List;

public class GroupServerHelper {
    public  static boolean CreateGroup(ServerPlayer player,String groupId) {
        String playerName = player.getName().getString();

       if(GroupManager.getInstance().isInGroup(playerName)){
           player.sendSystemMessage(Component.literal("§c你已在群组中，不能创建！"));
           return false;
       }
       if(GroupManager.getInstance().isGroupIdUsed(groupId)){
           player.sendSystemMessage(Component.literal("§c该群组ID已被占用！"));
           return false;
       }
       Group group = GroupManager.getInstance().createGroup(playerName,groupId);

       if(group == null){
           player.sendSystemMessage(Component.literal("§c创建群组失败！"));
           return false;
       }
        PlayerTag.setGroup(player,groupId,group.getGroupName());

        player.sendSystemMessage(Component.literal(
                "§a已创建群组：" + group.getGroupName() + " (ID: " + groupId + ")"
        ));
        return true;
    }//创建群组

    public static boolean joinGroup(ServerPlayer player,String groupId) {
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

        player.sendSystemMessage(Component.literal(
                "§a 已加入群组：" + group.getGroupName()
        ));
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

        player.sendSystemMessage(Component.literal(
                "§a已离开群组：" + group.getGroupName()
        ));
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
//查询
}
