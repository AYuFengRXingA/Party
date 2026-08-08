package com.yufengandbabaozhou.party.group;

import net.minecraft.client.Minecraft;

import java.util.*;

public class GroupManager {
    private static final GroupManager INSTANCE = new GroupManager();
    private final Map<String, Group> groups = new HashMap<>();
    private final Map<String, String> playerGroupMap = new HashMap<>();

    private GroupManager() {}
    public static GroupManager getInstance() {
        return INSTANCE;
    }
    public Group createGroup(String playerName,String ID) {
        // 检查是否已在群组中
        if (playerGroupMap.containsKey(playerName)) {
            return null;
        }
        // 创建群组
        Group group = new Group(playerName,ID);
        groups.put(group.getGroupId(), group);
        playerGroupMap.put(playerName, group.getGroupId());
        return group;
    }
    public boolean joinGroup(String groupId, String playerName) {//添加

        if (playerGroupMap.containsKey(playerName)) {
            return false;
        }
        Group group = groups.get(groupId);
        if (group == null) {
            return false;
        }
        if (group.addMember(playerName)) {
            playerGroupMap.put(playerName,groupId);
            return true;
        }
        return false;
    }
    public boolean leaveGroup(String playerName) {//离开

        String groupId = playerGroupMap.get(playerName);
        if (groupId == null) {
            return false;
        }
        Group group = groups.get(groupId);
        if (group == null) {
            return false;
        }
        if (group.removeMember(playerName)) {
            playerGroupMap.remove(playerName);
            return true;
        }
        return false;
    }
    public Group getPlayerGroup(String playerName) {
        String groupId = playerGroupMap.get(playerName);
        if (groupId == null) return null;
        return groups.get(groupId);
    }

    public boolean isInGroup(String playerName) {
        return playerGroupMap.containsKey(playerName);
    }

    public int getGroupCount() {
        return groups.size();
    }
    public Group getGroup(UUID groupId) {
        return groups.get(groupId);
    }
}