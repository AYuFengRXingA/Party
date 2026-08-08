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
