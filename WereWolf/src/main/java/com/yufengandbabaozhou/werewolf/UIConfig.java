package com.yufengandbabaozhou.werewolf;

import com.yufengandbabaozhou.partiesloader.Group.Group;
import com.yufengandbabaozhou.partiesloader.Group.GroupManager;
import com.yufengandbabaozhou.partiesloader.PartiesLoader;
import com.yufengandbabaozhou.partiesloader.UI.GroupSet;
import com.yufengandbabaozhou.partiesloader.UI.ListSet;
import com.yufengandbabaozhou.werewolf.Server.DPacket.NumberPacket;
import com.yufengandbabaozhou.werewolf.Server.UPacket.AddWolfPacket;
import com.yufengandbabaozhou.werewolf.Server.WereWolfManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraftforge.network.NetworkDirection;

public class UIConfig extends Screen {
   static  int wolf;
   static int witch;
   static int hunger;

    private int maxplayer = 0;
    public UIConfig() {
        super(Component.literal("狼人杀设置"));
    }

    public static int setNumber(int wolf1, int witch1, int hunger1) {
        wolf = wolf1;
        witch = witch1;
        hunger = hunger1;
        return 0;

    }

    public UIConfig(Component title) {
        super(title);
    }

    @Override
    protected void init() {
        super.init();

        if (Minecraft.getInstance().player != null) {
            String name = Minecraft.getInstance().player.getGameProfile().getName();
            Group group = GroupManager.getInstance().getPlayerGroup(name);
            if (group != null) {
                maxplayer = group.getMemberCount();
            }
        }




        int buttonWidth = 20;
        int buttonHeight = 10;
        int centerX = this.width / 2;

        //================================================================狼人
        Button wolfadd = Button.builder(Component.literal("->"), (btn) -> {
        //PartiesLoader.NETWORK.sendToServer(new AddWolfPacket());
            wolf++;

        }).bounds(60, 120, buttonWidth, buttonHeight).build();
        this.addRenderableWidget(wolfadd);

        Button wolfloss = Button.builder(Component.literal("<-"), (btn) -> {

        }).bounds(40, 120, buttonWidth, buttonHeight).build();
        this.addRenderableWidget(wolfloss);
        //==================================================================女巫
        Button witchadd = Button.builder(Component.literal("->"), (btn) -> {

        }).bounds(110, 120, buttonWidth, buttonHeight).build();
        this.addRenderableWidget(witchadd);

        Button witchloss = Button.builder(Component.literal("<-"), (btn) -> {

        }).bounds(90, 120, buttonWidth, buttonHeight).build();
        this.addRenderableWidget(witchloss);

    }

    @Override
    public void render(GuiGraphics g, int mouseX, int mouseY, float partialTick) {

        g.fill(0, 0, this.width, this.height, 0xFF1A1A2E);
        g.drawString(this.font, "狼人杀设置", 20, 20, 0xFFFFFF);
        g.drawString(this.font, "游戏参与人数: " + maxplayer, 20, 50, 0xFFFFFF);

        g.drawString(this.font, "狼人: " +wolf, 40, 100, 0xFFFFFF);
        g.drawString(this.font, "女巫: " +witch, 90, 110, 0xFFFFFF);
        g.drawString(this.font, "猎人: " +hunger, 120, 100, 0xFFFFFF);


        super.render(g, mouseX, mouseY, partialTick);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}