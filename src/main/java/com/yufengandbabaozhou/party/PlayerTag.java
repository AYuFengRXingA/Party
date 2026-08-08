package com.yufengandbabaozhou.party;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;

public class PlayerTag {
    private static final String GROUP_ID_KEY = "party_group_id";
    private static final String GROUP_NAME_KEY = "party_group_name";

    public static void setGroup(ServerPlayer player, String name, String groupId){
        CompoundTag persistentData = player.getPersistentData();
        persistentData.putString(GROUP_ID_KEY, groupId);
         persistentData.putString(GROUP_NAME_KEY, name);

    }
    public static void removeGroup(ServerPlayer player){
        CompoundTag persistentData = player.getPersistentData();
        persistentData.remove(GROUP_ID_KEY);
        persistentData.remove(GROUP_NAME_KEY);

    }
    public static String getPlayerGroupId(ServerPlayer player) {
        CompoundTag persistentData = player.getPersistentData();
        return persistentData.getString(GROUP_ID_KEY);
    }


    public static String getPlayerGroupName(ServerPlayer player) {
        CompoundTag persistentData = player.getPersistentData();
        return persistentData.getString(GROUP_NAME_KEY);
    }


    public static boolean inGroup(ServerPlayer player) {
        CompoundTag persistentData = player.getPersistentData();
        return !persistentData.getString(GROUP_ID_KEY).isEmpty();
    }
}
