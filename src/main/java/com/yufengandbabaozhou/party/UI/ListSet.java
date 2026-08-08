package com.yufengandbabaozhou.party.UI;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class ListSet extends Screen {
    public static GroupListWidget groupListWidget;

    int buttonWidth = 80;
    int buttonHeight = 20;
    @Override
    public void init(){
        super.init();

        groupListWidget = new GroupListWidget(0,100,this.width,this.height);
        groupListWidget.addEntry("第一条测试");
        groupListWidget.addEntry("第二条测试");
        groupListWidget.addEntry("第三条测试");
        groupListWidget.addEntry("第四条测试");
        groupListWidget.addEntry("第五条测试");
        groupListWidget.addEntry("第六条测试");
        this.addRenderableWidget(groupListWidget);

    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {

        guiGraphics.fillGradient(0, 0, this.width, this.height, 0xC0101010, 0xD0101010);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }



    public ListSet(Component title) {
        super(title);
    }
    public void add(String playerName){
        groupListWidget.addEntry(playerName);
    }
    public GroupListWidget getGroupList(){
        return groupListWidget;
    }
}
