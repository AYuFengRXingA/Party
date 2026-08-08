package com.yufengandbabaozhou.partiesloader.group;

import com.yufengandbabaozhou.partiesloader.UI.ListSet;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private final String groupId;
    private final String groupName;
    private final List<String> members;


    public Group(String creatorName, String ID) {
        this.groupId =ID;
        this.groupName = creatorName + "的群组";
        this.members = new ArrayList<>();
        this.members.add(creatorName);
        ListSet.groupListWidget.addEntry(groupId);
    }

    //添加
    public boolean addMember(String playerName) {
        if (members.contains(playerName)) return false;
        members.add(playerName);
        return true;
    }

    //移除
    public boolean removeMember(String playerName) {
        return members.remove(playerName);
    }

    // 判断玩家是否在群组中
    public boolean containsMember(String playerName) {
        return members.contains(playerName);
    }


    public String getGroupId() { return groupId; }
    public String getGroupName() { return groupName; }
    public List<String> getMembers() { return members; }
    public int getMemberCount() { return members.size(); }
    public boolean isEmpty() { return members.isEmpty(); }
}