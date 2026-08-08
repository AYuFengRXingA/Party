package com.yufengandbabaozhou.party.UI;

import com.yufengandbabaozhou.party.group.GroupManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;


public class SimpleScreen extends Screen{
    private String playerName =Minecraft.getInstance().player.getName().getString();;
    GroupManager manager =GroupManager.getInstance();
    private GroupListWidget groupListWidget;

    int centerX= this.width / 2;
    int centerY= this.height / 2;

    public SimpleScreen(Component title) {
        super(title);

    }


    @Override
    protected void init() {
        super.init();
        int buttonWidth = 80;
        int buttonHeight = 20;


        Button button1 = Button.builder(Component.literal("创建群组"), (btn) -> {
            Minecraft.getInstance().setScreen(new GroupSet(Component.literal("占位")));

        }).bounds(this.width/4-40, this.height/4-40, buttonWidth, buttonHeight).build();
        this.addRenderableWidget(button1);

        Button button2 = Button.builder(Component.literal("加入群组"), (btn) -> {
            Minecraft.getInstance().setScreen(new ListSet(Component.literal("占位")));

        }).bounds(this.width/4*2-40, this.height/4-40, buttonWidth, buttonHeight).build();
        this.addRenderableWidget(button2);

        Button button3 = Button.builder(Component.literal("离开群组"), (btn) -> {
            manager.leaveGroup(playerName);
            Minecraft.getInstance().player.sendSystemMessage(Component.literal("你已经离开群组"));


        }).bounds(this.width/4*3-40, this.height/4-40, buttonWidth, buttonHeight).build();
        this.addRenderableWidget(button3);

    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {

        guiGraphics.fillGradient(0, 0, this.width, this.height, 0xC0101010, 0xD0101010);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }
}
