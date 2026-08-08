package com.yufengandbabaozhou.party;

import com.yufengandbabaozhou.party.UI.ListSet;
import com.yufengandbabaozhou.party.UI.SimpleScreen;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(modid = "party", value = Dist.CLIENT)

public class ButtonClientEvents {
    // 注册一个按键绑定
    public static final KeyMapping OPEN_UI_KEY = new KeyMapping(
            "key.open_ui",  // 按键名称（翻译key）
            GLFW.GLFW_KEY_P,  // P键
            "key.categories.misc"  // 按键分类

    );

    @SubscribeEvent
    public static void registerBindings(RegisterKeyMappingsEvent event){
        event.register(OPEN_UI_KEY);//按键注册

    }

    @SubscribeEvent
    public static void onTick(TickEvent.ClientTickEvent event) {
        // 每帧检测按键是否被按下
        if (OPEN_UI_KEY.consumeClick()) {
            Minecraft.getInstance().setScreen(new ListSet(Component.literal("列表")));
            Minecraft.getInstance().setScreen(new SimpleScreen(Component.literal("占位")));
        }
    }
   /*@SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        if (event.getKey() == 86 && event.getAction() == 1) {
            System.out.println("P以触发");
            Minecraft.getInstance().setScreen(new SimpleScreen(Component.literal("UI")));
        }
    }*/
}
