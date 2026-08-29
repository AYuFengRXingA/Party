package com.yufengandbabaozhou.partiesloader.GameInterfaces;

import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public interface IGameConfig {
     double getStartXPos();
     double getStartYPos();
     double getStartZPos();
}
