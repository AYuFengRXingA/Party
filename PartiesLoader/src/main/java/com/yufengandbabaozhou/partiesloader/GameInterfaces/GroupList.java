package com.yufengandbabaozhou.partiesloader.GameInterfaces;


import com.yufengandbabaozhou.partiesloader.Group.Group;
import net.minecraft.server.level.ServerPlayer;

public interface GroupList {

    Group getGroup(ServerPlayer player);
}