package com.yufengandbabaozhou.party;

import com.yufengandbabaozhou.party.games.IGame;
import com.yufengandbabaozhou.party.games.IGameConfig;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.*;
import java.util.stream.Collectors;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Forge's config APIs
@Mod.EventBusSubscriber(modid = Party.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    static final ForgeConfigSpec SPEC = BUILDER.build();
    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
    }
}
